package com.munner.groceryhelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ListActivity extends Activity{

	private static final String PREFERENCES = "preferences";
	ArrayList<String> al;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		al = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		SharedPreferences info = getSharedPreferences(PREFERENCES, 0);
		al.addAll(info.getStringSet("set", set));
		
		ListView lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int index, long id) {
				al.remove(index);
				ListView lv = (ListView) findViewById(R.id.listView1);
				lv.invalidateViews();

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
		if (id == R.id.action_settings) {
			return true;
		}
//		if (id == R.id.listreset) {
//			int i;
//			ListView lv = (ListView) findViewById(R.id.listView1);
//			for (i = 0; i < al.size(); i++) {
//				al.remove(i);
//				lv.invalidateViews();
//			}
//		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addToList(View view) {
		EditText et = (EditText) findViewById(R.id.ListText1);
		if (et.getText().toString().equals("")) {
			//do nothing
		}
		else { 
			al.add(et.getText().toString());
			ListView lv = (ListView) findViewById(R.id.listView1);
			lv.invalidateViews();
			et.setText(null);
		}
	}
		
	@Override
	public void onStop() {
		super.onStop();
		Set<String> set = new HashSet<String>();
		set.addAll(al);
		SharedPreferences info = getSharedPreferences(PREFERENCES, 0);
		SharedPreferences.Editor editor = info.edit();
		editor.putStringSet("set", set);
		editor.commit();
	}
	
}
