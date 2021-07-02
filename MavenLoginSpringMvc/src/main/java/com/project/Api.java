package com.project;

import java.awt.List;

import java.io.IOException;
import java.net.URL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
@RestController
public class Api {
    @RequestMapping("/controllerApi")
    public ModelAndView covid(Model m) {
    	ModelAndView mv=new ModelAndView();
          try {
                 ObjectMapper objectMapper = new ObjectMapper();

                 CovidReport api = objectMapper.readValue(new URL("https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true"), CovidReport.class);
         long activecases=api.getActiveCases();
         long deaths=api.getDeaths();
        long previoustests= api.getPreviousDayTests();
         List list=(List) api.getRegionData();
         long total=api.getTotalCases();
         m.addAttribute("activecases",activecases);
         m.addAttribute("deaths",deaths);
         m.addAttribute("previoustests",previoustests);
         m.addAttribute("total",total);
         m.addAttribute("list",list);

         mv.setViewName("covidreport.jsp");
          } catch (JsonGenerationException e) {
                 e.printStackTrace();
          } catch (JsonMappingException e) {
                 e.printStackTrace();
          } catch (IOException e) {
                 e.printStackTrace();
          }
          
    	return mv;
    }

}*/
