package com.service;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.dao.CustomerDao;
import com.modelclass.Cartlisting;
import com.modelclass.Customer;
import com.modelclass.Food;
import com.modelclass.OrderDetails;


public class ServiceClass {
	@Autowired
	CustomerDao dao;
	public String addfood(Model m) {
		try {
		List<Food> list1 = dao.getFoodDetails();
		m.addAttribute("list1",list1);
		}
		catch(Exception e) { 
			System.out.println("hai");
		}return "addfood.jsp";
	}
	
	public String changePrice(Model m) {
		
		List<Food> list2 = dao.getFoodDetails();
		m.addAttribute("list1",list2);
		return "pricechange.jsp";
	}

	public String viewOrder(Model m) {
		List<OrderDetails> list = dao.getOrderDetails();
		m.addAttribute("list",list);
		return "order.jsp";
	}

	public String viewFood(Model m,int sum,List<Cartlisting> listing) {
		List<Food> list = dao.getFoodDetails();
		m.addAttribute("list",list);
		m.addAttribute("sum",sum);
		m.addAttribute("listing",listing);
		return "restaurant.jsp";
	}
	
public ModelAndView login(HttpServletRequest request,Customer customer,int sum,List<Cartlisting> listing) {
	ModelAndView mav=new ModelAndView();
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	if (username.equals("subin123") && password.equals("0000")) {
		mav.setViewName("admin.jsp");
		return mav;
	}

	List<Customer> list = dao.getData(username, password);
	          customer=list.get(0);
	if (list.isEmpty()) {
		String message = "you are entered wrong details";
		mav.setViewName("error.jsp");
		 mav.addObject("message", message);
		return mav;
	} else {
		
		sum=0;
		dao.emptyTable();
		mav.addObject("message",customer.getFirstname());
		listing=null;dao.emptycart();mav.setViewName("welcome.jsp");
		return mav;
	}	
	}

	
	public String register(HttpServletRequest request) {
	
		Customer customer = new Customer();
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		customer.setFirstname(request.getParameter("firstname"));
		customer.setLastname(request.getParameter("lastname"));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress(request.getParameter("address"));
		customer.setPhone(request.getParameter("phone"));
		dao.saveEmployeeByPreparedStatement(customer);
		
		return "success.jsp";
		
	}

}
