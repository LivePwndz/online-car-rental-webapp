package miu.edu.cs.cs425.carRentalWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ReservationRepository extends JpaRepository<Repository, Long> {
}
