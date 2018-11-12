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

public class UpdateProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("username") != null) {
			req.getRequestDispatcher("employeeInfo.html").forward(req, res);
		}
		else {
			res.sendRedirect("login");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		User user = new User();
		user = UserService.logIn(session.getAttribute("username").toString());
		
		if(!req.getParameter("username").equals("")) {
			user.setUsername(req.getParameter("username").toString());
		}
		if(!req.getParameter("password").equals("")) {
			user.setPassword(req.getParameter("password").toString());
		}
		if(!req.getParameter("email").equals("")) {
			user.setEmail(req.getParameter("email").toString());
		}
		
		UserService.updateProfile(user);
		
		PrintWriter pw = res.getWriter();
		ObjectMapper obj = new ObjectMapper();
		String html = "";
		html += "<div class = \"container\">\n";
		html += "<h1>Your Employee Profile</h1>\n";
		html += "<h2>Username: "+ user.getUsername() + "</h2>";
		html += "<h2>Password: "+ user.getPassword() + "</h2>";
		html += "<h2>First Name: "+ user.getFirstName() + "</h2>";
		html += "<h2>Last Name: "+ user.getLastName() + "</h2>";
		html += "<h2>Email: "+ user.getEmail() + "</h2>";
		pw.println(html);
		pw.write("<p> Profile Successfully Updated </p>");
		pw.write("<a href = \"viewProfile\">Return</a>");
		pw.close();
	}
}
