package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.repository.CarRepository;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.CarReservationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewCarCheckoutDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CarServiceImp implements CarService {
    private CarRepository carRepository;

    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car findCarById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if(!carOptional.isPresent()){
            throw new EntityNotFoundException("Car with id "+id+" not found.");
        }
        return  carOptional.get();
    }

    @Override
    public void updateCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void addNewCar(Car car) {

    }

    @Override
    public List<Car> getAllAvailableCars(String startDateStr, String endDateStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, dtf);
        return carRepository.findCarByCarReservationsEmptyOrCarReservationsEndDateLessThan(startDate);
    }

    @Override
    public CarReservationDto getReservationDto(Long carId, String startDateStr, String endDateStr) {
        Car car = findCarById(carId);
        CarReservationDto dto = new CarReservationDto(
                car.getId()
                , car.getPlateNo()
                , car.getModel()
                , car.getMaxNoDays()
                , car.getPricePerDay()
                , car.getTransmissionType()
                , car.getNuOfSeats()
                , car.getNuOfDoors()
                , car.getPhotoUrl());

        BigDecimal costOfDelivery = BigDecimal.valueOf(0);
        dto.setCostOfDelivery(costOfDelivery);

        // Total number of days
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, dtf);
        LocalDate endDate = LocalDate.parse(endDateStr, dtf);
        Period period = Period.between(startDate, endDate);
        int totalNoOfDays = period.getDays();
        dto.setTotalNoOfDays(totalNoOfDays);

        // Total cost of rent
        BigDecimal totalCostOfRent = dto.getPricePerDay().multiply(BigDecimal.valueOf(totalNoOfDays));
        dto.setTotalCostOfRent(totalCostOfRent);


        // Total cost of reservation
        BigDecimal totalCostOfReservation = totalCostOfRent.add(costOfDelivery);
        dto.setTotalCostOfReservation(totalCostOfReservation);

        // duration description i.e. Jun 27 - Jun 29, 2020 (3 days)
        String durationDescription = startDate.getMonth().getDisplayName( TextStyle.SHORT, Locale.US)
                +"  "+startDate.getDayOfMonth()+" - "
                +endDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US)+" "
                +endDate.getDayOfMonth()+", "
                +endDate.getYear()
                +" ("+totalNoOfDays+" day/s)";
        dto.setDurationDescription(durationDescription);
        return dto;
    }

    @Override
    public NewCarCheckoutDto getNewCheckoutDto(CarReservation carReservation) {
        Car car = carReservation.getCar();
        Customer customer = carReservation.getCustomer();
        return new NewCarCheckoutDto(
                carReservation.getId()
                , car.getModel()
                , car.getPlateNo()
                , customer.getFirstName()
                , customer.getLastName()
                , customer.getDrivingLicense(), carReservation.getCode()
                , ReservationUtils.formatLocalDateToUIString(carReservation.getCreateDate().toLocalDate())
                , ReservationUtils.formatLocalDateToUIString(carReservation.getEndDate()));
    }


}
