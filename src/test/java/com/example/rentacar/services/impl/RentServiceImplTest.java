package com.example.rentacar.services.impl;

import com.example.rentacar.domain.CarEntity;
import com.example.rentacar.domain.CarTypeEntity;
import com.example.rentacar.domain.RentEntity;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.support.ReflectionSupport;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RentServiceImplTest {

    @Test
    void createRent() {
    }

    @Test
    void testCheckRequestedRentPeriodCollidesWithExistingRents()
    {

        /* El metodo obtiene una lista de rentEntities, y un periodo de alquiler de auto en dos fechas
         La idea es que si alguno de los periodos de alquileres registrados comprende en algun punto al periodo solicitado
        retornar un true */

        //Caso de prueba: solicitar un periodo de alquiler que colisiona

        //Given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        CarTypeEntity carTypeEntity = new CarTypeEntity(5L, BigDecimal.valueOf(200), "Hatchback");
        CarEntity carEntity = new CarEntity(2L, carTypeEntity, "Ford", "Fiesta Kinetic", true );
        RentEntity rentEntity1 = new RentEntity();
        RentEntity rentEntity2 = new RentEntity();

        String startDateTimeString = "2024-08-04 10:00:00";
        String endDateTimeString = "2024-08-07 10:00:00";
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, formatter);
        rentEntity1.setStartRent(startDateTime);
        rentEntity1.setEndRent(endDateTime);

        String startDateTimeString2 = "2024-08-09 10:00:00";
        String endDateTimeString2 = "2024-08-12 10:00:00";
        LocalDateTime startDateTime2 = LocalDateTime.parse(startDateTimeString2, formatter);
        LocalDateTime endDateTime2 = LocalDateTime.parse(endDateTimeString2, formatter);
        rentEntity2.setStartRent(startDateTime2);
        rentEntity2.setEndRent(endDateTime2);


        List<RentEntity> mockExistingRentEntities = new ArrayList<>();
        mockExistingRentEntities.add(rentEntity1);
        mockExistingRentEntities.add(rentEntity2);

        //El auto está alquilado entre el 04 y el 07 de agosto, y entre el 09 y el 12 de agosto

        //Va a venir una request para alquilarlo a partir del 08 de agosto hasta el 14 de agosto
        //El metodo debería retornar true


        String requestedStartDate = "2024-08-08 10:00:00";
        String requestedEndDate = "2024-08-14 10:00:00";
        LocalDateTime requestedStartDateParam = LocalDateTime.parse(requestedStartDate, formatter);
        LocalDateTime requestedEndDateParam = LocalDateTime.parse(requestedEndDate, formatter);


        //Then
        Optional<Method> methodOptional = ReflectionSupport.findMethod(RentServiceImpl.class, "checkIfRequestedRentPeriodCollidesWithExistingRents", List.class, List.class );
        if(methodOptional.isPresent())
        {
            Method method = methodOptional.get();
            boolean methodResult = (boolean)  ReflectionSupport.invokeMethod(method, mockExistingRentEntities, requestedStartDateParam, requestedEndDateParam);
            assertTrue(methodResult);
        }



    }



}