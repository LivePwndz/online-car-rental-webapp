package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Address;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles(value = {"in-memory"})
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private Customer initCustomer() {
        Address address = new Address();
        address.setHouseNumber("1000A");
        address.setStreet("1000 N 4th");
        address.setCity("Houston");
        address.setZipCode(5000);
        address.setState("IOWA");

        Customer customer = new Customer();
        customer.setUser(new User());
        customer.setDrivingLicense("I12ABC");
        customer.getUser().setFirstName("Alex");
        customer.getUser().setMiddleName("James");
        customer.getUser().setLastName("Smith");
        customer.setPhoneNo("+1000000000");
        customer.getUser().setEmail("james@miu.edu");
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        customer.setAddress(address);
        customer.setUser(new User());
        return customer;
    }

    @Test
    public void shouldAddCustomerWithAddress() {
        Customer customer = initCustomer();
        Customer savedCustormer = customerRepository.save(customer);
        Assertions.assertEquals(savedCustormer.getId(), 2);
    }

    @Test
    public void shouldAddAddress() {
        Customer customer = initCustomer();
        Customer savedCustormer = customerRepository.save(customer);
        Assertions.assertEquals(savedCustormer.getAddress().getAddressId(), 1);
    }

    @Test
    public void shouldNotAddWhenDrivingLicenseIsNull() {
        try {
            Customer customer = initCustomer();
            customer.setDrivingLicense(null);
            Customer savedCustomer = customerRepository.save(customer);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Drving license can not be empty"));
        }
    }

    @Test
    public void shouldNotAddWhenFirstNameIsEmpty() {
        try {
            Customer customer = initCustomer();
            customer.getUser().setFirstName(null);
            Customer savedCustomer = customerRepository.save(customer);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("First Name can't be empty"));
        }
    }

    @Test
    public void shouldNotAddWhenLastNameIsEmpty() {
        try {
            Customer customer = initCustomer();
            customer.getUser().setLastName(null);
            Customer savedCustomer = customerRepository.save(customer);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Last Name can't be empty"));
        }
    }

    @Test
    public void shouldNotAddWhenPhoneNumberIsEmpty() {
        try {
            Customer customer = initCustomer();
            customer.setPhoneNo(null);
            Customer savedCustomer = customerRepository.save(customer);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Phone number is required"));
        }
    }

    @Test
    public void shouldNotAddWhenEmailIsEmpty() {
        try {
            Customer customer = initCustomer();
            customer.getUser().setEmail(null);
            Customer savedCustomer = customerRepository.save(customer);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("email can't be empty"));
        }
    }

    @Test
    public void shouldNotAddWhenCreateDateIsEmpty() {
        try {
            Customer customer = initCustomer();
            customer.setCreateDate(null);
            Customer savedCustomer = customerRepository.save(customer);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Create date can't be null"));
        }
    }

    @Test
    public void shouldNotAddWhenLastUpdateDateIsEmpty() {
        try {
            Customer customer = initCustomer();
            customer.setLastUpdate(null);
            Customer savedCustomer = customerRepository.save(customer);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Last Update date can't be null"));
        }
    }

    @Test
    public void shouldNotAddWhenAddressIsEmpty() {
        try {
            Customer customer = initCustomer();
            customer.setAddress(null);
            Customer savedCustomer = customerRepository.save(customer);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Address Id can't be empty"));
        }
    }
}
