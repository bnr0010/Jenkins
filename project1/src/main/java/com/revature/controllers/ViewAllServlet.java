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

public class ViewAllServlet extends HttpServlet {
private static final long serialVersionUID = -8264970847423151011L;
	
	public ViewAllServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("username") != null) {
			req.getRequestDispatcher("AllReimbursements.html").forward(req, res);
		}
		else {
			res.sendRedirect("login");
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		User user = new User();
		List<Reimbursement> rs = new ArrayList<Reimbursement>();
		rs = ReimbursementService.getAllReimbs();
		user = UserService.logIn(session.getAttribute("username").toString());
		
		System.out.println(rs.size());
		
		PrintWriter pw = res.getWriter();
		ObjectMapper obj = new ObjectMapper();
		String html = "";
		html += "<div class = \"container\">\n";
		html += "<h2>Pending Reimbursements</h2>\n";
		html += "<table class = \"table\">";
		html += "<thead>";
		html += "<tr>";
		html += "<th>User ID</th>";
		html += "<th>Amount</th>";
		html += "<th>Type</th>";
		html += "<th>Status</th>";
		html += "<th>Date Submitted</th>";
		html += "</tr>";
		html += "</thead>";
		html += "<tbody>";
		for(int i = 0; i < rs.size(); i++) {
			html += "<tr>";
			html += "<td>"+ rs.get(i).getUserId() + "</td>";
			html += "<td> "+ rs.get(i).getAmount() + "</td>";
			html += "<td>"+ rs.get(i).getType() + "</td>";
			html += "<td>"+ rs.get(i).getStatus() + "</td>";
			html += "<td>"+ rs.get(i).getDateSubmitted() + "</td>";
			html += "</tr>";
		}
		pw.println(html);
		pw.write("<a href = \"viewAllReimbursements\">Return</a>");
		pw.close();
	}
}
