package com.example.rentacar.repositories;

import com.example.rentacar.domain.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<RentEntity, Long> {

}
