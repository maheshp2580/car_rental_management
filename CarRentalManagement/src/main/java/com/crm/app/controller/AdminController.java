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
import com.crm.app.model.BookDriver;
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
	
	@GetMapping("/confirmCarBooking/{id}")
	public String confirmCarBooking(@PathVariable(name="id") Long id)
	{
		adminService.confirmCarBooking(id);
		
		return "redirect:/carbookings";
	}
	
	@GetMapping("/driverbookings")
	public String driverbookings(@ModelAttribute("car") Car car, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<BookDriver> driverbookings = adminService.getAllDriverBookings();
		
        model.addAttribute("sessionMessages", messages);
        model.addAttribute("driverbookings", driverbookings);

		return "admin/driverbookings";
	}
	
	@GetMapping("/confirmDriverBooking/{id}")
	public String confirmDriverBooking(@PathVariable(name="id") Long id)
	{
		adminService.confirmDriverBooking(id);
		
		return "redirect:/driverbookings";
	}
}
