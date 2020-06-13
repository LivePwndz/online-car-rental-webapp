package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
