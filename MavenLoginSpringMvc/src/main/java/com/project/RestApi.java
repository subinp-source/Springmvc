package com.project;


import java.util.List;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RestApi {
	
	 @RequestMapping(value="/controllerApi",method=RequestMethod.GET)
	 public ModelAndView covid(Model m,ModelMap map) {
	    	ModelAndView mv=new ModelAndView();
	                 RestTemplate resttemplate=new RestTemplate();

	                 CovidReport api =resttemplate.getForObject("https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true",CovidReport.class);
	         /*long activecases=api.getActiveCases();
	         long deaths=api.getDeaths();
	        long previoustests= api.getPreviousDayTests();
	         List<regionData> list=(List) api.getRegionData();
	         long total=api.getTotalCases();
	         m.addAttribute("activecases",activecases);
	         m.addAttribute("deaths",deaths);
	         m.addAttribute("previoustests",previoustests);
	         m.addAttribute("total",total);
	         m.addAttribute("list",list);*/
	                 map.put("api",api);
	         mv.setViewName("covidreport.jsp");
	    	return mv;
	    
	
	
	
	 }	
	

}
