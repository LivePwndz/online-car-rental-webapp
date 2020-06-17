package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.*;
import miu.edu.cs.cs425.carRentalWebApp.seeds.UserSeed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static miu.edu.cs.cs425.carRentalWebApp.model.TransmissionType.MANUAL;

@SpringBootTest
@ActiveProfiles(value = {"in-memory"})
public class CheckoutRecordTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserSeed userSeed;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    CheckoutRecordRepository checkoutRecordRepository;

    Customer customerSeeder(String email) {
        Address address = new Address();
        address.setHouseNumber("1000A");
        address.setStreet("1000 N 4th");
        address.setCity("Houston");
        address.setZipCode(5000);
        address.setState("IOWA");

        Customer customer = new Customer();
        customer.setUser(new User());
        customer.setDrivingLicense("I12ABBB");
        customer.getUser().setFirstName("Alex");
        customer.getUser().setMiddleName("James");
        customer.getUser().setLastName("Smith");
        customer.setPhoneNo("+1000000000");
        customer.getUser().setEmail(email);
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        customer.setAddress(address);
        return customerRepository.save(customer);
    }

    private CheckoutRecord initCheckoutRecord() {
        CheckoutRecord checkoutRecord = new CheckoutRecord();


        Car car = new Car();
        car.setPlateNo("IA220AA-2020");
        car.setModel("TOYOTA XLL");
        car.setMaxNoDays(20);
        car.setPricePerDay(new BigDecimal(20));
        car.setTransmissionType(MANUAL);
        car.setNuOfSeats(8);
        car.setNuOfDoors(4);
        car.setPhotoUrl("picture");
        car.setCreateDate(LocalDateTime.of(2020, 01, 03, 2, 2, 2, 2));
        car.setLastUpdate(LocalDateTime.of(2020, 01, 02, 2, 2, 2, 2));
        Car savedCar = carRepository.save(car);

        Customer customer = customerSeeder("randomemail@reservationcustoms.com");


        CarReservation carReservation = new CarReservation();
        carReservation.setStartDate(LocalDate.now());
        carReservation.setEndDate(LocalDate.now().plusDays(20));
        carReservation.setCar(savedCar);
        carReservation.setCustomer(customer);
        reservationRepository.save(carReservation);

        checkoutRecord.setClerk(userSeed.getSeededClerk());
        checkoutRecord.setReservation(carReservation);
        checkoutRecord.setCreateDate(LocalDateTime.now());
        checkoutRecord.setLastUpdate(LocalDateTime.now());
        return checkoutRecord;
    }

    @Test
    public void shouldAddCheckoutRecord() {
        CheckoutRecord checkoutRecord = initCheckoutRecord();
        CheckoutRecord savedRecord = checkoutRecordRepository.save(checkoutRecord);
        Assertions.assertEquals(1, savedRecord.getCheckoutId());
    }

    @Test
    public void shouldNotAddCheckoutRecordWhenReservationIdIsEmpty() {
        try {
            CheckoutRecord checkoutRecord = initCheckoutRecord();
            checkoutRecord.setReservation(null);
            CheckoutRecord savedRecord = checkoutRecordRepository.save(checkoutRecord);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Reservation Id can't be empty"));
        }
    }

    @Test
    public void shouldNotAddCheckoutRecordWhenClerkIdIsEmpty() {
        try {
            CheckoutRecord checkoutRecord = initCheckoutRecord();
            checkoutRecord.setClerk(null);
            CheckoutRecord savedRecord = checkoutRecordRepository.save(checkoutRecord);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Clerk Id can't be empty"));
        }
    }
}
