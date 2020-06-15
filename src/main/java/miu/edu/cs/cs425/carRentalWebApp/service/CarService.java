package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CarService {
    List<Car> getAllCars();
    Car findCarById(Long id);
    void updateCar(Car car);
    void addNewCar(Car car);

    List<Car> getAllAvailableCars(String startDate, String endDate);
}
