package com.munner.groceryhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ItemList il;
	
	@Override
	protected void onCreate(Bundle bundle) {
		
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
 		il = new ItemList();
		
		if (bundle != null) {
			il.addItem(bundle.getDouble("meat"), "meat");
			il.addItem(bundle.getDouble("produce"), "produce");
			il.addItem(bundle.getDouble("alcohol"), "alcohol");
			il.addItem(bundle.getDouble("dairy"), "dairy");
			il.addItem(bundle.getDouble("deli"), "deli");
			il.addItem(bundle.getDouble("bread"), "bread");
			il.addItem(bundle.getDouble("other"), "other");
	        Toast.makeText(getApplicationContext(), Double.toString(bundle.getDouble("bread")), Toast.LENGTH_SHORT).show();
	        updateDisplay();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override 
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		double meat = il.getMeat();
		double produce = il.getProduce();
		double deli = il.getDeli();
		double bread = il.getBread();
		double other = il.getOther();
		double alcohol = il.getAlcohol();
		double dairy = il.getDairy();
		bundle.putDouble("meat", meat);
		bundle.putDouble("produce", produce);
		bundle.putDouble("other", other);
		bundle.putDouble("deli", deli);
		bundle.putDouble("bread", bread);
		bundle.putDouble("alcohol", alcohol);
		bundle.putDouble("dairy", dairy);
	}
	
	public void buttonAdd(View view) {
		EditText et = (EditText) findViewById(R.id.editText1);
		if (et.getText().toString().equals("")) {
			//do nothing
		}
		else {
			double	 cost = Double.parseDouble(et.getText().toString());
			// new String() should be a string containing the product category
			Spinner sp = (Spinner) findViewById(R.id.spinner1);
			String category = sp.getSelectedItem().toString();
			
	        Toast.makeText(getApplicationContext(), "Selected Item :" + category, Toast.LENGTH_SHORT).show();
			
			il.addItem(cost, category);
			updateDisplay();		

			et.setText(null);
		}
	}
	
	public void reset(View view) {
		il = new ItemList();
		updateDisplay();
	}

	private void updateDisplay() {
		TextView tv = (TextView) findViewById(R.id.textView10);
		tv.setText(Double.toString(il.getTotal()));

		TextView other = (TextView) findViewById(R.id.TextView06);
		other.setText(Double.toString(il.getOther()));

		TextView produce = (TextView) findViewById(R.id.textView9);
		produce.setText(Double.toString(il.getProduce()));

		TextView meat = (TextView) findViewById(R.id.TextView01);
		meat.setText(Double.toString(il.getMeat()));
		
		TextView alcohol = (TextView) findViewById(R.id.TextView02);
		alcohol.setText(Double.toString(il.getAlcohol()));
		
		TextView dairy = (TextView) findViewById(R.id.TextView03);
		dairy.setText(Double.toString(il.getDairy()));
		
		TextView deli = (TextView) findViewById(R.id.TextView04);
		deli.setText(Double.toString(il.getDeli()));
		
		TextView bread = (TextView) findViewById(R.id.TextView05);
		bread.setText(Double.toString(il.getBread()));
	}	
	
}
