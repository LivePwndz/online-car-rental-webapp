package miu.edu.cs.cs425.carRentalWebApp.controller;


import miu.edu.cs.cs425.carRentalWebApp.model.Car;
import miu.edu.cs.cs425.carRentalWebApp.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/car")
public class CarController {
    private CarService carService;


    public  CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping(value = "/list")
    public String displayLisfOfCars(Model model){
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("carCount", cars.size());
        return "car/list";
    }

    @GetMapping(value = "/new")
    public String displayAddCarForm(Model model){
        model.addAttribute("car", new Car());
        return "car/new";
    }


    @PostMapping(value = "/new")
    public String addCarForm(@ModelAttribute("car") Car car){
        car.setCreateDate(LocalDateTime.now());
        car.setLastUpdate(LocalDateTime.now());
        carService.addNewCar(car);
        return "redirect:/car/list";
    }

    @GetMapping(value = "/edit/{carId}")
    public String displayEditCarForm(Model model, @PathVariable() Long carId ){
        Car car = carService.findCarById(carId);
        model.addAttribute("car", car);
        return "car/edit";
    }

    @PostMapping(value = "/edit/{carId}")
    public String editCarForm(@ModelAttribute("car") Car carUpdate, @PathVariable() Long carId){
        Car savedCar = carService.findCarById(carId);
        savedCar.setMaxNoDays(carUpdate.getMaxNoDays());
        savedCar.setModel(carUpdate.getModel());
        savedCar.setNuOfDoors(carUpdate.getNuOfDoors());
        savedCar.setNuOfSeats(carUpdate.getNuOfSeats());
        savedCar.setPricePerDay(carUpdate.getPricePerDay());
        savedCar.setTransmissionType(carUpdate.getTransmissionType());

        savedCar.setLastUpdate(LocalDateTime.now());
        carService.addNewCar(savedCar);
        return "redirect:/car/list";
    }

}
