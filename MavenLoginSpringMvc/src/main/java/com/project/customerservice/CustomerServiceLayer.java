package com.project.customerservice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.modelclass.Admin;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.OrderDetails;

public class CustomerServiceLayer {

	@Autowired
	CustomerDao customerdao;
	
	@Autowired
	AdminDao admindao;
	
	public String viewOrder(Model m) {
		List<OrderDetails> list = customerdao.getOrderDetails();
		m.addAttribute("list",list);
		return "order.jsp";
	}
	
	
	
	public String register(HttpServletRequest request) throws SQLIntegrityConstraintViolationException,DuplicateKeyException{
		
		Customer customer = new Customer();
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		customer.setFirstname(request.getParameter("firstname"));
		customer.setLastname(request.getParameter("lastname"));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress(request.getParameter("address"));
		customer.setPhone(request.getParameter("phone"));
		customerdao.saveEmployeeByPreparedStatement(customer);
		
		return "success.jsp";
		
	}
	
	
	
	public ModelAndView logging(String username, String password) {
		ModelAndView modelAndView=new ModelAndView();
		/*if (username.equals("subin123") && password.equals("0000")) {
			mav.setViewName("admin.jsp");
			return mav;
		}*/ Admin admin=new Admin();
            Customer customer=new Customer();
            List<Admin> adminList=admindao.getAdminData(username, password);
		List<Customer> customerList = customerdao.getCustomerData(username, password);
		          admin=adminList.get(0);
		          customer=customerList.get(0);
		          
		          
		    if(adminList.isEmpty()) {
		    	
		    	if (customerList.isEmpty()) {
					String message = "you are entered wrong details";
					modelAndView.setViewName("error.jsp");
					 modelAndView.addObject("message", message);
					return modelAndView;
				} else {
					
					int sum = 0;
					customerdao.emptyTable();
					modelAndView.addObject("message",customer.getFirstname());
					List<Cartlisting> listing=null;
					customerdao.emptycart();modelAndView.setViewName("welcome.jsp");
					return modelAndView;
				}
		    	
		    }else {
		    	
		    	if (customerList.isEmpty()) {
                    modelAndView.setViewName("admin.jsp");
					return modelAndView;
				} else {
					modelAndView.setViewName("checkForUser.jsp");
					modelAndView.addObject("customerId", customer.getCustomer_id());
					modelAndView.addObject("admin", admin);
					modelAndView.addObject("customerName", customer.getFirstname());
					return modelAndView;
				}
		    }
		          
	}	          
	
}	    

