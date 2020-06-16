package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.CheckInRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRecordRepository extends JpaRepository<CheckInRecord,Long> {
}
