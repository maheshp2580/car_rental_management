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

	
	public int saveUser(User user) {
		user.setUsertype("customer");
		userRepo.save(user);
		
		if(userRepo.save(user)!=null) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public User findUser(String email) {
		List<User> user = userRepo.findAll();
		System.out.println("----"+user.size());
		if(user.size() == 0) {
			return null;
		}
		List<User> veifiedUser = user.stream().filter(n -> n.getEmail().equals(email) || n.getUsername().equals(email)).collect(Collectors.toList());
		if(veifiedUser.size() > 0) {
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
		
	}
	
	public User authenticateUser(User user) {
		
		if(user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin")) {
			
			user.setUsertype("admin");
			
			return user;
		}
		
		List<User> users = userRepo.findAll();
		List<User> veifiedUser = users.stream().filter(n -> (n.getEmail().equals(user.getEmail()) || n.getUsername().equals(user.getEmail())) && n.getPassword().equals(user.getPassword())).collect(Collectors.toList());
		
		if(veifiedUser.size() ==1) {
			
			
			return veifiedUser.get(0);
		}
		else {
				return null;
			
		}
			
	}
	
	public User findUserByUsername(String username) {
		
		List<User> users = userRepo.findAll();
		List<User> veifiedUser = users.stream().filter(n -> n.getUsername().equals(username)).collect(Collectors.toList());
		if(veifiedUser.size() > 0) {
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
		
	}
	
	public int validatePassword(User usermodel, String securityQuestion, String securityAnswer) {
		
		
		
		List<User> users = userRepo.findAll();
		List<User> verifiedUser = users.stream().filter(n -> n.getEmail().equals(usermodel.getEmail())).collect(Collectors.toList());
		if(verifiedUser.size() ==1) {
			List<User> userSecurities = userRepo.findAll();
			
			List<User> securedUser = userSecurities.stream().filter(security -> security.getSecurityQuestion().equals(securityQuestion) && security.getSecurityAnswer().equals(securityAnswer)
					
					).collect(Collectors.toList());
			if(securedUser.size() == 1) {
				return 1;
			}
			else {
				return 2;
			}
		}
		else {
			return 0;
		}
		
		
	}
	
	public void saveNewPassword(User usermodel) {
		
		
		
			User user = userRepo.findbyEmail(usermodel.getEmail());
			System.out.println("user#########"+user.toString());
			user.setPassword(usermodel.getPassword());
			userRepo.save(user);
			
		
	}
	
	public void deleteUser(Long id) {
			
			userRepo.deleteById(id);
			
	}


	@Override
	public List<Car> getAllCar() {
		// TODO Auto-generated method stub
		return carRepo.findAll();
	}

	
	@Override
	public List<Car> filterCars(String company, String type, String seats) {
		
		if(company.isEmpty() && type.isEmpty() && seats.isEmpty()) {
			return  carRepo.findAll();
		}
		
		List<Car> cars = carRepo.findAll();
		List<Car> filteredCars = new ArrayList<Car>();
		
		if(!company.isEmpty() && type.isEmpty() && seats.isEmpty()) {
			filteredCars = cars.stream().filter(car -> car.getCompany().equals(company)).collect(Collectors.toList());	
		}
		
		else if(company.isEmpty() && !type.isEmpty() && seats.isEmpty()) {
			filteredCars = cars.stream().filter(car -> car.getType().equals(type)).collect(Collectors.toList());	
		}
		
		else if(company.isEmpty() && type.isEmpty() && !seats.isEmpty()) {
			filteredCars = cars.stream().filter(car -> car.getNoOfSeats().equals(seats)).collect(Collectors.toList());	
		}
		
		else if(!company.isEmpty() && !type.isEmpty() && seats.isEmpty()) {
			filteredCars = cars.stream().filter(car -> car.getCompany().equals(company) && car.getType().equals(type)).collect(Collectors.toList());	
		}
		
		else if(company.isEmpty() && !type.isEmpty() && !seats.isEmpty()) {
			filteredCars = cars.stream().filter(car -> car.getNoOfSeats().equals(seats) && car.getType().equals(type)).collect(Collectors.toList());	
		}
		
		else if(!company.isEmpty() && type.isEmpty() && !seats.isEmpty()) {
			filteredCars = cars.stream().filter(car -> car.getCompany().equals(company) && car.getNoOfSeats().equals(seats)).collect(Collectors.toList());	
		}
		else {
			filteredCars = cars.stream().filter(car -> car.getCompany().equals(company) && car.getType().equals(type) && car.getNoOfSeats().equals(seats)).collect(Collectors.toList());	
		}
		
		 
		
		
		
		return filteredCars.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Car::getId))),
                ArrayList::new));
	}

	@Override
	public void saveCarBooking(BookCar bookcar) {
		// TODO Auto-generated method stub
		bookCarRepo.save(bookcar);
		
		
	}

	@Override
	public BookCar getUserBooking(String email) {
		// TODO Auto-generated method stub
		return bookCarRepo.findAll().stream().filter(bc -> bc.getUserEmail().equals(email) && bc.getStatus().equals("payment_pending")).collect(Collectors.toList()).get(0);
	}

	@Override
	public void savePayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.save(payment);
		BookCar bookCar = bookCarRepo.findBookCarById(Long.parseLong(payment.getBookingId()));
		bookCar.setStatus("payment_completed");
		bookCarRepo.save(bookCar);
		
	}

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

	@Override
	public void saveReview(Rating rating) {
		// TODO Auto-generated method stub
		ratingRepo.save(rating);
		
	}

	@Override
	public void saveFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		feedbackRepo.save(feedback);
	}
}
