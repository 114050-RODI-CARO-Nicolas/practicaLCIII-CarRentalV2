package com.example.rentacar.DTOs.Response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentCreationResponseDTO {

    public String carBrand;
    public String carModel;
    public String customer;
    public Integer rentedDays;
    public LocalDateTime startRent;
    public LocalDateTime endRent;
    public BigDecimal totalPrice;
}
