package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer addCustomer(Customer customer);
    void deleteCustomerById(Long id);
    Customer updateCustomer(Customer customer);

}
