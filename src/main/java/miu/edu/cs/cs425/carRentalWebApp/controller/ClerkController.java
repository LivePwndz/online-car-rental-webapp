package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.model.ReservationStatus;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import miu.edu.cs.cs425.carRentalWebApp.service.ClerkService;
import miu.edu.cs.cs425.carRentalWebApp.service.ReservationService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.*;
import miu.edu.cs.cs425.carRentalWebApp.service.serviceImp.ReservationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/clerk")
public class ClerkController {
    private final ClerkService clerkService;
    private final ReservationService reservationService;
    private final CarService carService;


    @Autowired
    public ClerkController(ClerkService clerkService, ReservationService reservationService, CarService carService) {
        this.clerkService = clerkService;
        this.reservationService = reservationService;
        this.carService = carService;
    }

    @GetMapping
    public String displayHome(Model model) {
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());
        return "clerk/index";
    }

    @GetMapping(value = "/sec/car/reservation/list")
    public String displayAuthenticatedCustomerReservationList(Model model) {
        List<CarReservation> carReservations = reservationService.getAllReservations();
        model.addAttribute("reservations", carReservations);
        model.addAttribute("count", carReservations.size());
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());
        return "clerk/sec_reservation_list";
    }



    @GetMapping(value = "/list")
    public String displayLisfOfClerks(Model model) {
        List<NewUserDto> clerks = clerkService.getAllClecks();
        model.addAttribute("clerks", clerks);
        model.addAttribute("clerkCount", clerks.size());
        //model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());
        return "clerk/list";
    }

    @GetMapping(value = "/new")
    public String displayAddClerkForm(Model model) {
        model.addAttribute("clerk", new NewUserDto());
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());
        return "clerk/new";
    }

    @PostMapping(value = "/new")
    public String addClerkForm(@ModelAttribute("clerk") NewUserDto clerk) {
        clerkService.addNewClerk(clerk);
        return "redirect:/clerk/list";
    }

    @GetMapping(value = "/edit/{clerkId}")
    public String displayEditClerkForm(Model model, @PathVariable() Long clerkId) {
        NewUserDto clerk = clerkService.findClerkById(clerkId);
        model.addAttribute("clerk", clerk);
        //model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());
        return "clerk/edit";
    }

    @PostMapping(value = "/edit/{clerkId}")
    public String editClerkForm(@ModelAttribute("clerk") NewUserDto clerkUpdate, @PathVariable() Long clerkId) {
        clerkService.updateClerk(clerkUpdate);
        return "redirect:/clerk/list";
    }

    @GetMapping(value = "/reservation/{reservationId}")
    public String displayReservationDetails(Model model, @PathVariable() Long reservationId) {

        CarReservation carReservation = reservationService.findById(reservationId);
        ReservationStatus reservationStatus = carReservation.getStatus();
        if (reservationStatus.equals(ReservationStatus.PENDING_CHECKOUT)) {
            return getPendingCheckoutReservationDetails(model, carReservation);
        } else{
            return getCheckInReservationDetails(model, carReservation);
        }


    }

    private String getPendingCheckoutReservationDetails(Model model, CarReservation carReservation) {
        String startDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getStartDate());
        String endDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getEndDate());
        CarReservationDto reservationDto = carService.getReservationDto(carReservation.getCar().getId(), startDateStr, endDateStr, carReservation.getStatus());

        NewCarCheckoutDto newCarCheckoutDto = carService.getNewCheckoutDto(carReservation);
        model.addAttribute("reservationDto", reservationDto);
        model.addAttribute("newCarCheckoutDto", newCarCheckoutDto);
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());

        return "clerk/checkout_reservation";
    }

    private String getCheckInReservationDetails(Model model, CarReservation carReservation) {
        String startDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getStartDate());
        String endDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getEndDate());

        CarReservationDto reservationDto = carService.getReservationDto(carReservation.getCar().getId(), startDateStr, endDateStr, carReservation.getStatus());
        NewCarCheckInDto newCarCheckInDto = carService.getNewCheckInDto(carReservation);

        model.addAttribute("reservationDto", reservationDto);
        model.addAttribute("newCarCheckInDto", newCarCheckInDto);
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());

        return "clerk/check_in_reservation";
    }

    @PostMapping(value = "/reservation/checkout")
    public String checkoutCar(Model model, @ModelAttribute("newCarCheckoutDto") NewCarCheckoutDto newCarCheckoutDto) {
        CheckoutNotificationDto checkoutNotificationDto = reservationService.addNewCheckoutRecord(newCarCheckoutDto.getReservationId());
        model.addAttribute("checkoutNotificationDto", checkoutNotificationDto);
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());
        return "clerk/checkout_reservation_notification";
    }

    @PostMapping(value = "/reservation/check-in")
    public String checkInCar(Model model, @ModelAttribute("newCarCheckoutDto") NewCarCheckoutDto newCarCheckoutDto) {
        CheckInNotificationDto checkInNotificationDto = reservationService.addNewCheckInRecord(newCarCheckoutDto.getReservationId());
        model.addAttribute("checkInNotificationDto", checkInNotificationDto);
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());
        return "clerk/check_in_reservation_notification";
    }

}
