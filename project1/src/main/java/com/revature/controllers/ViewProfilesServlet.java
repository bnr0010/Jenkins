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
import com.revature.model.User;
import com.revature.services.UserService;

public class ViewProfilesServlet extends HttpServlet {
private static final long serialVersionUID = -8264970847423151011L;
	
	public ViewProfilesServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("username") != null) {
			req.getRequestDispatcher("AllProfiles.html").forward(req, res);
		}
		else {
			res.sendRedirect("login");
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		User user = new User();
		List<User> userList = new ArrayList<User>();
		userList = UserService.getAllUserDetails();
		user = UserService.logIn(session.getAttribute("username").toString());
		
		PrintWriter pw = res.getWriter();
		ObjectMapper obj = new ObjectMapper();
		String html = "";
		html += "<div class = \"container\">\n";
		html += "<h2>Employee Profiles</h2>\n";
		html += "<table class = \"table\">";
		html += "<thead>";
		html += "<tr>";
		html += "<th>Username</th>";
		html += "<th>Password</th>";
		html += "<th>First Name</th>";
		html += "<th>Last Name</th>";
		html += "<th>Email</th>";
		html += "</tr>";
		html += "</thead>";
		html += "<tbody>";
		for(int i = 0; i < userList.size(); i++) {
			html += "<tr>";
			html += "<td>"+ userList.get(i).getUsername() + "</td>";
			html += "<td> "+ userList.get(i).getPassword() + "</td>";
			html += "<td>"+ userList.get(i).getFirstName() + "</td>";
			html += "<td>"+ userList.get(i).getLastName() + "</td>";
			html += "<td>"+ userList.get(i).getEmail() + "</td>";
			html += "</tr>";
		}
		pw.println(html);
		pw.write("<a href = \"viewAllProfiles\">Return</a>");
		pw.close();
	}
}
