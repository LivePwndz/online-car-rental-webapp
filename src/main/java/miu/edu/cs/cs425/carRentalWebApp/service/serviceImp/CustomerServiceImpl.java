package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.Address;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.model.RoleName;
import miu.edu.cs.cs425.carRentalWebApp.repository.CustomerRepository;
import miu.edu.cs.cs425.carRentalWebApp.service.CustomerService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewCustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public NewCustomerDto addCustomer(NewCustomerDto newCustomerDto) {
        String email = newCustomerDto.getEmail();
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if(customerOptional.isPresent()){
            throw new IllegalStateException("Customer with email "+email+" already exist.");
        }

        if(!newCustomerDto.getPassword().equals(newCustomerDto.getConfirmPassword())){
            throw new IllegalStateException("Password and confirmed password do not match");
        }

        Customer customer = new Customer();
        customer.setFirstName(newCustomerDto.getFirstName());
        customer.setMiddleName(newCustomerDto.getMiddleName());
        customer.setLastName(newCustomerDto.getLastName());
        customer.setEmail(newCustomerDto.getEmail());
        customer.setPassword(newCustomerDto.getPassword());
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        customer.setDrivingLicense(newCustomerDto.getDrivingLicense());
        customer.setPhoneNo(newCustomerDto.getPhoneNo());
        customer.setRoleName(RoleName.CUSTOMER);

        Address address = newCustomerDto.getAddress();

        customer.setAddress(address);
        Customer savedCustomer = customerRepository.save(customer);

        return new NewCustomerDto(savedCustomer.getId()
                , savedCustomer.getDrivingLicense()
                , savedCustomer.getFirstName()
                , savedCustomer.getMiddleName()
                , savedCustomer.getLastName()
                , savedCustomer.getPhoneNo()
                , savedCustomer.getEmail()
                ,null
                , null
                , savedCustomer.getCreateDate()
                , savedCustomer.getLastUpdate()
                , savedCustomer.getAddress());
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);

        }

        @Override
        public Customer updateCustomer(Customer customer) {
            Optional<Customer> existingCustomerOptional = customerRepository.findById(customer.getId());
            if(!existingCustomerOptional.isPresent()){
                throw new EntityNotFoundException("Customer with id "+customer.getId()+" not found.");
            }

            Customer existingCustomer = existingCustomerOptional.get();

            String email = customer.getEmail();
            Optional<Customer> customerOptional = customerRepository.findByEmail(email);
            if(customerOptional.isPresent()){
                Customer customerByEmail = customerOptional.get();
                if(!customerByEmail.getId().equals(existingCustomer.getId())){
                    throw new IllegalStateException("Customer with email "+email+" already exist.");
                }
            }

            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setMiddleName(customer.getMiddleName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setLastUpdate(LocalDateTime.now());
            existingCustomer.setDrivingLicense(customer.getDrivingLicense());
            existingCustomer.setPhoneNo(customer.getPhoneNo());
            existingCustomer.getAddress().setHouseNumber(customer.getAddress().getHouseNumber());
            existingCustomer.getAddress().setCity(customer.getAddress().getCity());
            existingCustomer.getAddress().setState(customer.getAddress().getState());
            existingCustomer.getAddress().setZipCode(customer.getAddress().getZipCode());
            existingCustomer.getAddress().setStreet(customer.getAddress().getStreet());
            return customerRepository.save(existingCustomer);

        }
}
