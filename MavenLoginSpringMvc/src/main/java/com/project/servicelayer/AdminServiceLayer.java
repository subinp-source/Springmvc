package com.project.servicelayer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.AdminDao;
import com.project.modelclass.Admin;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.Food;
import com.project.modelclass.OrderDetails;

public class AdminServiceLayer {
	
	@Autowired
	AdminDao admindao;
	ModelAndView modelandview = new ModelAndView();
	
	public ModelAndView addfoodservice() {
	
		List<Food> FoodDetailsList = admindao.getFoodDetails();
		modelandview.addObject("FoodDetailsList",FoodDetailsList);
		modelandview.setViewName("addfood.jsp");
		return modelandview;
	}
	
	
public ModelAndView changePriceservice() {
		modelandview.setViewName("pricechange.jsp");
		List<Food> FoodList = admindao.getFoodDetails();
		modelandview.addObject("list1",FoodList);
		return modelandview;
	}


public String viewFood(Model m,int sum,List<Cartlisting> listing) {
	List<Food> FoodList = admindao.getFoodDetails();
	m.addAttribute("list",FoodList);
	m.addAttribute("sum",sum);
	m.addAttribute("listing",listing);
	return "restaurant.jsp";
}
	
	
	
public ModelAndView registerByUserservice(HttpServletRequest request) {
	
	Customer customer = new Customer();
	customer.setUsername(request.getParameter("username"));
	customer.setPassword(request.getParameter("password"));
	customer.setFirstname(request.getParameter("firstname"));
	customer.setLastname(request.getParameter("lastname"));
	customer.setEmail(request.getParameter("email"));
	customer.setAddress(request.getParameter("address"));
	customer.setPhone(request.getParameter("phone"));
	admindao.saveUserByAdmin(customer);
	
	modelandview.setViewName( "/addUser");
	return modelandview;
}





public ModelAndView insertAdminService(HttpServletRequest request) {
	String username = request.getParameter("usernameOfAdmin");
	String password = request.getParameter("passwordOfAdmin");
	admindao.insertAdminDetails(username,password);
	modelandview.setViewName("admin.jsp");
	return modelandview;
}
	
	

public ModelAndView viewOrderservice() {
	List<OrderDetails> OrderFoodList = admindao.getOrderDetails();
	modelandview.addObject("OrderFoodList",OrderFoodList);
	modelandview.setViewName("order.jsp");
	return modelandview;
}


public ModelAndView usermanagementservice() {
	modelandview.setViewName("usermanagement.jsp");
	return modelandview;
}


public ModelAndView adminManagementservice() {
	modelandview.setViewName("adminmanagement.jsp");
	return modelandview;
}


public ModelAndView ordinaryHotelservice() {
	modelandview.setViewName("ordinaryhotel.jsp");
	return modelandview;
}


public ModelAndView AlibabaRestaurantAdminManagementservice() {
	modelandview.setViewName("alibabaRestaurantAdminManagement.jsp");
	return modelandview;
}


public ModelAndView AzheekalRestaurantAdminManagementservice() {
	modelandview.setViewName("azheekalRestaurantAdminManagement.jsp");
	return modelandview;
}


public ModelAndView DeleteCustomerservice(HttpServletRequest request) {
	int customer_id=Integer.parseInt(request.getParameter("customer_id"));
	admindao.deleteCustomer(customer_id);
	modelandview.setViewName("/deleteUser");
	//m.addAttribute("customerList",customerList);
	return modelandview;
}


public ModelAndView passcodeCheckerservice(HttpServletRequest request) {
	int passcode=Integer.parseInt(request.getParameter("passcode"));
	if(passcode==123) {
		modelandview.setViewName("/admin.jsp");
		return modelandview;
	}else {
		modelandview.setViewName("/error.jsp");
		return modelandview;
	}
}


public ModelAndView addUserservice() {
	modelandview.setViewName("Adduser.jsp");
	
	return modelandview;
}


public ModelAndView userDetailsService() {
	modelandview.setViewName("customerDetailsByAdmin.jsp");
	List<Customer> customerList=admindao.GetFullUserDetails();
	modelandview.addObject("customerList",customerList);
	return modelandview;
}


public ModelAndView DeleteCustomerByAdminservice(String username) {
	admindao.deleteAdmin(username);
	modelandview.setViewName( "/deleteAdmin");
	//m.addAttribute("customerList",customerList);
	return modelandview;
}


public ModelAndView UserExceptionByAdminservice() {
	modelandview.setViewName("/admin.jsp");
	return modelandview;
}


public ModelAndView deleteAdminservice() {
	modelandview.setViewName("adminDetailsByAdmin.jsp");
	List<Admin> adminList=admindao.GetFullAdminDetails();
	modelandview.addObject("adminList",adminList);
	return modelandview;
}


public ModelAndView pricechangeservice(int food_id, HttpServletRequest request) {
	int price = Integer.parseInt(request.getParameter("price"));
	admindao.changeprice(price,food_id);
	modelandview.setViewName("redirect:/changeprice");
	return modelandview;
}


public ModelAndView updatefoodservice(HttpServletRequest request, int food_id) {
	int foodcount = Integer.parseInt(request.getParameter("count"));
	admindao.updatefoodcount(foodcount,food_id);
	modelandview.setViewName("redirect:/addfood");
	return modelandview;
}


public ModelAndView orderDetailsOfAlibabaRollsService() {
	modelandview.setViewName("OrderDetailRolls.jsp");
	List<OrderDetails> OrderFoodList = admindao.getOrderDetailsRolls();
	modelandview.addObject("OrderFoodListRolls",OrderFoodList);
	return modelandview;
}


public ModelAndView orderDetailsOfAlibabaChowmeinservice() {
	modelandview.setViewName("OrderDetailchowmein.jsp");
	List<OrderDetails> OrderFoodList = admindao.getOrderDetailschowmein();
	modelandview.addObject("OrderFoodListChowmein",OrderFoodList);
	return modelandview;
}


public ModelAndView orderDetailsOfAzheekalSoupService() {

	modelandview.setViewName("orderDetailsOfAzheekalsoup.jsp");
	List<OrderDetails> OrderFoodList = admindao.getOrderDetailssoup();
	modelandview.addObject("OrderFoodListSoup",OrderFoodList);
	return modelandview;
}


public ModelAndView orderDetailsOfAzheekalstartersDeepFryservice() {
	modelandview.setViewName("orderDetailsOfAzheekalstartersDeepFry.jsp");
	List<OrderDetails> OrderFoodList = admindao.getOrderDetailsstartersdeepfry();
	modelandview.addObject("OrderFoodListStartersdeepfry",OrderFoodList);
	return modelandview;
}


public ModelAndView PriceChangeOfAlibabaRollsservice() {
	List<Food> RollsFoodList = admindao.getFoodDetailsRolls();
	modelandview.addObject("RollsFoodList",RollsFoodList);
	modelandview.setViewName("priceChangeOfAlibabaRolls.jsp");
	return modelandview;
}


public ModelAndView pricechangeRollsService(HttpServletRequest request, int food_id) {
	int price = Integer.parseInt(request.getParameter("price"));
	admindao.changepriceRolls(price,food_id);
	modelandview.setViewName("redirect:/PriceChangeOfAlibabaRolls");
	return modelandview;
}


public ModelAndView PriceChangeOfAlibabaChowmeinservice() {
	List<Food> ChowmeinFoodList = admindao.getFoodDetailsChowmein();
	modelandview.addObject("ChowmeinFoodList",ChowmeinFoodList);
	modelandview.setViewName("priceChangeOfAlibabaChowmein.jsp");
	return modelandview;
}


public ModelAndView pricechangeChowmeinservice(HttpServletRequest request, int food_id) {
	int price = Integer.parseInt(request.getParameter("price"));
	admindao.changepriceChowmein(price,food_id);
	modelandview.setViewName("redirect:/PriceChangeOfAlibabaChowmein");
	return modelandview;
}


public ModelAndView PriceChangeOfAzheekalSoupservice() {
	List<Food> SoupFoodList = admindao.getFoodDetailsSoup();
	modelandview.addObject("SoupFoodList",SoupFoodList);
	modelandview.setViewName("priceChangeOfAzheekalSoup.jsp");
	return modelandview;
}


public ModelAndView pricechangeSoup(HttpServletRequest request, int food_id) {
	int price = Integer.parseInt(request.getParameter("price"));
	admindao.changepriceSoup(price,food_id);
	modelandview.setViewName("redirect:/PriceChangeOfAzheekalSoup");
	return modelandview;
}


public ModelAndView PriceChangeOfAzheekalStartersDeepFry() {
	List<Food> StartersdeepfryFoodList = admindao.getFoodDetailsStartersdeepfry();
	modelandview.addObject("StartersdeepfryFoodList",StartersdeepfryFoodList);
	modelandview.setViewName("priceChangeOfAzheekalStartersdeepfry.jsp");
	return modelandview;
}


public ModelAndView pricechangeStartersdeepfryservice(HttpServletRequest request, int food_id) {
	int price = Integer.parseInt(request.getParameter("price"));
	admindao.changepriceStartersdeepfry(price,food_id);
	modelandview.setViewName("redirect:/PriceChangeOfAzheekalStartersDeepFry");
	return modelandview;
}


public ModelAndView AddFoodOfAlibabaRolls() {
	modelandview.setViewName("RollsaddFood.jsp");
	List<Food> RollsFoodDetailsList = admindao.getFoodDetailsRolls();
	modelandview.addObject("RollsFoodDetailsList",RollsFoodDetailsList);
	return modelandview;
}


public ModelAndView updatefoodRollsService(HttpServletRequest request, int food_id) {
	int foodcount = Integer.parseInt(request.getParameter("count"));
		modelandview.setViewName("redirect:/AddFoodOfAlibabaRolls");
		admindao.updatefoodcountRolls(foodcount,food_id);
		return modelandview;
}


public ModelAndView AddFoodOfAlibabaChowmeinservice() {
	modelandview.setViewName("ChowmeinaddFood.jsp");
		List<Food> ChowmeinFoodDetailsList = admindao.getFoodDetailsChowmein();
		modelandview.addObject("ChowmeinFoodDetailsList",ChowmeinFoodDetailsList);
		return modelandview;
}


public ModelAndView updatefoodchowmeinservice(HttpServletRequest request, int food_id) {
	int foodcount = Integer.parseInt(request.getParameter("count"));
	modelandview.setViewName("redirect:/AddFoodOfAlibabaChowmein");
	admindao.updatefoodcountChowmein(foodcount,food_id);
	return modelandview;
}


public ModelAndView AddFoodOfAzheekalSoupservice() {
	modelandview.setViewName("SoupaddFood.jsp");
		List<Food> SoupFoodDetailsList = admindao.getFoodDetailsSoup();
		modelandview.addObject("SoupFoodDetailsList",SoupFoodDetailsList);
		return modelandview;
}


public ModelAndView updatingfoodSoupservice(HttpServletRequest request, int food_id) {
	int foodcount = Integer.parseInt(request.getParameter("count"));
	modelandview.setViewName("redirect:/AddFoodOfAzheekalSoup");
	admindao.updatefoodcountSoup(foodcount,food_id);
	return modelandview;
}


public ModelAndView AddFoodOfAzheekalStartersDeepFryservice() {
	modelandview.setViewName("StartersdeepfryaddFood.jsp");
		List<Food> StartersdeepfryFoodDetailsList = admindao.getFoodDetailsStartersdeepfry();
		modelandview.addObject("StartersdeepfryFoodDetailsList",StartersdeepfryFoodDetailsList);
		return modelandview;
}


public ModelAndView updatingfoodStartersdeepfryservice(HttpServletRequest request, int food_id) {
	int foodcount = Integer.parseInt(request.getParameter("count"));
	modelandview.setViewName("redirect:/AddFoodOfAzheekalStartersDeepFry");
	admindao.updatefoodcountStartersdeepfry(foodcount,food_id);
	return modelandview;
}


}
