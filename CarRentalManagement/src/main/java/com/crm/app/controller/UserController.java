package com.crm.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
import com.crm.app.model.Coupon;
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
	
	@GetMapping("/drivers")
	public String viewDrivers(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        
        List<Driver> driverList = adminService.getAllDrivers();
        
        model.addAttribute("drivers", driverList);

		return "user/drivers";
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
	
	@GetMapping("/myDriverBookings")
	public String myDriverBookings(@ModelAttribute("user") User user, Model model, HttpSession session)
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
        
        List<BookDriver> userDriverBookings = userService.getUserDriverBookings(userdata.getEmail());
        
        model.addAttribute("bookings", userDriverBookings);

		return "user/userdriverbookings";
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
	
	@GetMapping("/bookDriver/{id}")
	public String bookDriver(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
		Driver driver = userService.getDriverById(id);
		BookDriver bookdriver = new BookDriver(); 
		model.addAttribute("bookdriver", bookdriver);
		model.addAttribute("driverId", driver.getId());
		model.addAttribute("driverName",driver.getName());
		model.addAttribute("pricePerDay",driver.getPricePerDay());
		model.addAttribute("userEmail", userdata.getEmail());
		
        model.addAttribute("sessionMessages", messages);
		
		return "user/bookdriver";
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
	
	@PostMapping("/saveDriverBooking")
	public String saveDriverBooking(@ModelAttribute("bookdriver") BookDriver bookdriver, Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        
       
        
        bookdriver.setStatus("payment_pending");
        
        userService.saveDriverBooking(bookdriver);
        
        return "redirect:/makeDriverPayment";
	}
	
	
	
	
	@GetMapping("/makePayment")
	public String makePayment(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
		BookCar bookcar = userService.getUserBooking(userdata.getEmail());
		Payment payment = new Payment(); 
		model.addAttribute("payment", payment);
		model.addAttribute("bookId", bookcar.getId());
		model.addAttribute("amountPaid",bookcar.getTotalAmount());
		model.addAttribute("userEmail", userdata.getEmail());
		
		List<Coupon> couponList = adminService.getAllCoupons();
		
		List<String> couponCodes = couponList.stream().map(c -> c.getCouponCode()).collect(Collectors.toList());
		
		List<String> couponAmount = couponList.stream().map(c -> c.getAmount()).collect(Collectors.toList());
		
		model.addAttribute("couponCodes", couponCodes);
		model.addAttribute("couponAmount", couponAmount);
        model.addAttribute("sessionMessages", messages);
		
		return "user/payment";
	}
	
	@GetMapping("/makeDriverPayment")
	public String makeDriverPayment(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
		BookDriver bookdriver = userService.getUserDirverBooking(userdata.getEmail());
		Payment payment = new Payment(); 
		model.addAttribute("payment", payment);
		model.addAttribute("bookId", bookdriver.getId());
		model.addAttribute("amountPaid",bookdriver.getTotalAmount());
		model.addAttribute("userEmail", userdata.getEmail());
		List<Coupon> couponList = adminService.getAllCoupons();
		
		List<String> couponCodes = couponList.stream().map(c -> c.getCouponCode()).collect(Collectors.toList());
		
		List<String> couponAmount = couponList.stream().map(c -> c.getAmount()).collect(Collectors.toList());
		
		model.addAttribute("couponCodes", couponCodes);
		model.addAttribute("couponAmount", couponAmount);
        model.addAttribute("sessionMessages", messages);
		
		return "user/payment";
	}
	
	@PostMapping("/completePayment")
	public String completePayment(@ModelAttribute("payment") Payment payment, Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        
        payment.setType("car");
        
        userService.savePayment(payment);
        
        return "redirect:/user";
	}
	
	@PostMapping("/completeDriverPayment")
	public String completeDriverPayment(@ModelAttribute("payment") Payment payment, Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        
        
        
        payment.setType("driver");
        
        userService.saveDriverPayment(payment);
        
        return "redirect:/user";
	}
	
	@GetMapping("/addReview/{id}")
	public String addReview(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
		Rating rating = new Rating(); 
		model.addAttribute("rating", rating);
		model.addAttribute("driverId",id);
		
		model.addAttribute("userEmail", userdata.getEmail());
		
        model.addAttribute("sessionMessages", messages);
		
		return "user/addreview";
	}
	
	@GetMapping("/addFeedback/{id}")
	public String addFeedback(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
		Feedback feedback = new Feedback(); 
		model.addAttribute("feedback", feedback);
		model.addAttribute("carId", id);
		
		model.addAttribute("userEmail", userdata.getEmail());
		
        model.addAttribute("sessionMessages", messages);
		
		return "user/addfeedback";
	}
	
	@PostMapping("/saveReview")
	public String saveReview(@ModelAttribute("rating") Rating rating, Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        
    
        
        userService.saveReview(rating);
        
        return "redirect:/user";
	}
	
	@PostMapping("/saveFeedback")
	public String saveFeedback(@ModelAttribute("feedback") Feedback feedback, Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        
    
        
        userService.saveFeedback(feedback);
        
        return "redirect:/user";
	}
	
	
}
