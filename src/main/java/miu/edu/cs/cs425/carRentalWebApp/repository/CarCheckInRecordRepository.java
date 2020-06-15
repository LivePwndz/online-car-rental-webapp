package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.CarCheckInRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarCheckInRecordRepository extends JpaRepository<CarCheckInRecord,Long> {
}
