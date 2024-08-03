package com.example.rentacar.repositories;

import com.example.rentacar.domain.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
