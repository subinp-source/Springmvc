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
import com.project.modelclass.FoodCartAzheekal;
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
	
	

	@RequestMapping(value="/booking",method = RequestMethod.GET) 
	public ModelAndView book(HttpServletRequest request){
		ModelAndView modelandview =new ModelAndView();
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
		 int food_id=Integer.parseInt(request.getParameter("food_id"));
		 String food_item=request.getParameter("food_item");
		 modelandview.addObject("food_id", food_id);
		 modelandview.addObject("food_item", food_item);
		//return customerservice.booking(sum,username,customer_id,request);
		
		int Initialsum=0;OutOfStock outofstocks=new OutOfStock();
		int id=customer_id;
		 int value=Integer.parseInt(request.getParameter("count"));
		 List<OutOfStock> outofstockList =customerdao.stockquantitychecker(food_id);
	  outofstocks=outofstockList.get(0);
	  int quantity=outofstocks.getQuantity();
	  int flag=customerdao.checker(quantity,value);
	  if(flag==value) {
		  value=flag;
	  }else if(flag==1) {
		  modelandview.setViewName("/outofstock.jsp");
			return modelandview;
	  }else {
		  value=flag;
	  }
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
	  modelandview.setViewName("redirect:/detail");
		  //customerdao.updatetablevalues(food_item,id,value);
		  
		  return modelandview;
	 }
	
	
	@RequestMapping(value="/detail",method = RequestMethod.GET)
	public ModelAndView viewfood(HttpServletRequest request) {
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
		return customerservice.viewFood(sum,username,customer_id);
	}
	
	
	
	
	@RequestMapping("/back")
	public String back() {
		return "redirect:/detail";
	}
	
	
	@RequestMapping("/home")
	public String home() {
		return"redirect:/index";
	}
	  
	@RequestMapping(value="/summation",method = RequestMethod.GET)
	public ModelAndView summation(HttpServletRequest request) {
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
		 sum=Integer.parseInt(request.getParameter("sum"));
		return customerservice.summationService(sum,username);
		
	}
	
	  @RequestMapping("/shipping") 
	  public ModelAndView address(HttpServletRequest request) { 
		  sum=0;
		  String username=request.getParameter("username");
		  return customerservice.shippingaddress(request,username);
		 
	  }
	  
	  
	  
	  
	  
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	

}
