package com.xworkz.botique;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xworkz.botique.dao.UserDAOImpl;
import com.xworkz.botique.dto.UserDTO;

public class RegistrationPage extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("inside servlet");
		
		String dburl;
		String dbusername;
		String dbpassword;
		
		
		String firstNameStr=req.getParameter("fname");
		String lastNameStr=req.getParameter("lname");
		String emailStr=req.getParameter("email");
		String passwordStr=req.getParameter("pass");
		String confirmPasswordStr=req.getParameter("cpass");
		
		System.out.println("firstname entered is :"+firstNameStr);
		System.out.println("lastname entered is :"+lastNameStr);
		System.out.println("email entered is :"+emailStr);
		System.out.println("password entered is :"+passwordStr);
		System.out.println("confirmpassword entered is :"+confirmPasswordStr);
		
		
		UserDTO userDTO=new UserDTO(firstNameStr, lastNameStr, emailStr, passwordStr, confirmPasswordStr);
		UserDAOImpl userDAOImpl=new UserDAOImpl();
		
		if(userDAOImpl.checkIfUserExist(emailStr))
		 {
		System.out.println("done");
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("UserExists.html");
		requestDispatcher.forward(req, resp);
			
		}else {
			boolean res=userDAOImpl.insertRecord(userDTO);
			if(res) {
				System.out.println("Successfully Registered");
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/loginpageurl");
				requestDispatcher.forward(req, resp);
			
			}
			
		}
		
	}

}
