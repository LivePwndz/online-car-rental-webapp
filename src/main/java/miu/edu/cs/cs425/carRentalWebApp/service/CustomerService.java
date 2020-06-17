package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewCustomerDto;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    NewCustomerDto addCustomer(NewCustomerDto newCustomerDto);
    void deleteCustomerById(Long id);
    Customer updateCustomer(Customer customer);
}
