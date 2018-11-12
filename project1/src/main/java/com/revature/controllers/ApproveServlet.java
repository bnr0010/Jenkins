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

import com.revature.model.Reimbursement;
import com.revature.services.ReimbursementService;

public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ApproveServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("username") != null) {
			req.getRequestDispatcher("pendingReimbursements.html").forward(req, res);
		}
		else {
			res.sendRedirect("login");
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		List<Reimbursement> rs = new ArrayList<Reimbursement>();
		rs = ReimbursementService.getPendingReimbursements();
		ReimbursementService.approveEmployeeReimbursement(rs.get(Integer.parseInt(req.getParameter("anumber"))));
		
		PrintWriter pw = res.getWriter();
		pw.write("<p> Reimbursement Approved </p>");
		pw.write("<a href = \"pendingReimbursements\">Return to Pending Reimbursements</a>");
		pw.close();
	}
}
