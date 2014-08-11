package com.munner.groceryhelper;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{

	double cost;
	String category;
	
	public Item(double cost, String category) {
		this.cost = cost;
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	
	public double getCost() {
		return cost;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeDouble(cost);
		dest.writeString(category);
	}
}
