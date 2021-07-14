package com.project.controller;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.adminservice.AdminServiceLayer;
import com.project.customerservice.CustomerServiceLayer;
import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.modelclass.Admin;
import com.project.modelclass.Cart;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.OrderDetails;
import com.project.modelclass.Price;

@Controller
public class LoginController {

	@Autowired
	CustomerDao customerdao;
    //Customer customer;
    @Autowired
    CustomerServiceLayer service;
    @Autowired
    AdminServiceLayer adminservice;
    //int sum ;
    List<Cartlisting> listing;
    List<Cart> cart; 
    @Autowired
    AdminDao admindao;
    
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//return service.logging(username,password);
		ModelAndView modelAndView=new ModelAndView();
		/*if (username.equals("subin123") && password.equals("0000")) {
			mav.setViewName("admin.jsp");
			return mav;
		}*/ Admin admin=new Admin();
            Customer customer=new Customer();
            List<Admin> adminList=admindao.getAdminData(username, password);
                   
		List<Customer> customerList = customerdao.getCustomerData(username, password);
		          
		          
		          
		    if(adminList.isEmpty()) {
		    	
		    	if (customerList.isEmpty()) {
					String message = "you are entered wrong details";
					modelAndView.setViewName("error.jsp");
					 modelAndView.addObject("message", message);
					return modelAndView;
				} else {
					
					customer=customerList.get(0);
					 int sum = 0;
					customerdao.emptyTable();
					modelAndView.addObject("customer",customer);
					 listing=null;
					customerdao.emptycart();modelAndView.setViewName("welcome.jsp");
					return modelAndView;
				}
		    	
		    }else {
		    	
		    	if (customerList.isEmpty()) {
		    		admin=adminList.get(0);
                    modelAndView.setViewName("admin.jsp");
					return modelAndView;
				} else {
					customer=customerList.get(0);
					modelAndView.setViewName("checkForUser.jsp");
					modelAndView.addObject("customerId", customer.getCustomer_id());
					modelAndView.addObject("admin", admin);
					modelAndView.addObject("customerName", customer.getFirstname());
					return modelAndView;
				}
		    }
		          
		          
		          
		/*if (customerList.isEmpty()) {
			String message = "you are entered wrong details";
			mav.setViewName("error.jsp");
			 mav.addObject("message", message);
			return mav;
		} else {
			
			int sum = 0;
			dao.emptyTable();
			mav.addObject("message",customer.getFirstname());
			List<Cartlisting> listing=null;dao.emptycart();mav.setViewName("welcome.jsp");
			return mav;
		}*/
		
	}

	@RequestMapping("/index")
	public String index() {
		return "index.jsp";
	}
	



	@RequestMapping("/updatingfood/{food_id}")
	public String updatefood(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
		int foodcount = Integer.parseInt(request.getParameter("count"));
		customerdao.updatefoodcount(foodcount,food_id);
		return "redirect:/addfood";
	}
	
	@RequestMapping("/changeprice")
	public String changeprice(Model m) {
		return adminservice.changePrice(m);
		/*List<Food> list2 = dao.getFoodDetails();
		m.addAttribute("list1",list2);
		return "pricechange.jsp";*/
	}
	
	@RequestMapping("/pricechange/{food_id}")
	public String pricechange(@PathVariable int food_id, HttpServletRequest request, HttpServletResponse response) {
		
		int price = Integer.parseInt(request.getParameter("price"));
		customerdao.changeprice(price,food_id);
		return "redirect:/changeprice";
		
	}
	@RequestMapping("/orderdetails")
	public String vieworder(Model m) {
		//return service.viewOrder(m);
		List<OrderDetails> list = customerdao.getOrderDetails();
		m.addAttribute("list",list);
		return "order.jsp";
	}
	
	@RequestMapping(value="/GotoWelcomePage/{customerName}",method=RequestMethod.GET)
	public ModelAndView Goto(@PathVariable String customerName) {
		ModelAndView modelandview=new ModelAndView();
		modelandview.addObject("customerName",customerName );
		modelandview.setViewName("/restaurant.jsp");
		return modelandview;
	}
	
	@RequestMapping(value="/Exception")
	public String redirectExceptionPage() {
		return "/Register.jsp";
		
	}
}

//return service.login(request,customer,sum,listing);
		//ModelAndView mav=new ModelAndView();


/*if (username.equals("subin123") && password.equals("0000")) {
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
listing=null;dao.emptycart();
mav.setViewName("welcome.jsp");
return mav;
}*/
