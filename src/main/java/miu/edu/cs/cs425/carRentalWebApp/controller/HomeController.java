package miu.edu.cs.cs425.carRentalWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "/home"})
    public String displayHomePage(){
        return "index";
    }
}
