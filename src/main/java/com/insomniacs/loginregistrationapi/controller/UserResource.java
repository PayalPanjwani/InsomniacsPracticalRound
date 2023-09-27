package com.insomniacs.loginregistrationapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.insomniacs.loginregistrationapi.dao.UserDaoService;
import com.insomniacs.loginregistrationapi.entities.LoginDetails;
import com.insomniacs.loginregistrationapi.entities.User;
import com.insomniacs.loginregistrationapi.exceptions.UserAlreadyExistsException;
import com.insomniacs.loginregistrationapi.exceptions.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;
	}

	// GET/users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.findAll();
	}

	// GET/users/email/password
	@PostMapping("/login")
	public User login(@RequestBody LoginDetails loginDetails) {
		User user = service.loginUser(loginDetails.getEmail(), loginDetails.getPassword());

		if (user == null) {
			throw new UserNotFoundException("Incorrect password or user not found with email: " + loginDetails.getEmail());
		}
		return user;
	}

	// GET/users/email
	@GetMapping("/users/{email}")
	public User getUser(@PathVariable String email) {
		User user = service.findUser(email);

		if (user == null) {
			throw new UserNotFoundException("User not found with email: " + email);
		}
		return user;
	}

	// GET/users/email
	@PostMapping("/updatePassword")
	public User updatePassword(@RequestBody LoginDetails loginDetails) {
		User user = service.loginUser(loginDetails.getEmail(), loginDetails.getPassword());

		if (user == null) {
			throw new UserNotFoundException("Incorrect password or user not found with email: " + loginDetails.getEmail());
		}
		
		user = service.updatePassword(user, loginDetails.getNewpassword());
		return user;
	}

	// POST/users
	@PostMapping("/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.saveUser(user);

		if (savedUser == null) {
			throw new UserAlreadyExistsException("User already exists with email: " + user.getEmail());
		}

		return ResponseEntity.created(null).build();
	}
}
