package com.countnumbers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CountingMachine {
	@RequestMapping("/count")
	public ModelAndView count(HttpServletRequest request,HttpServletResponse response) {
		String s =request.getParameter("t1");
		int k=0;
		for(int i=0;i<s.length();i++) {

				if(s.charAt(i)!=' ' && s.charAt(i)!='.') {
				k++;
				}
				}
		ModelAndView mv= new ModelAndView();
		mv.setViewName("displays.jsp");
		mv.addObject("result",k);
		return mv;
		
		
	}

}
