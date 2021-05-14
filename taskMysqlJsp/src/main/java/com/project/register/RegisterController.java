package com.project.register;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.register.dao.RegisterControllerDao;

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fn=request.getParameter("fn");
		String ud =request.getParameter("ud");
		String pwd=request.getParameter("pwd");
		String ads=request.getParameter("ads");
		String phr=request.getParameter("phr");
		
		RegisterControllerDao dao =new RegisterControllerDao();
		try {
			dao.insert(fn,ud,pwd,ads,phr);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}




}
