package com.trial.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.trial.Customer.Customer;

public class CustomerDao {
	
	
	private JdbcTemplate template;  
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	  
	public List<Customer> Data(String username,String password){  
		String query ="select * from users where username='"+username+"' and password='"+password+"'";
	 return template.query(query,new RowMapper<Customer>(){  
	    public Customer mapRow(ResultSet rs, int rownumber) throws SQLException {  
	        Customer e=new Customer();  
	        e.setUsername(rs.getString(1));  
	        e.setPassword(rs.getString(2));  
	        e.setFirstname(rs.getString(3)); 
	        e.setLastname(rs.getString(4));
	        e.setEmail(rs.getString(5));
	        e.setAddress(rs.getString(6));
	        e.setPhone(rs.getString(7));	        
	        return e;  
	    }  
	    });  
	}  
		

}
