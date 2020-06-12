package miu.edu.cs.cs425.carRentalWebApp.controller;

import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CarController {
    CarService carService;
@Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }
    //Display the list of all cars
    @GetMapping(value = "/carRentalWebApp/car/list")
    public ModelAndView listCars() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Car> cars = carService.getAllCars();
        modelAndView.addObject("cars", cars);
        modelAndView.setViewName("car/list");
        return modelAndView;
    }
    //Display a form for new car registration
    @GetMapping(value = "/carRentalWebApp/car/new")
    public String displayNewCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "car/new";
    }
    @PostMapping(value = "/carRentalWebApp/car/new")
    public String addNewCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("car", car);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "car/new";
        }
        carService.addNewCar(car);
        return "redirect:/carRentalWebApp/car/list";
    }

    @GetMapping(value = {"/carRentalWebApp/car/edit/{carId}","/car/edit/{id}"})
    public String editCar(@PathVariable Long id, Model model) {
        Optional<Car> car = carService.findCarById(id);
        if (car != null) {
            model.addAttribute("car", car);
            return "car/edit";
        }
        return "car/list";
    }

}
