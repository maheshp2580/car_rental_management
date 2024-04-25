package com.crm.app.service;

import java.util.List;

import com.crm.app.model.BookCar;
import com.crm.app.model.BookDriver;
import com.crm.app.model.Car;
import com.crm.app.model.Coupon;
import com.crm.app.model.Driver;
import com.crm.app.model.Feedback;
import com.crm.app.model.Rating;




public interface AdminService {

	List<Car> getAllCar();

	int saveCar(Car car);

	int deleteCar(Long id);

	Car getCarById(Long id);

	int updateCar(Car car);

	int saveDriver(Driver driver);

	Driver getDriverById(Long id);

	void deleteDriver(Long id);

	List<Driver> getAllDrivers();

	List<BookCar> getAllCarBookings();

	void confirmCarBooking(Long id);

	List<BookDriver> getAllDriverBookings();

	void confirmDriverBooking(Long id);

	List<Feedback> getFeedbacks();

	List<Rating> getAllRatings();

	List<Coupon> getAllCoupons();

	void saveCoupon(Coupon coupon);


	



}
