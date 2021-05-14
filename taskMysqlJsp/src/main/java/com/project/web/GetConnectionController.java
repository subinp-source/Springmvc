package com.project.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.web.dao.ConnectionDao;


/**
 * Servlet implementation class GetConnectionController
 */
public class GetConnectionController extends HttpServlet {
	
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ud=request.getParameter("uname");
		String pwd=request.getParameter("pass");
		
		
		ConnectionDao dao = new ConnectionDao();
		
		if(dao.getConnect(ud, pwd)) {
			
			
			RequestDispatcher rd =request.getRequestDispatcher("showData.jsp");
			rd.forward(request, response);
			
		}else {
			
			RequestDispatcher rd =request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			
		}
		
		
		
		
		
		
		
		
		
		
	}

	

}
