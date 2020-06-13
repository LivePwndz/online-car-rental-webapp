package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Address;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles( value = {"in-memory"})
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private Customer initCustomer(){
       Address address = new Address();
       address.setAddressId((long) 1);
       address.setHouseNumber("1000A");
       address.setStreet("1000 N 4th");
       address.setCity("Houston");
       address.setZipCode(5000);
       address.setState("IOWA");

//        Customer customer = new Customer();
//        customer.getAddress().setHouseNumber("1000A");
//        customer.getAddress().setStreet("1000 N 4th");
//        customer.getAddress().setCity("Houston");
//        customer.getAddress().setZipCode(5000);
//        customer.getAddress().setState("IOWA");

       Customer customer = new Customer();
       customer.setDrivingLicense("I12ABC");
       customer.setFirstName("Alex");
       customer.setMiddleName("James");
       customer.setLastName("Smith");
       customer.setEmail("james@miu.edu");
       customer.setCreateDate(LocalDateTime.now());
       customer.setLastUpdate(LocalDateTime.now());
       customer.setAddress(address);

       return customer;
    }
    @Test
    public void shouldNotAddWhenDrivingLicenseIsNull(){
        try {
            Customer customer = initCustomer();
            customer.setDrivingLicense(null);
            Customer savedCustomer = customerRepository.save(customer);
        } catch(Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Drving license can not be empty"));
        }
    }
    @Test
    public void shouldNotAddWhenFirstNameIsEmpty(){
        try{
            Customer customer = initCustomer();
            customer.setFirstName(null);
            Customer savedCustomer = customerRepository.save(customer);
        }catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("First Name can't be empty"));
        }
    }
    @Test
    public void shouldNotAddWhenLastNameIsEmpty(){
        try{
            Customer customer = initCustomer();
            customer.setLastName(null);
            Customer savedCustomer = customerRepository.save(customer);
        }catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Last Name can't be empty"));
        }
    }
    @Test
    public void shouldNotAddWhenEmailIsEmpty(){
        try{
            Customer customer = initCustomer();
            customer.setEmail(null);
            Customer savedCustomer = customerRepository.save(customer);
        }catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("email can't be empty"));
        }
    }
    @Test
    public void shouldNotAddWhenCreateDateIsEmpty(){
        try{
            Customer customer = initCustomer();
            customer.setCreateDate(null);
            Customer savedCustomer = customerRepository.save(customer);
        }catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Create date can't be null"));
        }
    }
    @Test
    public void shouldNotAddWhenLastUpdateDateIsEmpty(){
        try{
            Customer customer = initCustomer();
            customer.setLastUpdate(null);
            Customer savedCustomer = customerRepository.save(customer);
        }catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Last Update date can't be null"));
        }
    }
    @Test
    public void shouldNotAddWhenAddressIsEmpty(){
        try{
            Customer customer = initCustomer();
            customer.setAddress(null);
            Customer savedCustomer = customerRepository.save(customer);
        }catch (Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Address Id can't be empty"));
        }
    }
}
