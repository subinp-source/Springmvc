package com.project.servicelayer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;

import com.project.dao.AdminDao;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.Food;
import com.project.modelclass.OrderDetails;

public class AdminServiceLayer {
	
	@Autowired
	AdminDao admindao;
	
	
	public String addfood(Model m) {
	
		List<Food> FoodDetailsList = admindao.getFoodDetails();
		m.addAttribute("FoodDetailsList",FoodDetailsList);
		return "addfood.jsp";
	}
	
	
public String changePrice(Model m) {
		
		List<Food> FoodList = admindao.getFoodDetails();
		m.addAttribute("list1",FoodList);
		return "pricechange.jsp";
	}


public String viewFood(Model m,int sum,List<Cartlisting> listing) {
	List<Food> FoodList = admindao.getFoodDetails();
	m.addAttribute("list",FoodList);
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
	
	

public String viewOrder(Model m) {
	List<OrderDetails> OrderFoodList = admindao.getOrderDetails();
	m.addAttribute("OrderFoodList",OrderFoodList);
	return "order.jsp";
}







	
	
}
