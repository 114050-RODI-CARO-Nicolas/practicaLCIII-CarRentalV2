package com.example.rentacar.DTOs.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentForCreationDTO {

    public Long carId;
    public String customer;
    public Integer rentedDays;
    public LocalDateTime startRent;


}
