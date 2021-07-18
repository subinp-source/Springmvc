package com.project.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.modelclass.Cart;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.servicelayer.CustomerServiceLayer;


@Controller
public class CustomerRegisterController {
    @Autowired
    CustomerServiceLayer customerservice; 
	ModelAndView modelandview =new ModelAndView();
    
    
	@RequestMapping("/registerprocess")
	public ModelAndView registerprocess(HttpServletRequest request) {
		try {
		return customerservice.register(request);
		}
		catch(DuplicateKeyException key) {
			key.printStackTrace();
			modelandview.setViewName("registerException.jsp");
			return modelandview;
		}
	}
	
	

}
