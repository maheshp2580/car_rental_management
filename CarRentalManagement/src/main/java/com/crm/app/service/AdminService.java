package com.crm.app.service;

import java.util.List;

import com.crm.app.model.BookCar;
import com.crm.app.model.Car;




public interface AdminService {

	List<Car> getAllCar();

	int saveCar(Car car);

	int deleteCar(Long id);

	Car getCarById(Long id);

	int updateCar(Car car);

<<<<<<< Updated upstream
=======
	void saveDriver(Driver driver);

	Driver getDriverById(Long id);

	void updateDriver(Driver driver);

	void deleteDriver(Long id);

	List<Driver> getAllDrivers();

	List<BookCar> getAllCarBookings();

	void confirmCarBooking(Long id);

>>>>>>> Stashed changes

	



}
