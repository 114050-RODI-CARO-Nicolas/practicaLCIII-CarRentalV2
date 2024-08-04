package com.example.rentacar.controllers;

import com.example.rentacar.DTOs.Request.RentForCreationDTO;
import com.example.rentacar.models.Rent;
import com.example.rentacar.services.IRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private IRentService rentService;

    @GetMapping
    public ResponseEntity<List<Rent>> getAllRents()
    {
        List<Rent> rents = rentService.getAllRents();
        return ResponseEntity.ok(rents);
    }
    @PostMapping
    public ResponseEntity<Rent> registerRent(@RequestBody RentForCreationDTO requestDTO)
    {
        Rent rent = rentService.createRent(requestDTO);
        return ResponseEntity.ok(rent);
    }



}
