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
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(cost);
		dest.writeString(category);
	}

}
