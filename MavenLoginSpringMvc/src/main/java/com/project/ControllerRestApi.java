package com.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.CustomerDao;
import com.modelclass.OrderDetails;

@RestController
public class ControllerRestApi {
	@Autowired
	CustomerDao dao;
	
	@RequestMapping(value = "foodBooking", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetails>> listAllUsers() {
        List<OrderDetails> users =dao.getOrderDetails();
        if(users.isEmpty()){
            return new ResponseEntity<List<OrderDetails>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<OrderDetails>>(users, HttpStatus.OK);
    }
	
	
	

}
