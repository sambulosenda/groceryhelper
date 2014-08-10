package com.munner.groceryhelper;

import java.util.ArrayList;

public class ItemList{
	
	ArrayList<Item> al = new ArrayList();
	private double total;
	public ItemList() {
		this.total = 0.0;
	}
	public void addToTotal(double value) {
		// TODO Auto-generated method stub
		total += value;
	}
	public double getTotal() {
		// TODO Auto-generated method stub
		return total;
	}
	public void addItem(double cost, String category) {
		// TODO Auto-generated method stub
		addToTotal(cost);
		Item it = new Item(cost, category);
		al.add(it);
		
	}

}
