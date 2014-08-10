package com.munner.groceryhelper;

import java.util.ArrayList;

public class ItemList{
	
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

}
