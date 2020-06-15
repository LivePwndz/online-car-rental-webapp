package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.model.ReservationStatus;
import miu.edu.cs.cs425.carRentalWebApp.repository.CarRepository;
import miu.edu.cs.cs425.carRentalWebApp.repository.CustomerRepository;
import miu.edu.cs.cs425.carRentalWebApp.repository.ReservationRepository;
import miu.edu.cs.cs425.carRentalWebApp.service.ReservationService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewReservationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.PlaceRerservationInfoDto;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImp implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public ReservationServiceImp(ReservationRepository reservationRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CarReservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public PlaceRerservationInfoDto addNewReservation(NewReservationDto newReservationDto) {

        Optional<Car> carOptinal = carRepository.findById(newReservationDto.getCarId());
        if(!carOptinal.isPresent()){
            throw new EntityNotFoundException("Car with id "+newReservationDto.getCarId()+" not found.");
        }

        Car car = carOptinal.get();

        //TODO Use logged customer id
        Long customerId = 1L;
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(!carOptinal.isPresent()){
            throw new EntityNotFoundException("Customer with id "+customerId+" not found.");
        }

        Customer customer = customerOptional.get();
        LocalDate startDate = ReservationUtils.convertFromStringToLocalDate(newReservationDto.getStartDateStr());
        LocalDate endDate = ReservationUtils.convertFromStringToLocalDate(newReservationDto.getEndDateStr());

        CarReservation carReservation = new CarReservation();
        carReservation.setStartDate(startDate);
        carReservation.setEndDate(endDate);
        carReservation.setCar(car);
        carReservation.setCustomer(customer);
        carReservation.setCreateDate(LocalDateTime.now());
        carReservation.setLastUpdate(LocalDateTime.now());

        String code = StringUtils.randomAlphanumeric(4)
                +" - "+car.getId()+newReservationDto.getEndDateStr();

        carReservation.setCode(code);
        carReservation.setStatus(ReservationStatus.PENDING_CHECKOUT);
        CarReservation savedCarReservation = reservationRepository.save(carReservation);
        BigDecimal totalCostOfReservation = ReservationUtils.calculateTotalCostOfReservation(car.getPricePerDay(), newReservationDto.getStartDateStr(), newReservationDto.getEndDateStr());

        return new PlaceRerservationInfoDto(car.getModel(), code, ReservationUtils.formatLocalDateToUIString(endDate), totalCostOfReservation);

    }

    @Override
    public CarReservation findById(Long reservationId) {
        Optional<CarReservation> carReservationOptional = reservationRepository.findById(reservationId);
        if(!carReservationOptional.isPresent()){
           throw new EntityNotFoundException("Reservation with id "+reservationId+" not found.");
        }
        return carReservationOptional.get();
    }
}
