package miu.edu.cs.cs425.carRentalWebApp.seeds;

import miu.edu.cs.cs425.carRentalWebApp.model.Address;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomerSeed implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void run(String... args) throws Exception {
        customerSeeder();
    }
    private void customerSeeder(){
        Address address = new Address();
        address.setHouseNumber("1000A");
        address.setStreet("1000 N 4th");
        address.setCity("Houston");
        address.setZipCode(5000);
        address.setState("IOWA");

        Customer customer = new Customer();
        customer.setDrivingLicense("I12ABC");
        customer.setFirstName("Alex");
        customer.setMiddleName("James");
        customer.setLastName("Smith");
        customer.setPhoneNo("+1000000000");
        customer.setEmail("james@miu.edu");
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        customer.setAddress(address);
        customerRepository.save(customer);
    }
}
