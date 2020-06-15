package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.CarReservationDto;

import java.util.List;


public interface CarService {
    List<Car> getAllCars();
    Car findCarById(Long id);
    void updateCar(Car car);
    void addNewCar(Car car);

    List<Car> getAllAvailableCars(String startDate, String endDate);
    CarReservationDto getReservationDto(Long carId, String startDateStr, String endDateStr);
}
