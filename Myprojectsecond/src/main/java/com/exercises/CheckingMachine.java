package com.exercises;
import java.util.Arrays;
import java.lang.*;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;


@
public class CheckingMachine {
	List<Details> details = Arrays.asList(
            new Details("subin123@gmail.com","00000"),
            new Details("raghuram@outlook.com","abcd"),
            new Details("arya345@yopmail.com","race123"),
            new Details("mohan37@gmail.com","12345"),
            new Details("getready@gmail.com","567890"));
	
	public ModelAndView check(HttpServletRequest request,HttpServletResponse response) {
		String s = request.getParameter("t1");
		String s1 = request.getParameter("t2");
		String s2;
        for(Details d: details) {
        	if((s.equals(details).getUsername()) && (s1.equals(details).getPassword())) {
        		
        		 s3 = s;
        	}
        	else 
        		s3="not working";
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Display.jsp");
        mv.addObject("result1",s3);
        return mv;
        
        
	}
	
	
	
	


}
