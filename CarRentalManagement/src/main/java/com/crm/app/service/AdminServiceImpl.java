package com.crm.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.app.dao.BookCarRepo;
import com.crm.app.dao.BookDriverRepo;
import com.crm.app.dao.CarRepo;
import com.crm.app.dao.DriverRepo;
import com.crm.app.model.BookCar;
import com.crm.app.model.BookDriver;
import com.crm.app.model.Car;
import com.crm.app.model.Driver;



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
	
	

	@Override
	public List<Car> getAllCar() {
		// TODO Auto-generated method stub
		return carRepo.findAll();
	}


	@Override
	public void saveDriver(Driver driver) {
		// TODO Auto-generated method stub
		driverRepo.save(driver);
	}

	@Override
	public Driver getDriverById(Long id) {
		// TODO Auto-generated method stub
		return driverRepo.getById(id);
	}


	

}
