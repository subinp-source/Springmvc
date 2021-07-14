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

import com.project.customerservice.CustomerServiceLayer;
import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.modelclass.Cart;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.Food;
import com.project.modelclass.Price;

@Controller
public class BookingController {
	@Autowired
	CustomerDao customerdao;
   // Customer customer;
    @Autowired
    CustomerServiceLayer customerservice;
    int sum=0 ;
    List<Cartlisting> listing;
    List<Cart> cart; 
    @Autowired
    AdminDao admindao;
	
	

	@RequestMapping(value="/booking/{food_id}/{food_item}/{username}/{customer_id}",method = RequestMethod.GET) 
	public ModelAndView book(@PathVariable String username,@PathVariable int customer_id,@PathVariable int food_id,@PathVariable String food_item,HttpServletRequest request,Model m){
		ModelAndView modelandview =new ModelAndView();
		int id=customer_id;
	  int value=Integer.parseInt(request.getParameter("count"));
	  customerdao.updatecartlisting(id,value,food_item);
	  customerdao.updateFood(value,food_id);
	  List<Cartlisting> listed=customerdao.makelist();
	  listing=listed;
	  List<Price> accept=customerdao.price(food_id);
	  modelandview.addObject("username",username);
		modelandview.addObject("customer_id",customer_id);
	  
	  for (Price price : accept) {
	   sum=sum+(price.getEach_price()*value);
	  }
	  customerdao.updateorder(food_id,id,value);
	 /* dao.insertorderfunction(id,food_item,value);
	Cart carts =dao.cart(id,food_item,value);
	 cart.add(carts);
	  
	  Cart objt = new Cart(id,food_item,value); 
	  List<Cart> carts=new ArrayList<Cart>();

	  carts.add(objt);
          carted=carts;*/
	  modelandview.setViewName("redirect:http://localhost:8080/MavenLoginSpringMvc/detail/{username}/{customer_id}");
		  customerdao.updatetablevalues(food_item,id,value); 
		  cart=customerdao.getTablevalues(id);
	 return modelandview; 
	 
	 }//{username}{customer_id}
	
	
	@RequestMapping(value="/detail/{username}/{customer_id}",method = RequestMethod.GET)
	public ModelAndView viewfood(Model m,@PathVariable String username,@PathVariable int customer_id) {
		ModelAndView modelandview=new ModelAndView();
		//return customerservice.viewFood(m,sum,listing);
		List<Food> Foodlist = admindao.getFoodDetails();
		modelandview.addObject("username",username);
		modelandview.addObject("customer_id",customer_id);
		modelandview.addObject("list",Foodlist);
		modelandview.setViewName("/restaurant.jsp");
		modelandview.addObject("sum",sum);
		//m.addAttribute("listing",listing);
		return modelandview;
	}
	
	
	
	
	@RequestMapping("/summation/back")
	public String back() {
		return "redirect:/detail";
	}
	
	
	@RequestMapping("/summation/home")
	public String home() {
		return"redirect:/index";
	}
	  
	@RequestMapping(value="/summation/{sum}/{username}/{customer_id}",method = RequestMethod.GET)
	public String summation(@PathVariable int customer_id,@PathVariable String username,@PathVariable int sum,HttpServletRequest request, Model m) {
		
		m.addAttribute("name",username);
		m.addAttribute("sum", sum);
		int userid = customer_id;
		 //List<UserOrder> lists = dao.getUserOrders(userid);
		//m.addAttribute("userorder",lists);
		m.addAttribute("items",cart);
		
		return "/sumDetails.jsp";
	}
	
	  @RequestMapping("/shipping/{username}") 
	  public ModelAndView address(HttpServletRequest request,@PathVariable String username) { 
		  ModelAndView mvn=new ModelAndView();
		  mvn.addObject("trace",request.getParameter("address"));
		  mvn.addObject("user",username);
		  mvn.setViewName("/final.jsp");
	      return mvn;
	  }
	

}
