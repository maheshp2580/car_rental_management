package com.crm.app.service;

import java.util.List;

import com.crm.app.model.Car;




public interface AdminService {

	List<Car> getAllCar();

	int saveCar(Car car);

	int deleteCar(Long id);

	Car getCarById(Long id);

	int updateCar(Car car);


	



}
