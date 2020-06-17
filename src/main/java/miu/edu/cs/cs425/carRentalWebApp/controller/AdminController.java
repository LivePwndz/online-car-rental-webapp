package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.service.serviceImp.ReservationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {

    @GetMapping
    public String displayHome(Model model) {
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedCustomerUIDisplayName());
        return "admin/index";
    }

}
