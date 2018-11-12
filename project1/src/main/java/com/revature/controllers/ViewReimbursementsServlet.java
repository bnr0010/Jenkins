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

public class ViewReimbursementsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ViewReimbursementsServlet() {
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("username") != null) {
			req.getRequestDispatcher("reimbursementHistory.html").forward(req, res);
		}
		else {
			res.sendRedirect("login");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		User user = new User();
		user = UserService.logIn(session.getAttribute("username").toString());
		List<Reimbursement> rs = new ArrayList<Reimbursement>();
		rs = ReimbursementService.getUserReimbursement(user.getUserId());
		
		PrintWriter pw = res.getWriter();
		ObjectMapper obj = new ObjectMapper();
		String html = "";
		html += "<div class = \"container\">\n";
		html += "<h1>Reimbursement History</h1>\n";
		html += "<table class = \"table\">\n";
		html += "<thead>\n";
		html += "<tr>";
		html += "<th><h2>Amount</h2></th>";
		html += "<th><h2>Type</h2></th>";
		html += "<th><h2>Status</h2></th>";
		html += "<th><h2>Date Submitted</h2></th>";
		html += "</tr>";
		html += "<tbody>";
		for(int i = 0; i < rs.size(); i ++) {
			html += "<tr>";
			html += "<td><h3>" + rs.get(i).getAmount()+ "</h3></td>";
			html += "<td><h3>" + rs.get(i).getType()+ "</h3></td>";
			html += "<td><h3>" + rs.get(i).getStatus()+ "</h3></td>";
			html += "<td><h3>" + rs.get(i).getDateSubmitted()+ "</h3></td>";
			html += "</tr>";
		}
		pw.println(html);
		pw.write("<a href = \"viewReimbursements\">Return</a>");
		pw.close();
	}

}
