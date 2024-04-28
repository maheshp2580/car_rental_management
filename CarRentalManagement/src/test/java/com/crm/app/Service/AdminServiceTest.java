package com.crm.app.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.crm.app.dao.CarRepo;
import com.crm.app.dao.DriverRepo;
import com.crm.app.model.Car;
import com.crm.app.model.Driver;
import com.crm.app.service.AdminServiceImpl;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private CarRepo carRepo;

    @Mock
    private DriverRepo driverRepo;

    
    @Test
    public void getAllCars() {

       Car car = new Car();
        
        car.setCompany("TATA");
        car.setDescription("A new SUV Car");
        car.setIsAvailable("yes");
        car.setModel("XT");
        car.setName("TATA SAFARI");
        car.setNoOfSeats("5");
        car.setPricePerDay("3000");
        car.setType("AUTO");
        
        
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        
        when(carRepo.findAll()).thenReturn(cars);

        assertEquals(cars, adminService.getAllCar());

    }
    
    @Test
    public void getEmptyCars() {

       Car car = new Car();
        
        car.setCompany("TATA");
        car.setDescription("A new SUV Car");
        car.setIsAvailable("yes");
        car.setModel("XT");
        car.setName("TATA SAFARI");
        car.setNoOfSeats("5");
        car.setPricePerDay("3000");
        car.setType("AUTO");
        
        
        List<Car> cars = new ArrayList<>();
        
        
        when(carRepo.findAll()).thenReturn(cars);

        assertEquals(0, adminService.getAllCar().size());

    }
    
    @Test
    public void checkCarList() {


        Car car = new Car();
         
         car.setCompany("TATA");
         car.setDescription("A new SUV Car");
         car.setIsAvailable("yes");
         car.setModel("XT");
         car.setName("TATA SAFARI");
         car.setNoOfSeats("5");
         car.setPricePerDay("3000");
         car.setType("AUTO");
         
         
         List<Car> cars = new ArrayList<>();
         
         when(carRepo.findAll()).thenReturn(cars);

         assertEquals(0, adminService.getAllCar().size());

    }
    
}