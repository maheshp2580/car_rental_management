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
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/login")
	public String getLoginPage(Model model,  HttpSession session, HttpServletRequest request)
	{	
		request.getSession().invalidate();
		User usermodel = new User();
		model.addAttribute("user", usermodel);
		return "home/login";
	}
	
	@PostMapping("/authenticateLogin")
	public String loginUser(@ModelAttribute("user") User user,RedirectAttributes attributes,HttpServletRequest request,HttpServletResponse response, Model model)
	{
		System.out.println("login**************************************** ");
		User  userModel = userService.authenticateUser(user);
		String username="";
		String useremail="";
		System.out.println("output=== "+userModel);
		if(userModel != null)
		{
			@SuppressWarnings("unchecked")
			List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
			if (messages == null) {
				messages = new ArrayList<>();
				request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
			}
			if(userModel.getUsertype().equals("customer")) {
				username=userModel.getEmail().split("@")[0].toString().toUpperCase();
				useremail=userModel.getEmail();
				messages.add(useremail);
				request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
				
				return "redirect:/user";
				
			}
			else {
				username=userModel.getEmail().split("@")[0].toString().toUpperCase();
				useremail=userModel.getEmail();
				messages.add(useremail);
				request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
				return "redirect:/admin";
			}
		}
		else {
			model.addAttribute("errormsg", "Login Failed. Invalid Credentials. Please try again.");
			return "home/error";
		}
		
		
	}

	
	@GetMapping("/forgotPassword")
	public String getForgotPasswordPage(Model model)
	{
		User usermodel = new User();
		model.addAttribute("user", usermodel);
		return "home/forgotpassword";
	}
	
	
	
	@PostMapping("/validateForgotPassword")
	public String validatePassword(@ModelAttribute("user") User user, @RequestParam("securityQuestion") String securityQuestion,
			 @RequestParam("securityAnswer") String securityAnswer,
			Model model,RedirectAttributes redirectAttrs)
	{
		System.out.println("forgot password**************************************** ");
		int output = userService.validatePassword(user, securityQuestion, securityAnswer);
		
		if(output == 1)
		{
			
			return "home/changepassword";
		}
		else if(output == 0) {
			model.addAttribute("errormsg", "Invalid User Email");
			return "home/error";
		}
		else if(output == 2) {
			model.addAttribute("errormsg", "Invalid Security Question or Answer");
			return "home/error";
		}
		else {
			model.addAttribute("errormsg", "Password cannot be changed. Invalid credentials.");
			return "home/error";
		}
		
		
	}
	
	@GetMapping("/changePassword")
	public String getChangePasswordPage(Model model)
	{
		User usermodel = new User();
		model.addAttribute("user", usermodel);
		return "home/changepassword";
	}
	
	@PostMapping("/saveNewPassword")
	public String saveNewPassword(@ModelAttribute("user") User user, HttpServletRequest request, @Param("confirmPassword") String confirmPassword, Model model)
	{
		if(confirmPassword.equals(user.getPassword())) {
			
			userService.saveNewPassword(user);
		}
		else {
			model.addAttribute("errormsg", "Passwords donot match");
			return "home/error";
		}
		System.out.println("save===usernew password");
		System.out.println("userModel#########"+user.toString());
		 request.getSession().invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
	
	
	@GetMapping("/resetPassword")
	public String resetPassword(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		User userdata = userService.findUser(messages.get(0));
		
		model.addAttribute("user", userdata);
		
		return "home/resetpassword";
		
		
		
	}
	
	@RequestMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
			model.addAttribute("user", userdata);
		
		
        model.addAttribute("sessionMessages", messages);
		
        return "user/profile";
    }
	
	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute("user") User user, Model model)
	{
		System.out.println("save===user");
		int output =userService.saveUser(user);
		if(output>0) {
			return "redirect:/profile";
		}
		
		else {
			model.addAttribute("errormsg", "Operation failed. Please try again");
			return "home/error";
		}
		
	}
	
	@PostMapping("/deleteProfile/{id}")
	public String deleteProfile(@PathVariable(name="id") Long id,HttpServletRequest request, Model model)
	{
		userService.deleteUser(id);
		 request.getSession().invalidate();
		 model.addAttribute("errormsg", "Your Account Deleted Successfully");
			return "home/error";
	}

}
