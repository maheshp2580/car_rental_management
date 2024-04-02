package com.crm.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.app.model.BookCar;
import com.crm.app.model.BookDriver;
import com.crm.app.model.Car;
import com.crm.app.model.Driver;
import com.crm.app.model.Feedback;
import com.crm.app.model.Payment;
import com.crm.app.model.Rating;
import com.crm.app.model.User;
import com.crm.app.service.AdminService;
import com.crm.app.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	

	@GetMapping("/user")
	public String getUserWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        
        List<Car> carList = userService.getAllCar();
        
        model.addAttribute("cars", carList);

		return "user/userwelcome";
	}
	
	
	@GetMapping("/myCarBookings")
	public String userCarBookings(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
    	User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        
        List<BookCar> userCarBookings = userService.getUserCarBookings(userdata.getEmail());
        
        model.addAttribute("bookings", userCarBookings);

		return "user/usercarbookings";
	}
	

	@PostMapping("/applyFilters")
	public String applyFilters(Model model, HttpSession session, @RequestParam("company") String company,
			 @RequestParam("type") String type, @RequestParam("seats") String seats) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        List<Car> carList = userService.filterCars(company,type, seats);
        System.out.println("Length"+carList.size()+company+type+seats);
        model.addAttribute("cars", carList);
		return "user/userwelcome";
	}
	
	@PostMapping("/filterDrivers")
	public String filterDrivers(Model model, HttpSession session, @RequestParam("experience") String experience,
			 @RequestParam("rating") String rating, @RequestParam("price") String price) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        List<Driver> driverList = userService.filterDrivers(experience,rating, price);
        //System.out.println("Length"+driverList.size()+company+type+seats);
        model.addAttribute("drivers", driverList);
		return "user/drivers";
	}
	
	@GetMapping("/bookCar/{id}")
	public String editCar(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
		Car car = adminService.getCarById(id);
		BookCar bookcar = new BookCar(); 
		model.addAttribute("bookcar", bookcar);
		model.addAttribute("carId", car.getId());
		model.addAttribute("carNumberPlate",car.getNumberPlate());
		model.addAttribute("pricePerDay",car.getPricePerDay());
		model.addAttribute("userEmail", userdata.getEmail());
		
        model.addAttribute("sessionMessages", messages);
		
		return "user/bookcar";
	}
	
	
	@PostMapping("/saveCarBooking")
	public String saveCarBooking(@ModelAttribute("bookcar") BookCar bookcar, Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        
       
        
        bookcar.setStatus("payment_pending");
        
        userService.saveCarBooking(bookcar);
        
        return "redirect:/makePayment";
	}
	
	
}
	
	
	