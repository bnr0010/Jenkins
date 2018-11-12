package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutSession extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	public LogoutSession() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		pw.write("<p> You have successfully logged out </p>");
		pw.write("<a href = \"login\">Return To Login</a>");
		pw.close();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
