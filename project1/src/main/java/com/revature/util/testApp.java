package com.revature.util;
import com.revature.model.*;
import com.revature.services.*;

public class testApp {
	public static void main(String[] args) {
		String username = "BRoberts";
		String password = "password";
		
		System.out.println(username);
		System.out.println(password);
		
		User user = new User();
		
		user = UserService.logIn(username);
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.toString());
	}
}
