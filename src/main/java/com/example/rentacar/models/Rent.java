package com.example.rentacar.models;

import com.example.rentacar.domain.CarEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Rent {
    private Long id;
    private Car car;
    private String customer;
    private Integer rentedDays;
    private LocalDateTime startRent;
    private LocalDateTime endRent;
    private BigDecimal totalPrice;
}
