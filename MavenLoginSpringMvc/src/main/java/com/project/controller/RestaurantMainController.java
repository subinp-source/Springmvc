package com.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.CustomerDao;



@Controller
public class RestaurantMainController {

@Autowired
CustomerDao customerdao;
	
	@RequestMapping(value="/RestaurantName")
	public ModelAndView RestaurantName(HttpServletRequest request) {
		ModelAndView modelandview =new ModelAndView();
		int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
		String restaurant=request.getParameter("restaurantlist");
		
		if(restaurant.equals("Alibaba Restaurant")) {
			modelandview.addObject("username",username);
			modelandview.addObject("customer_id",customer_id);
			customerdao.deleteTableFoodcartQuantityEggmasala();
			customerdao.deleteTableFoodcartSumEggmasala();
			customerdao.deleteTableFoodcartQuantityChickennoodles();
			customerdao.deleteTableFoodcartSumChickennoodles();
			customerdao.deleteTableFoodcartQuantityEggchowmein();
			customerdao.deleteTableFoodcartSumEggchowmein();
			customerdao.deleteTableFoodcartQuantityChickenchowmein();
			customerdao.deleteTableFoodcartSumChickenchowmein();
			customerdao.deleteTableFoodcartQuantityPrawschowmein();
			customerdao.deleteTableFoodcartSumPrawschowmein();
			modelandview.setViewName("/AlibabaRestaurant");
			return modelandview;
			
		}else if(restaurant.equals("Azheekal Restaurant")){
			modelandview.addObject("username",username);
			modelandview.addObject("customer_id",customer_id);
			customerdao.deleteTableFoodcartQuantityTomatosoup();
			customerdao.deleteTableFoodcartSumTomatosoup();
			customerdao.deleteTableFoodcartQuantityCornsoup();
			customerdao.deleteTableFoodcartSumCornsoup();
			customerdao.deleteTableFoodcartQuantityCarrotsoup();
			customerdao.deleteTableFoodcartSumCarrotsoup();
			customerdao.deleteTableFoodcartQuantityFishfry();
			customerdao.deleteTableFoodcartSumFishfry();
			customerdao.deleteTableFoodcartQuantityOmlet();
			customerdao.deleteTableFoodcartSumCornOmlet();
			customerdao.deleteTableFoodcartQuantityEggpakoda();
			customerdao.deleteTableFoodcartSumEggpakoda();
			modelandview.setViewName("/AzheekalRestaurant");
			return modelandview;
			
		}else {
			modelandview.addObject("username",username);
			modelandview.addObject("customer_id",customer_id);
			customerdao.deleteTableFoodcartQuantityDosa();
			customerdao.deleteTableFoodcartSumDosa();
			customerdao.deleteTableFoodcartQuantityChapathi();
			customerdao.deleteTableFoodcartSumChapathi();
			customerdao.deleteTableFoodcartQuantityBeefRoast();
			customerdao.deleteTableFoodcartSumBeefRoast();
			modelandview.setViewName("/detail");
			return modelandview;
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}
