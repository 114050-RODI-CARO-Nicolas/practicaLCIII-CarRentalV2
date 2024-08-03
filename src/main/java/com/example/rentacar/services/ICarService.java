package com.example.rentacar.services;
import com.example.rentacar.models.Car;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICarService {

    List<Car> getAllCars();
    Car createCar(Long carTypeId, String brand, String model);

    Car updateCar(Long carId, Long carTypeId, String brand, String model);

    Car deleteCar(Long carId);


}
