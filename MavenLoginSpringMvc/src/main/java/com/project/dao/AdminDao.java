package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ui.Model;

import com.project.modelclass.Admin;
import com.project.modelclass.Customer;
import com.project.modelclass.Food;
import com.project.modelclass.OrderDetails;

public class AdminDao {
	
	
	private JdbcTemplate jdbctemplate;
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	
	


	public List<Food> getFoodDetails() {
		
		 return jdbctemplate.query("select * from fooditem",new RowMapper<Food>(){    
	            public Food mapRow(ResultSet resultset, int row) throws SQLException {    
	                Food food=new Food(); 
	                food.setFood_id(resultset.getInt("food_id"));
	                food.setFood_item(resultset.getString("food_item"));    
	                food.setFood_price(resultset.getInt("food_price"));    
	                food.setFinal_quantity(resultset.getInt("final_quantity"));
	                return food;    
	            }    
	        });
	}




	public int insertAdminDetails(String username, String password) {
		
		
		
		String  sql="insert into admindatabase(username,password) values("+"'"+username+"'"+","+"'"+password+"'"+")";
				 return jdbctemplate.update(sql);
		
		
		
	}




	public List<Admin> getAdminData(String username, String password) {
	
		
		String query="select * from admindatabase where username='"+username+"' and password='"+password+"'";
		return jdbctemplate.query(query,new RowMapper<Admin>(){ 
	        public Admin mapRow(ResultSet rs, int row) throws SQLException {    
	            Admin admin=new Admin();
	            admin.setUsername(rs.getString("username"));
	            admin.setPassword(rs.getString("password"));
	    	
	    		return admin;}
	        });      
		
		
		
		
		
	}




	public Boolean saveUserByAdmin(final Customer customer) {
		 String query="insert into users(username,password,firstname,lastname,email,address,phone) values(?,?,?,?,?,?,?)";  
		    return jdbctemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {  
		              
		        ps.setString(1,customer.getUsername());  
		        ps.setString(2,customer.getPassword());  
		        ps.setString(3,customer.getFirstname());
		        ps.setString(4,customer.getLastname());  
		        ps.setString(5,customer.getEmail());  
		        ps.setString(6,customer.getAddress()); 
		        ps.setString(7,customer.getPhone());
		         return ps.execute();
		              
		    }  
		    }); 
		
	}




	public List<Customer> GetFullUserDetails() {
		String query="select * from users ";
		return jdbctemplate.query(query,new RowMapper<Customer>(){ 
	        public Customer mapRow(ResultSet rs, int row) throws SQLException {    
	            Customer customer=new Customer();
	            customer.setUsername(rs.getString("username"));
	    		customer.setPassword(rs.getString("password"));
	    		customer.setFirstname(rs.getString("firstname"));
	    		customer.setLastname(rs.getString("lastname"));
	    		customer.setAddress(rs.getString("address"));
	    		customer.setEmail(rs.getString("email"));
	    		customer.setPhone(rs.getString("phone"));
	    		customer.setCustomer_id(rs.getInt("customer_id"));
	    		return customer;}
	        });    
		
	}




	public int deleteCustomer(int customer_id) {
		 String sql="DELETE FROM users WHERE customer_id="+customer_id; 
		    return jdbctemplate.update(sql);
		   
	}




	public List<Admin> GetFullAdminDetails() {
		String query="select * from admindatabase";
		return jdbctemplate.query(query,new RowMapper<Admin>(){ 
	        public Admin mapRow(ResultSet rs, int row) throws SQLException {    
	            Admin admin=new Admin();
	            admin.setUsername(rs.getString("username"));
	    		admin.setPassword(rs.getString("password"));
	    		return admin;}
	        }); 
		
		
		
		
	}




	public int deleteAdmin(String username) {
		
		 String sql="DELETE FROM admindatabase WHERE username="+"'"+username+"'"; 
		    return jdbctemplate.update(sql);
		
		
		
	}


	public int changeprice(int price, int food_id) {
		
		 String sql="update fooditem set food_price="+price+" where food_id="+food_id;    
		    return jdbctemplate.update(sql);
	}

	
	public List<OrderDetails> getOrderDetails() {   
        return jdbctemplate.query("select * from  o_details",new RowMapper<OrderDetails>(){    
            public OrderDetails mapRow(ResultSet resultset, int row) throws SQLException {    
            	OrderDetails details=new OrderDetails(); 
                details.setFood_id(resultset.getInt("food_id"));
                details.setCustomer_id(resultset.getInt("customer_id"));    
                details.setOrder_quantity(resultset.getInt("order_quantity"));    
                
                return details;    
            }    
        });    
	}
	
	
	
	public int updatefoodcount(int foodcount,int food_id) {
		
		 String sql="update fooditem set final_quantity=final_quantity+"+foodcount+" where food_id="+food_id;    
		    return jdbctemplate.update(sql);
	}
	
	
	
	
	

}
