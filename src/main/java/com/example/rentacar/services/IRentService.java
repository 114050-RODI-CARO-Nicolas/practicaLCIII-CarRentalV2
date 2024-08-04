package com.example.rentacar.services;

import com.example.rentacar.DTOs.Request.RentForCreationDTO;
import com.example.rentacar.models.Rent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRentService {

    List<Rent> getAllRents();
    Rent createRent(RentForCreationDTO requestDTO);

}
