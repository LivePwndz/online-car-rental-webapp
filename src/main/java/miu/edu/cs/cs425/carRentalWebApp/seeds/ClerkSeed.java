package miu.edu.cs.cs425.carRentalWebApp.seeds;

import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import miu.edu.cs.cs425.carRentalWebApp.repository.ClerkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClerkSeed implements CommandLineRunner {
    @Autowired
    private ClerkRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        clerkSeeder("james@miu.edu");
    }

    Clerk clerkSeeder(String email) {
        Clerk clerk = new Clerk();
        clerk.setFirstName("Hey");
        clerk.setMiddleName("midle");
        clerk.setLastName("Last");
        clerk.setEmail(email);
        clerk.setCreateDate(LocalDateTime.now());
        clerk.setLastUpdate(LocalDateTime.now());
        return customerRepository.save(clerk);
    }

}
