package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
