package com.crm.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String email;
	private String username;
	private String password;
	private String firstname;
	private String usertype;
	private String lastname;
	private String mobileNumber;
	private String doorNo;
	private String street;
	private String city;
	private String zipCode;
	private String securityQuestion;
	private String securityAnswer;
	

}
