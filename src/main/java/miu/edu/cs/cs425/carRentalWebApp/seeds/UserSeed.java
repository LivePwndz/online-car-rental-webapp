package miu.edu.cs.cs425.carRentalWebApp.seeds;

import miu.edu.cs.cs425.carRentalWebApp.model.RoleName;
import miu.edu.cs.cs425.carRentalWebApp.model.User;
import miu.edu.cs.cs425.carRentalWebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserSeed implements CommandLineRunner, Ordered {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private User seededAdmin;
    private User seededClerk;

    @Override
    public void run(String... args) throws Exception {
        seededAdmin = adminSeederForLogin();
        seededClerk = clerkSeederForLogin();
    }



    User adminSeederForLogin() {
        User customer = initUser("admin@online.com");
        customer.setFirstName("Jean");
        customer.setLastName("de Dieu");
        customer.setPassword(passwordEncoder.encode("aaa"));
        customer.setRoleName(RoleName.ADMIN);
        return userRepository.save(customer);
    }

    User clerkSeederForLogin() {
        User customer = initUser("clerk@online.com");
        customer.setFirstName("Robert");
        customer.setLastName("Ogoppa");
        customer.setPassword(passwordEncoder.encode("aaa"));
        customer.setRoleName(RoleName.CLERK);
        return userRepository.save(customer);
    }

    User seedUser( String email, RoleName roleName ) {
        User customer = initUser(email);
        customer.setPassword(passwordEncoder.encode("aaa"));
        customer.setRoleName(roleName);
        return userRepository.save(customer);
    }

    User initUser(String email) {

        User user = new User();
        user.setFirstName("Alex");
        user.setMiddleName("James");
        user.setLastName("Smith");
        user.setEmail(email);
        user.setCreateDate(LocalDateTime.now());
        user.setLastUpdate(LocalDateTime.now());
        return user;
    }

    public User getSeededAdmin() {
        return seededAdmin;
    }

    public User getSeededClerk() {
        return seededClerk;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
