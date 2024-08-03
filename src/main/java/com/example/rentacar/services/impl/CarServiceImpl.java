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
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    CarTypeRepository carTypeRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<Car> getAllCars()
    {
        List<CarEntity> carEntities = carRepository.findAll().stream().filter(CarEntity::isActive).collect(Collectors.toList());
        return modelMapper.map(carEntities, new TypeToken<List<Car>>() {}.getType());

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

    @Override
    public Car updateCar(Long carId, Long carTypeId, String brand, String model) {

        CarEntity foundCarEntity = carRepository.findById(carId).orElse(null);
        if(foundCarEntity==null){
            throw new  HttpClientErrorException(HttpStatusCode.valueOf(400), "No car  ound with the sent ID");
        };


        if(carTypeId != null && carTypeId != 0)
        {
            CarTypeEntity foundCarTypeEntity = carTypeRepository.findById(carTypeId).orElse(null);
            if(foundCarTypeEntity==null) {
                throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "No car type found with the sent ID");
            };
            foundCarEntity.setCarTypeEntity(foundCarTypeEntity);
        }


        if( brand!=null && ( !brand.isBlank() && !brand.isEmpty() )    )
        {
            foundCarEntity.setBrand(brand);
        }

        if( model != null && (!model.isBlank() && !model.isEmpty()) )
        {
            foundCarEntity.setModel(model);
        }
        return modelMapper.map(carRepository.save(foundCarEntity), Car.class);
    }

    @Override
    @Transactional
    public Car deleteCar(Long carId) {

        boolean hasBeenDeleted = false;
        CarEntity foundCarEntity = carRepository.findById(carId).orElse(null);
        if(foundCarEntity==null)
        {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "No car type found with the sent ID");
        };
        foundCarEntity.setActive(false);
        CarEntity result =   carRepository.save(foundCarEntity);
        if(!result.isActive())
        {
            hasBeenDeleted=true;
        }
        return modelMapper.map(result, Car.class);

    }


}
