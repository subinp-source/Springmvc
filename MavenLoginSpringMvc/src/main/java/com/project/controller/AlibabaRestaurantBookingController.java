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

import com.project.dao.CustomerDaoAlibabaRestaurant;
import com.project.modelclass.FoodCartAzheekal;
import com.project.modelclass.FoodCartAlibaba;
import com.project.modelclass.FoodListOfRestaurant;
import com.project.modelclass.OutOfStock;
import com.project.modelclass.Price;
import com.project.servicelayer.CustomerServiceLayer;

@Controller
public class AlibabaRestaurantBookingController {
	@Autowired
	CustomerDaoAlibabaRestaurant customerdaoalibaba;
	@Autowired
	CustomerServiceLayer customerservice;
	int sum=0;
	
	
	 @RequestMapping(value="/AlibabaRestaurant")
	public ModelAndView FoodDetailsAlibaba(HttpServletRequest request) {
		
		 return customerservice.FoodDetailsAlibabaService(request);
			
		} 
	
	@RequestMapping(value="/SelectFoodOfAlibaba")
	public ModelAndView selectFoodOfAlibaba(HttpServletRequest request) {
		
		return customerservice.selectFoodOfAlibabaservice(request,sum);
		
		
	}
	
	@RequestMapping(value="/Alibabarestaurantbooking")
	public ModelAndView booking(HttpServletRequest request) {
		ModelAndView modelandview=new ModelAndView();
		int food_id=Integer.parseInt(request.getParameter("food_id"));
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		int food_price=Integer.parseInt(request.getParameter("food_price"));
		String food_item=request.getParameter("food_item");
		String username=request.getParameter("username");
		String foodlist=request.getParameter("foodlist");
		modelandview.addObject("customer_id",customer_id);
		modelandview.addObject("username",username);
		modelandview.addObject("foodlist",foodlist);
		int Initialsum=0;
		OutOfStock outofstocks=new OutOfStock();
		int id=customer_id;
		int value=Integer.parseInt(request.getParameter("count"));
		Initialsum=Initialsum+(food_price*value);
		if(foodlist.equals("rolls")) {
		customerdaoalibaba.updateFoodrolls(value,food_id);
		customerdaoalibaba.updateFoodCartrolls(value,food_id);
		 customerdaoalibaba.updateorderrolls(food_id,id,value);
		customerdaoalibaba.sumAdditionToCartTablerolls(Initialsum,food_id);
		}else {
			customerdaoalibaba.updateFoodchowmein(value,food_id);
			customerdaoalibaba.updateFoodCartchowmein(value,food_id);
			 customerdaoalibaba.sumAdditionToCartTablechowmein(Initialsum,food_item);
			 customerdaoalibaba.updateorderchowmein(food_id,id,value);
		}
		sum=sum+Initialsum;
		modelandview.setViewName("SelectFoodOfAlibaba");
		return modelandview;
	}
	
	
	@RequestMapping(value="/summationalibaba",method = RequestMethod.GET)
	public ModelAndView summation(HttpServletRequest request) {
		ModelAndView modelandview =new ModelAndView();
		String username=request.getParameter("username");
		 sum=Integer.parseInt(request.getParameter("sum"));
		 int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String foodlist=request.getParameter("foodlist");
		modelandview.addObject("username",username);
		modelandview.addObject("sum", sum);
		if(foodlist.equals("rolls")) {
			
			List<FoodCartAlibaba> Foodcart=customerdaoalibaba.getFoodCartTablealibabarolls();
			modelandview.addObject("fooditems",Foodcart);
			modelandview.setViewName("/alibabaSumDetails.jsp");
			
		}else {
			
			List<FoodCartAlibaba> Foodcart=customerdaoalibaba.getFoodCartTablealibabachowmein();
			modelandview.addObject("fooditems",Foodcart);
			modelandview.setViewName("/alibabaSumDetails.jsp");
		}
				
		return modelandview;
		
	}
	

	@RequestMapping(value="/shippingAlibaba")
	public ModelAndView shipping(HttpServletRequest request) {
		ModelAndView modelandview=new ModelAndView();
		sum=0;
		return customerservice.shippingService(request);
		
	}
	
	
	
	
	
	
	
	

}
