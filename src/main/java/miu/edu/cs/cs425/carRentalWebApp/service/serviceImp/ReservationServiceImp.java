package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.*;
import miu.edu.cs.cs425.carRentalWebApp.repository.*;
import miu.edu.cs.cs425.carRentalWebApp.service.ReservationService;
import miu.edu.cs.cs425.carRentalWebApp.service.UserService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.*;
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
    private final CheckoutRecordRepository checkoutRecordRepository;
    private final CheckInRecordRepository checkInRecordRepository;
    private final UserService userService;

    public ReservationServiceImp(ReservationRepository reservationRepository, CarRepository carRepository, CustomerRepository customerRepository, CheckoutRecordRepository checkoutRecordRepository, CheckInRecordRepository checkInRecordRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.checkoutRecordRepository = checkoutRecordRepository;
        this.checkInRecordRepository = checkInRecordRepository;
        this.userService = userService;
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
        return getCarReservation(reservationId);
    }

    @Override
    public CheckoutNotificationDto addNewCheckoutRecord(Long reservationId) {
        CarReservation carReservation = getCarReservation(reservationId);
        if(!ReservationStatus.PENDING_CHECKOUT.equals(carReservation.getStatus())){
            throw new IllegalStateException("Reservation status "+carReservation.getStatus()+" not valid for checkout.");
        }


        User clerk = userService.getUserById(ReservationUtils.getAuthenticatedUserId());

        CheckoutRecord checkoutRecord = new CheckoutRecord();
        checkoutRecord.setClerk(clerk);
        checkoutRecord.setReservation(carReservation);
        checkoutRecord.setCreateDate(LocalDateTime.now());
        checkoutRecord.setLastUpdate(LocalDateTime.now());

        CheckoutRecord savedCheckoutRecord = checkoutRecordRepository.save(checkoutRecord);
        Car car = carReservation.getCar();
        Customer customer = carReservation.getCustomer();

        carReservation.setStatus(ReservationStatus.CHECKED_OUT);
        carReservation.setLastUpdate(LocalDateTime.now());
        reservationRepository.save(carReservation);

        return new CheckoutNotificationDto(car.getModel()
                , car.getPlateNo()
                , customer.getUser().getFirstName()
                , customer.getUser().getLastName()
                , clerk.getFirstName()
                , ReservationUtils.formatLocalDateToUIString(carReservation.getCreateDate().toLocalDate())
                , ReservationUtils.formatLocalDateToUIString(carReservation.getEndDate()) );
    }

    @Override
    public CheckInNotificationDto addNewCheckInRecord(Long reservationId) {
        CarReservation carReservation = getCarReservation(reservationId);
        if(!ReservationStatus.CHECKED_OUT.equals(carReservation.getStatus())){
            throw new IllegalStateException("Reservation status "+carReservation.getStatus()+" not valid for check-in.");
        }

        User clerk = userService.getUserById(ReservationUtils.getAuthenticatedUserId());

        CheckInRecord checkInRecord = new CheckInRecord();
        checkInRecord.setClerk(clerk);
        checkInRecord.setReservation(carReservation);
        checkInRecord.setCreateDate(LocalDateTime.now());
        checkInRecord.setLastUpdate(LocalDateTime.now());

        CheckInRecord savedCheckInRecord = checkInRecordRepository.save(checkInRecord);
        Car car = carReservation.getCar();
        Customer customer = carReservation.getCustomer();

        carReservation.setStatus(ReservationStatus.CHECKED_IN);
        carReservation.setLastUpdate(LocalDateTime.now());
        reservationRepository.save(carReservation);

        return new CheckInNotificationDto(car.getModel()
                , car.getPlateNo()
                , customer.getUser().getFirstName()
                , customer.getUser().getLastName()
                , clerk.getFirstName() );
    }


    private CarReservation getCarReservation(Long reservationId) {
        Optional<CarReservation> carReservationOptional = reservationRepository.findById(reservationId);
        if(!carReservationOptional.isPresent()){
            throw new EntityNotFoundException("Reservation with id "+reservationId+" not found.");
        }

        return carReservationOptional.get();

    }
}
