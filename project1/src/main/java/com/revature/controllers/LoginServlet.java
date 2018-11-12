package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.services.UserService;
import com.revature.model.Reimbursement;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8264970847423151011L;
	
	public LoginServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("LogIn.html").forward(req,  res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User user = new User();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		user = UserService.logIn(username);
		
		HttpSession sess = req.getSession();
		
		if(!user.getUsername().equals(username) || !user.getPassword().equals(password)) {
			System.out.println("error");
			res.sendRedirect("login");
		}
		else if(user.getIsManager() == 1) {
			res.sendRedirect("managerMenu");
			sess.setAttribute("username", username);
		}
		else {
			res.sendRedirect("employeeMenu");
			sess.setAttribute("username", username);
		}
	}
}
