package com.example.rentacar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "RENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CarEntity car;

    String customer;
    Integer rentedDays;

    LocalDateTime startRent;
    LocalDateTime endRent;

    BigDecimal totalPrice;



}
