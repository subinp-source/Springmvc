package com.project;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			String message= username;
			mv.setViewName("welcome.jsp");
			mv.addObject("message", message);
		return mv;
		}
		}
	   
	
	
	
	
	@RequestMapping("/restaurant")    
    public String viewfood(Model m){    
        List<Food> list=dao.getFoodDetails();    
        m.addAttribute("list",list);  
        return "restaurant.jsp";   
	}
	
	@RequestMapping("/booking")
	public ModelAndView BookFood(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		int DosaCount = Integer.parseInt(request.getParameter("dosa"));
		int BeefCount = Integer.parseInt(request.getParameter("beef"));
		int ChapathiCount = Integer.parseInt(request.getParameter("chapathi"));
		
		
		if ((DosaCount+BeefCount+ChapathiCount)>0) {
			int totalprice= dao.getPrice(DosaCount,BeefCount,ChapathiCount);
			
			mav.addObject("totalprice",totalprice);
			mav.setViewName("booked.jsp");
			return mav;
			
		}else {
			String message ="please provide some food quantity";
			mav.setViewName("declare.jsp");
			mav.addObject("message",message);
			return mav;
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
	


