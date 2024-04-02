package com.crm.app.service;

import java.util.List;

import com.crm.app.model.BookCar;
import com.crm.app.model.BookDriver;
import com.crm.app.model.Car;
import com.crm.app.model.Driver;




public interface AdminService {

	List<Car> getAllCar();

	int saveCar(Car car);

	int deleteCar(Long id);

	Car getCarById(Long id);

	int updateCar(Car car);

	void saveDriver(Driver driver);

	Driver getDriverById(Long id);

	void updateDriver(Driver driver);
}
