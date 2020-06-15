package miu.edu.cs.cs425.carRentalWebApp.seeds;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.repository.CarRepository;
import miu.edu.cs.cs425.carRentalWebApp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static miu.edu.cs.cs425.carRentalWebApp.model.TransmissionType.MANUAL;

@Component
public class CarReservationSeeder implements CommandLineRunner {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerSeed customerSeed;

    private void seedReservation1() {
        CarReservation carReservation = initReservation();
        carReservation.setStartDate(LocalDate.now().plusDays(1));
        carReservation.setStartDate(LocalDate.now().plusDays(3));
        reservationRepository.save(carReservation);

    }


    private CarReservation initReservation() {

        Car car = new Car();
        car.setPlateNo("IA220AA-0098");
        car.setModel("TOYOTA XL");
        car.setMaxNoDays(20);
        car.setPricePerDay(new BigDecimal(20));
        car.setTransmissionType(MANUAL);
        car.setNuOfSeats(8);
        car.setNuOfDoors(4);
        car.setPhotoUrl("picture");
        car.setCreateDate(LocalDateTime.of(2020, 01, 03, 2, 2, 2, 2));
        car.setLastUpdate(LocalDateTime.of(2020, 01, 02, 2, 2, 2, 2));
        Car savedCar = carRepository.save(car);

        Customer customer = customerSeed.customerSeeder("randomemail@reservationcustom.com");

        CarReservation carReservation = new CarReservation();
        carReservation.setStartDate(LocalDate.now());
        carReservation.setEndDate(LocalDate.now().plusDays(20));
        carReservation.setCar(savedCar);
        carReservation.setCustomer(customer);
        return carReservation;
    }


    @Override
    public void run(String... args) throws Exception {
        seedReservation1();
    }
}
