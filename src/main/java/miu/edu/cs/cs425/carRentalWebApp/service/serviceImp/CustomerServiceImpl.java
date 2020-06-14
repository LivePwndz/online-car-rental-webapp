package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.repository.CustomerRepository;
import miu.edu.cs.cs425.carRentalWebApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
   @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);

        }

        @Override
        public Customer updateCustomer(Customer customer) {
            Customer existingCustomer= customerRepository.findById(customer.getId()).orElse(null);
            if(existingCustomer!=null){
                existingCustomer.setFirstName(customer.getFirstName());
                existingCustomer.setMiddleName(customer.getMiddleName());
                existingCustomer.setLastName(customer.getLastName());
                existingCustomer.setEmail(customer.getEmail());
                existingCustomer.setLastUpdate(customer.getLastUpdate());
                existingCustomer.setDrivingLicense(customer.getDrivingLicense());
                existingCustomer.setPhoneNo(customer.getPhoneNo());
                existingCustomer.getAddress().setHouseNumber(customer.getAddress().getHouseNumber());
                existingCustomer.getAddress().setCity(customer.getAddress().getCity());
                existingCustomer.getAddress().setState(customer.getAddress().getState());
                existingCustomer.getAddress().setZipCode(customer.getAddress().getZipCode());
                existingCustomer.getAddress().setStreet(customer.getAddress().getStreet());
                return existingCustomer;
        }

        return null;
    }
}
