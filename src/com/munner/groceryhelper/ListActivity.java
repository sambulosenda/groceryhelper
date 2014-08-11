package com.munner.groceryhelper;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ListActivity extends Activity{

	ArrayList<String> al;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		if (savedInstanceState != null) {
			al = savedInstanceState.getStringArrayList("list");
		}
		else {
			al = new ArrayList<String>();
		}
//		if (al.size() == 0) {
//			Toast.makeText(getApplicationContext(), "Nothing to Remove", Toast.LENGTH_SHORT).show();
//			finish();
//		}
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
		return super.onOptionsItemSelected(item);
	}
	
	public void addToList(View view) {
		EditText et = (EditText) findViewById(R.id.ListText1);
		al.add(et.getText().toString());
		ListView lv = (ListView) findViewById(R.id.listView1);
		lv.invalidateViews();
	}
	
	@Override
	public void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		bundle.putStringArrayList("list", al);
	}
	
}
