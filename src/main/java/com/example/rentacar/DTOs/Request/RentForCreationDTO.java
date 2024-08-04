package com.example.rentacar.DTOs.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class RentForCreationDTO {

    public Long carId;

    @NotBlank(message = "Ademas de not null, la longitud del nombre del cliente, si es trimmeado, tiene que ser mas grande que cero")
    @NotEmpty(message = "Ademas de not null, la longitud del nombre del cliente, tiene que ser mayor a cero")
    public String customer;
    @Positive
    public Integer rentedDays;
    public LocalDateTime startRent;


}
