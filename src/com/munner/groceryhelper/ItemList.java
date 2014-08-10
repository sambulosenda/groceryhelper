package com.munner.groceryhelper;

import java.util.ArrayList;

public class ItemList{
	
	ArrayList<Item> al = new ArrayList<Item>();
	private double total;
	private double produce;
	private double bread;
	private double alcohol;
	private double deli;
	private double dairy;
	private double meat;
	private double other;
	public ItemList() {
		this.total = 0.0;
	}
	public void updateTotal() {
		// TODO Auto-generated method stub
		total = produce + bread + alcohol + deli + dairy + meat + other;
	}
	public double getTotal() {
		// TODO Auto-generated method stub
		return round(total);
	}
	public void addItem(double cost, String category) {
		// TODO Auto-generated method stub
		Item it = new Item(cost, category);
		al.add(it);
		if (category.equals("produce")) {
			produce += cost;
		}
		else if (category.equals("bread")) {
			bread += cost;
		}
		else if (category.equals("alcohol")) {
			alcohol += cost;
		}
		else if (category.equals("deli")) {
			deli += cost;
		}
		else if (category.equals("dairy")) {
			dairy += cost;
		}
		else if (category.equals("meat")) {
			meat += cost;
		}
		else if (category.equals("other")) {
			other += cost;
		}
		
		updateTotal();
		
	}
	public double getOther() {
		// TODO Auto-generated method stub
		return round(other);
	}
	public double getProduce() {
		// TODO Auto-generated method stub
		return round(produce);
	}
	public double getMeat() {
		// TODO Auto-generated method stub
		return round(meat);
	}
	public double getAlcohol() {
		// TODO Auto-generated method stub
		return round(alcohol);
	}
	public double getDairy() {
		// TODO Auto-generated method stub
		return round(dairy);
	}
	public double getBread() {
		// TODO Auto-generated method stub
		return round(bread);
	}
	public double getDeli() {
		// TODO Auto-generated method stub
		return round(deli);
	}
	
	private double round(double value) {
		return Math.floor(value * 100) / 100;
	}

}
