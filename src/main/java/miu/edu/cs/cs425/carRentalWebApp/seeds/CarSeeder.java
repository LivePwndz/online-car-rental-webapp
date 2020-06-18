package miu.edu.cs.cs425.carRentalWebApp.seeds;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static miu.edu.cs.cs425.carRentalWebApp.model.TransmissionType.MANUAL;

@Component
public class CarSeeder implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;

    private void seedCar1() {
        Car car = new Car();

        car.setPlateNo("333 JBS IA2202B");
        car.setModel("TOYOTA");
        car.setMaxNoDays(20);
        car.setPricePerDay( new BigDecimal(20));
        car.setTransmissionType(MANUAL);
        car.setNuOfSeats(8);
        car.setNuOfDoors(4);
        car.setPhotoUrl("picture");
        car.setCreateDate(LocalDateTime.of(2020,01,03,2,2,2,2));
        car.setLastUpdate(LocalDateTime.of(2020,01,02,2,2,2,2));
        carRepository.save(car);

    }

    private void seedCar2() {
        Car car = new Car();

        car.setPlateNo("KFS JBS IL2202B");
        car.setModel("2020 MAZDA XL");
        car.setMaxNoDays(30);
        car.setPricePerDay( new BigDecimal(20));
        car.setTransmissionType(MANUAL);
        car.setNuOfSeats(8);
        car.setNuOfDoors(4);
        car.setPhotoUrl("picture");
        car.setCreateDate(LocalDateTime.of(2020,01,03,2,2,2,2));
        car.setLastUpdate(LocalDateTime.of(2020,01,02,2,2,2,2));
        carRepository.save(car);

    }

    private void seedCar3() {
        Car car = new Car();

        car.setPlateNo("KFS JBS IL200202B");
        car.setModel("2020 MAZDA XL");
        car.setMaxNoDays(30);
        car.setPricePerDay( new BigDecimal(20));
        car.setTransmissionType(MANUAL);
        car.setNuOfSeats(8);
        car.setNuOfDoors(4);
        car.setPhotoUrl("picture");
        car.setCreateDate(LocalDateTime.of(2020,01,03,2,2,2,2));
        car.setLastUpdate(LocalDateTime.of(2020,01,02,2,2,2,2));
        carRepository.save(car);

    }

    private void seedCar4() {
        Car car = new Car();

        car.setPlateNo("KFS JBS IL29902B");
        car.setModel("2020 MAZDA XL");
        car.setMaxNoDays(30);
        car.setPricePerDay( new BigDecimal(20));
        car.setTransmissionType(MANUAL);
        car.setNuOfSeats(8);
        car.setNuOfDoors(4);
        car.setPhotoUrl("picture");
        car.setCreateDate(LocalDateTime.of(2020,01,03,2,2,2,2));
        car.setLastUpdate(LocalDateTime.of(2020,01,02,2,2,2,2));
        carRepository.save(car);

    }


    @Override
    public void run(String... args) throws Exception {
        seedCar1();
        // seedCar2();
        // seedCar3();
        // seedCar4();
    }
}
