package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewReservationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.PlaceRerservationInfoDto;

import java.util.List;


public interface ReservationService {
    List<CarReservation> getAllReservations();
    PlaceRerservationInfoDto addNewReservation(NewReservationDto newReservationDto);
    CarReservation findById(Long reservationId);
}
