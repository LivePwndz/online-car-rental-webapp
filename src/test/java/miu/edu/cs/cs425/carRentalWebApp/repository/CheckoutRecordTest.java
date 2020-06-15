package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.*;
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
    ClerkRepository clerkRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    CheckoutRecordRepository checkoutRecordRepository;

    Customer customerSeeder(String email){
        Address address = new Address();
        address.setHouseNumber("1000A");
        address.setStreet("1000 N 4th");
        address.setCity("Houston");
        address.setZipCode(5000);
        address.setState("IOWA");

        Customer customer = new Customer();
        customer.setDrivingLicense("I12ABBB");
        customer.setFirstName("Alex");
        customer.setMiddleName("James");
        customer.setLastName("Smith");
        customer.setPhoneNo("+1000000000");
        customer.setEmail(email);
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        customer.setAddress(address);
        return  customerRepository.save(customer);
    }
    private ChekoutRecord initCheckoutRecord(){
        ChekoutRecord chekoutRecord = new ChekoutRecord();
        Clerk clerk = new Clerk();
        //clerk.setId(5L);
        clerk.setFirstName("clerk1");
        clerk.setLastName("clerkLastName");
        clerk.setEmail("clerk Email");
        clerk.setLastUpdate(LocalDateTime.now());
        clerk.setCreateDate(LocalDateTime.now());

        clerkRepository.save(clerk);

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

        chekoutRecord.setClerckId(clerk);
        chekoutRecord.setReservationId(carReservation);
        chekoutRecord.setCreateDate(LocalDateTime.now());
        chekoutRecord.setUpdateDate(LocalDateTime.now());
        return chekoutRecord;
    }
    @Test
    public void shouldAddCheckoutRecord(){
        ChekoutRecord chekoutRecord = initCheckoutRecord();
        ChekoutRecord savedRecord = checkoutRecordRepository.save(chekoutRecord);
        Assertions.assertEquals(1, savedRecord.getCheckoutId());
    }
    @Test
    public void shouldNotAddCheckoutRecordWhenReservationIdIsEmpty(){
        try {
            ChekoutRecord chekoutRecord = initCheckoutRecord();
            chekoutRecord.setReservationId(null);
            ChekoutRecord savedRecord = checkoutRecordRepository.save(chekoutRecord);
        }catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Reservation Id can't be empty"));
        }
    }
    @Test
    public void shouldNotAddCheckoutRecordWhenClerkIdIsEmpty(){
        try {
            ChekoutRecord chekoutRecord = initCheckoutRecord();
            chekoutRecord.setClerckId(null);
            ChekoutRecord savedRecord = checkoutRecordRepository.save(chekoutRecord);
        }catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Clerk Id can't be empty"));
        }
    }
}
