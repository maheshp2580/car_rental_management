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

import com.crm.app.dao.BookCarRepo;
import com.crm.app.dao.BookDriverRepo;
import com.crm.app.dao.CarRepo;
import com.crm.app.dao.DriverRepo;
import com.crm.app.model.BookCar;
import com.crm.app.model.BookDriver;
import com.crm.app.model.Car;
import com.crm.app.model.Driver;
import com.crm.app.service.AdminServiceImpl;
import com.crm.app.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	 @InjectMocks
	    private UserServiceImpl userService;
	    
	    @InjectMocks
	    private AdminServiceImpl adminService;

	    @Mock
	    private CarRepo carRepo;

	    @Mock
	    private DriverRepo driverRepo;
	    

	    @Mock
	    private BookCarRepo bookCarRepo;
	    
	    @Mock
	    private BookDriverRepo bookDriverRepo;
	    
	    @Test
	    public void viewEmptyDrivers() {

	        Driver driver = new Driver();
	        
	        driver.setAge("33");
	        driver.setGender("male");
	        driver.setIsAvailable("yes");
	        driver.setName("Ramesh");
	        driver.setNoOfYearsExperience("5");
	        driver.setPricePerDay("1000");
	        
	        
	        List<Driver> drivers = new ArrayList<>();
	        
	        
	        when(driverRepo.findAll()).thenReturn(drivers);

	        assertEquals(0, adminService.getAllDrivers().size());

	    }
	    
	    @Test
	    public void getUserCarBookings() {

	        BookCar bookCar = new BookCar();
	        
	        bookCar.setCarNumberPlate("1111");
	        bookCar.setCarId("1L");
	        bookCar.setFromDate("1/1/2024");
	        bookCar.setToDate("3/1/2024");
	        bookCar.setPricePerDay("500");
	        bookCar.setTotalAmount("1500");
	        bookCar.setAddress("address");
	        bookCar.setUserEmail("user@gmail.com");
	        
	        
	        List<BookCar> bookcars = new ArrayList<>();
	        bookcars.add(bookCar);
	        
	        when(bookCarRepo.findAll()).thenReturn(bookcars);

	        assertEquals(bookcars, userService.getUserCarBookings("user@gmail.com"));

	    }
	    
	    @Test
	    public void viewEmptyCarBookings() {

	        BookCar bookCar = new BookCar();
	        
	        bookCar.setCarNumberPlate("1111");
	        bookCar.setCarId("1L");
	        bookCar.setFromDate("1/1/2024");
	        bookCar.setToDate("3/1/2024");
	        bookCar.setPricePerDay("500");
	        bookCar.setTotalAmount("1500");
	        bookCar.setAddress("address");
	        bookCar.setUserEmail("user@gmail.com");
	        
	        
	        List<BookCar> bookcars = new ArrayList<>();
	       
	        
	        when(bookCarRepo.findAll()).thenReturn(bookcars);

	        assertEquals(0, userService.getUserCarBookings("user@gmail.com").size());

	    }
	    
	   

}