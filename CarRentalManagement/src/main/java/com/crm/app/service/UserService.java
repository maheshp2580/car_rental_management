package com.crm.app.service;

import java.util.List;

import com.crm.app.model.BookCar;
import com.crm.app.model.BookDriver;
import com.crm.app.model.Car;
import com.crm.app.model.Driver;
import com.crm.app.model.Feedback;
import com.crm.app.model.Payment;
import com.crm.app.model.Rating;
import com.crm.app.model.User;

public interface UserService {

	List<Car> getAllCar();

	User findUser(String email);

	User findUserByUsername(String username);

	int saveUser(User user);

	User authenticateUser(User user);

	int validatePassword(User user, String securityQuestion, String securityAnswer);

	void saveNewPassword(User user);

	void deleteUser(Long id);

	List<Car> filterCars(String company, String type, String seats);

	void saveCarBooking(BookCar bookcar);

	BookCar getUserBooking(String email);

	void savePayment(Payment payment);

	List<BookCar> getUserCarBookings(String email);

	List<Driver> filterDrivers(String experience, String rating, String price);

	Driver getDriverById(Long id);

	void saveDriverBooking(BookDriver bookdriver);

	BookDriver getUserDirverBooking(String email);

	List<BookDriver> getUserDriverBookings(String email);

	void saveReview(Rating rating);

	void saveFeedback(Feedback feedback);
	
	
	

}
