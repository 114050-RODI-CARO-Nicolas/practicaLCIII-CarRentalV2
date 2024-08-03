package com.example.rentacar.services.impl;

import com.example.rentacar.domain.CarEntity;
import com.example.rentacar.domain.CarTypeEntity;
import com.example.rentacar.models.Car;
import com.example.rentacar.models.CarType;
import com.example.rentacar.repositories.CarRepository;
import com.example.rentacar.repositories.CarTypeRepository;
import com.example.rentacar.services.ICarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarServiceImplTest {

    @MockBean
    CarRepository carRepository;

    @MockBean
    CarTypeRepository carTypeRepository;

    @SpyBean
    ICarService carService;


    @Test
    void createCar() {

        //Given parametros para insercion de nuevo auto
        Long carTypeIdParam = 2L;
        String brandParam = "Ford";
        String modelParam  = "Fiesta Kinetic";

        CarTypeEntity carTypeEntity = new CarTypeEntity(2L, BigDecimal.valueOf(500), "Hatchback");

        CarEntity carEntity = new CarEntity();
        carEntity.setCarTypeEntity(carTypeEntity);
        carEntity.setBrand(brandParam);
        carEntity.setModel(modelParam);


        when(carTypeRepository.findById(carTypeIdParam)).thenReturn(Optional.of(carTypeEntity));
        when(carRepository.save(Mockito.any(CarEntity.class))).thenReturn(carEntity);

        //Then

        Car returnCar = carService.createCar(carTypeIdParam, brandParam, modelParam);
        assertEquals("Hatchback", returnCar.getCarType().getType());
        assertEquals("Fiesta Kinetic", returnCar.getModel());

    }
}