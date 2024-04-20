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

	List<BookCar> getAllCarBookings();

	List<Feedback> getFeedbacks();

	List<Rating> getAllRatings();

	List<Coupon> getAllCoupons();

	void saveCoupon(Coupon coupon);

	List<BookDriver> getAllDriverBookings();

    void confirmCarBooking(Long id);

    List<Driver> getAllDrivers();


	



}
