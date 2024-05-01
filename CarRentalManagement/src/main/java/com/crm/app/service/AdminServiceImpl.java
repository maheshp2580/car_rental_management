package com.crm.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.app.dao.BookCarRepo;
import com.crm.app.dao.BookDriverRepo;
import com.crm.app.dao.CarRepo;
import com.crm.app.dao.CouponRepo;
import com.crm.app.dao.DriverRepo;
import com.crm.app.dao.FeedbackRepo;
import com.crm.app.dao.RatingRepo;
import com.crm.app.model.BookCar;
import com.crm.app.model.BookDriver;
import com.crm.app.model.Car;
import com.crm.app.model.Coupon;
import com.crm.app.model.Driver;
import com.crm.app.model.Feedback;
import com.crm.app.model.Rating;



@Service
public class AdminServiceImpl implements AdminService{

	
	@Autowired
	private CarRepo carRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private BookCarRepo bookCarRepo;
	
	@Autowired
	private BookDriverRepo bookDriverRepo;
	
	@Autowired
	private FeedbackRepo feedbackRepo;
	
	@Autowired
	private RatingRepo ratingRepo;
	
	@Autowired
	private CouponRepo couponRepo;
	

	@Override
	public List<Car> getAllCar() {
		// TODO Auto-generated method stub
		return carRepo.findAll();
	}

	@Override
	public int saveCar(Car menu) {
		// TODO Auto-generated method stub
		Car men = carRepo.save(menu);
		if(men != null) {
			return 1;
		}
		else {
			return 0;
		}
		
		
	}

	@Override
	public int deleteCar(Long id) {
		// TODO Auto-generated method stub
		carRepo.deleteById(id);
		return 1;
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Car getCarById(Long id) {
		// TODO Auto-generated method stub
		return carRepo.getById(id);
	}

	@Override
	public int updateCar(Car car) {
		// TODO Auto-generated method stub
		Car men = carRepo.save(car);
		
		if(car != null) {
			return 1;
		}
		else {
			return 0;
		}
		
	}

	@Override
	public int saveDriver(Driver driver) {
		// TODO Auto-generated method stub
		if(driverRepo.save(driver) != null) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public Driver getDriverById(Long id) {
		// TODO Auto-generated method stub
		return driverRepo.getById(id);
	}

	@Override
	public int updateDriver(Driver driver) {
		// TODO Auto-generated method stub
		if(driverRepo.save(driver) != null) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public void deleteDriver(Long id) {
		// TODO Auto-generated method stub
		driverRepo.deleteById(id);
		
		
	}

	@Override
	public List<Driver> getAllDrivers() {
		// TODO Auto-generated method stub
		return driverRepo.findAll();
	}

	@Override
	public List<BookCar> getAllCarBookings() {
		// TODO Auto-generated method stub
		return bookCarRepo.findAll();
	}

	@Override
	public void confirmCarBooking(Long id) {
		// TODO Auto-generated method stub
		BookCar bookCar = bookCarRepo.findBookCarById(id);
		bookCar.setStatus("confirmed");
		bookCarRepo.save(bookCar);
	}

	@Override
	public List<BookDriver> getAllDriverBookings() {
		// TODO Auto-generated method stub
		return bookDriverRepo.findAll();
	}

	@Override
	public void confirmDriverBooking(Long id) {
		// TODO Auto-generated method stub
		BookDriver bookDriver = bookDriverRepo.findBookDriverById(id);
		bookDriver.setStatus("confirmed");
		bookDriverRepo.save(bookDriver);
	}

	@Override
	public List<Feedback> getFeedbacks() {
		// TODO Auto-generated method stub
		return feedbackRepo.findAll();
	}

	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingRepo.findAll();
	}

	@Override
	public List<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		return couponRepo.findAll();
	}

	@Override
	public void saveCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		couponRepo.save(coupon);
	}

	

}
