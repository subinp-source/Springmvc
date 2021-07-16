package com.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.project.modelclass.FoodCart;
import com.project.modelclass.FoodCartAlibaba;
import com.project.modelclass.FoodListOfAlibaba;
import com.project.modelclass.Price;

public class CustomerDaoAlibabaRestaurant {
private JdbcTemplate jdbctemplate;
	
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	public List<FoodListOfAlibaba> LoadFoodDetails(String foodlist) {
		
		 return (List<FoodListOfAlibaba>) jdbctemplate.query("select * from "+foodlist,new RowMapper<FoodListOfAlibaba>(){    
	            public FoodListOfAlibaba mapRow(ResultSet resultset, int row) throws SQLException {    
	            	FoodListOfAlibaba foodlistofalibaba=new FoodListOfAlibaba();
	            	foodlistofalibaba.setFood_id(resultset.getInt("food_id"));
	            	foodlistofalibaba.setFood_price(resultset.getInt("food_price"));
	            	foodlistofalibaba.setFood_item(resultset.getString("food_item"));
	            	foodlistofalibaba.setQuantity(resultset.getInt("quantity"));
	                return foodlistofalibaba;    
	            }    
	        });
		
		
		
	}
	public int updateFoodrolls(int value, int food_id) {
		String sql="update rolls set quantity=quantity-"+value+" where food_id="+food_id;    
	    return jdbctemplate.update(sql);
		
	}
	public int updateFoodchowmein(int value, int food_id) {
		String sql="update chowmein set quantity=quantity-"+value+" where food_id="+food_id;    
	    return jdbctemplate.update(sql);
		
	}
	public int updateFoodCartrolls(int value, int food_id) {
		
		String sql="update foodcartalibabarolls set quantity=quantity+"+value+" where food_id="+food_id;    
	    return jdbctemplate.update(sql);
	}

	
public int updateFoodCartchowmein(int value, int food_id) {
		
		String sql="update foodcartalibabachowmein set quantity=quantity+"+value+" where food_id="+food_id;    
	    return jdbctemplate.update(sql);
	}
public int sumAdditionToCartTablerolls(int initialsum, int food_id) {
	
	 String sql="update foodcartalibabarolls set sum=sum+"+initialsum+" where food_id="+food_id;    
	    return jdbctemplate.update(sql);
	
}
public int sumAdditionToCartTablechowmein(int initialsum, String food_item) {
	
	 String sql="update foodcartalibabachowmein set sum=sum+"+initialsum+" where food_item="+"'"+food_item+"'";    
	    return jdbctemplate.update(sql);
	
}
public int updateorderrolls(int food_id, int id, int value) {
	String sql="insert into updateorderrolls(customer_id,food_id,order_quantity) values("+id+","+food_id+","+value+")";    
    return jdbctemplate.update(sql);	
	
}
	
public int updateorderchowmein(int food_id, int id, int value) {
	String sql="insert into updateorderchowmein(customer_id,food_id,order_quantity) values("+id+","+food_id+","+value+")";    
    return jdbctemplate.update(sql);	
	
}
public List<FoodCartAlibaba> getFoodCartTablealibabarolls() {
	
	 return (List<FoodCartAlibaba>) jdbctemplate.query("select * from foodcartalibabarolls",new RowMapper<FoodCartAlibaba>(){    
         public FoodCartAlibaba mapRow(ResultSet resultset, int row) throws SQLException {    
        	 FoodCartAlibaba foodcart=new FoodCartAlibaba();
             foodcart.setFood_item(resultset.getString("food_item")); 
             foodcart.setQuantity(resultset.getInt("quantity")); 
             foodcart.setSum(resultset.getInt("sum"));
             return foodcart;    
         }    
     }); 
	
}



public List<FoodCartAlibaba> getFoodCartTablealibabachowmein() {
	
	 return (List<FoodCartAlibaba>) jdbctemplate.query("select * from foodcartalibabachowmein",new RowMapper<FoodCartAlibaba>(){    
         public FoodCartAlibaba mapRow(ResultSet resultset, int row) throws SQLException {    
        	 FoodCartAlibaba foodcart=new FoodCartAlibaba();
             foodcart.setFood_item(resultset.getString("food_item")); 
             foodcart.setQuantity(resultset.getInt("quantity")); 
             foodcart.setSum(resultset.getInt("sum"));
             return foodcart;    
         }    
     }); 
	
	
	
}



}
