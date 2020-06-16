package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.CheckInNotificationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.CheckoutNotificationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewReservationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.PlaceRerservationInfoDto;

import java.util.List;


public interface ReservationService {
    List<CarReservation> getAllReservations();
    PlaceRerservationInfoDto addNewReservation(NewReservationDto newReservationDto);
    CarReservation findById(Long reservationId);
    CheckoutNotificationDto addNewCheckoutRecord(Long reservationId);
    CheckInNotificationDto addNewCheckInRecord(Long reservationId);
}
