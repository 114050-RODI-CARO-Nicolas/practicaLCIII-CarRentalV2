package com.example.rentacar.DTOs.Request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RentForCreationDTO {

    public Long carId;
    public String customer;
    public Integer rentedDays;
    public LocalDateTime startRent;


}
