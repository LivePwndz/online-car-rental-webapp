package miu.edu.cs.cs425.carRentalWebApp.seeds;

import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles( value = {"in-memory"})
public class CustomerSeedTests {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldSeedCustomer() {
        List<Customer> customers = customerRepository.findAll();
        Assertions.assertTrue(customers.size() > 0);
    }

}
