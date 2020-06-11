package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import org.springframework.stereotype.Service;


public interface CarService {
    public Car registerNewCar(Car car);
}
