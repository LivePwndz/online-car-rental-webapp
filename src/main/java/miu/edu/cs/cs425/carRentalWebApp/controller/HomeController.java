package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.model.RoleName;
import miu.edu.cs.cs425.carRentalWebApp.service.serviceImp.ReservationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String displayHome(Model model) {
        return "home/index";
    }

    @GetMapping("/sec")
    public String displayHomeForCustomer(Principal principal, Model model) {
        model.addAttribute("displayName", ReservationUtils.getAuthenticatedUserUIDisplayName());
        return "home/sec_index";
    }

    @PostMapping("/login/success")
    public String displayLoggedUserHomePage() throws AccessDeniedException {
        RoleName roleName = ReservationUtils.getAuthenticatedUserRoleName();

        if(RoleName.CUSTOMER.equals(roleName)) {
            return "redirect:/customer";
        }else if(RoleName.ADMIN.equals(roleName)) {
            return "redirect:/admin";
        }else if(RoleName.CLERK.equals(roleName)) {
            return "redirect:/clerk";
        } else {
            throw new AccessDeniedException("Access denied. Role name "+roleName+" not known.");
        }
    }


}
