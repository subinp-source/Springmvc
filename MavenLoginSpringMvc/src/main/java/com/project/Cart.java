package com.project;

public class Cart {
	
	private int customer_id;
	private String food_item;
	private int order_quantity;
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getFood_item() {
		return food_item;
	}
	public void setFood_item(String food_item) {
		this.food_item = food_item;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	@Override
	public String toString() {
		return "Cart [customer_id=" + customer_id + ", food_item=" + food_item + ", order_quantity=" + order_quantity
				+ "]";
	}
	

}
