package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
