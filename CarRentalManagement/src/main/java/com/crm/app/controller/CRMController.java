package com.crm.app.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.app.model.Car;
import com.crm.app.model.User;
import com.crm.app.service.UserService;


@Controller
public class CRMController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String getHome(Model model) {
		
	      
		List<Car> carList = userService.getAllCar();
			
	        
	        model.addAttribute("cars", carList);
		return "index";
	}

	@GetMapping("/register")
	public String register(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "home/register";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user,Model model) {
		System.out.println("save===user");
		
	

		User existingUser = userService.findUser(user.getEmail());

		if (existingUser != null) {
			model.addAttribute("errormsg", "Email already exists");
			return "home/error";
		}

		User existingUsername = userService.findUserByUsername(user.getUsername());

		if (existingUsername != null) {
			model.addAttribute("errormsg", "Username already exists");
			return "home/error";
		}

		int output = userService.saveUser(user);
		
		if (output > 0) {
			return "redirect:/login";
		} else {
			model.addAttribute("errormsg", "Account creation failed");
			return "home/error";
		}
	}
	
	