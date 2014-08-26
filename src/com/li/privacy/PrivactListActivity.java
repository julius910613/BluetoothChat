package com.li.privacy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.BluetoothChat.R;
import com.li.database.CaseHistoryDatabase;
import com.li.dataholder.CaseHistory;
import com.li.dataholder.DataStorge;
import com.li.dataholder.ListData;

import com.li.listActivity.ListViewAdapter;
import com.li.selectActivity.SelectActivity;

public class PrivactListActivity extends Activity {
	private String SerializableKey = "data";

	private ListData[] dataQueue;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewactivity);
		CaseHistoryDatabase cd = new CaseHistoryDatabase(PrivactListActivity.this);
		cd.open();
	    cd.insertData();
		ArrayList<CaseHistory> casehistory = cd.getlineData();
		cd.delectAll();
		cd.close();

		final DataStorge ds = new DataStorge();
		ds.getCaseHistoryline(casehistory);
		dataQueue = ds.returnlineList();

		ListView lv = (ListView) findViewById(R.id.listView_1);

		ListViewAdapter adapter1 = new ListViewAdapter(this,
				R.layout.clientdataitem, dataQueue);

		// ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1);

		lv.setAdapter(adapter1);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				Intent in = new Intent();
				in.setClass(PrivactListActivity.this, PrivacyActivity.class);

				// 实例化一个SerializableBook对象
				CaseHistory ch = ds.getCaseHistorylineData(arg2);

				Bundle extras = new Bundle();
				extras.putSerializable(SerializableKey, ch);

				in.putExtras(extras);
				startActivity(in);

			}

		});

	}
	
	

}
