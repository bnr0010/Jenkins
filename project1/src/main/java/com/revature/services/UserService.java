package com.revature.services;

import java.util.List;

import com.revature.dao.UserImplementation;
import com.revature.model.User;

public class UserService {
	private static UserService userService;
	
	private UserService() {
		
	}
	
	public static UserService getUserService() {
		if(userService == null) {
			userService = new UserService();
		}
		return userService;
	}
	
	public static boolean createUser(User user) {
		return UserImplementation.getUserDao().insertUserProcedure(user);
	}
	
	public static User getUserDetails(int userId) {
		return UserImplementation.getUserDao().getUser(userId);
	}
	
	public static List<User> getAllUserDetails() {
		return UserImplementation.getUserDao().getAllUsers();
	}
	
	public static User logIn(String username) {
		return UserImplementation.getUserDao().logInUser(username);
	}
	
	public static boolean updateProfile(User user) {
		return UserImplementation.getUserDao().updateUser(user);
	}
}
