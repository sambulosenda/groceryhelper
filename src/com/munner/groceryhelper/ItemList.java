package com.munner.groceryhelper;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemList implements Parcelable{
	
	ArrayList<Item> al;
	public ItemList() {
		al = new ArrayList<Item>();
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(al);
	}

	public ArrayList<Item> getarrayList() {
		return al;
	}
	
	public String[] getStringList() {
		int i;
		int j = al.size();
		String[] sList = new String[j];
		for (i=0; i < al.size(); i++) {
			sList[i] = al.get(i).getCost() + " " + al.get(i).getCategory();
		}
		return sList;
	}
	
	public double getTotal() {
		double total = 0.0;
		int i;
		for (i = 0; i < al.size(); i++) {
			total += al.get(i).getCost();
		}
		return round(total);
	}

	public double getCost(String category) {
		int i;
		double total = 0.0;
		for (i= 0; i < al.size(); i++) {
			if (al.get(i).getCategory().equals(category)) {
				total += al.get(i).getCost();
			}
		}
		return round(total);
	}

	public void addItem(double cost, String category) {
		Item it = new Item(cost, category);
		al.add(it);
	}
	
	public void removeItem(int index) {
		al.remove(index);
	}
	
	private double round(double value) {
		return Math.floor(value * 100) / 100;
	}
	
}
