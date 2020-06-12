package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static miu.edu.cs.cs425.carRentalWebApp.model.TransmissionType.MANUAL;

@SpringBootTest
@ActiveProfiles( value = {"in-memory"})
public class CarReservationTest{
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CarRepository carRepository;
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
        carReservation.setStartDate(LocalDateTime.now());
        carReservation.setEndDate(LocalDateTime.of(2015, 03, 02, 11, 30));
        carReservation.setCar(savedCar);
        return carReservation;
    }
    @Test
    public void shouldNotAddWhenStartDateIsEmpty(){
        try {
            CarReservation cr = initReservation();
            cr.setStartDate(null);
            CarReservation carReservation = reservationRepository.save(cr);
        } catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Start date can't be null"));
        }
    }

    @Test
    public void shouldNotAddWhenEndDateIsEmpty(){
        try {
            CarReservation cr = initReservation();
            cr.setEndDate(null);
            CarReservation carReservation = reservationRepository.save(cr);
        } catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("End date can't be null"));
        }
    }
    @Test
    public void shouldNotAddWhenCarIsEmpty(){
        try {
            CarReservation cr = initReservation();
            cr.setCar(null);
            CarReservation carReservation = reservationRepository.save(cr);
        } catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Car cant't be null"));
        }
    }
}
