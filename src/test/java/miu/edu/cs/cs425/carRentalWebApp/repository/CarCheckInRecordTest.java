package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.CarCheckInRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles(value = {"in-memory"})
public class CarCheckInRecordTest {
    @Autowired
    private CarCheckInRecordRepository carCheckInRecordRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ClerkRepository clerkRepository;

    private CarCheckInRecord initialCarCheckInRecord() {
        CarCheckInRecord carCheckInRecord = new CarCheckInRecord();
        carCheckInRecord.setClerk(clerkRepository.findById(1L).orElse(null));
        carCheckInRecord.setCarReservation(reservationRepository.findById(1L).orElse(null));
        carCheckInRecord.setCreateDate(LocalDateTime.now());
        carCheckInRecord.setLastUpdate(LocalDateTime.now());
        return carCheckInRecord;
    }

    @Test
    public void shouldAddCheckInRecord() {
        CarCheckInRecord carCheckInRecord = initialCarCheckInRecord();
        CarCheckInRecord savedCarCheckInRecord = carCheckInRecordRepository.save(carCheckInRecord);
        Assertions.assertEquals(1, savedCarCheckInRecord.getId());
    }

    @Test
    public void shouldNotAddCarCheckInRecordWithNullCarReservation() {
        try {
            CarCheckInRecord carCheckInRecord = initialCarCheckInRecord();
            carCheckInRecord.setCarReservation(null);
            CarCheckInRecord savedCarCheckInRecord = carCheckInRecordRepository.save(carCheckInRecord);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("reservation cant't be null"));
        }
    }
    @Test
    public void shouldNotAddCarCheckInRecordWithNullClerk() {
        try {
            CarCheckInRecord carCheckInRecord = initialCarCheckInRecord();
            carCheckInRecord.setClerk(null);
            CarCheckInRecord savedCarCheckInRecord = carCheckInRecordRepository.save(carCheckInRecord);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("clerk cant't be null"));
        }
    }
}