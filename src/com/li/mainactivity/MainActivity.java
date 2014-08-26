package com.li.mainactivity;

import java.util.ArrayList;




import com.example.android.BluetoothChat.BluetoothReceiver;
import com.example.android.BluetoothChat.R;
import com.li.dataholder.ListData;
import com.li.listActivity.*;
import com.li.personInfoActivity.PersonInfoActivity;
import com.li.privacy.PrivactListActivity;
import com.li.receive.ReceiveActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity{
	
	private ListView lv;
	private Button logoffButton;
	private Button exitButton;
	private ArrayList<String> list;
	
	private ListData[] dataQueue;
	
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		lv = (ListView) findViewById(R.id.listView);
		logoffButton = (Button) findViewById(R.id.privacy_contentbutton);
		exitButton = (Button) findViewById(R.id.main_exitbuttone);
		
		ListData ld = new ListData();
		dataQueue = ld.buildMainListData();
		
		list = new ArrayList<String>();
		
		ListViewAdapter adapter1 = new ListViewAdapter(this,R.layout.main_activity_listitem,dataQueue);  
		lv.setAdapter(adapter1);
		
		
		exitButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		
		lv.setOnItemClickListener(new OnItemClickListener(){


			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(arg2 == 0){
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, PersonInfoActivity.class);
					startActivity(intent);
					
				}
				else if(arg2 == 1){
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, ListViewActivity.class);
					startActivity(intent);
					
					
					
				}
				else if(arg2 == 2){
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, PrivactListActivity.class);
					startActivity(intent);
					
				}
				else if(arg2 == 3){
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, BluetoothReceiver.class);
					startActivity(intent);
				}
			}
			
		});
	}

}
