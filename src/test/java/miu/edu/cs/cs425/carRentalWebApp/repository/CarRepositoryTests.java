package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static miu.edu.cs.cs425.carRentalWebApp.model.TransmissionType.MANUAL;

@SpringBootTest
@ActiveProfiles( value = {"in-memory"})
public class CarRepositoryTests {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void shouldAddCar() {
        Car car = new Car();

        car.setPlateNo("IA2202B");
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
        Assertions.assertEquals(1, savedCar.getId() );

    }


}
