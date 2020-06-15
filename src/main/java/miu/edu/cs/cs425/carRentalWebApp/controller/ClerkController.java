package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import miu.edu.cs.cs425.carRentalWebApp.model.ReservationStatus;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import miu.edu.cs.cs425.carRentalWebApp.service.ClerkService;
import miu.edu.cs.cs425.carRentalWebApp.service.ReservationService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.CarReservationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.CheckoutNotificationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewCarCheckoutDto;
import miu.edu.cs.cs425.carRentalWebApp.service.serviceImp.ReservationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping(value = "/list")
    public String displayLisfOfClerks(Model model) {
        List<Clerk> clerks = clerkService.getAllClerck();
        model.addAttribute("clerks", clerks);
        model.addAttribute("clerkCount", clerks.size());
        return "clerk/list";
    }

    @GetMapping(value = "/new")
    public String displayAddClerkForm(Model model) {
        model.addAttribute("clerk", new Clerk());
        return "clerk/new";
    }

    @PostMapping(value = "/new")
    public String addClerkForm(@ModelAttribute("clerk") Clerk clerk) {
        clerk.setCreateDate(LocalDateTime.now());
        clerk.setLastUpdate(LocalDateTime.now());
        clerkService.addNewClerk(clerk);
        return "redirect:/clerk/list";
    }

    @GetMapping(value = "/edit/{clerkId}")
    public String displayEditClerkForm(Model model, @PathVariable() Long clerkId) {
        Clerk clerk = clerkService.findClerkById(clerkId);
        model.addAttribute("clerk", clerk);
        return "clerk/edit";
    }

    @PostMapping(value = "/edit/{clerkId}")
    public String editClerkForm(@ModelAttribute("clerk") Clerk clerkUpdate, @PathVariable() Long clerkId) {
        Clerk savedClerk = clerkService.findClerkById(clerkId);
        savedClerk.setFirstName(clerkUpdate.getFirstName());
        savedClerk.setMiddleName(clerkUpdate.getMiddleName());
        savedClerk.setLastUpdate(clerkUpdate.getLastUpdate());
        savedClerk.setEmail(clerkUpdate.getEmail());
        savedClerk.setLastUpdate(LocalDateTime.now());
        clerkService.addNewClerk(savedClerk);
        return "redirect:/clerk/list";
    }

    @GetMapping(value = "/reservation/{reservationId}")
    public String displayReservationDetails(Model model, @PathVariable() Long reservationId) {

        CarReservation carReservation = reservationService.findById(reservationId);

        ReservationStatus reservationStatus = carReservation.getStatus();
        if (!reservationStatus.equals(ReservationStatus.PENDING_CHECKOUT)) {
            return getPendingCheckoutReservationDetails(model, carReservation);
        } else{
            return getCheckoutReservationDetails(model, carReservation);
        }


    }

    private String getPendingCheckoutReservationDetails(Model model, CarReservation carReservation) {
        String startDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getStartDate());
        String endDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getEndDate());
        CarReservationDto reservationDto = carService.getReservationDto(carReservation.getCar().getId(), startDateStr, endDateStr);

        NewCarCheckoutDto newCarCheckoutDto = carService.getNewCheckoutDto(carReservation);
        model.addAttribute("reservationDto", reservationDto);
        model.addAttribute("newCarCheckoutDto", newCarCheckoutDto);

        return "clerk/checkout_reservation";
    }

    private String getCheckoutReservationDetails(Model model, CarReservation carReservation) {
        String startDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getStartDate());
        String endDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getEndDate());
        CarReservationDto reservationDto = carService.getReservationDto(carReservation.getCar().getId(), startDateStr, endDateStr);

        NewCarCheckoutDto newCarCheckoutDto = carService.getNewCheckoutDto(carReservation);
        model.addAttribute("reservationDto", reservationDto);
        model.addAttribute("newCarCheckoutDto", newCarCheckoutDto);

        return "clerk/check_in_reservation";
    }

    @PostMapping(value = "/reservation/checkout")
    public String checkoutCar(Model model, @ModelAttribute("newCarCheckoutDto") NewCarCheckoutDto newCarCheckoutDto) {
        // TODO add new car checkout record

        model.addAttribute("checkoutNotificationDto", new CheckoutNotificationDto());
        return "clerk/checkout_reservation_notification";
    }

    @PostMapping(value = "/reservation/check-in")
    public String checkInCar(Model model, @ModelAttribute("newCarCheckoutDto") NewCarCheckoutDto newCarCheckoutDto) {
        // TODO add new car checkout record

        model.addAttribute("checkoutNotificationDto", new CheckoutNotificationDto());
        return "clerk/check_in_reservation_notification";
    }

}
