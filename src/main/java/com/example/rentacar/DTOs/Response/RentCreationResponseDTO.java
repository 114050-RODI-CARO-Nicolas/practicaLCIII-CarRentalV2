package com.example.rentacar.DTOs.Response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class RentCreationResponseDTO {

    public String carId;
    public String customer;
    public Integer rentedDays;
    public LocalDateTime startRent;
    public LocalDateTime endRent;
    public BigDecimal totalPrice;
}
