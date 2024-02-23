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


	

}
