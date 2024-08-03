package com.example.rentacar.services.impl;

import com.example.rentacar.domain.CarEntity;
import com.example.rentacar.domain.CarTypeEntity;
import com.example.rentacar.models.Car;
import com.example.rentacar.models.CarType;
import com.example.rentacar.repositories.CarRepository;
import com.example.rentacar.repositories.CarTypeRepository;
import com.example.rentacar.services.ICarService;
import jakarta.persistence.Table;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    CarTypeRepository carTypeRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<Car> getAllCars() {
        return null;
    }

    @Override
    @Transactional
    public Car createCar(Long carTypeId, String brand, String model) {
        CarTypeEntity foundCarTypeEntity = carTypeRepository.findById(carTypeId).orElse(null);
        if(foundCarTypeEntity==null) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "No car type found with the sent ID");
        }
        CarEntity carEntity = new CarEntity();
        carEntity.setCarTypeEntity(foundCarTypeEntity);
        carEntity.setBrand(brand);
        carEntity.setModel(model);
        return modelMapper.map(carRepository.save(carEntity), Car.class);

    }
}
