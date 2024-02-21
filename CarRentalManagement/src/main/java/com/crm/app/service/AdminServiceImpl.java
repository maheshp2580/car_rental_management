package com.crm.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.app.dao.CarRepo;
import com.crm.app.model.Car;



@Service
public class AdminServiceImpl implements AdminService{

	
	@Autowired
	private CarRepo carRepo;
	
	

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

	

}
