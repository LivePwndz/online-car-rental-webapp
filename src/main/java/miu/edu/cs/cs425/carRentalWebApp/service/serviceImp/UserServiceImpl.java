package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.RoleName;
import miu.edu.cs.cs425.carRentalWebApp.model.User;
import miu.edu.cs.cs425.carRentalWebApp.repository.UserRepository;
import miu.edu.cs.cs425.carRentalWebApp.service.UserService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewUserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public NewUserDto getUserDtoById(Long id) {
        return initNewDto(getUserById(id));
    }

    @Override
    public User getUserById(Long id) {
        return findUserById(id);
    }

    private User findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("User with id " + id + " not found.");
        }
        return userOptional.get();
    }

    @Override
    public NewUserDto addUser(NewUserDto newUserDto, RoleName roleName) {
        String email = newUserDto.getEmail();
        Optional<User> customerOptional = userRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("User with email " + email + " already exist.");
        }

        if (newUserDto.getPassword() == null || !newUserDto.getPassword().equals(newUserDto.getConfirmPassword())) {
            throw new IllegalStateException("Password and confirmed password do not match");
        }

        User user = new User();

        user.setFirstName(newUserDto.getFirstName());
        user.setMiddleName(newUserDto.getMiddleName());
        user.setLastName(newUserDto.getLastName());
        user.setEmail(newUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        user.setRoleName(roleName);
        user.setCreateDate(LocalDateTime.now());
        user.setLastUpdate(LocalDateTime.now());

        return initNewDto(userRepository.save(user));
    }

    private NewUserDto initNewDto(User savedCustomer) {
        return new NewUserDto(savedCustomer.getId()
                , savedCustomer.getFirstName()
                , savedCustomer.getMiddleName()
                , savedCustomer.getLastName()
                , savedCustomer.getEmail()
                , savedCustomer.getRoleName()
                , savedCustomer.getCreateDate()
                , savedCustomer.getLastUpdate());
    }

    @Override
    public void deleteCustomerById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public List<NewUserDto> getUserByRole(RoleName roleName) {
        return userRepository.findByRoleName(roleName)
                .stream()
                .map(this::initNewDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewUserDto updateUser(NewUserDto newUserDto) {
        Optional<User> existingCustomerOptional = userRepository.findById(newUserDto.getId());
        if (!existingCustomerOptional.isPresent()) {
            throw new EntityNotFoundException("User with id " + newUserDto.getId() + " not found.");
        }

        User existingUser = existingCustomerOptional.get();

        String email = newUserDto.getEmail();
        Optional<User> customerOptional = userRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            User userByEmail = customerOptional.get();
            if (!userByEmail.getId().equals(existingUser.getId())) {
                throw new IllegalStateException("User with email " + email + " already exist.");
            }
        }

        existingUser.setFirstName(newUserDto.getFirstName());
        existingUser.setMiddleName(newUserDto.getMiddleName());
        existingUser.setLastName(newUserDto.getLastName());
        existingUser.setEmail(newUserDto.getEmail());
        existingUser.setLastUpdate(LocalDateTime.now());
        existingUser.setLastUpdate(LocalDateTime.now());

        return initNewDto(userRepository.save(existingUser));

    }

}
