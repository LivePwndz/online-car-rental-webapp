package miu.edu.cs.cs425.carRentalWebApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {

    @GetMapping
    public String displayHome() {
        return "admin/index";
    }

}
