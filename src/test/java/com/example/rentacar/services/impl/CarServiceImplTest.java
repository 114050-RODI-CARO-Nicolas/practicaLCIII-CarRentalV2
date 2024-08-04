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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Test
    void updateCar()
    {
        // Caso de prueba: modificar el tipo de auto de un auto, Sandero, pero estaba anotado como Hatchback y ahora lo actualizan como SUV

        //Given parametros para modificacion de auto existente
        Long carIdParam=25L;
        Long carTypeIdParam = 3L;

        CarTypeEntity previousCarTypeEntity = new CarTypeEntity(2L, BigDecimal.valueOf(500), "Hatchback");


        CarEntity existingCarEntity = new CarEntity();
        existingCarEntity.setCarTypeEntity(previousCarTypeEntity);
        existingCarEntity.setBrand("Renault");
        existingCarEntity.setModel("Sandero");

        CarTypeEntity newCarTypeEntity = new CarTypeEntity(3L, BigDecimal.valueOf(600), "SUV");
        existingCarEntity.setCarTypeEntity(newCarTypeEntity);



        when(carTypeRepository.findById(carTypeIdParam)).thenReturn(Optional.of(newCarTypeEntity));
        when(carRepository.findById(carIdParam)).thenReturn(Optional.of(existingCarEntity));
        when(carRepository.save(any(CarEntity.class))).thenReturn(existingCarEntity);

        //Then

        Car returnObject =   carService.updateCar(carIdParam, carTypeIdParam, null, null);
        assertEquals(3L, returnObject.getCarType().getId());
        assertEquals("SUV", returnObject.getCarType().getType());
        //Se valida que no se haya visto afectado el objeto existente en otras properties mas alla de la modificada
        assertEquals("Renault", returnObject.getBrand());


    }

    @Test
    void getAllCars() {

        //Given lista de CarEntity en el repositorio
        List<CarEntity> mockCarEntities = new ArrayList<>();
        CarTypeEntity carTypeEntity = new CarTypeEntity(1L, BigDecimal.valueOf(400), "Sedan");
        for (int i = 0; i < 6; i++)
        {

            CarEntity carEntity = new CarEntity();
            carEntity.setId((long)i+1);
            carEntity.setActive(true);
            carEntity.setCarTypeEntity(carTypeEntity);
            carEntity.setBrand("Brand " + i);
            carEntity.setModel("Mockmodel " + i);
            mockCarEntities.add(carEntity);
        }

        when(carRepository.findAll()).thenReturn(mockCarEntities);

        //Then

        List<Car> returnList = carService.getAllCars();

        assertNotNull(returnList);
        assertEquals(6, returnList.size());
        assertEquals("Sedan", returnList.get(0).getCarType().getType());
        //AssertAll de que todos estan en isActive

    }

    @Test
    void deleteCar() {

        //Se podría mejorar la rigurosidad del test si el metodo devolviera booleano;


        //Given un parametro para buscar auto a borrar

        Long carIdParam = 15L;

        //Given un auto encontrado por el repository con el parametro
        CarTypeEntity carTypeEntity = new CarTypeEntity(1L, BigDecimal.valueOf(400), "Sedan");
        CarEntity mockCarEntity = new CarEntity();
        CarEntity carEntity = new CarEntity();
        carEntity.setId(15L);
        carEntity.setActive(true);
        carEntity.setCarTypeEntity(carTypeEntity);
        carEntity.setBrand("Brand " );
        carEntity.setModel("Mockmodel ");


        //When
        when(carRepository.findById(carIdParam)).thenReturn(Optional.of(mockCarEntity));
        mockCarEntity.setActive(false);
        when(carRepository.save(any(CarEntity.class))).thenReturn(mockCarEntity);

        //Then
        Car resultObject = carService.deleteCar(carIdParam);
        assertFalse(resultObject.isActive());


    }
}