package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.model.ReservationStatus;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.CarReservationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewCarCheckInDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewCarCheckoutDto;

import java.util.List;


public interface CarService {
    List<Car> getAllCars();
    Car findCarById(Long id);
    void updateCar(Car car);
    void addNewCar(Car car);

    List<Car> getAllAvailableCars(String startDate, String endDate);
    CarReservationDto getReservationDto(Long carId, String startDateStr, String endDateStr, ReservationStatus reservationStatus);
    NewCarCheckoutDto getNewCheckoutDto(CarReservation carReservation);

    NewCarCheckInDto getNewCheckInDto(CarReservation carReservation);
}
