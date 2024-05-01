package com.crm.app.Controller;

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
public class UserControllerTest {

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
    public void vewCars() {

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

        assertEquals(cars, userService.getAllCar());

    }
    
    @Test
    public void viewEmptyCars() {

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

        assertEquals(0, userService.getAllCar().size());

    }
    
    
   
    
        
    @Test
    public void viewDrivers() {

        Driver driver = new Driver();
        
        driver.setAge("33");
        driver.setGender("male");
        driver.setIsAvailable("yes");
        driver.setName("Ramesh");
        driver.setNoOfYearsExperience("5");
        driver.setPricePerDay("1000");
        
        
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);
        
        when(driverRepo.findAll()).thenReturn(drivers);

        assertEquals(drivers, adminService.getAllDrivers());

    }
    
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
    public void viewCarBookings() {

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
    
    @Test
    public void viewDriverBookings() {

        BookDriver bookDriver = new BookDriver();
        
        bookDriver.setDriverName("Driver");
        bookDriver.setDriverId("1L");
        bookDriver.setFromDate("1/1/2024");
        bookDriver.setToDate("3/1/2024");
        bookDriver.setPricePerDay("500");
        bookDriver.setTotalAmount("1500");
        bookDriver.setAddress("address");
        bookDriver.setUserEmail("user@gmail.com");
        
        
        List<BookDriver> bookdrivers = new ArrayList<>();
        bookdrivers.add(bookDriver);
        
        when(bookDriverRepo.findAll()).thenReturn(bookdrivers);

        assertEquals(bookdrivers, userService.getUserDriverBookings("user@gmail.com"));

    }
    
    @Test
    public void viewEmptyDriverBookings() {

        BookDriver bookDriver = new BookDriver();
        
        bookDriver.setDriverName("Driver");
        bookDriver.setDriverId("1L");
        bookDriver.setFromDate("1/1/2024");
        bookDriver.setToDate("3/1/2024");
        bookDriver.setPricePerDay("500");
        bookDriver.setTotalAmount("1500");
        bookDriver.setAddress("address");
        bookDriver.setUserEmail("user@gmail.com");
        
        
        List<BookDriver> bookdrivers = new ArrayList<>();
        
        
        when(bookDriverRepo.findAll()).thenReturn(bookdrivers);

        assertEquals(0, userService.getUserDriverBookings("user@gmail.com").size());

    }

    
    
   
    
    

}