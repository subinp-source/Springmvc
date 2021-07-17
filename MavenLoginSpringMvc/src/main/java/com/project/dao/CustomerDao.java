package com.project.dao;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.servlet.ModelAndView;

import com.project.modelclass.Cart;
import com.project.modelclass.Cartlisting;
import com.project.modelclass.Customer;
import com.project.modelclass.Food;
import com.project.modelclass.FoodCartAzheekal;
import com.project.modelclass.OrderDetails;
import com.project.modelclass.OutOfStock;
import com.project.modelclass.Price;

public class CustomerDao {
	private JdbcTemplate jdbctemplate;
	
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	public List<Customer> getCustomerData(String username,String password){
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
	
	    public  Boolean  saveCustomerDetails(final Customer customer) throws SQLIntegrityConstraintViolationException,DuplicateKeyException{ 
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
	    

	public int updateFood(int Count,int food_id) {
		  String sql="update fooditem set final_quantity=final_quantity-"+Count+" where food_id="+food_id;    
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

	public int updateorder(int food_id, int id, int value) {
			
			String sql="insert into order_details(customer_id,food_id,order_quantity) values("+id+","+food_id+","+value+")";    
		    return jdbctemplate.update(sql);	
		
	}
	
	public int updatetablevalues(String food_item, int id, int value) {
		String sql="insert into temp(customer_id,food_item,order_quantity) values("+id+","+"'"+food_item+"'"+","+value+")";    
	    return jdbctemplate.update(sql);
		
	}
	
	public int emptyTable() {
		String sql="TRUNCATE TABLE temp";    
	    return jdbctemplate.update(sql);
		
	}
	
	public int updateFoodCart(int value, String food_item) {
		
		 String sql="update foodcart set quantity=quantity+"+value+" where food_item="+"'"+food_item+"'";    
		    return jdbctemplate.update(sql);
		
	}
	public int sumAdditionToCartTable(int Initialsum, String food_item) {
		
		 String sql="update foodcart set sum=sum+"+Initialsum+" where food_item="+"'"+food_item+"'";    
		    return jdbctemplate.update(sql);
		
	}
	public List<FoodCartAzheekal> getFoodCartTable() {
		
		 return (List<FoodCartAzheekal>) jdbctemplate.query("select * from foodcart",new RowMapper<FoodCartAzheekal>(){    
	            public FoodCartAzheekal mapRow(ResultSet resultset, int row) throws SQLException {    
	                FoodCartAzheekal foodcart=new FoodCartAzheekal();
	                foodcart.setFood_item(resultset.getString("food_item")); 
	                foodcart.setQuantity(resultset.getInt("quantity")); 
	                foodcart.setSum(resultset.getInt("sum"));
	                return foodcart;    
	            }    
	        }); 
		
	}
	
	
	
	
	public int deleteTableFoodcartQuantityDosa() {
		

		 String sql="update foodcart set quantity="+0+" where food_id="+"'"+1+"'";    
		    return jdbctemplate.update(sql);
		
	}
	
	public int deleteTableFoodcartQuantityChapathi() {
		

		 String sql="update foodcart set quantity="+0+" where food_id="+"'"+2+"'";    
		    return jdbctemplate.update(sql);
		
	}
	
	public int deleteTableFoodcartQuantityBeefRoast() {
		

		 String sql="update foodcart set quantity="+0+" where food_id="+"'"+3+"'";    
		    return jdbctemplate.update(sql);
		
	}
	
	public int deleteTableFoodcartSumBeefRoast() {
		

		 String sql="update foodcart set sum="+0+" where food_id="+"'"+3+"'";    
		    return jdbctemplate.update(sql);
		
	}
	
	public int deleteTableFoodcartSumChapathi() {
		

		 String sql="update foodcart set sum="+0+" where food_id="+"'"+2+"'";    
		    return jdbctemplate.update(sql);
		
	}
	
	
	public int deleteTableFoodcartSumDosa() {
		

		 String sql="update foodcart set sum="+0+" where food_id="+"'"+1+"'";    
		    return jdbctemplate.update(sql);
		
	}
	
	
	
	public List<OutOfStock> stockquantitychecker(int food_id) {
		
		
		 return (List<OutOfStock>) jdbctemplate.query("select * from fooditem where food_id="+food_id,new RowMapper<OutOfStock>(){    
	            public OutOfStock mapRow(ResultSet resultset, int row) throws SQLException {    
	            	OutOfStock outofstock=new OutOfStock();
	            	outofstock.setQuantity(resultset.getInt("final_quantity")); 
	                return outofstock;    
	            }    
	        }); 
		
		
	}

	
	public int checker(int quantity,int value) {
		if(quantity!=0) {
		if(quantity>=value) {
			return value;
		}else {
			return quantity;
		}}else {
			return 1;
		}
	}
	public int deleteTableFoodcartQuantityEggmasala() {
		String sql="update foodcartalibabarolls set quantity="+0+" where food_id="+"'"+1+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumEggmasala() {
		String sql="update foodcartalibabarolls set sum="+0+" where food_id="+"'"+1+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityChickennoodles() {
		String sql="update foodcartalibabarolls set quantity="+0+" where food_id="+"'"+2+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumChickennoodles() {
		 String sql="update foodcartalibabarolls set sum="+0+" where food_id="+"'"+2+"'";    
		    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityEggchowmein() {
		String sql="update foodcartalibabachowmein set quantity="+0+" where food_id="+"'"+1+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumEggchowmein() {
		String sql="update foodcartalibabachowmein set sum="+0+" where food_id="+"'"+1+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityChickenchowmein() {
		String sql="update foodcartalibabachowmein set quantity="+0+" where food_id="+"'"+2+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumChickenchowmein() {
		 String sql="update foodcartalibabachowmein set sum="+0+" where food_id="+"'"+2+"'";    
		    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityPrawschowmein() {
		String sql="update foodcartalibabachowmein set quantity="+0+" where food_id="+"'"+3+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumPrawschowmein() {
		String sql="update foodcartalibabachowmein set sum="+0+" where food_id="+"'"+3+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityTomatosoup() {
		String sql="update foodcartazheekalsoup set quantity="+0+" where food_id="+"'"+1+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumTomatosoup() {
		String sql="update foodcartazheekalsoup set sum="+0+" where food_id="+"'"+1+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityCornsoup() {
		String sql="update foodcartazheekalsoup set quantity="+0+" where food_id="+"'"+2+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumCornsoup() {
		String sql="update foodcartazheekalsoup set sum="+0+" where food_id="+"'"+2+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityCarrotsoup() {
		String sql="update foodcartazheekalsoup set quantity="+0+" where food_id="+"'"+3+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumCarrotsoup() {
		String sql="update foodcartazheekalsoup set sum="+0+" where food_id="+"'"+3+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityFishfry() {
		String sql="update foodcartazheekalstartersdeepfry set quantity="+0+" where food_id="+"'"+1+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumFishfry() {
		String sql="update foodcartazheekalstartersdeepfry set sum="+0+" where food_id="+"'"+1+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityOmlet() {
		String sql="update foodcartazheekalstartersdeepfry set quantity="+0+" where food_id="+"'"+2+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumCornOmlet() {
		String sql="update foodcartazheekalstartersdeepfry set sum="+0+" where food_id="+"'"+2+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartQuantityEggpakoda() {
		String sql="update foodcartazheekalstartersdeepfry set quantity="+0+" where food_id="+"'"+3+"'";    
	    return jdbctemplate.update(sql);
		
	}
	public int deleteTableFoodcartSumEggpakoda() {
		String sql="update foodcartazheekalstartersdeepfry set sum="+0+" where food_id="+"'"+3+"'";    
	    return jdbctemplate.update(sql);
		
	}
	
	
	
	
	
	
	
	
	
	
}	


	
	
	

