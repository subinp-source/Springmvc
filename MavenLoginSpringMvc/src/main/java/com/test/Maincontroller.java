/*package com.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.Customer;
import com.project.CustomerDao;

@Controller
public class Maincontroller {
	@Autowired
	CustomerDao dao;
	
	
	@RequestMapping("/login")
	public String loggin(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletRequest request,HttpServletResponse response) {
		
		
		List<Customer> list = dao.getData(username,password);
		
		if (list.isEmpty()) {
			
			return "error.jsp";
		}else {
			
			return "welcome.jsp";
		}
	}

}*/
