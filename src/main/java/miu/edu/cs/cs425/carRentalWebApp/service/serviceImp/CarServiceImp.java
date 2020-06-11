package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImp implements CarService {
    @Autowired
    CarService carService;

    public CarServiceImp(CarService carService) {
        this.carService = carService;
    }

    @Override
    public Car registerNewCar(Car car) {
        return carService.registerNewCar(car);
    }
}
