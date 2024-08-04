package com.example.rentacar.controllers;

import com.example.rentacar.DTOs.Request.RentForCreationDTO;
import com.example.rentacar.models.Car;
import com.example.rentacar.models.Rent;
import com.example.rentacar.services.ICarService;
import com.example.rentacar.services.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private ICarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars()
    {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }
    @PostMapping
    public ResponseEntity<Car> registerCar(@RequestParam Long carTypeId, @RequestParam String brand, @RequestParam String model)
    {
        Car car = carService.createCar(carTypeId, brand, model);
        return ResponseEntity.ok(car);
    }


    }



