package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.modelclass.Cart;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.Food;
import com.project.modelclass.FoodCart;
import com.project.modelclass.OutOfStock;
import com.project.modelclass.Price;
import com.project.servicelayer.CustomerServiceLayer;

@Controller
public class BookingController {
	@Autowired
	CustomerDao customerdao;
    @Autowired
    CustomerServiceLayer customerservice;
    int sum=0;
    @Autowired
    AdminDao admindao;
	
	

	@RequestMapping(value="/booking/{food_id}/{food_item}/{username}/{customer_id}",method = RequestMethod.GET) 
	public ModelAndView book(@PathVariable String username,@PathVariable int customer_id,@PathVariable int food_id,@PathVariable String food_item,HttpServletRequest request,Model m){
		ModelAndView modelandview =new ModelAndView();
	
		//return customerservice.booking(sum,username,customer_id,request);
		
		int Initialsum=0;OutOfStock outofstocks=new OutOfStock();
		int id=customer_id;
		 int value=Integer.parseInt(request.getParameter("count"));
		 List<OutOfStock> outofstockList =customerdao.stockquantitychecker(food_id);
	  outofstocks=outofstockList.get(0);
	  int quantity=outofstocks.getQuantity();
	  int flag=customerdao.checker(quantity);
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
		  //customerdao.updatetablevalues(food_item,id,value); 
		  
		  if(flag==1) {
		  modelandview.setViewName("./outofstock.jsp");
				return modelandview;
			}
		  
		  
		  return modelandview;
	 }
	
	
	@RequestMapping(value="/detail/{username}/{customer_id}",method = RequestMethod.GET)
	public ModelAndView viewfood(Model m,@PathVariable String username,@PathVariable int customer_id) {
		return customerservice.viewFood(sum,username,customer_id);
	}
	
	
	
	
	@RequestMapping("/back")
	public String back() {
		return "redirect:/detail/{username}/{customer_id}";
	}
	
	
	@RequestMapping("/home")
	public String home() {
		return"redirect:/index";
	}
	  
	@RequestMapping(value="/summation/{sum}/{username}/{customer_id}",method = RequestMethod.GET)
	public ModelAndView summation(@PathVariable int customer_id,@PathVariable String username,@PathVariable int sum,HttpServletRequest request, Model m) {
		
		return customerservice.summationService(sum,username);
		
	}
	
	  @RequestMapping("/shipping/{username}") 
	  public ModelAndView address(HttpServletRequest request,@PathVariable String username) { 
		  sum=0;
		  return customerservice.shippingaddress(request,username);
		 
	  }
	  
	  
	  
	  
	  
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	

}
