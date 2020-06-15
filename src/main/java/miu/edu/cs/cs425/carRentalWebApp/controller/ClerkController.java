package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import miu.edu.cs.cs425.carRentalWebApp.service.ClerkService;
import miu.edu.cs.cs425.carRentalWebApp.service.ReservationService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.CarReservationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewReservationDto;
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
    public String displayLisfOfClerks(Model model){
        List<Clerk> clerks = clerkService.getAllClerck();
        model.addAttribute("clerks", clerks);
        model.addAttribute("clerksCount", clerks.size());
        return "clerk/list";
    }

    @GetMapping(value = "/new")
    public String displayAddClerkForm(Model model){
        model.addAttribute("clerk", new Clerk());
        return "clerk/new";
    }

    @PostMapping(value = "/new")
    public String addClerkForm(@ModelAttribute("clerk") Clerk clerk){
        clerk.setCreateDate(LocalDateTime.now());
        clerk.setLastUpdate(LocalDateTime.now());
        clerkService.addNewClerk(clerk);
        return "redirect:/clerk/list";
    }
    @GetMapping(value = "/edit/{clerkId}")
    public String displayEditClerkForm(Model model, @PathVariable() Long clerkId ){
        Clerk clerk = clerkService.findClerkById(clerkId);
        model.addAttribute("clerk", clerk);
        return "clerk/edit";
    }
    @PostMapping(value = "/edit/{clerkId}")
    public String editClerkForm(@ModelAttribute("clerk") Clerk clerkUpdate, @PathVariable() Long clerkId){
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
    public String displayReservationDetails(Model model, @PathVariable() Long reservationId ){

        CarReservation carReservation = reservationService.findById(reservationId);
        String startDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getStartDate());
        String endDateStr = ReservationUtils.formatLocalDateToStandardString(carReservation.getEndDate());
        CarReservationDto reservationDto = carService.getReservationDto(carReservation.getCar().getId(), startDateStr, endDateStr);

        //TODO new checkout record
        model.addAttribute("newReservationDto", new NewReservationDto());

        model.addAttribute("reservationDto", reservationDto);

        Car car = carReservation.getCar();
        Customer customer = carReservation.getCustomer();

        NewCarCheckoutDto newCarCheckoutDto = new NewCarCheckoutDto(
                carReservation.getId()
                , car.getModel()
                ,car.getPlateNo()
                ,customer.getFirstName()
                ,customer.getLastName()
                ,customer.getDrivingLicense(), carReservation.getCode()
                ,ReservationUtils.formatLocalDateToUIString(carReservation.getCreateDate().toLocalDate())
                ,ReservationUtils.formatLocalDateToUIString(carReservation.getEndDate()));

        model.addAttribute("newCarCheckoutDto", newCarCheckoutDto);

        return "clerk/checkout_reservation";
    }

}
