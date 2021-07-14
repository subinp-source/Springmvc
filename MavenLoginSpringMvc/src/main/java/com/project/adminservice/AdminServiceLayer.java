package com.project.adminservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;

import com.project.dao.AdminDao;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.Food;

public class AdminServiceLayer {
	
	@Autowired
	AdminDao admindao;
	
	
	public String addfood(Model m) {
		try {
		List<Food> list1 = admindao.getFoodDetails();
		m.addAttribute("list1",list1);
		}
		catch(Exception e) { 
			System.out.println("hai");
		}return "addfood.jsp";
	}
	
	
public String changePrice(Model m) {
		
		List<Food> list2 = admindao.getFoodDetails();
		m.addAttribute("list1",list2);
		return "pricechange.jsp";
	}


public String viewFood(Model m,int sum,List<Cartlisting> listing) {
	List<Food> list = admindao.getFoodDetails();
	m.addAttribute("list",list);
	m.addAttribute("sum",sum);
	m.addAttribute("listing",listing);
	return "restaurant.jsp";
}
	
	
	
public String registerByUser(HttpServletRequest request) {
	
	Customer customer = new Customer();
	customer.setUsername(request.getParameter("username"));
	customer.setPassword(request.getParameter("password"));
	customer.setFirstname(request.getParameter("firstname"));
	customer.setLastname(request.getParameter("lastname"));
	customer.setEmail(request.getParameter("email"));
	customer.setAddress(request.getParameter("address"));
	customer.setPhone(request.getParameter("phone"));
	admindao.saveUserByAdmin(customer);
	
	
	return "/addUser";
}





public String insertAdminservice(HttpServletRequest request) {
	String username = request.getParameter("usernameOfAdmin");
	String password = request.getParameter("passwordOfAdmin");
	admindao.insertAdminDetails(username,password);
	return "admin.jsp";
}
	
	
	
	
}
