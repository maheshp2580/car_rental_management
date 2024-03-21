package com.crm.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.app.dao.CarRepo;
import com.crm.app.dao.DriverRepo;
import com.crm.app.model.Car;
import com.crm.app.model.Driver;



@Service
public class AdminServiceImpl implements AdminService{

	
	@Autowired
	private CarRepo carRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	

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
	public void saveDriver(Driver driver) {
		// TODO Auto-generated method stub
		driverRepo.save(driver);
	}

	@Override
	public Driver getDriverById(Long id) {
		// TODO Auto-generated method stub
		return driverRepo.getById(id);
	}

	@Override
	public void updateDriver(Driver driver) {
		// TODO Auto-generated method stub
		driverRepo.save(driver);
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

	

}
