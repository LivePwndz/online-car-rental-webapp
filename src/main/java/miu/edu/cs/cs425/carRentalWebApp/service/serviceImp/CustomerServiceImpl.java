package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.Address;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.model.RoleName;
import miu.edu.cs.cs425.carRentalWebApp.model.User;
import miu.edu.cs.cs425.carRentalWebApp.repository.CustomerRepository;
import miu.edu.cs.cs425.carRentalWebApp.service.CustomerService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewCustomerDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public NewCustomerDto getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            throw new EntityNotFoundException("Customer with id " + id + " not found.");
        }
        return initNewCustomerDto(customerOptional.get());
    }

    @Override
    public NewCustomerDto addCustomer(NewCustomerDto newCustomerDto) {
        String email = newCustomerDto.getEmail();
        Optional<Customer> customerOptional = customerRepository.findByUserEmail(email);
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Customer with email " + email + " already exist.");
        }

        if (!newCustomerDto.getPassword().equals(newCustomerDto.getConfirmPassword())) {
            throw new IllegalStateException("Password and confirmed password do not match");
        }

        Customer customer = new Customer();
        customer.setUser(new User());

        customer.getUser().setFirstName(newCustomerDto.getFirstName());
        customer.getUser().setMiddleName(newCustomerDto.getMiddleName());
        customer.getUser().setLastName(newCustomerDto.getLastName());
        customer.getUser().setEmail(newCustomerDto.getEmail());
        customer.getUser().setPassword(passwordEncoder.encode(newCustomerDto.getPassword()));
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        customer.getUser().setCreateDate(LocalDateTime.now());
        customer.getUser().setLastUpdate(LocalDateTime.now());
        customer.setDrivingLicense(newCustomerDto.getDrivingLicense());
        customer.setPhoneNo(newCustomerDto.getPhoneNo());
        customer.getUser().setRoleName(RoleName.CUSTOMER);

        Address address = newCustomerDto.getAddress();

        customer.setAddress(address);
        Customer savedCustomer = customerRepository.save(customer);

        return initNewCustomerDto(savedCustomer);
    }

    private NewCustomerDto initNewCustomerDto(Customer savedCustomer) {
        return new NewCustomerDto(savedCustomer.getId()
                , savedCustomer.getDrivingLicense()
                , savedCustomer.getUser().getFirstName()
                , savedCustomer.getUser().getMiddleName()
                , savedCustomer.getUser().getLastName()
                , savedCustomer.getPhoneNo()
                , savedCustomer.getUser().getEmail()
                , null
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
    public NewCustomerDto updateCustomer(NewCustomerDto newCustomerDto) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(newCustomerDto.getId());
        if (!existingCustomerOptional.isPresent()) {
            throw new EntityNotFoundException("Customer with id " + newCustomerDto.getId() + " not found.");
        }

        Customer existingCustomer = existingCustomerOptional.get();

        String email = newCustomerDto.getEmail();
        Optional<Customer> customerOptional = customerRepository.findByUserEmail(email);
        if (customerOptional.isPresent()) {
            Customer customerByEmail = customerOptional.get();
            if (!customerByEmail.getId().equals(existingCustomer.getId())) {
                throw new IllegalStateException("Customer with email " + email + " already exist.");
            }
        }

        existingCustomer.getUser().setFirstName(newCustomerDto.getFirstName());
        existingCustomer.getUser().setMiddleName(newCustomerDto.getMiddleName());
        existingCustomer.getUser().setLastName(newCustomerDto.getLastName());
        existingCustomer.getUser().setEmail(newCustomerDto.getEmail());
        existingCustomer.getUser().setLastUpdate(LocalDateTime.now());
        existingCustomer.setDrivingLicense(newCustomerDto.getDrivingLicense());
        existingCustomer.setPhoneNo(newCustomerDto.getPhoneNo());
        existingCustomer.getAddress().setHouseNumber(newCustomerDto.getAddress().getHouseNumber());
        existingCustomer.getAddress().setCity(newCustomerDto.getAddress().getCity());
        existingCustomer.getAddress().setState(newCustomerDto.getAddress().getState());
        existingCustomer.getAddress().setZipCode(newCustomerDto.getAddress().getZipCode());
        existingCustomer.getAddress().setStreet(newCustomerDto.getAddress().getStreet());
        existingCustomer.setLastUpdate(LocalDateTime.now());
        existingCustomer.getUser().setLastUpdate(LocalDateTime.now());

        return initNewCustomerDto(customerRepository.save(existingCustomer));

    }

}
