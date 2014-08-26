package com.li.personInfoActivity;


import com.example.android.BluetoothChat.R;
import com.li.database.*;
import com.li.dataholder.PersonalInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class PersonInfoActivity extends Activity {
	
	private Button backButton;
	private Button updateButton;
	private Button deleteButton;
	private TextView nameview;
	private TextView dateview;
	private TextView ageview;
	private TextView addressview;
	private TextView contactview;
	private TextView hightview;
	private TextView weightview;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalinfo);
		
		nameview = (TextView)findViewById(R.id.casehistory_name);
		dateview = (TextView)findViewById(R.id.casehistory_dateofbirth);
		ageview = (TextView)findViewById(R.id.personalInfo_clientage);
		addressview = (TextView)findViewById(R.id.personalInfo_clientaddress);
		contactview = (TextView)findViewById(R.id.personalInfo_clientcontact);
		hightview = (TextView)findViewById(R.id.personalInfo_hight);
		weightview = (TextView)findViewById(R.id.casehistory_sight);
				
		databaseAccesser da = new databaseAccesser(this);
		
		da.open();
		da.insertData();
		PersonalInfo pi = da.getData();
		da.close();
					
		nameview.setText(pi.getName());
		dateview.setText(pi.getDate());
		ageview.setText(pi.getAge());
		addressview.setText(pi.getAddress());
		contactview.setText(pi.getContact());
		hightview.setText(pi.getHight());
		weightview.setText(pi.getWeight());
		
		backButton = (Button)findViewById(R.id.personalInfo_backBT);
		
		backButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
	}

}
