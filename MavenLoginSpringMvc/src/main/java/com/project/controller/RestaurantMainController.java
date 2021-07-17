package com.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestaurantMainController {

	
	@RequestMapping(value="/RestaurantName")
	public ModelAndView RestaurantName(HttpServletRequest request) {
		ModelAndView modelandview =new ModelAndView();
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
		String restaurant=request.getParameter("restaurantlist");
		
		if(restaurant.equals("Alibaba Restaurant")) {
			modelandview.addObject("username",username);
			modelandview.addObject("customer_id",customer_id);

			modelandview.setViewName("/AlibabaRestaurant");
			return modelandview;
		}else if(restaurant.equals("Azheekal Restaurant")){
			modelandview.addObject("username",username);
			modelandview.addObject("customer_id",customer_id);

			modelandview.setViewName("/AzheekalRestaurant");
			return modelandview;
			
		}else {
			modelandview.addObject("username",username);
			modelandview.addObject("customer_id",customer_id);

			modelandview.setViewName("/detail");
			return modelandview;
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}
