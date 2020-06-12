package miu.edu.cs.cs425.carRentalWebApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/clerk")
public class ClerkController {

    @GetMapping(value = "/list")
    public String displayLisfOfCars(){
        return "clerk/list";
    }

    @GetMapping(value = "/new")
    public String displayAddCarForm(Model model){
        return "clerk/new";
    }

}
