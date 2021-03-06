package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class EmployeeProfileServlet extends HttpServlet{

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
		pw.write("<a href = \"viewProfile\">Return</a>");
		pw.close();
	}

}
