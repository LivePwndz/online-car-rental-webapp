package miu.edu.cs.cs425.carRentalWebApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/","/customer"})
public class CustomerController {

    @GetMapping(value = "")
    public String displayCustomerHome(){
        return "customer/index";
    }

    @GetMapping(value = "/list")
    public String displayLisfOfCustomers(){
        return "customer/list";
    }

    @GetMapping(value = "/new")
    public String displayAddCustomerForm(Model model){
        return "customer/new";
    }

    @GetMapping(value = "/car/dates")
    public String displayCarReservationDates(){
        return "customer/dates";
    }

    @GetMapping(value = "/car/all/list")
    public String displayLisfOfCars(){
        return "customer/all_car_list";
    }

    @GetMapping(value = "/car/dates/list")
    public String displayListOfCarsAvailable(){
        return "customer/filtered_car_list";
    }

    @GetMapping(value = "/car/reserve")
    public String displayCarReservationForm(){
        return "customer/place_reservation";
    }

    @GetMapping(value = "/car/reserve/list")
    public String displayCustomerReservationList(){
        return "customer/reservation_list";
    }

}
