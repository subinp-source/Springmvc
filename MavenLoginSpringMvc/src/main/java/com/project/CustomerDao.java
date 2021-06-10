package com.project;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

public class CustomerDao {
	private JdbcTemplate jdbctemplate;
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	public List<Customer> getData(String username,String password){
		String query="select * from users where username='"+username+"' and password='"+password+"'";
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
	
	    public  Boolean  saveEmployeeByPreparedStatement(final Customer e){  
	    String query="insert into users(username,password,firstname,lastname,email,address,phone) values(?,?,?,?,?,?,?)";  
	    return jdbctemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException {  
	              
	        ps.setString(1,e.getUsername());  
	        ps.setString(2,e.getPassword());  
	        ps.setString(3,e.getFirstname());
	        ps.setString(4,e.getLastname());  
	        ps.setString(5,e.getEmail());  
	        ps.setString(6,e.getAddress()); 
	        ps.setString(7,e.getPhone());
	         return ps.execute();
	              
	    }  
	    }); 
	}  
	    
	    
	    public List<Food> getFoodDetails(){    
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
	    

	public int updateFood(int Count,int food_id) {
		  String sql="update fooditem set final_quantity=final_quantity-"+Count+" where food_id="+food_id;    
		    return jdbctemplate.update(sql);
		
	}
	
	
	public int updatefoodcount(int foodcount,int food_id) {
		
		 String sql="update fooditem set final_quantity=final_quantity+"+foodcount+" where food_id="+food_id;    
		    return jdbctemplate.update(sql);
	}
	
	
	
	
	
	
	public List<Price> price(int food_id) {
		 return (List<Price>) jdbctemplate.query("select food_price from fooditem where food_id="+food_id,new RowMapper<Price>(){    
	            public Price mapRow(ResultSet resultset, int row) throws SQLException {    
	                Price price=new Price();    
	                price.setEach_price(resultset.getInt("food_price"));    
	                return price;    
	            }    
	        }); 
	}
	public int changeprice(int price, int food_id) {
		
		 String sql="update fooditem set food_price="+price+" where food_id="+food_id;    
		    return jdbctemplate.update(sql);
	}
	
	
	public int updateorder(int food_id, int id, int value) {
		
		String sql="insert into o_details(customer_id,food_id,order_quantity) values("+id+","+food_id+","+value+")";    
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
	
	
	
    
	    
}	  
	
	
	

