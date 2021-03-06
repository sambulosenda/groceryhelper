package com.munner.groceryhelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity {

	private static final String PREFERENCES = "preferences";
	ItemList il;
	ArrayAdapter<String> adapter;
	ArrayList<String> al;
	
	@Override
	protected void onCreate(Bundle bundle) {
		
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		
		if (bundle != null) {
			il = bundle.getParcelable("list");
			updateDisplay();
		}

		else {
			il = new ItemList();
		}
		
		IntentFilter filter = new IntentFilter("remove");
		BroadcastReceiver receiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if(intent.getAction().equals("remove")) {
					// Do my stuff
					int index = intent.getIntExtra("index", 0);
					il.removeItem(index);
					updateDisplay();
				}
			}
		};
		registerReceiver(receiver, filter);
		
		EditText et = (EditText) findViewById(R.id.mainText1);
		//not modifying
		et.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView et, int actionId, KeyEvent event) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN){
					if (et.getText().toString().equals("")) {
						//do nothing
					}
					else {
						double	 cost = Double.parseDouble(et.getText().toString());
						// new String() should be a string containing the product category
						Spinner sp = (Spinner) findViewById(R.id.spinner1);
						String category = sp.getSelectedItem().toString();
						
						il.addItem(cost, category);
						updateDisplay();

						Toast.makeText(getApplicationContext(), category + " " 
										+ Double.toString(cost), Toast.LENGTH_SHORT).show();

						et.setText(null);
						handled = true;
					}
				}
				return handled;
			}

		});
		al = new ArrayList<String>();

		Set<String> set = new HashSet<String>();
		SharedPreferences info = getSharedPreferences(PREFERENCES, 0);
		al.addAll(info.getStringSet("set", set));
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, al);
		ListView lv = (ListView) findViewById(R.id.mainGList);
		lv.setAdapter(adapter);

		
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
		else if (id == R.id.item1) {
			Intent intent = new Intent(this, ListActivity.class);
			startActivity(intent);
			return true;
		}
		else if (id == R.id.mainreset){
			il.clear();
			updateDisplay();
			Toast.makeText(getApplicationContext(), "Reset all Items", Toast.LENGTH_SHORT).show();
			return true;
		}
		else if (id == R.id.mainremove) {
			if (il.size() <= 0 ) {
				Toast.makeText(getApplicationContext(), "Nothing to remove", Toast.LENGTH_SHORT).show();
				return true;
			}
			Intent intent = new Intent(this, Remove.class);
			intent.putExtra("il", il.getStringList());
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override 
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		bundle.putParcelable("list", il);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Set<String> set = new HashSet<String>();
		SharedPreferences info = getSharedPreferences(PREFERENCES, 0);
		al.clear();
		al.addAll(info.getStringSet("set", set));
		adapter.notifyDataSetChanged();
		
	}	

	private void updateDisplay() {
		TextView tv = (TextView) findViewById(R.id.textView10);
		tv.setText(Double.toString(il.getTotal()));

		TextView other = (TextView) findViewById(R.id.TextView06);
		other.setText(Double.toString(il.getCost("other")));

		TextView produce = (TextView) findViewById(R.id.textView9);
		produce.setText(Double.toString(il.getCost("produce")));

		TextView meat = (TextView) findViewById(R.id.TextView01);
		meat.setText(Double.toString(il.getCost("meat")));
		
		TextView alcohol = (TextView) findViewById(R.id.TextView02);
		alcohol.setText(Double.toString(il.getCost("alcohol")));
		
		TextView dairy = (TextView) findViewById(R.id.TextView03);
		dairy.setText(Double.toString(il.getCost("dairy")));
		
		TextView deli = (TextView) findViewById(R.id.TextView04);
		deli.setText(Double.toString(il.getCost("deli")));
		
		TextView bread = (TextView) findViewById(R.id.TextView05);
		bread.setText(Double.toString(il.getCost("bread")));
		
		TextView frozen = (TextView) findViewById(R.id.TextView07);
		frozen.setText(Double.toString(il.getCost("frozen")));
		
		TextView snacks = (TextView) findViewById(R.id.TextView09);
		snacks.setText(Double.toString(il.getCost("snacks")));
		
	}	
	
}
