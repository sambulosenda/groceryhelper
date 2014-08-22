package com.munner.groceryhelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class ListActivity extends Activity{

	private static final String PREFERENCES = "preferences";
	ArrayAdapter<String> adapter;
	ArrayList<String> al;
	ListView lv;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		al = new ArrayList<String>();

		Set<String> set = new HashSet<String>();
		SharedPreferences info = getSharedPreferences(PREFERENCES, 0);
		al.addAll(info.getStringSet("set", set));
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, al);
		lv = (ListView) findViewById(R.id.listGList);
		lv.setAdapter(adapter);

		EditText et = (EditText) findViewById(R.id.ListText1);
		et.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView et, int actionId, KeyEvent event) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN){
					if (et.getText().toString().equals("")) {
						//do nothing
					}
					else { 
						al.add(et.getText().toString());
						adapter.notifyDataSetChanged();
						et.setText(null);
						saveinfo();
					}
					handled = true;
				}
				return handled;
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		switch (id) {
		
		case R.id.action_settings:
			return true;
			
		case R.id.resetList:
			al.clear();
			adapter.notifyDataSetChanged();
			break;
		case R.id.listCheck:
			int index = lv.getCheckedItemPosition();
			if (index == -1) {
				Toast.makeText(getApplicationContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
			}
			else {
				Toast.makeText(getApplicationContext(), al.get(index) + " Removed", Toast.LENGTH_SHORT).show();				
				al.remove(index);
				adapter.notifyDataSetChanged();
				lv.clearChoices();

			}
			break;
		default: 
			break;
		}
		saveinfo();
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		saveinfo();
	}

	protected void saveinfo() {
		Set<String> set = new HashSet<String>();
		set.addAll(al);
		SharedPreferences info = getSharedPreferences(PREFERENCES, 0);
		SharedPreferences.Editor editor = info.edit();
		editor.putStringSet("set", set);
		editor.commit();
	}
	
}
