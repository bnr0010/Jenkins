package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {
	public boolean insertUserProcedure(User user);
	public User getUser(int userId);
	public List<User> getAllUsers();
	public User logInUser(String username);
	public boolean updateUser(User user);
}
