package com.example.rentacar.repositories;

import com.example.rentacar.domain.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<RentEntity, Long> {

    List<RentEntity> findByCarId(Long carId);

}
