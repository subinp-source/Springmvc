package com.project;

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
			sum=0;
			mv.addObject("message",customer.getFirstname());
			return mv;
		}
	}

	@RequestMapping("/details")
	public String viewfood(Model m) {
		List<Food> list = dao.getFoodDetails();
		m.addAttribute("list",list);
		m.addAttribute("sum",sum); 
		return "restaurant.jsp";
	}
	
	
	@RequestMapping("/addfood")
	public String addfood(Model m) {
		List<Food> list1 = dao.getFoodDetails();
		m.addAttribute("list1",list1);
		return "addfood.jsp";
	}

	/*
	 * @RequestMapping("/booking/${fooditem.food_id}") public ModelAndView
	 * BookFood(@PathVariable("fooditem.food_id") List<Integer> foodid,@RequestParam
	 * ArrayList<Integer> values,HttpServletRequest request,HttpServletResponse
	 * response) { ModelAndView mav = new ModelAndView(); request.getParameterMap();
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * return mav; }
	 */

	// int DosaCount = Integer.parseInt(request.getParameter("dosa"));
	// int BeefCount = Integer.parseInt(request.getParameter("beef"));
	// int ChapathiCount = Integer.parseInt(request.getParameter("chapathi"));
	// dao.updateDosa(DosaCount);
	// dao.updateBeefroast(BeefCount);
	// dao.updateChapathi(ChapathiCount);}

	/*
	 * if ((DosaCount+BeefCount+ChapathiCount)!= 0) { int totalprice=
	 * dao.getPrice(DosaCount,BeefCount,ChapathiCount);
	 * 
	 * mav.addObject("totalprice",totalprice); mav.setViewName("booked.jsp"); return
	 * mav;
	 * 
	 * }else { String message ="please provide some food quantity";
	 * mav.setViewName("declare.jsp"); mav.addObject("message",message); return mav;
	 * } }
	 */

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

	
	
	
	  @RequestMapping(value="/booking/{food_id}",method = RequestMethod.GET) 
	public String book(@PathVariable int food_id,HttpServletRequest request,Model m){
		  
	  int value=Integer.parseInt(request.getParameter("count"));
	  
	  dao.updateFood(value,food_id);
	  
	  List<Price> accept=dao.price(food_id);
	  
	  for (Price price : accept) {
	  sum=sum+(price.getEach_price()*value);
	  }
	  
	 int id=customer.getCustomer_id();
	  dao.updateorder(food_id,id,value);
	  
	  
	
	  
	 return "redirect:/details"; 
	 }

	  
	  
	  
	  
	  
	  
	@RequestMapping(value="/summation/{sum}",method = RequestMethod.GET)
	public String summation(@PathVariable int sum, HttpServletRequest request, Model m) {
		m.addAttribute("name",customer.getFirstname());
		m.addAttribute("address",customer.getAddress());
		m.addAttribute("sum", sum);
		return "/nxt.jsp";
	}

	@RequestMapping("/out")
	public String logout() {
		return"index.jsp";
	}
	
	@RequestMapping("/orderdetails")
	public String vieworder(Model m) {
		List<OrderDetails> list = dao.getOrderDetails();
		m.addAttribute("list",list);
		 
		return "order.jsp";
	}
	
}
