package com.project;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class LoginController {
	
	@Autowired
	CustomerDao dao;
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv= new ModelAndView();
		List<Customer> list=dao.getData(username,password);
		if(list.isEmpty()) {
			String message="you are entered wrong details";
		  mv.setViewName("error.jsp");
		  mv.addObject("message", message);
		return mv;
		}
		else {
			mv.setViewName("welcome.jsp");
		return mv;
		}
		}
	   
	@RequestMapping("/registerprocess")
	public String registerprocess(HttpServletRequest request,HttpServletResponse response) {
	  Customer customer =new Customer();
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
	
}
	


