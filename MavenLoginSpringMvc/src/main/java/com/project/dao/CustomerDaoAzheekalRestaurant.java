package com.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.project.modelclass.FoodCartAlibaba;
import com.project.modelclass.FoodCartAzheekal;
import com.project.modelclass.FoodListOfRestaurant;

public class CustomerDaoAzheekalRestaurant {
	
		private JdbcTemplate jdbctemplate;
			
			public void setJdbctemplate(JdbcTemplate jdbctemplate) {
				this.jdbctemplate = jdbctemplate;
			}
	

	public List<FoodListOfRestaurant> LoadFoodDetails(String foodlist) {
		
		 return (List<FoodListOfRestaurant>) jdbctemplate.query("select * from "+foodlist,new RowMapper<FoodListOfRestaurant>(){    
	            public FoodListOfRestaurant mapRow(ResultSet resultset, int row) throws SQLException {    
	            	FoodListOfRestaurant foodlistofalibaba=new FoodListOfRestaurant();
	            	foodlistofalibaba.setFood_id(resultset.getInt("food_id"));
	            	foodlistofalibaba.setFood_price(resultset.getInt("food_price"));
	            	foodlistofalibaba.setFood_item(resultset.getString("food_item"));
	            	foodlistofalibaba.setQuantity(resultset.getInt("quantity"));
	                return foodlistofalibaba;    
	            }    
	        });
		
	}
	
	

	public int updateFoodsoup(int value, int food_id) {
		
		String sql="update soup set quantity=quantity-"+value+" where food_id="+food_id;    
	    return jdbctemplate.update(sql);
		
	}



	public int updateFoodCartsoup(int value, int food_id) {
		
		String sql="update foodcartazheekalsoup set quantity=quantity+"+value+" where food_id="+food_id;    
	    return jdbctemplate.update(sql);
		
	}


	public int updateordersoup(int food_id, int id, int value) {
		String sql="insert into updateordersoup(customer_id,food_id,order_quantity) values("+id+","+food_id+","+value+")";    
	    return jdbctemplate.update(sql);
		
	}



	public int sumAdditionToCartTablesoup(int initialsum, String food_item) {
		String sql="update foodcartazheekalsoup set sum=sum+"+initialsum+" where food_item="+"'"+food_item+"'";    
	    return jdbctemplate.update(sql);
		
	}



	public int updateFoodstartersdeepfry(int value, int food_id) {
		String sql="update startersdeepfry set quantity=quantity-"+value+" where food_id="+food_id;    
	    return jdbctemplate.update(sql);
		
	}



	public int updateFoodCartstartersdeepfry(int value, int food_id) {
		String sql="update foodcartazheekalstartersdeepfry set quantity=quantity+"+value+" where food_id="+food_id;    
	    return jdbctemplate.update(sql);
	}


	public int sumAdditionToCartTablestartersdeepfry(int initialsum, String food_item) {
		
		String sql="update foodcartazheekalstartersdeepfry set sum=sum+"+initialsum+" where food_item="+"'"+food_item+"'";    
	    return jdbctemplate.update(sql);
	}



	public int updateorderstartersdeepfry(int food_id, int id, int value) {
		String sql="insert into updateorderstartersdeepfry(customer_id,food_id,order_quantity) values("+id+","+food_id+","+value+")";    
	    return jdbctemplate.update(sql);
		
	}


	public List<FoodCartAzheekal> getFoodCartTableAzheekalSoup() {
		 return (List<FoodCartAzheekal>) jdbctemplate.query("select * from foodcartazheekalsoup",new RowMapper<FoodCartAzheekal>(){    
	         public FoodCartAzheekal mapRow(ResultSet resultset, int row) throws SQLException {    
	        	 FoodCartAzheekal foodcart=new FoodCartAzheekal();
	             foodcart.setFood_item(resultset.getString("food_item")); 
	             foodcart.setQuantity(resultset.getInt("quantity")); 
	             foodcart.setSum(resultset.getInt("sum"));
	             return foodcart;    
	         }    
	     });
	}


	public List<FoodCartAzheekal> getFoodCartTableAzheekalStartersDeepfry() {
		 return (List<FoodCartAzheekal>) jdbctemplate.query("select * from foodcartazheekalstartersdeepfry",new RowMapper<FoodCartAzheekal>(){    
	         public FoodCartAzheekal mapRow(ResultSet resultset, int row) throws SQLException {    
	        	 FoodCartAzheekal foodcart=new FoodCartAzheekal();
	             foodcart.setFood_item(resultset.getString("food_item")); 
	             foodcart.setQuantity(resultset.getInt("quantity")); 
	             foodcart.setSum(resultset.getInt("sum"));
	             return foodcart;    
	         }    
	     });
	}
	

	
}