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
import com.project.modelclass.Food;
import com.project.modelclass.OrderDetails;
import com.project.servicelayer.AdminServiceLayer;

@Controller
public class AdminController {
	@Autowired
	AdminDao admindao;
	@Autowired
	AdminServiceLayer adminservice;
	ModelAndView modelandview =new ModelAndView();
	
	
	@RequestMapping(value="/userManagement")
	public ModelAndView userManagement() {
		
		return adminservice.usermanagementservice();
		
	}
	
	@RequestMapping(value="/adminManagement")
	public ModelAndView adminManagement() {
		
		return adminservice.adminManagementservice();
		
	}
	
	
	
	@RequestMapping(value="/ordinaryHotel")
	public ModelAndView ordinaryHotel() {
		
		return adminservice.ordinaryHotelservice();
		
	}
	
	
	
	@RequestMapping(value="/AlibabaRestaurantAdminManagement")
	public ModelAndView AlibabaRestaurantAdminManagement() {
		return adminservice.AlibabaRestaurantAdminManagementservice();
		
	}
	
	
	@RequestMapping(value="/AzheekalRestaurantAdminManagement")
	public ModelAndView AzheekalRestaurantAdminManagement() {
		return adminservice.AzheekalRestaurantAdminManagementservice();
		
	}
	
	
	
	
	
	@RequestMapping(value="/addAdmin")
	public String addAdmins() {
		return "addadminpage.jsp";
	}
	
	
	@RequestMapping("/addfood")
	public ModelAndView addfood() {
		return adminservice.addfoodservice();
	}
	
	@RequestMapping(value="/inputAdmin")
	public ModelAndView addAdmin(HttpServletRequest request) {
		try {
			
		return adminservice.insertAdminService(request);
		}
		catch(DuplicateKeyException duplicatekey) {
			duplicatekey.printStackTrace();
			modelandview.setViewName("adminExceptionByAdmin.jsp");
			return modelandview;
		}
		
	}

	
	
	@RequestMapping(value="/passcode")
	public ModelAndView passcodeChecker(HttpServletRequest request) {
		
		
		return adminservice.passcodeCheckerservice(request);
		
		
	}
	
	@RequestMapping(value="/addUser")
	public ModelAndView addUser() {
		
		return adminservice.addUserservice();
		
	}
	
	@RequestMapping(value="/userRegistrationByAdmin")
	public ModelAndView UserRegistrationByAdmin(HttpServletRequest request) {
		try {
			
		return adminservice.registerByUserservice(request);
		}catch(DuplicateKeyException key) {
			modelandview.setViewName("adminExceptionPageOfUser.jsp");
			return modelandview;
		}
	}
	
	
	@RequestMapping(value="/deleteUser")
	public ModelAndView userDetails() {
		
		return adminservice.userDetailsService();
		
	}
	
	
	@RequestMapping(value="/DeleteCustomer")
	public ModelAndView DeleteCustomerByAdmin(Model m,HttpServletRequest request) {
		
		return adminservice.DeleteCustomerservice(request);
		
		
	}
	
	
	
	@RequestMapping(value="/DeleteAdmin/{username}")
	public ModelAndView DeleteCustomerByAdmin(@PathVariable String username) {
		return adminservice.DeleteCustomerByAdminservice(username);
		
	}
	
	
	
	@RequestMapping(value="/UserExceptionByAdmin",method=RequestMethod.POST)
	public ModelAndView UserExceptionByAdmin() {
		return adminservice.UserExceptionByAdminservice();
		
	}
	
	
	@RequestMapping(value="/AdminExceptionByAdmin")
	public String AdminExceptionByAdmin() {
		return "/admin.jsp";
	}
	
	
	@RequestMapping(value="/deleteAdmin")
	public ModelAndView deleteAdmin() {
		return adminservice.deleteAdminservice();
		
	}
	
	
	
	
	
	@RequestMapping("/changeprice")
	public ModelAndView changeprice() {
		return adminservice.changePriceservice();
		/*List<Food> list2 = dao.getFoodDetails();
		m.addAttribute("list1",list2);
		return "pricechange.jsp";*/
	}
	
	
	@RequestMapping("/pricechange/{food_id}")
	public ModelAndView pricechange(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
		return adminservice.pricechangeservice(food_id,request);
	}
	
	
	

        @RequestMapping("/orderdetails")
    	public ModelAndView vieworder(Model m) {
    		return adminservice.viewOrderservice();
    		/*List<OrderDetails> list = admindao.getOrderDetails();
    		m.addAttribute("list",list);
    		return "order.jsp";*/
    	}
        
        
        
        @RequestMapping("/updatingfood/{food_id}")
    	public ModelAndView updatefood(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
    		return adminservice.updatefoodservice(request,food_id);
        	
        	
    	}
        
        
        
        @RequestMapping(value="/orderDetailsOfAlibabaRolls")
        public ModelAndView orderDetailsOfAlibabaRolls() {
        	
        	return adminservice.orderDetailsOfAlibabaRollsService();
        	
        	
        }
        
        
        
