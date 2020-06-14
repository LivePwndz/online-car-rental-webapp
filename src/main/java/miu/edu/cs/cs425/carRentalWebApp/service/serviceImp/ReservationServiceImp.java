package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.repository.ReservationRepository;
import miu.edu.cs.cs425.carRentalWebApp.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImp implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImp(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<CarReservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
