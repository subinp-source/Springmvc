package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.CustomerDaoAzheekalRestaurant;
import com.project.modelclass.FoodCartAlibaba;
import com.project.modelclass.FoodCartAzheekal;
import com.project.modelclass.FoodListOfRestaurant;
import com.project.modelclass.OutOfStock;

@Controller
public class AzheekalRestaurantBookingController {

	@Autowired
	CustomerDaoAzheekalRestaurant customerdaoazheekal;
	ModelAndView modelandview =new ModelAndView();
	int sum=0;
	
	@RequestMapping(value="/AzheekalRestaurant")
	public ModelAndView AzheekalRestaurant(HttpServletRequest request) {
		
		 int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String username=request.getParameter("username");
			modelandview.setViewName("FoodDetailsOfAzheekal.jsp");
			modelandview.addObject("username", username);
			modelandview.addObject("customer_id", customer_id);
			return modelandview;
		
		
		
	}
	
	@RequestMapping(value="/SelectFoodOfAzheekal")
	public ModelAndView selectFoodOfAlibaba(HttpServletRequest request) {
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
	
	
	@RequestMapping(value="/Azheekalrestaurantbooking")
	public ModelAndView booking(HttpServletRequest request,Model model) {
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
		if(foodlist.equals("soup")) {
		customerdaoazheekal.updateFoodsoup(value,food_id);
		customerdaoazheekal.updateFoodCartsoup(value,food_id);
		customerdaoazheekal.updateordersoup(food_id,id,value);
		customerdaoazheekal.sumAdditionToCartTablesoup(Initialsum,food_item);
		}else {
			customerdaoazheekal.updateFoodstartersdeepfry(value,food_id);
			customerdaoazheekal.updateFoodCartstartersdeepfry(value,food_id);
			 customerdaoazheekal.sumAdditionToCartTablestartersdeepfry(Initialsum,food_item);
			 customerdaoazheekal.updateorderstartersdeepfry(food_id,id,value);
		}
		sum=sum+Initialsum;
		modelandview.setViewName("SelectFoodOfAzheekal");
		return modelandview;
	}
	
	
	@RequestMapping(value="/summationazheekal",method = RequestMethod.GET)
	public ModelAndView summation(HttpServletRequest request) {
		String username=request.getParameter("username");
		 sum=Integer.parseInt(request.getParameter("sum"));
		 int customer_id=Integer.parseInt(request.getParameter("customer_id"));
		 String foodlist=request.getParameter("foodlist");
		modelandview.addObject("username",username);
		modelandview.addObject("sum", sum);
		if(foodlist.equals("soup")) {
			
			List<FoodCartAzheekal> Foodcart=customerdaoazheekal.getFoodCartTableAzheekalSoup();
			modelandview.addObject("fooditems",Foodcart);
			modelandview.setViewName("/azheekalSumDetails.jsp");
			
		}else {
			
			List<FoodCartAzheekal> Foodcart=customerdaoazheekal.getFoodCartTableAzheekalStartersDeepfry();
			modelandview.addObject("fooditems",Foodcart);
			modelandview.setViewName("/azheekalSumDetails.jsp");
		}
				
		return modelandview;
		
	}
	

	@RequestMapping(value="/shippingAzheekal")
	public ModelAndView shipping(HttpServletRequest request) {
		ModelAndView modelandview=new ModelAndView();
		sum=0;
		String username=request.getParameter("username");
		String address=request.getParameter("address");
		modelandview.addObject("username",username);
		modelandview.addObject("address",address);
		modelandview.setViewName("azheekalFinal.jsp");
		return modelandview;
	}
	
	
	
	
	
	
	
	
}
