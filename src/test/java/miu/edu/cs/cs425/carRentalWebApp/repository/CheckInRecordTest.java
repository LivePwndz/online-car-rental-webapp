package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.CheckInRecord;
import miu.edu.cs.cs425.carRentalWebApp.seeds.UserSeed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles(value = {"in-memory"})
public class CheckInRecordTest {
    @Autowired
    private CheckInRecordRepository checkInRecordRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserSeed userSeed;


    private CheckInRecord initialCarCheckInRecord() {
        CheckInRecord checkInRecord = new CheckInRecord();
        checkInRecord.setClerk(userSeed.getSeededClerk());
        checkInRecord.setReservation(reservationRepository.findById(1L).orElse(null));
        checkInRecord.setCreateDate(LocalDateTime.now());
        checkInRecord.setLastUpdate(LocalDateTime.now());
        return checkInRecord;
    }

    @Test
    public void shouldAddCheckInRecord() {
        CheckInRecord checkInRecord = initialCarCheckInRecord();
        CheckInRecord savedCheckInRecord = checkInRecordRepository.save(checkInRecord);
        Assertions.assertEquals(1, savedCheckInRecord.getId());
    }

    @Test
    public void shouldNotAddCarCheckInRecordWithNullCarReservation() {
        try {
            CheckInRecord checkInRecord = initialCarCheckInRecord();
            checkInRecord.setReservation(null);
            CheckInRecord savedCheckInRecord = checkInRecordRepository.save(checkInRecord);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("reservation cant't be null"));
        }
    }

    @Test
    public void shouldNotAddCarCheckInRecordWithNullClerk() {
        try {
            CheckInRecord checkInRecord = initialCarCheckInRecord();
            checkInRecord.setClerk(null);
            CheckInRecord savedCheckInRecord = checkInRecordRepository.save(checkInRecord);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("clerk cant't be null"));
        }
    }
}