package com.example.rentacar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    private Long id;
    private CarType carType;
    private String brand;
    private String model;
}