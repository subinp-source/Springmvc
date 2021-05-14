package com.project;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CssIn {
@RequestMapping("/add")	
public ModelAndView check(HttpServletRequest request,HttpServletResponse response) {
		String s= request.getParameter("t1");
		String s1=request.getParameter("t2");
		ArrayList<String> user = new ArrayList<String>();
		user.add("subin123@gmail.com");
		user.add("raghuram@outlook.com");
		user.add("arya345@yopmail.com");
		user.add("mohan37@gmail.com");
		user.add("getready@gmail.com");
		ArrayList<String> pass =new ArrayList<String>();
		pass.add("00000");
		pass.add("abcd");
		pass.add("race123");
		pass.add("12345");
		pass.add("567890");
		String s2 = null;
		for(int i=0;i<5;i++) {
			if(user.get(i).equals(s) && pass.get(i).equals(s1)) {
				s2=s;
				break;
			}
			else {
				s2="data doesnot match";
			}
		}
		ModelAndView mb =new ModelAndView();
		mb.setViewName("declare.jsp");
		mb.addObject("results",s2);
		return mb;
	}
}
