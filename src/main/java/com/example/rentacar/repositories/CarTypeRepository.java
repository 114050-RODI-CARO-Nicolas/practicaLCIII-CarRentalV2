package com.example.rentacar.repositories;

import com.example.rentacar.domain.CarTypeEntity;
import com.example.rentacar.models.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepository extends JpaRepository<CarTypeEntity, Long> {



}
