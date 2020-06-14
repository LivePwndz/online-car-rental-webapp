package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.model.Address;
import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.model.Customer;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import miu.edu.cs.cs425.carRentalWebApp.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = {"/","/customer"})
public class CustomerController {
    private final CustomerService customerService;
    private final CarService carService;

    public CustomerController(CustomerService customerService, CarService carService) {
        this.customerService = customerService;
        this.carService = carService;
    }

    @GetMapping(value = {"/","/carRentalWebApp/customer","/customer"})
    public String displayCustomerHome(){

        return "customer/index";
    }

    @GetMapping(value = "/list")
    public String displayLisfOfCustomers(Model model){
        List<Customer> customer = customerService.getAllCustomers();
        model.addAttribute("customers", customer);
        model.addAttribute("customerCount",customer.size());
        return "customer/list";
    }

    @GetMapping(value = "/new")
    public String displayAddCustomerForm(Model model){
        Customer customer = new Customer();
        customer.setAddress(new Address());
        model.addAttribute("customer", customer);
        return "customer/new";
    }
    @PostMapping(value = "/new")
    public String addCustomerForm(@ModelAttribute("customer") Customer customer){
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        customerService.addCustomer(customer);
        return "redirect:/customer/list";
    }
@GetMapping(value = {"/carRentalWebApp/customer/delete/{id}", "/customer/delete/{id}"})
    public String deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomerById(id);
        return "redirect:/carRentalWebApp/customer/list";
    }
@GetMapping("customer/edit/{id}")
    public ModelAndView editCustomerForm(@PathVariable Long id){
      
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        Customer customer = customerService.getCustomerById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
}
@PostMapping(value = {"/customer/update"})
    public String updateCustomer(@ModelAttribute("customer") Customer customer){

       Customer savedCustomer= customerService.updateCustomer(customer);
       return "redirect:/customer/list";
}

    @GetMapping(value = "/car/dates")
    public String displayCarReservationDates(){
        return "customer/dates";
    }

    @GetMapping(value = "/car/all/list")
    public String displayLisfOfCars(Model model){
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("count", cars.size());
        model.addAttribute("counterHelper", new CounterHelper());
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
