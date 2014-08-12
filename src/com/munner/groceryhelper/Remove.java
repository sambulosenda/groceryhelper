package com.munner.groceryhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Remove extends Activity {

	String[] sList;
	ArrayAdapter<String> adapter;
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove);
		Intent intent = getIntent();
		
		sList = intent.getStringArrayExtra("il");
		if (sList.length == 0) {
			Toast.makeText(getApplicationContext(), "Nothing to Remove", Toast.LENGTH_SHORT).show();
			finish();
		}
		lv = (ListView) findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, sList);
		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove, menu);
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
		else if (id == R.id.removelist) {
			int index = lv.getCheckedItemPosition();
			if (index == -1) {
				Toast.makeText(getApplicationContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
				return true;
			}
			Toast.makeText(getApplicationContext(), "Item removed", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent("remove");
			intent.putExtra("index", index);
			sendBroadcast(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}


}
