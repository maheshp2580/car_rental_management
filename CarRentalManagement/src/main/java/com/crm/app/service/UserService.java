package com.crm.app.service;

import java.util.List;

import com.crm.app.model.BookCar;
import com.crm.app.model.Car;
import com.crm.app.model.Payment;
import com.crm.app.model.User;

public interface UserService {

	List<Car> getAllCar();

	User findUser(String email);

	User findUserByUsername(String username);

	int saveUser(User user);

	User authenticateUser(User user);

	int validatePassword(User user, String securityQuestion, String securityAnswer);

	void saveNewPassword(User user);

<<<<<<< Updated upstream
=======
	void deleteUser(Long id);

	List<Car> filterCars(String company, String type, String seats);

	void saveCarBooking(BookCar bookcar);

	BookCar getUserBooking(String email);

	void savePayment(Payment payment);

	List<BookCar> getUserCarBookings(String email);
	

>>>>>>> Stashed changes
}
