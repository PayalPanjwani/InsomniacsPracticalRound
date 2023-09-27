package com.insomniacs.loginregistrationapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {

	@Id
	@GeneratedValue
	private int id;
	
	@Size(message="Username should have atleast 2 characters", min=2)
	private String user_name;
	
	@Digits(message="Number should contain 10 digits", fraction = 0, integer = 10)
	private String mobile_no;
	
	@Email(message="Enter valid email id", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	
	@Size(message="Password should have atleast 5 characters", min=5)
	private String password;

	public User(int id, String user_name, String mobile_no, String email, String password) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.mobile_no = mobile_no;
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", mobile_no=" + mobile_no + ", email=" + email
				+ ", password=" + password + "]";
	}
	
}
