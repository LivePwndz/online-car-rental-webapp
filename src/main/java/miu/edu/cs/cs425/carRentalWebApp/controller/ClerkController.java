package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import miu.edu.cs.cs425.carRentalWebApp.service.ClerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/clerk")
public class ClerkController {
    ClerkService clerkService;
@Autowired
    public ClerkController(ClerkService clerkService) {
        this.clerkService = clerkService;
    }

    @GetMapping(value = "/list")
    public String displayLisfOfClerks(Model model){
        List<Clerk> clerks = clerkService.getAllClerck();
        model.addAttribute("clerks", clerks);
        model.addAttribute("clerksCount", clerks.size());
        return "clerk/list";
    }

    @GetMapping(value = "/new")
    public String displayAddClerkForm(Model model){
        model.addAttribute("clerk", new Clerk());
        return "clerk/new";
    }

    @PostMapping(value = "/new")
    public String addClerkForm(@ModelAttribute("clerk") Clerk clerk){
        clerk.setCreateDate(LocalDateTime.now());
        clerk.setLastUpdate(LocalDateTime.now());
        clerkService.addNewClerk(clerk);
        return "redirect:/clerk/list";
    }
    @GetMapping(value = "/edit/{clerkId}")
    public String displayEditClerkForm(Model model, @PathVariable() Long clerkId ){
        Clerk clerk = clerkService.findClerkById(clerkId);
        model.addAttribute("clerk", clerk);
        return "clerk/edit";
    }
    @PostMapping(value = "/edit/{clerkId}")
    public String editClerkForm(@ModelAttribute("clerk") Clerk clerkUpdate, @PathVariable() Long clerkId){
        Clerk savedClerk = clerkService.findClerkById(clerkId);
        savedClerk.setFirstName(clerkUpdate.getFirstName());
        savedClerk.setMiddleName(clerkUpdate.getMiddleName());
        savedClerk.setLastUpdate(clerkUpdate.getLastUpdate());
        savedClerk.setEmail(clerkUpdate.getEmail());
        savedClerk.setLastUpdate(LocalDateTime.now());
        clerkService.addNewClerk(savedClerk);
        return "redirect:/clerk/list";
    }

}