        @RequestMapping(value="/orderDetailsOfAlibabaChowmein")
        public ModelAndView orderDetailsOfAlibabaChowmein() {
        	
        	return adminservice.orderDetailsOfAlibabaChowmeinservice();
        	
        }
        
        
        
        
        @RequestMapping(value="/orderDetailsOfAzheekalSoup")
        public ModelAndView orderDetailsOfAzheekalSoup() {
        	
        	return adminservice.orderDetailsOfAzheekalSoupService();
        	
        	
        }
        
        
        
        @RequestMapping(value="/orderDetailsOfAzheekalStartersDeepFry")
        public ModelAndView orderDetailsOfAzheekalstartersDeepFry() {
        	
        	return adminservice.orderDetailsOfAzheekalstartersDeepFryservice();
        	
        }
        
        
        @RequestMapping("/PriceChangeOfAlibabaRolls")
    	public ModelAndView PriceChangeOfAlibabaRolls() {
        	return adminservice.PriceChangeOfAlibabaRollsservice();
    		
    	}
    	
    	
    	@RequestMapping("/pricechangeRolls/{food_id}")
    	public ModelAndView pricechangeRolls(@PathVariable int food_id, HttpServletRequest request) {
    		return adminservice.pricechangeRollsService(request,food_id);
    		
    		
    	}
    	
        
    	  @RequestMapping("/PriceChangeOfAlibabaChowmein")
    	public ModelAndView PriceChangeOfAlibabaChowmein() {
    		return adminservice.PriceChangeOfAlibabaChowmeinservice();
        	
    		
    	}
    	
    	
    	@RequestMapping("/pricechangeChowmein/{food_id}")
    	public ModelAndView pricechangeChowmein(@PathVariable int food_id, HttpServletRequest request) {
    		return adminservice.pricechangeChowmeinservice(request,food_id);
    	}
        
        
    	 @RequestMapping("/PriceChangeOfAzheekalSoup")
    	public ModelAndView PriceChangeOfAzheekalSoup() {
    		return adminservice.PriceChangeOfAzheekalSoupservice();
        	
    		
    	}
    	
    	
    	@RequestMapping("/pricechangeSoup/{food_id}")
    	public ModelAndView pricechangeSoup(@PathVariable int food_id, HttpServletRequest request) {
    		return adminservice.pricechangeSoup(request,food_id);
    		
    		
    	}
        
        
        
        
    	
        
        
    	 @RequestMapping("/PriceChangeOfAzheekalStartersDeepFry")
    	public ModelAndView PriceChangeOfAzheekalStartersDeepFry() {
    		return adminservice.PriceChangeOfAzheekalStartersDeepFry();
        	
    		
    	}
    	
    	
    	@RequestMapping("/pricechangeStartersdeepfry/{food_id}")
    	public ModelAndView pricechangeStartersdeepfry(@PathVariable int food_id, HttpServletRequest request) {
    		return adminservice.pricechangeStartersdeepfryservice(request,food_id);
    		
    		
    	}
        
        
        
        
        
        
    	@RequestMapping("/AddFoodOfAlibabaRolls")
    	public ModelAndView AddFoodOfAlibabaRolls() {
    		return adminservice.AddFoodOfAlibabaRolls();
    		
    	}
        
        
        
    	  @RequestMapping("/updatingfoodRolls/{food_id}")
      	public ModelAndView updatefoodRolls(@PathVariable int food_id, HttpServletRequest request) {
      		
    		  return adminservice.updatefoodRollsService(request,food_id);
    		  
      	}
        
        
    	  
        
        
        
    	 	@RequestMapping("/AddFoodOfAlibabaChowmein")
      	public ModelAndView AddFoodOfAlibabaChowmein() {
      		return adminservice.AddFoodOfAlibabaChowmeinservice();
      		
      	}
          
          
          
      	  @RequestMapping("/updatingfoodChowmein/{food_id}")
        	public ModelAndView updatefoodchowmein(@PathVariable int food_id, HttpServletRequest request) {
        		
      		  return adminservice.updatefoodchowmeinservice(request,food_id);
      		  
      		  
      		  
        	}
          
       	@RequestMapping("/AddFoodOfAzheekalSoup")
      	public ModelAndView AddFoodOfAzheekalSoup() {
      		return adminservice.AddFoodOfAzheekalSoupservice();
      		
      	}
          
          
          
      	  @RequestMapping("/updatingfoodSoup/{food_id}")
        	public ModelAndView updatingfoodSoup(@PathVariable int food_id, HttpServletRequest request) {
        		
      		  return adminservice.updatingfoodSoupservice(request,food_id);
      		  
        	}
    	  
    	  
      	@RequestMapping("/AddFoodOfAzheekalStartersDeepFry")
      	public ModelAndView AddFoodOfAzheekalStartersDeepFry() {
      		return adminservice.AddFoodOfAzheekalStartersDeepFryservice();
      		
      	}
          
          
          
      	  @RequestMapping("/updatingfoodStartersdeepfry/{food_id}")
        	public ModelAndView updatingfoodStartersdeepfry(@PathVariable int food_id, HttpServletRequest request) {
        		
      		  return adminservice.updatingfoodStartersdeepfryservice(request,food_id);
      		  
        	}
    	  
    	  
      	
   

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

