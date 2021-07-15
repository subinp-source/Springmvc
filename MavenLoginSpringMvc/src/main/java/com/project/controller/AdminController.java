package com.project.controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.AdminDao;
import com.project.modelclass.Admin;
import com.project.modelclass.Customer;
import com.project.modelclass.OrderDetails;
import com.project.servicelayer.AdminServiceLayer;

@Controller
public class AdminController {
	@Autowired
	AdminDao admindao;
	@Autowired
	AdminServiceLayer adminservice;
	
	
	
	@RequestMapping(value="/addAdmin")
	public String addAdmins() {
		return "addadminpage.jsp";
	}
	
	
	@RequestMapping("/addfood")
	public String addfood(Model m) {
		return adminservice.addfood(m);
		/*List<Food> list1 = dao.getFoodDetails();
		m.addAttribute("list1",list1);
		return "addfood.jsp";*/
	}
	
	@RequestMapping(value="/inputAdmin",method=RequestMethod.POST)
	public String addAdmin(HttpServletRequest request) {
		try {
		/*String username = request.getParameter("usernameOfAdmin");
		String password = request.getParameter("passwordOfAdmin");
		admindao.insertAdminDetails(username,password);*/
		return adminservice.insertAdminservice(request);
		}
		catch(DuplicateKeyException duplicatekey) {
			duplicatekey.printStackTrace();
			return "adminExceptionByAdmin.jsp";
		}
		
	}

	
	
	@RequestMapping(value="/passcode")
	public String passcodeChecker(HttpServletRequest request) {
		int passcode=Integer.parseInt(request.getParameter("passcode"));
		//service.passcodeChecking(passcode);
		if(passcode==123) {
			return "/admin.jsp";
		}else {
			return "/error.jsp";
		}
		
	}
	
	@RequestMapping(value="/addUser")
	public String addUser() {
		return "Adduser.jsp";
	}
	
	@RequestMapping(value="/userRegistrationByAdmin",method=RequestMethod.POST)
	public String UserRegistrationByAdmin(HttpServletRequest request) {
		try {
		return adminservice.registerByUser(request);
		}catch(DuplicateKeyException key) {
			return "adminExceptionPageOfUser.jsp";
		}
	}
	
	
	@RequestMapping(value="/deleteUser")
	public String userDetails(Model m) {
		List<Customer> customerList=admindao.GetFullUserDetails();
		m.addAttribute("customerList",customerList);
		return "customerDetailsByAdmin.jsp";
	}
	
	
	@RequestMapping(value="/DeleteCustomer/{customer_id}")
	public String DeleteCustomerByAdmin(@PathVariable int customer_id,Model m) {
		admindao.deleteCustomer(customer_id);
		//m.addAttribute("customerList",customerList);
		return "/deleteUser";
	}
	
	
	
	@RequestMapping(value="/DeleteAdmin/{username}")
	public String DeleteCustomerByAdmin(@PathVariable String username,Model m) {
		admindao.deleteAdmin(username);
		//m.addAttribute("customerList",customerList);
		return "/deleteAdmin";
	}
	
	
	
	@RequestMapping(value="/UserExceptionByAdmin")
	public String UserExceptionByAdmin() {
		return "/admin.jsp";
	}
	
	
	@RequestMapping(value="/AdminExceptionByAdmin")
	public String AdminExceptionByAdmin() {
		return "/admin.jsp";
	}
	
	
	@RequestMapping(value="/deleteAdmin")
	public String deleteAdmin(Model m) {
		List<Admin> adminList=admindao.GetFullAdminDetails();
		m.addAttribute("adminList",adminList);
		return "adminDetailsByAdmin.jsp";
	}
	
	
	
	
	
	@RequestMapping("/changeprice")
	public String changeprice(Model m) {
		return adminservice.changePrice(m);
		/*List<Food> list2 = dao.getFoodDetails();
		m.addAttribute("list1",list2);
		return "pricechange.jsp";*/
	}
	
	
	@RequestMapping("/pricechange/{food_id}")
	public String pricechange(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
		
		int price = Integer.parseInt(request.getParameter("price"));
		admindao.changeprice(price,food_id);
		return "redirect:/changeprice";
		
	}
	
	
	

        @RequestMapping("/orderdetails")
    	public String vieworder(Model m) {
    		return adminservice.viewOrder(m);
    		/*List<OrderDetails> list = admindao.getOrderDetails();
    		m.addAttribute("list",list);
    		return "order.jsp";*/
    	}
        
        
        
        @RequestMapping("/updatingfood/{food_id}")
    	public String updatefood(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
    		int foodcount = Integer.parseInt(request.getParameter("count"));
    		admindao.updatefoodcount(foodcount,food_id);
    		return "redirect:/addfood";
    	}
        
        
        
        
        
        
        
        

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

