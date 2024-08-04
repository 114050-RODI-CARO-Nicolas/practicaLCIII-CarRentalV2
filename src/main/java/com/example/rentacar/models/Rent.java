package com.example.rentacar.models;

import com.example.rentacar.domain.CarEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    private Long id;
    private Car car;
    private String customer;
    private Integer rentedDays;
    private LocalDateTime startRent;
    private LocalDateTime endRent;
    private BigDecimal totalPrice;
}
