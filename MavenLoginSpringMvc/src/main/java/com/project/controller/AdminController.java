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
		modelandview.setViewName("usermanagement.jsp");
		return modelandview;
	}
	
	@RequestMapping(value="/adminManagement")
	public ModelAndView adminManagement() {
		modelandview.setViewName("adminmanagement.jsp");
		return modelandview;
	}
	
	
	
	@RequestMapping(value="/ordinaryHotel")
	public ModelAndView ordinaryHotel() {
		modelandview.setViewName("ordinaryhotel.jsp");
		return modelandview;
	}
	
	
	
	@RequestMapping(value="/AlibabaRestaurantAdminManagement")
	public ModelAndView AlibabaRestaurantAdminManagement() {
		modelandview.setViewName("alibabaRestaurantAdminManagement.jsp");
		return modelandview;
	}
	
	
	@RequestMapping(value="/AzheekalRestaurantAdminManagement")
	public ModelAndView AzheekalRestaurantAdminManagement() {
		modelandview.setViewName("azheekalRestaurantAdminManagement.jsp");
		return modelandview;
	}
	
	
	
	
	
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
	
	@RequestMapping(value="/inputAdmin")
	public String addAdmin(HttpServletRequest request) {
		try {
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
	
	@RequestMapping(value="/userRegistrationByAdmin")
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
	
	
	@RequestMapping(value="/DeleteCustomer")
	public String DeleteCustomerByAdmin(Model m,HttpServletRequest request) {
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
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
	
	
	
	@RequestMapping(value="/UserExceptionByAdmin",method=RequestMethod.POST)
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
        
        
        
        @RequestMapping(value="/orderDetailsOfAlibabaRolls")
        public ModelAndView orderDetailsOfAlibabaRolls() {
        	modelandview.setViewName("OrderDetailRolls.jsp");
        	List<OrderDetails> OrderFoodList = admindao.getOrderDetailsRolls();
        	modelandview.addObject("OrderFoodListRolls",OrderFoodList);
        	return modelandview;
        }
        
        
        
        @RequestMapping(value="/orderDetailsOfAlibabaChowmein")
        public ModelAndView orderDetailsOfAlibabaChowmein() {
        	modelandview.setViewName("OrderDetailchowmein.jsp");
        	List<OrderDetails> OrderFoodList = admindao.getOrderDetailschowmein();
        	modelandview.addObject("OrderFoodListChowmein",OrderFoodList);
        	return modelandview;
        }
        
        
        
        
        @RequestMapping(value="/orderDetailsOfAzheekalSoup")
        public ModelAndView orderDetailsOfAzheekalSoup() {
        	modelandview.setViewName("orderDetailsOfAzheekalsoup.jsp");
        	List<OrderDetails> OrderFoodList = admindao.getOrderDetailssoup();
        	modelandview.addObject("OrderFoodListSoup",OrderFoodList);
        	return modelandview;
        }
        
        
        
        @RequestMapping(value="/orderDetailsOfAzheekalStartersDeepFry")
        public ModelAndView orderDetailsOfAzheekalstartersDeepFry() {
        	modelandview.setViewName("orderDetailsOfAzheekalstartersDeepFry.jsp");
        	List<OrderDetails> OrderFoodList = admindao.getOrderDetailsstartersdeepfry();
        	modelandview.addObject("OrderFoodListStartersdeepfry",OrderFoodList);
        	return modelandview;
        }
        
        
        @RequestMapping("/PriceChangeOfAlibabaRolls")
    	public ModelAndView PriceChangeOfAlibabaRolls() {
    		//return adminservice.changePrice(m);
        	
    		List<Food> RollsFoodList = admindao.getFoodDetailsRolls();
    		modelandview.addObject("RollsFoodList",RollsFoodList);
    		modelandview.setViewName("priceChangeOfAlibabaRolls.jsp");
    		return modelandview;
    	}
    	
    	
    	@RequestMapping("/pricechangeRolls/{food_id}")
    	public ModelAndView pricechangeRolls(@PathVariable int food_id, HttpServletRequest request) {
    		
    		int price = Integer.parseInt(request.getParameter("price"));
    		admindao.changepriceRolls(price,food_id);
    		modelandview.setViewName("redirect:/PriceChangeOfAlibabaRolls");
    		return modelandview;
    		
    	}
    	
        
    	  @RequestMapping("/PriceChangeOfAlibabaChowmein")
    	public ModelAndView PriceChangeOfAlibabaChowmein() {
    		//return adminservice.changePrice(m);
        	
    		List<Food> ChowmeinFoodList = admindao.getFoodDetailsChowmein();
    		modelandview.addObject("ChowmeinFoodList",ChowmeinFoodList);
    		modelandview.setViewName("priceChangeOfAlibabaChowmein.jsp");
    		return modelandview;
    	}
    	
    	
    	@RequestMapping("/pricechangeChowmein/{food_id}")
    	public ModelAndView pricechangeChowmein(@PathVariable int food_id, HttpServletRequest request) {
    		
    		int price = Integer.parseInt(request.getParameter("price"));
    		admindao.changepriceChowmein(price,food_id);
    		modelandview.setViewName("redirect:/PriceChangeOfAlibabaChowmein");
    		return modelandview;
    		
    	}
        
        
    	 @RequestMapping("/PriceChangeOfAzheekalSoup")
    	public ModelAndView PriceChangeOfAzheekalSoup() {
    		//return adminservice.changePrice(m);
        	
    		List<Food> SoupFoodList = admindao.getFoodDetailsSoup();
    		modelandview.addObject("SoupFoodList",SoupFoodList);
    		modelandview.setViewName("priceChangeOfAzheekalSoup.jsp");
    		return modelandview;
    	}
    	
    	
    	@RequestMapping("/pricechangeSoup/{food_id}")
    	public ModelAndView pricechangeSoup(@PathVariable int food_id, HttpServletRequest request) {
    		
    		int price = Integer.parseInt(request.getParameter("price"));
    		admindao.changepriceSoup(price,food_id);
    		modelandview.setViewName("redirect:/PriceChangeOfAzheekalSoup");
    		return modelandview;
    		
    	}
        
        
        
        
    	
        
        
    	 @RequestMapping("/PriceChangeOfAzheekalStartersDeepFry")
    	public ModelAndView PriceChangeOfAzheekalStartersDeepFry() {
    		//return adminservice.changePrice(m);
        	
    		List<Food> StartersdeepfryFoodList = admindao.getFoodDetailsStartersdeepfry();
    		modelandview.addObject("StartersdeepfryFoodList",StartersdeepfryFoodList);
    		modelandview.setViewName("priceChangeOfAzheekalStartersdeepfry.jsp");
    		return modelandview;
    	}
    	
    	
    	@RequestMapping("/pricechangeStartersdeepfry/{food_id}")
    	public ModelAndView pricechangeStartersdeepfry(@PathVariable int food_id, HttpServletRequest request) {
    		
    		int price = Integer.parseInt(request.getParameter("price"));
    		admindao.changepriceStartersdeepfry(price,food_id);
    		modelandview.setViewName("redirect:/PriceChangeOfAzheekalStartersDeepFry");
    		return modelandview;
    		
    	}
        
        
        
        
        
        
    	@RequestMapping("/AddFoodOfAlibabaRolls")
    	public ModelAndView AddFoodOfAlibabaRolls() {
    		//return adminservice.addfood(m);
    		modelandview.setViewName("RollsaddFood.jsp");
    		List<Food> RollsFoodDetailsList = admindao.getFoodDetailsRolls();
    		modelandview.addObject("RollsFoodDetailsList",RollsFoodDetailsList);
    		return modelandview;
    	}
        
        
        
    	  @RequestMapping("/updatingfoodRolls/{food_id}")
      	public ModelAndView updatefoodRolls(@PathVariable int food_id, HttpServletRequest request) {
      		int foodcount = Integer.parseInt(request.getParameter("count"));
      		modelandview.setViewName("redirect:/AddFoodOfAlibabaRolls");
      		admindao.updatefoodcountRolls(foodcount,food_id);
      		return modelandview;
      	}
        
        
    	  
        
        
        
    	 	@RequestMapping("/AddFoodOfAlibabaChowmein")
      	public ModelAndView AddFoodOfAlibabaChowmein() {
      		//return adminservice.addfood(m);
      		modelandview.setViewName("ChowmeinaddFood.jsp");
      		List<Food> ChowmeinFoodDetailsList = admindao.getFoodDetailsChowmein();
      		modelandview.addObject("ChowmeinFoodDetailsList",ChowmeinFoodDetailsList);
      		return modelandview;
      	}
          
          
          
      	  @RequestMapping("/updatingfoodChowmein/{food_id}")
        	public ModelAndView updatefoodchowmein(@PathVariable int food_id, HttpServletRequest request) {
        		int foodcount = Integer.parseInt(request.getParameter("count"));
        		modelandview.setViewName("redirect:/AddFoodOfAlibabaChowmein");
        		admindao.updatefoodcountChowmein(foodcount,food_id);
        		return modelandview;
        	}
          
       	@RequestMapping("/AddFoodOfAzheekalSoup")
      	public ModelAndView AddFoodOfAzheekalSoup() {
      		//return adminservice.addfood(m);
      		modelandview.setViewName("SoupaddFood.jsp");
      		List<Food> SoupFoodDetailsList = admindao.getFoodDetailsSoup();
      		modelandview.addObject("SoupFoodDetailsList",SoupFoodDetailsList);
      		return modelandview;
      	}
          
          
          
      	  @RequestMapping("/updatingfoodSoup/{food_id}")
        	public ModelAndView updatingfoodSoup(@PathVariable int food_id, HttpServletRequest request) {
        		int foodcount = Integer.parseInt(request.getParameter("count"));
        		modelandview.setViewName("redirect:/AddFoodOfAzheekalSoup");
        		admindao.updatefoodcountSoup(foodcount,food_id);
        		return modelandview;
        	}
    	  
    	  
      	@RequestMapping("/AddFoodOfAzheekalStartersDeepFry")
      	public ModelAndView AddFoodOfAzheekalStartersDeepFry() {
      		//return adminservice.addfood(m);
      		modelandview.setViewName("StartersdeepfryaddFood.jsp");
      		List<Food> StartersdeepfryFoodDetailsList = admindao.getFoodDetailsStartersdeepfry();
      		modelandview.addObject("StartersdeepfryFoodDetailsList",StartersdeepfryFoodDetailsList);
      		return modelandview;
      	}
          
          
          
      	  @RequestMapping("/updatingfoodStartersdeepfry/{food_id}")
        	public ModelAndView updatingfoodStartersdeepfry(@PathVariable int food_id, HttpServletRequest request) {
        		int foodcount = Integer.parseInt(request.getParameter("count"));
        		modelandview.setViewName("redirect:/AddFoodOfAzheekalStartersDeepFry");
        		admindao.updatefoodcountStartersdeepfry(foodcount,food_id);
        		return modelandview;
        	}
    	  
    	  
      	
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
        
        
        

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

