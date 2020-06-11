package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClerkRepository  extends JpaRepository<Clerk, Long> {
}
