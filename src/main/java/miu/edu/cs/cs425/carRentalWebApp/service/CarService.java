package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CarService {
    Iterable<Car> getAllCars();
    Optional<Car> findCarById(Long id);
    void updateCar(Car car);
    void addNewCar(Car car);
}
