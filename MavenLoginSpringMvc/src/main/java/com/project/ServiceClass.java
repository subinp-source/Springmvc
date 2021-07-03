package com.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public class ServiceClass {
	@Autowired
	CustomerDao dao;
	/*public String addfood(Model m) {
		List<Food> list1 = dao.getFoodDetails();
		m.addAttribute("list1",list1);
		return "addfood.jsp";
		
	}*/
	
	/*public String changePrice(Model m) {
		
		List<Food> list2 = dao.getFoodDetails();
		m.addAttribute("list1",list2);
		return "pricechange.jsp";
	}*/

	/*public String viewOrder(Model m) {
		List<OrderDetails> list = dao.getOrderDetails();
		m.addAttribute("list",list);
		return "order.jsp";
	}*/

	/*public String viewFood(Model m,int sum,List<Cartlisting> listing) {
		List<Food> list = dao.getFoodDetails();
		m.addAttribute("list",list);
		m.addAttribute("sum",sum);
		m.addAttribute("listing",listing);
		return "restaurant.jsp";
	}*/
	
/*public ModelAndView login(HttpServletRequest request,Customer customer,int sum,List<Cartlisting> listing) {
	ModelAndView mav=new ModelAndView();
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
	}	
	}*/

	
	
	


}
