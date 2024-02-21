package com.crm.app.service;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.app.dao.BookCarRepo;
import com.crm.app.dao.BookDriverRepo;
import com.crm.app.dao.CarRepo;
import com.crm.app.dao.DriverRepo;
import com.crm.app.dao.FeedbackRepo;
import com.crm.app.dao.PaymentRepo;
import com.crm.app.dao.RatingRepo;
import com.crm.app.dao.UserRepo;
import com.crm.app.model.BookCar;
import com.crm.app.model.BookDriver;
import com.crm.app.model.Car;
import com.crm.app.model.Driver;
import com.crm.app.model.Feedback;
import com.crm.app.model.Payment;
import com.crm.app.model.Rating;
import com.crm.app.model.User;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CarRepo carRepo;
	
	@Autowired
	private BookCarRepo bookCarRepo;
	
	@Autowired
	private BookDriverRepo bookDriverRepo;
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private RatingRepo ratingRepo;
	@Autowired
	private FeedbackRepo feedbackRepo;

	
	

	@Override
	public List<BookCar> getUserCarBookings(String email) {
		// TODO Auto-generated method stub
		return bookCarRepo.findAll().stream().filter(bc -> bc.getUserEmail().equals(email)).collect(Collectors.toList());
		
	}

	@Override
	public List<Driver> filterDrivers(String experience, String rating, String price) {
		if(experience.isEmpty() && rating.isEmpty() && price.isEmpty()) {
			return  driverRepo.findAll();
		}
		
		
		List<Driver> drivers = driverRepo.findAll();
		List<Driver> filteredDrivers = new ArrayList<Driver>();
		
		if(!experience.isEmpty() && rating.isEmpty() && price.isEmpty()) {
			filteredDrivers = drivers.stream().filter(driver -> Integer.parseInt(driver.getNoOfYearsExperience()) >= Integer.parseInt(experience)).collect(Collectors.toList());	
		}
		
		else if(experience.isEmpty() && !rating.isEmpty() && price.isEmpty()) {
			filteredDrivers = drivers.stream().filter(driver -> Integer.parseInt(driver.getRating()) >= Integer.parseInt(rating) ).collect(Collectors.toList());	
		}
		
		else if(experience.isEmpty() && rating.isEmpty() && !price.isEmpty()) {
			filteredDrivers = drivers.stream().filter(driver -> Integer.parseInt(driver.getPricePerDay()) <= Integer.parseInt(price)).collect(Collectors.toList());	
		}
		
		else if(!experience.isEmpty() && !rating.isEmpty() && price.isEmpty()) {
			filteredDrivers = drivers.stream().filter(driver -> Integer.parseInt(driver.getNoOfYearsExperience()) >= Integer.parseInt(experience) && Integer.parseInt(driver.getRating()) >= Integer.parseInt(rating)).collect(Collectors.toList());	
		}
		
		else if(experience.isEmpty() && !rating.isEmpty() && !price.isEmpty()) {
			filteredDrivers = drivers.stream().filter(driver -> Integer.parseInt(driver.getRating()) >= Integer.parseInt(rating) && Integer.parseInt(driver.getPricePerDay()) <= Integer.parseInt(price)).collect(Collectors.toList());	
		}
		
		else if(!experience.isEmpty() && rating.isEmpty() && !price.isEmpty()) {
			filteredDrivers = drivers.stream().filter(driver -> Integer.parseInt(driver.getNoOfYearsExperience()) >= Integer.parseInt(experience) && Integer.parseInt(driver.getPricePerDay()) <= Integer.parseInt(price)).collect(Collectors.toList());	
		}
		else {
			filteredDrivers = drivers.stream().filter(driver -> Integer.parseInt(driver.getNoOfYearsExperience()) >= Integer.parseInt(experience) && Integer.parseInt(driver.getRating()) >= Integer.parseInt(rating) && Integer.parseInt(driver.getPricePerDay()) <= Integer.parseInt(price)).collect(Collectors.toList());	
		}
		
		 
		
		
		
		return filteredDrivers.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Driver::getId))),
                ArrayList::new));
	}

	@Override
	public Driver getDriverById(Long id) {
		// TODO Auto-generated method stub
		return driverRepo.getById(id);
		
	}

	@Override
	public void saveDriverBooking(BookDriver bookdriver) {
		// TODO Auto-generated method stub
		bookDriverRepo.save(bookdriver);
		
	}

	@Override
	public BookDriver getUserDirverBooking(String email) {
		// TODO Auto-generated method stub
		return bookDriverRepo.findAll().stream().filter(bc -> bc.getUserEmail().equals(email) && bc.getStatus().equals("payment_pending")).collect(Collectors.toList()).get(0);
	}

	@Override
	public List<BookDriver> getUserDriverBookings(String email) {
		// TODO Auto-generated method stub
		return bookDriverRepo.findAll().stream().filter(bc -> bc.getUserEmail().equals(email)).collect(Collectors.toList());
	}

}
