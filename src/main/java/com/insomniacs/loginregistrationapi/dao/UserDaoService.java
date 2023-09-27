package com.insomniacs.loginregistrationapi.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.insomniacs.loginregistrationapi.entities.User;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;
	
	public List<User> findAll(){
		List<User> descUsers = new ArrayList<>(users);
		Collections.sort(descUsers, Comparator.comparingInt(User::getId).reversed());
		return descUsers;
	}
	
	public User findUser(String email) {
		Predicate<? super User> predicate1 = user -> user.getEmail().equals(email);
		User foundUser = users.stream().filter(predicate1).findFirst().orElse(null);
		return foundUser;
	}
	
	public User loginUser(String email, String password) {
		Predicate<? super User> predicate1 = user -> user.getEmail().equals(email);
		User foundUser = users.stream().filter(predicate1).findFirst().orElse(null);
		if(foundUser == null) return null;
		if(foundUser.getPassword().equals(password)) return foundUser;
		else return null;
	}
	
	public User saveUser(User user) {
		// check if user already exists
		Predicate<? super User> predicate = u -> u.getEmail().equals(user.getEmail());
		User foundUser = users.stream().filter(predicate).findFirst().orElse(null);
		
		if(foundUser!=null) return null;
		
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	public User updatePassword(User user, String password) {
		users.remove(user);
		user.setPassword(password);
		users.add(user);
		return user;
	}
}
