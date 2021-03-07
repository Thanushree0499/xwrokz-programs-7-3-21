package com.xworkz.botique;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePage extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("invoked servlet");
		
		String htmlContent="<html>"
				+ "<body>"
				+ "<h3>Women's online clothing store</h3>"
				+ "</body>"
				+ "</html>";
		
		PrintWriter out=resp.getWriter();
		out.write(htmlContent);
	}
	
	
}
