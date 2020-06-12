package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.repository.CarRepository;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImp implements CarService {
    private CarRepository carRepository;

    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public void updateCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void addNewCar(Car car) {
        carRepository.save(car);
    }


}
