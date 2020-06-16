package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.model.Address;
import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.CarReservation;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import miu.edu.cs.cs425.carRentalWebApp.service.CustomerService;
import miu.edu.cs.cs425.carRentalWebApp.service.ReservationService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.CarReservationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewCustomerDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewReservationDto;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.PlaceRerservationInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = {"/customer"})
public class CustomerController {
    private final CustomerService customerService;
    private final CarService carService;
    private final ReservationService reservationService;


    public CustomerController(CustomerService customerService, CarService carService, ReservationService reservationService) {
        this.customerService = customerService;
        this.carService = carService;
        this.reservationService = reservationService;
    }

    @GetMapping()
    public String displayCustomerHome() {
        return "customer/index";
    }

    @GetMapping(value = "/list")
    public String displayLisfOfCustomers(Model model) {
        List<Customer> customer = customerService.getAllCustomers();
        model.addAttribute("customers", customer);
        model.addAttribute("customerCount", customer.size());
        return "customer/list";
    }

    @GetMapping(value = "/new")
    public String displayAddCustomerForm(Model model) {
        NewCustomerDto customer = new NewCustomerDto();
        customer.setAddress(new Address());
        model.addAttribute("customer", customer);
        return "customer/new";
    }



    @PostMapping(value = "/new")
    public String addCustomerForm(@ModelAttribute("customer") NewCustomerDto customer) {
        customerService.addCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping(value = "/register")
    public String displayCustomerRegistrationForm(Model model) {
        NewCustomerDto customer = new NewCustomerDto();
        customer.setAddress(new Address());
        model.addAttribute("customer", customer);
        return "customer/new";
    }

    @PostMapping(value = "/register")
    public String registerCustomer(Model model, @ModelAttribute("customer") NewCustomerDto customer) {
        customerService.addCustomer(customer);
        model.addAttribute("customerEmail", customer.getEmail());
        return "customer/register_new_customer_notification";
    }

    @GetMapping(value = {"/carRentalWebApp/customer/delete/{id}", "/customer/delete/{id}"})
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/carRentalWebApp/customer/list";
    }

    @GetMapping("customer/edit/{id}")
    public ModelAndView editCustomerForm(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("customer/edit");
        Customer customer = customerService.getCustomerById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping(value = {"/customer/update"})
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {

        Customer savedCustomer = customerService.updateCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping(value = "/car/dates", params = {"startDate", "endDate"})
    public String displayCarReservationDates(Model model, @RequestParam String startDate, @RequestParam String endDate) {
        model.addAttribute("reservationStartDate", startDate);
        model.addAttribute("reservationEndDate", endDate);
        return "customer/dates";
    }


    @GetMapping(value = "/car/all/list")
    public String displayLisfOfCars(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("count", cars.size());
        return "customer/all_car_list";
    }

    @GetMapping(value = "/car/dates/list", params = {"startDate", "endDate"})
    public String displayListOfCarsAvailable(Model model, @RequestParam String startDate, @RequestParam String endDate) {
        List<Car> cars = carService.getAllAvailableCars(startDate, endDate);

        model.addAttribute("reservationStartDate", startDate);
        model.addAttribute("reservationEndDate", endDate);
        model.addAttribute("cars", cars);
        model.addAttribute("count", cars.size());
        return "customer/filtered_car_list";
    }

    @GetMapping(value = "/car/reserve/{carId}", params = {"startDate", "endDate"})
    public String displayCarReservationForm(Model model
            , @PathVariable Long carId
            , @RequestParam String startDate
            , @RequestParam String endDate) {

        NewReservationDto newReservationDto = new NewReservationDto(carId, startDate, endDate);
        CarReservationDto reservationDto = carService.getReservationDto(carId, startDate, endDate);
        model.addAttribute("reservationDto", reservationDto);
        model.addAttribute("newReservationDto", newReservationDto);
        return "customer/place_reservation";
    }

    @PostMapping(value = "/car/reserve")
    public String addCarReservationForm(Model model, @ModelAttribute("newReservationDto") NewReservationDto newReservationDto) {
        // TODO add car reservation
        PlaceRerservationInfoDto rerservationInfoDto = reservationService.addNewReservation(newReservationDto);
        model.addAttribute("reservationInfoDto", rerservationInfoDto);
        return "customer/place_reservation_notification";
    }

    @GetMapping(value = "/car/reserve/list")
    public String displayCustomerReservationList(Model model) {
        List<CarReservation> carReservations = reservationService.getAllReservations();
        model.addAttribute("reservations", carReservations);
        model.addAttribute("count", carReservations.size());
        return "customer/reservation_list";
    }

}
