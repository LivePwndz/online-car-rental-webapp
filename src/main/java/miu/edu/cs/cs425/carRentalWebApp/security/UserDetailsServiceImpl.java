package miu.edu.cs.cs425.carRentalWebApp.security;

import miu.edu.cs.cs425.carRentalWebApp.model.User;
import miu.edu.cs.cs425.carRentalWebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> customerOptional = userRepository.findByEmail(email);
        if(!customerOptional.isPresent()){
            throw new UsernameNotFoundException("Invalid Username/Password.");
        }


        return new OnlineCarRentalUserDetails(customerOptional.get());
    }
}
