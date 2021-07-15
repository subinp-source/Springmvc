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

import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.modelclass.Admin;
import com.project.modelclass.Cart;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.OrderDetails;
import com.project.modelclass.Price;
import com.project.servicelayer.AdminServiceLayer;
import com.project.servicelayer.CustomerServiceLayer;

@Controller
public class LoginController {

	@Autowired
	CustomerDao customerdao;
    @Autowired
    CustomerServiceLayer customerservice;
    @Autowired
    AdminDao admindao;
    
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		return customerservice.logging(username,password);          
	}

	@RequestMapping("/index")
	public String index() {
		return "index.jsp";
	}
	



	
	
	
	
	
	
	
	@RequestMapping(value="/GotoWelcomePage/{username}/{customer_id}",method=RequestMethod.GET)
	public ModelAndView Goto(@PathVariable int customer_id,@PathVariable String username) {
		ModelAndView modelandview=new ModelAndView();
		modelandview.addObject("username",username);
		modelandview.addObject("customer_id",customer_id);
		modelandview.setViewName("/welcome.jsp");
		return modelandview;
	}
	
	@RequestMapping(value="/Exception")
	public String redirectExceptionPage() {
		return "/Register.jsp";
		
	}
}


