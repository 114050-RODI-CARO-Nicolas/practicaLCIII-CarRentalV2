package com.example.rentacar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarType {
    private Long id;
    private BigDecimal price;
    private String type;
}
