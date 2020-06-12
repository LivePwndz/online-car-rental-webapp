package miu.edu.cs.cs425.carRentalWebApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/car")
public class CarController {

    @GetMapping(value = "/list")
    public String displayLisfOfCars(){
        return "car/list";
    }

    @GetMapping(value = "/new")
    public String displayAddCarForm(Model model){
        return "car/new";
    }

}
