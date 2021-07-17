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
import com.project.modelclass.Admin;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.Food;
import com.project.modelclass.FoodCartAzheekal;
import com.project.modelclass.OrderDetails;
import com.project.modelclass.OutOfStock;
import com.project.modelclass.Price;

public class CustomerServiceLayer {

	@Autowired
	CustomerDao customerdao;
	
	@Autowired
	AdminDao admindao;
	
	
	
	public String register(HttpServletRequest request) throws SQLIntegrityConstraintViolationException,DuplicateKeyException{
		
		Customer customer = new Customer();
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		customer.setFirstname(request.getParameter("firstname"));
		customer.setLastname(request.getParameter("lastname"));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress(request.getParameter("address"));
		customer.setPhone(request.getParameter("phone"));
		customerdao.saveCustomerDetails(customer);
		
		return "success.jsp";
		
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
				//customerdao.emptyTable();
				customerdao.deleteTableFoodcartQuantityDosa();
				customerdao.deleteTableFoodcartSumDosa();
				customerdao.deleteTableFoodcartQuantityChapathi();
				customerdao.deleteTableFoodcartSumChapathi();
				customerdao.deleteTableFoodcartQuantityBeefRoast();
				customerdao.deleteTableFoodcartSumBeefRoast();
				modelAndView.addObject("customer",customer);
				modelAndView.addObject("username",customer.getUsername());
				modelAndView.addObject("customer_id",customer.getCustomer_id());
				//customerdao.emptycart();
				modelAndView.setViewName("MultipleRestaurant.jsp");
				return modelAndView;
			}
	    	
	    }else {
	    	
	    	if (customerList.isEmpty()) {
	    		admin=adminList.get(0);
               modelAndView.setViewName("admin.jsp");
				return modelAndView;
			} else {
				customer=customerList.get(0);
				customerdao.deleteTableFoodcartQuantityDosa();
				customerdao.deleteTableFoodcartSumDosa();
				customerdao.deleteTableFoodcartQuantityChapathi();
				customerdao.deleteTableFoodcartSumChapathi();
				customerdao.deleteTableFoodcartQuantityBeefRoast();
				customerdao.deleteTableFoodcartSumBeefRoast();
				modelAndView.addObject("customer",customer);
				modelAndView.setViewName("checkForUser.jsp");
				modelAndView.addObject("customerId", customer.getCustomer_id());
				modelAndView.addObject("admin", admin);
				modelAndView.addObject("customerName", customer.getFirstname());
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
		  modelandview.addObject("user",username);
		  modelandview.setViewName("/final.jsp");
	      return modelandview;
		
		
		
	}



	/*public ModelAndView booking(int sum, String username, int customer_id, HttpServletRequest request,int food_id,String food_item) {
		ModelAndView modelandview =new ModelAndView();
		int Initialsum=0;OutOfStock outofstocks=new OutOfStock();
		 int value=Integer.parseInt(request.getParameter("count"));
	  List<OutOfStock> outofstockList =customerdao.stockquantitychecker(food_id);
	  outofstocks=outofstockList.get(0);
	  int id = customer_id;
	  int quantity=outofstocks.getQuantity();
	  int flag=customerdao.checker(value,quantity);
	  customerdao.updateFood(value,food_id);
	  customerdao.updateFoodCart(value,food_item);
	  List<Price> accept=customerdao.price(food_id);
	  modelandview.addObject("username",username);
		modelandview.addObject("customer_id",customer_id);
	  for (Price price : accept) {
	   Initialsum=Initialsum+(price.getEach_price()*value);
	  }
	  customerdao.sumAdditionToCartTable(Initialsum,food_item);
	  sum=sum+Initialsum;
	  customerdao.updateorder(food_id,id,value);
	  modelandview.setViewName("redirect:http://localhost:8080/MavenLoginSpringMvc/detail/{username}/{customer_id}");
		  customerdao.updatetablevalues(food_item,id,value); 
		  if(flag==1) {
		  modelandview.setViewName("./outofstock.jsp");
				return modelandview;
			}
		  return modelandview;
	}*/
	
	
	
	
}	    

