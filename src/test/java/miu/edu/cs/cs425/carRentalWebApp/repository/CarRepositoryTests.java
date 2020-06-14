package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static miu.edu.cs.cs425.carRentalWebApp.model.TransmissionType.MANUAL;

@SpringBootTest
@ActiveProfiles(value = {"in-memory"})
public class CarRepositoryTests {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ReservationRepository carReservationRepository;


    private Car initCar() {
        Car car = new Car();
        car.setPlateNo("IA2202B");
        car.setModel("TOYOTA");
        car.setMaxNoDays(20);
        car.setPricePerDay(new BigDecimal(20));
        car.setTransmissionType(MANUAL);
        car.setNuOfSeats(8);
        car.setNuOfDoors(4);
        car.setPhotoUrl("picture");
        car.setCreateDate(LocalDateTime.of(2020, 01, 03, 2, 2, 2, 2));
        car.setLastUpdate(LocalDateTime.of(2020, 01, 02, 2, 2, 2, 2));
        return car;
    }

    @Test
    public void shouldAddCar() {
        Car car1 = initCar();
        Car savedCar = carRepository.save(car1);
        Assertions.assertEquals(1, savedCar.getId());
    }

    @Test
    public void shouldNotaddCarWithNullPlateNo() {
        try {
            Car car = initCar();
            car.setPlateNo(null);
            //TODO Set values of car.
            Car savedCar = carRepository.save(car);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("plateNo is required"));
        }
    }

    @Test
    public void shouldNotaddCarWithNullModel() {
        try {
            Car car = initCar();
            car.setModel(null);
            Car savedCar = carRepository.save(car);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("model is required"));
        }
    }

    @Test
    public void shouldNotaddCarWithNullMaxNoDays() {
        try {
            Car car = initCar();
            car.setMaxNoDays(1);
            Car savedCar = carRepository.save(car);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("\"maxNoDays range should be [1-30]\""));
        }

    }

    @Test
    public void shouldNotaddCarWithNullPricePerDay() {
        try {
            Car car = initCar();
            car.setPricePerDay(new BigDecimal(10));
            Car savedCar = carRepository.save(car);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("pricePerDay date should not be less than $10"));
        }

    }

    @Test
    public void shouldNotaddCarWithNullTransmissionType() {
        try {
            Car car = initCar();
            car.setTransmissionType(MANUAL);
            Car savedCar = carRepository.save(car);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("Select MANUAL or AUTOMATIC"));
        }

    }

    @Test
    public void shouldNotaddCarWithNullNuOfSeats() {
        try {
            Car car = initCar();
            car.setNuOfSeats(4);
            Car savedCar = carRepository.save(car);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("nuOfSeats is required"));
        }

    }

    @Test
    public void shouldNotaddCarWithNullNuOfDoors() {
        try {
            Car car = initCar();
            car.setNuOfDoors(10);
            Car savedCar = carRepository.save(car);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("nuOfDoors is required"));
        }

    }

    @Test
    public void shouldNotaddCarWithNullPhotoUrl() {
        try {
            Car car = initCar();
            car.setPhotoUrl("Picture");
            Car savedCar = carRepository.save(car);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("PhotoUrl is required"));
        }

    }

    @Test
    public void shouldNotaddCarWithNullCreateDate() {
        try {
            Car car = initCar();
            car.setCreateDate(null);
            Car savedCar = carRepository.save(car);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("Create date can't be null"));
        }

    }

    @Test
    public void shouldNotaddCarWithNullLastUpdate() {
        try {
            Car car = initCar();
            car.setLastUpdate(null);
            Car savedCar = carRepository.save(car);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains("LastUpdate date can't be null"));
        }

    }

    @Test
    public void shouldGetAvailableCars() {
        List<Car> cars = carRepository.findCarByCarReservationsEmptyOrCarReservationsEndDateLessThan(LocalDate.of(2020,6, 16));
        Assertions.assertEquals(4, cars.size());
    }

    @Test
    public void shouldGetAvailableCarsSkipOverlappingPreviousReservationEndDate() {
        createCarReservationForCarWithEndDateAfterStartDateOfNewDate();
        List<Car> cars = carRepository.findCarByCarReservationsEmptyOrCarReservationsEndDateLessThan(LocalDate.now().plusDays(2));
        Assertions.assertEquals(4, cars.size());
    }

    @Test
    public void shouldGetAvailableCarsAfterPreviousReservationReturnsCar() {
        createAfterPreviousCarReservationReturnsCar();
        List<Car> cars = carRepository.findCarByCarReservationsEmptyOrCarReservationsEndDateLessThan(LocalDate.now().plusDays(1));
        Assertions.assertEquals(5, cars.size());
    }


    private void createCarReservationForCarWithEndDateAfterStartDateOfNewDate() {
        CarReservation carReservation = initReservation();
        carReservation.setStartDate(LocalDate.now().plusDays(1));
        carReservation.setEndDate(LocalDate.now().plusDays(4));
        carReservationRepository.save(carReservation);
    }

    private void createAfterPreviousCarReservationReturnsCar() {
        CarReservation carReservation = initReservation();
        carReservation.setStartDate(LocalDate.now().plusDays(-2));
        carReservation.setEndDate(LocalDate.now().plusDays(-1));
        carReservationRepository.save(carReservation);
    }

    private CarReservation initReservation(){

        Car car = new Car();
        car.setPlateNo("IA220AA");
        car.setModel("TOYOTA");
        car.setMaxNoDays(20);
        car.setPricePerDay( new BigDecimal(20));
        car.setTransmissionType(MANUAL);
        car.setNuOfSeats(8);
        car.setNuOfDoors(4);
        car.setPhotoUrl("picture");
        car.setCreateDate(LocalDateTime.of(2020,01,03,2,2,2,2));
        car.setLastUpdate(LocalDateTime.of(2020,01,02,2,2,2,2));
        Car savedCar = carRepository.save(car);

        CarReservation carReservation = new CarReservation();
        carReservation.setStartDate(LocalDate.now());
        carReservation.setEndDate(LocalDate.now().plusDays(20));
        carReservation.setCar(savedCar);
        return carReservation;
    }
}
