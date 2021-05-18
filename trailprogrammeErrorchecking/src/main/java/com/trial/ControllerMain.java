package com.trial;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.trial.Customer.Customer;
import com.trial.dao.CustomerDao;

public class ControllerMain {
	@Autowired
	CustomerDao dao;
	
	
	public String datalogin(HttpServletRequest request,HttpServletResponse response) {
		
		String username= request.getParameter("username");
		String password = request.getParameter("password");
		List<Customer> list=dao.Data(username,password);
		
		if(list.isEmpty()){
			
			
			return "error.jsp";
		}else {
			return "welcome.jsp";
		}
		
	}

}
