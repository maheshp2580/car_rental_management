package com.crm.app.service;

import java.util.List;

import com.crm.app.model.Car;
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

}
