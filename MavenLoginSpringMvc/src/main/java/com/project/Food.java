package com.project;

public class Food {
	
	
	private String food_item;
	private int food_price;
	private int final_quantity;
	public String getFood_item() {
		return food_item;
	}
	public void setFood_item(String food_item) {
		this.food_item = food_item;
	}
	public int getFood_price() {
		return food_price;
	}
	public void setFood_price(int food_price) {
		this.food_price = food_price;
	}
	public int getFinal_quantity() {
		return final_quantity;
	}
	public void setFinal_quantity(int final_quantity) {
		this.final_quantity = final_quantity;
	}
	@Override
	public String toString() {
		return "Food [food_item=" + food_item + ", food_price=" + food_price + ", final_quantity=" + final_quantity
				+ "]";
	}
	
	

}
