package com.project.servicelayer;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.dao.CustomerDaoAlibabaRestaurant;
import com.project.dao.CustomerDaoAzheekalRestaurant;
import com.project.modelclass.Admin;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.Food;
import com.project.modelclass.FoodCartAzheekal;
import com.project.modelclass.FoodListOfRestaurant;
import com.project.modelclass.OrderDetails;
import com.project.modelclass.OutOfStock;
import com.project.modelclass.Price;

public class CustomerServiceLayer {

	@Autowired
	CustomerDao customerdao;
	@Autowired
	CustomerDaoAlibabaRestaurant customerdaoalibaba;
	@Autowired
	CustomerDaoAzheekalRestaurant customerdaoazheekal;
	@Autowired
	AdminDao admindao;
	
	
	
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView modelandview= new ModelAndView();
		Customer customer = new Customer();
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		customer.setFirstname(request.getParameter("firstname"));
		customer.setLastname(request.getParameter("lastname"));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress(request.getParameter("address"));
		customer.setPhone(request.getParameter("phone"));
		customerdao.saveCustomerDetails(customer);
		modelandview.setViewName("success.jsp");
		return modelandview;
		
	}
	
	
	
	public ModelAndView logging(String username, String password) {
		ModelAndView modelAndView=new ModelAndView();
        Admin admin=new Admin();
       Customer customer=new Customer();
       List<Admin> adminList=admindao.getAdminData(username, password); 
	List<Customer> customerList = customerdao.getCustomerData(username, password);
	    if(adminList.isEmpty()) {
	    	
	    	if (customerList.isEmpty()) {
				String message = "you are entered wrong details";
				modelAndView.setViewName("error.jsp");
				 modelAndView.addObject("message", message);
				return modelAndView;
			} else {
				
				customer=customerList.get(0);
				modelAndView.addObject("customer",customer);
				modelAndView.addObject("username",customer.getUsername());
				modelAndView.addObject("customer_id",customer.getCustomer_id());
				//customerdao.emptycart();
				modelAndView.setViewName("welcome.jsp");
				return modelAndView;
			}
	    	
	    }else {
	    	
	    	if (customerList.isEmpty()) {
	    		admin=adminList.get(0);
               modelAndView.setViewName("admin.jsp");
				return modelAndView;
			} else {
				customer=customerList.get(0);
				modelAndView.addObject("customer",customer);
				modelAndView.addObject("username",customer.getUsername());
				modelAndView.addObject("customer_id",customer.getCustomer_id());
				modelAndView.setViewName("checkForUser.jsp");
				return modelAndView;
			}
	    }	
		
	}
	
	
	



	public ModelAndView viewFood(int sum,String username,int customer_id) {
		ModelAndView modelandview = new ModelAndView();
		List<Food> Foodlist = admindao.getFoodDetails();
		modelandview.addObject("username",username);
		modelandview.addObject("customer_id",customer_id);
		modelandview.addObject("Foodlist",Foodlist);
		modelandview.setViewName("/restaurant.jsp");
		modelandview.addObject("sum",sum);
		return modelandview;
	}



	public ModelAndView summationService(int sum, String username) {
		ModelAndView modelandview =new ModelAndView();
		modelandview.addObject("name",username);
		modelandview.addObject("sum", sum);
		List<FoodCartAzheekal> Foodcart=customerdao.getFoodCartTable();
		modelandview.addObject("items",Foodcart);
		modelandview.setViewName("/sumDetails.jsp");
		return modelandview;
		
	}



	public ModelAndView shippingaddress(HttpServletRequest request, String username) {
		ModelAndView modelandview =new ModelAndView();
		 modelandview.addObject("trace",request.getParameter("address"));
		  modelandview.addObject("username",username);
		  modelandview.setViewName("/final.jsp");
	      return modelandview;
		
		
		
	}



	public ModelAndView FoodDetailsAlibabaService(HttpServletRequest request) {
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
		 ModelAndView modelandview=new ModelAndView();
			modelandview.setViewName("FoodDetailsOfAlibaba.jsp");
			modelandview.addObject("username", username);
			modelandview.addObject("customer_id", customer_id);
			return modelandview;
	}



	public ModelAndView shippingService(HttpServletRequest request) {
		ModelAndView modelandview =new ModelAndView();
		String username=request.getParameter("username");
		String address=request.getParameter("address");
		modelandview.addObject("username",username);
		modelandview.addObject("address",address);
		modelandview.setViewName("alibabaFinal.jsp");
		return modelandview;
	}



	public ModelAndView shippingazheekalservice(HttpServletRequest request) {
		ModelAndView modelandview =new ModelAndView();
		String username=request.getParameter("username");
		String address=request.getParameter("address");
		modelandview.addObject("username",username);
		modelandview.addObject("address",address);
		modelandview.setViewName("azheekalFinal.jsp");
		return modelandview;
	}



	public ModelAndView AzheekalRestaurantservice(HttpServletRequest request) {
		ModelAndView modelandview =new ModelAndView();
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
			modelandview.setViewName("FoodDetailsOfAzheekal.jsp");
			modelandview.addObject("username", username);
			modelandview.addObject("customer_id", customer_id);
			return modelandview;
	}



	public ModelAndView selectFoodOfAzheekalservice(HttpServletRequest request, int sum) {
		ModelAndView modelandview =new ModelAndView();
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
		String foodlist=request.getParameter("foodlist");
		List<FoodListOfRestaurant> listOfFood=customerdaoazheekal.LoadFoodDetails(foodlist);
		modelandview.addObject("sum",sum);
		modelandview.addObject("foodlist",foodlist);
		modelandview.addObject("listOfFood",listOfFood);
		modelandview.addObject("username",username);
		modelandview.addObject("customer_id",customer_id);
		modelandview.setViewName("azheekalrestaurant.jsp");
		return modelandview;
	}



	public ModelAndView selectFoodOfAlibabaservice(HttpServletRequest request, int sum) {
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
		ModelAndView modelandview=new ModelAndView();
		String foodlist=request.getParameter("foodlist");
		List<FoodListOfRestaurant> listOfFood=customerdaoalibaba.LoadFoodDetails(foodlist);
		modelandview.addObject("sum",sum);
		modelandview.addObject("foodlist",foodlist);
		modelandview.addObject("listOfFood",listOfFood);
		modelandview.addObject("username",username);
		modelandview.addObject("customer_id",customer_id);
		modelandview.setViewName("alibabarestaurant.jsp");
		return modelandview;
	}



	public ModelAndView GotoService(HttpServletRequest request) {
		ModelAndView modelandview=new ModelAndView();
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
		modelandview.addObject("username",username);
		modelandview.addObject("customer_id",customer_id);
		modelandview.setViewName("/welcome.jsp");
		return modelandview;
	}






	
}	    

