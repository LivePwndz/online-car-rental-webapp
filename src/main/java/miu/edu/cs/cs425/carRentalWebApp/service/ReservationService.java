package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;

import java.util.List;


public interface ReservationService {
    List<CarReservation> getAllReservations();
}
