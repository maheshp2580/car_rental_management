package com.crm.app.service;

import java.util.List;
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

}
