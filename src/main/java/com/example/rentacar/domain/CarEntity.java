package com.example.rentacar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CAR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CAR_TYPE")
    private CarTypeEntity carType;

    private String brand;
    private String model;
    @Column(name = "IS_ACTIVE")
    private boolean isActive = true;


}
