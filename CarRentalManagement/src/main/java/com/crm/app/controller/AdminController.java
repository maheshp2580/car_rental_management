package com.crm.app.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.app.model.BookCar;
import com.crm.app.model.Car;
import com.crm.app.model.Driver;
import com.crm.app.model.User;
import com.crm.app.service.AdminService;


@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	

	@GetMapping("/admin")
	public String getAdminWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);

		return "admin/adminwelcome";
	}
	
	@GetMapping("/car")
	public String getCarPage(@ModelAttribute("car") Car car, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<Car> carList = adminService.getAllCar();
		
        model.addAttribute("sessionMessages", messages);
        model.addAttribute("cars", carList);

		return "admin/car";
	}
	
	@PostMapping("/saveCar")
	public String saveCar(@ModelAttribute("car") Car car, Model model, HttpSession session, @RequestParam("image") MultipartFile itemImage)
	{
			try {
				car.setPhoto(Base64.getEncoder().encodeToString(itemImage.getBytes()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			adminService.saveCar(car);
		
			return "redirect:/car";
		
	}
	
	@GetMapping("/editCar/{id}")
	public String editCar(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Car car = adminService.getCarById(id);
		model.addAttribute("car", car);
		
        model.addAttribute("sessionMessages", messages);
		
		return "admin/updatecar";
	}
	
	@PostMapping("/updateCar")
	public String updateCar(@ModelAttribute("car") Car car, Model model, HttpSession session, @RequestParam("image") MultipartFile itemImage)
	{
		System.out.println("menu updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
       
        
        if(!itemImage.getOriginalFilename().isEmpty()) {
        	try {
        		
				car.setPhoto(Base64.getEncoder().encodeToString(itemImage.getBytes()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
        }
        else {
        	Car m = adminService.getCarById(car.getId());
        	car.setPhoto(m.getPhoto());
        }
		
		adminService.updateCar(car);
		
			return "redirect:/car";
		
	}
	
	@PostMapping("/deleteCar/{id}")
	public String deleteCar(@PathVariable(name="id") Long id)
	{
		adminService.deleteCar(id);
		
		return "redirect:/car";
	}
	
	@GetMapping("/driver")
	public String getDriverPage(@ModelAttribute("driver") Driver driver, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<Driver> driverList = adminService.getAllDrivers();
		
        model.addAttribute("sessionMessages", messages);
        model.addAttribute("drivers", driverList);

		return "admin/driver";
	}
	
	@PostMapping("/saveDriver")
	public String saveDriver(@ModelAttribute("driver") Driver driver, Model model, HttpSession session, @RequestParam("image") MultipartFile driverImage)
	{
			try {
				driver.setPhoto(Base64.getEncoder().encodeToString(driverImage.getBytes()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			driver.setRating("0");
			
			adminService.saveDriver(driver);
		
			return "redirect:/driver";
		
	}
	
	@GetMapping("/editDriver/{id}")
	public String editDriver(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Driver driver = adminService.getDriverById(id);
		model.addAttribute("driver", driver);
		
        model.addAttribute("sessionMessages", messages);
		
		return "admin/updatedriver";
	}
	
	@PostMapping("/updateDriver")
	public String updateDriver(@ModelAttribute("driver") Driver driver, Model model, HttpSession session, @RequestParam("image") MultipartFile driverImage)
	{
		System.out.println("menu updated");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
       
        
        if(!driverImage.getOriginalFilename().isEmpty()) {
        	try {
        		
				driver.setPhoto(Base64.getEncoder().encodeToString(driverImage.getBytes()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
        }
        else {
        	Driver d = adminService.getDriverById(driver.getId());
        	driver.setPhoto(d.getPhoto());
        }
		
		adminService.updateDriver(driver);
		
			return "redirect:/driver";
		
	}
	
	@PostMapping("/deleteDriver/{id}")
	public String deleteDriver(@PathVariable(name="id") Long id)
	{
		adminService.deleteDriver(id);
		
		return "redirect:/driver";
	}
	
	@GetMapping("/carbookings")
	public String carbookings(@ModelAttribute("car") Car car, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<BookCar> carbookings = adminService.getAllCarBookings();
		
        model.addAttribute("sessionMessages", messages);
        model.addAttribute("carbookings", carbookings);

		return "admin/carbookings";
	}
	
	@GetMapping("/confirmCarBooking/{id}")
	public String confirmCarBooking(@PathVariable(name="id") Long id)
	{
		adminService.confirmCarBooking(id);
		
		return "redirect:/carbookings";
	}
	
}
