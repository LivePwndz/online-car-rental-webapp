package miu.edu.cs.cs425.carRentalWebApp.seeds;

import miu.edu.cs.cs425.carRentalWebApp.model.Address;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.model.RoleName;
import miu.edu.cs.cs425.carRentalWebApp.model.User;
import miu.edu.cs.cs425.carRentalWebApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomerSeed implements CommandLineRunner, Ordered {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Customer seededCustomer;

    @Override
    public void run(String... args) throws Exception {
        customerSeeder("james@miu.edu");
        seededCustomer = customerSeederForLogin();
    }

    Customer customerSeeder(String email) {
        return customerRepository.save(initCustomer(email));
    }

    Customer customerSeederForLogin() {
        Customer customer = initCustomer("customer@online.com");
        customer.setDrivingLicense("IA-30929-NORICH-K");


        customer.getUser().setFirstName("Andualema");
        customer.getUser().setLastName("TheMan");
        customer.getUser().setPassword(passwordEncoder.encode("aaa"));
        customer.getUser().setRoleName(RoleName.CUSTOMER);
        return customerRepository.save(customer);
    }

    Customer initCustomer(String email) {
        Address address = new Address();
        address.setHouseNumber("1000A");
        address.setStreet("1000 N 4th");
        address.setCity("Fairfield");
        address.setZipCode(5000);
        address.setState("IOWA");

        Customer customer = new Customer();
        User user = new User();
        user.setPassword("");
        user.setEmail(email);
        user.setRoleName(RoleName.CUSTOMER);
        user.setCreateDate(LocalDateTime.now());
        user.setLastUpdate(LocalDateTime.now());

        customer.setUser(user);


        customer.setDrivingLicense("I12ABC");
        customer.getUser().setFirstName("Alex");
        customer.getUser().setMiddleName("James");
        customer.getUser().setLastName("Smith");
        customer.setPhoneNo("+1000000000");
        customer.getUser().setEmail(email);
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        customer.setAddress(address);
        return customer;
    }

    public Customer getSeededCustomer() {
        return seededCustomer;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
