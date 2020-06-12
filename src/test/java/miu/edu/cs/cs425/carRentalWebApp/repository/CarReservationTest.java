package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles( value = {"in-memory"})
public class CarReservationTest{
    @Autowired
    private ReservationRepository reservationRepository;

    private CarReservation initReservation(){
        CarReservation carReservation = new CarReservation();
        carReservation.setStartDate(LocalDateTime.now());
        carReservation.setEndDate(LocalDateTime.of(2015, 03, 02, 11, 30));
        carReservation.setCar();
    }
}
