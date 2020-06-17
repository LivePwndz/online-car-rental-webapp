package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.service.serviceImp.ReservationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String displayHome() {
        return "home/index";
    }

    @GetMapping("/sec")
    public String displayHomeForCustomer(Principal principal, Model model) {
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedCustomerUIDisplayName());
        return "home/sec_index";
    }

    @PostMapping("/login/success")
    public String displayLoggedUserHomePage() {
        return "redirect:/customer";
    }


}
