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

}
