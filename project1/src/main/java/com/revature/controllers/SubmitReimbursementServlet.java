package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class SubmitReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public SubmitReimbursementServlet() {
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("username") != null) {
			req.getRequestDispatcher("submitReimbursement.html").forward(req, res);
		}
		else {
			res.sendRedirect("login");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user = new User();
		
		user = UserService.logIn(session.getAttribute("username").toString());
		
		System.out.println(user.toString());
		
		String amount = req.getParameter("amount");
		String type = req.getParameter("type");
		
		System.out.println(amount);
		System.out.println(type);
		
		Reimbursement r = new Reimbursement(user.getUserId(), Integer.parseInt(amount), type, "Pending", "12-Nov-18");
		
		boolean submitted = ReimbursementService.newReimbursement(r);
		
		if(submitted) {
			res.sendRedirect("submitReimbursement");
		}
		else {
			System.out.println("error");
		}
	}

}
