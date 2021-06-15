package com.project;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	CustomerDao dao;
    Customer customer;
    int sum ;
    List<Cart> cart; 
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		if (username.equals("subin123") && password.equals("0000")) {
			mv.setViewName("admin.jsp");
			return mv;
		}

		List<Customer> list = dao.getData(username, password);
		          customer=list.get(0);
		if (list.isEmpty()) {
			String message = "you are entered wrong details";
			mv.setViewName("error.jsp");
			mv.addObject("message", message);
			return mv;
		} else {
			
			mv.setViewName("welcome.jsp");
			sum=0;dao.emptyTable();
			mv.addObject("message",customer.getFirstname());
			return mv;
		}
	}

	
	
	@RequestMapping("/addfood")
	public String addfood(Model m) {
		List<Food> list1 = dao.getFoodDetails();
		m.addAttribute("list1",list1);
		return "addfood.jsp";
	}

	@RequestMapping("/registerprocess")
	public String registerprocess(HttpServletRequest request, HttpServletResponse response) {
		Customer customer = new Customer();
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		customer.setFirstname(request.getParameter("firstname"));
		customer.setLastname(request.getParameter("lastname"));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress(request.getParameter("address"));
		customer.setPhone(request.getParameter("phone"));
		dao.saveEmployeeByPreparedStatement(customer);

		return "success.jsp";

	}

	@RequestMapping("/updatingfood/{food_id}")
	public String updatefood(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
		int foodcount = Integer.parseInt(request.getParameter("count"));
		dao.updatefoodcount(foodcount,food_id);
		return "redirect:/addfood";
	}
	
	@RequestMapping("/changeprice")
	public String changeprice(HttpServletRequest request, Model m) {
		List<Food> list2 = dao.getFoodDetails();
		m.addAttribute("list1",list2);
		return "pricechange.jsp";
	}
	
	@RequestMapping("/pricechange/{food_id}")
	public String pricechange(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
		
		int price = Integer.parseInt(request.getParameter("price"));
		dao.changeprice(price,food_id);
		return "redirect:/changeprice";
		
	}
	
	
	
	@RequestMapping("/detail")
	public String viewfood(Model m) {
		List<Food> list = dao.getFoodDetails();
		m.addAttribute("list",list);
		m.addAttribute("sum",sum);
		return "restaurant.jsp";
	}
	
	
	 
	@RequestMapping(value="/booking/{food_id}/{food_item}",method = RequestMethod.GET) 
	public String book(@PathVariable int food_id,@PathVariable String food_item,HttpServletRequest request,Model m){
		 
	  int value=Integer.parseInt(request.getParameter("count"));
	  
	  dao.updateFood(value,food_id);
	  
	  List<Price> accept=dao.price(food_id);
	  
	  for (Price price : accept) {
	  sum=sum+(price.getEach_price()*value);
	  }
	  
	 int id=customer.getCustomer_id();
	 
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
		
		return "/nxt.jsp";
	}
	

	/*
	 * @RequestMapping("/out") public String logout() { return"index.jsp"; }
	 */
	
	  @RequestMapping("/summation/shipping") 
	  public ModelAndView address(HttpServletRequest request,Model m) { 
		  ModelAndView mvn=new ModelAndView();
		 // m.addAttribute("trace",request.getParameter("address"));
		  mvn.addObject("trace",request.getParameter("address"));
		  mvn.setViewName("/final.jsp");
	  return mvn; }
	 
	
	@RequestMapping("/orderdetails")
	public String vieworder(Model m) {
		List<OrderDetails> list = dao.getOrderDetails();
		m.addAttribute("list",list);
		return "order.jsp";
	}
	
	
	
}
