package com.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.CustomerDao;
import com.modelclass.Cart;
import com.modelclass.Cartlisting;
import com.modelclass.Customer;
import com.modelclass.OrderDetails;
import com.modelclass.Price;
import com.service.ServiceClass;

@Controller
public class LoginController {

	@Autowired
	CustomerDao dao;
    Customer customer;
    @Autowired
    ServiceClass service;
    int sum ;List<Cartlisting> listing;
    List<Cart> cart; 
    
    
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		return service.login(request,customer,sum,listing);
		/*ModelAndView mav=new ModelAndView();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if (username.equals("subin123") && password.equals("0000")) {
			mav.setViewName("admin.jsp");
			return mav;
		}

		List<Customer> list = dao.getData(username, password);
		          customer=list.get(0);
		if (list.isEmpty()) {
			String message = "you are entered wrong details";
			mav.setViewName("error.jsp");
			 mav.addObject("message", message);
			return mav;
		} else {
			
			sum=0;
			dao.emptyTable();
			mav.addObject("message",customer.getFirstname());
			listing=null;dao.emptycart();mav.setViewName("welcome.jsp");
			return mav;
		}*/
		
	}

	@RequestMapping("/index")
	public String index() {
		return "index.jsp";
	}
	
	
	@RequestMapping("/addfood")
	public String addfood(Model m) {
		return service.addfood(m);
		/*List<Food> list1 = dao.getFoodDetails();
		m.addAttribute("list1",list1);
		return "addfood.jsp";*/
		
		
	}

	@RequestMapping("/registerprocess")
	public String registerprocess(HttpServletRequest request) {
		return service.register(request);

	}

	@RequestMapping("/updatingfood/{food_id}")
	public String updatefood(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
		int foodcount = Integer.parseInt(request.getParameter("count"));
		dao.updatefoodcount(foodcount,food_id);
		return "redirect:/addfood";
	}
	
	@RequestMapping("/changeprice")
	public String changeprice(Model m) {
		return service.changePrice(m);
		/*List<Food> list2 = dao.getFoodDetails();
		m.addAttribute("list1",list2);
		return "pricechange.jsp";*/
	}
	
	@RequestMapping("/pricechange/{food_id}")
	public String pricechange(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
		
		int price = Integer.parseInt(request.getParameter("price"));
		dao.changeprice(price,food_id);
		return "redirect:/changeprice";
		
	}
	
	
	
	@RequestMapping("/detail")
	public String viewfood(Model m) {
		return service.viewFood(m,sum,listing);
		/*List<Food> list = dao.getFoodDetails();
		m.addAttribute("list",list);
		m.addAttribute("sum",sum);
		m.addAttribute("listing",listing);
		return "restaurant.jsp";*/
	}
	
	
	 
	@RequestMapping(value="/booking/{food_id}/{food_item}",method = RequestMethod.GET) 
	public String book(@PathVariable int food_id,@PathVariable String food_item,HttpServletRequest request,Model m){
		int id=customer.getCustomer_id();
	  int value=Integer.parseInt(request.getParameter("count"));
	  dao.updatecartlisting(id,value,food_item);
	  dao.updateFood(value,food_id);
	  List<Cartlisting> listed=dao.makelist();
	  listing=listed;
	  List<Price> accept=dao.price(food_id);
	  
	  for (Price price : accept) {
	  sum=sum+(price.getEach_price()*value);
	  }
	  
	 
	 
	  dao.updateorder(food_id,id,value);
	 /* dao.insertorderfunction(id,food_item,value);
	Cart carts =dao.cart(id,food_item,value);
	 cart.add(carts);
	  
	  Cart objt = new Cart(id,food_item,value); 
	  List<Cart> carts=new ArrayList<Cart>();

	  carts.add(objt);
          carted=carts;*/


	  
	  
	  
		
		  dao.updatetablevalues(food_item,id,value); 
		  cart=dao.getTablevalues(id);
		 
	 
	 return "redirect:/detail"; 
	 
	 }

	  
	@RequestMapping(value="/summation/{sum}",method = RequestMethod.GET)
	public String summation(@PathVariable int sum,HttpServletRequest request, Model m) {
		
		m.addAttribute("name",customer.getFirstname());
		m.addAttribute("address",customer.getAddress());
		m.addAttribute("sum", sum);
		int userid = customer.getCustomer_id();
		 //List<UserOrder> lists = dao.getUserOrders(userid);
		//m.addAttribute("userorder",lists);
		m.addAttribute("items",cart);
		
		return "/sumDetails.jsp";
	}
	
	  @RequestMapping("/summation/shipping") 
	  public ModelAndView address(HttpServletRequest request) { 
		  ModelAndView mvn=new ModelAndView();
		  mvn.addObject("trace",request.getParameter("address"));
		  mvn.setViewName("/final.jsp");
	  return mvn;
	  }
	 
	
	@RequestMapping("/orderdetails")
	public String vieworder(Model m) {
		//return service.viewOrder(m);
		List<OrderDetails> list = dao.getOrderDetails();
		m.addAttribute("list",list);
		return "order.jsp";
	}
	
	
	@RequestMapping("/summation/back")
	public String back() {
		return "redirect:/detail";
	}
	
	
	@RequestMapping("/summation/home")
	public String home() {
		return"redirect:/index";
	}
	
	
	
	
	
}
