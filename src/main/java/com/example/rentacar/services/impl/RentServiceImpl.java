package com.example.rentacar.services.impl;

import com.example.rentacar.DTOs.Request.RentForCreationDTO;
import com.example.rentacar.DTOs.Response.RentCreationResponseDTO;
import com.example.rentacar.domain.CarEntity;
import com.example.rentacar.domain.RentEntity;
import com.example.rentacar.models.Rent;
import com.example.rentacar.repositories.CarRepository;
import com.example.rentacar.repositories.RentRepository;
import com.example.rentacar.services.IRentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RentServiceImpl implements IRentService {
    @Autowired
    RentRepository rentRepository;
    @Autowired
    CarRepository carRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public Rent createRent(RentForCreationDTO requestDTO) {

        CarEntity foundCarEntity = carRepository.findById(requestDTO.getCarId()).orElse(null);
        if(foundCarEntity==null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "No car was found with the provided ID");
        }
        else if (!foundCarEntity.isActive())
        {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "The requested car is no longer active");
        }
        List<RentEntity> existingRentEntities = foundCarEntity.getRentEntityList();


        if( checkIfCarCurrentylyRented(existingRentEntities) )
        {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Car already rented at the requested date");
        }


        RentEntity rentEntity = new RentEntity();
        rentEntity.setCarEntity(foundCarEntity);
        rentEntity.setCustomer(requestDTO.getCustomer());
        rentEntity.setRentedDays(requestDTO.getRentedDays());
        rentEntity.setStartRent(requestDTO.getStartRent());
        rentEntity.setEndRent(rentEntity.getStartRent().plusDays(requestDTO.getRentedDays()));
        BigDecimal totalPrice = foundCarEntity.getCarTypeEntity().getPrice().multiply( BigDecimal.valueOf(requestDTO.getRentedDays()));
        rentEntity.setTotalPrice(totalPrice);

        if(checkIfRequestedRentPeriodCollidesWithExistingRents(existingRentEntities, rentEntity.getStartRent(), rentEntity.getEndRent()))
        {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Car already rented at the requested date");
        }

        return modelMapper.map(rentRepository.save(rentEntity), Rent.class);

    }

    public List<Rent> getAllRents()
    {
        List<RentEntity> rentEntities = rentRepository.findAll();
        List<Rent> rents =  modelMapper.map(rentEntities, new TypeToken<List<Rent>>() {}.getType());
        return rents;
    }


    private static boolean checkIfCarCurrentylyRented(List<RentEntity> rentEntities)
    {
        if(rentEntities.isEmpty()){
            return false;
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        for(RentEntity rentEntity: rentEntities)
        {
            if(currentDateTime.isAfter(rentEntity.getStartRent()) && currentDateTime.isBefore(rentEntity.getEndRent()) )
            {
                return true;
            }
        }
        //At this currentDateTime , the car is not rented
        return false;
    }

    private boolean checkIfRentedAtParticularDate(List<RentEntity> rentEntities, LocalDateTime intendedStartDate)
    {
        if(rentEntities.isEmpty())
        {
            return false;
        }
        for(RentEntity rentEntity: rentEntities)
        {
            /*
            Se evalúa si la fecha pasada por parametro como fecha de inicio solicitada está entre medio de fechas de
            inicio y de fin de cada alquiler registrado para el auto
             */
            if(intendedStartDate.isAfter(rentEntity.getStartRent()) && intendedStartDate.isBefore(rentEntity.getEndRent()))
            {
                return true;
            }
        }
        //At this particular dateTime, the car is not rented
        return false;
    }



    private  boolean checkIfRequestedRentPeriodCollidesWithExistingRents(List<RentEntity> rentEntities, LocalDateTime requestedStartDate, LocalDateTime requestedEndDate )
    {
        if(rentEntities.isEmpty())
        {
            return false;
        }

    /*
    [
      { startDate: .....,
        endDate: .....

        },
        { startDate: .....,
            endDate: .....
        },
        ...
    ]
     */
        List<Map<String, LocalDateTime>> existingDatePeriods = new ArrayList<>();
        for(RentEntity existingRentEntity : rentEntities)
        {
            Map<String, LocalDateTime> dateTimeMap = new HashMap();
            dateTimeMap.put("startDate", existingRentEntity.getStartRent());
            dateTimeMap.put("endDate", existingRentEntity.getEndRent());
            existingDatePeriods.add(dateTimeMap);
        }

        for(Map<String, LocalDateTime> timePeriod : existingDatePeriods)
        {
            LocalDateTime beginningDate = timePeriod.get("startDate");
            LocalDateTime endDate = timePeriod.get("endDate");

            if(   !(requestedStartDate.isAfter(endDate) || requestedEndDate.isBefore(beginningDate) )       )
            {
                //Si la fecha de inicio solicitada NO es posterior a alguna fecha de finalizacion registrada
                // O
                // la fecha de fin solicitad NO es anterior a una fecha de inicio ya registrada
                //retornamos colisión
                return true;
            }
        }
        return false;
    }




};

