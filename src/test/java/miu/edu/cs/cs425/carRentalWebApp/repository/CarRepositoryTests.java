package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarRepositoryTests {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void shouldAddCar() {
        Car car = new Car();
        //TODO Set values of car.
        Car savedCar = carRepository.save(car);
        Assertions.assertEquals(savedCar.getId(), 1 );

    }
}
