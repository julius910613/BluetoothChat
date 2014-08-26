package com.li.privacy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.BluetoothChat.BluetoothChat;
import com.example.android.BluetoothChat.R;
import com.li.dataholder.CaseHistory;
import com.li.mainactivity.MainActivity;
import com.li.personInfoActivity.PersonInfoActivity;

public class PrivacyCheckActivity extends Activity{
	private String SerializableKey = "data";

	private Button cancelButton;
	private Button updateButton;
	private Button sendButton;
	private TextView nameview;
	private TextView dateview;
	private TextView bloodtypeview;
	private TextView bloodpressureview;
	private TextView heartbeatview;
	private TextView xrayview;
	private TextView sightview;
	private TextView hearview;
	private TextView holdview;
	private TextView storenumview;
	int dataID;
	Handler handler;
	Thread t1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.privacy_check);

		Bundle bd = this.getIntent().getExtras();
		final CaseHistory cd = (CaseHistory) bd.get(SerializableKey);

		nameview = (TextView) findViewById(R.id.privacycheck_name);
		dateview = (TextView) findViewById(R.id.privacycheck_dateofbirth);
		bloodtypeview = (TextView) findViewById(R.id.privacycheck_bloodtype);
		bloodpressureview = (TextView) findViewById(R.id.privacycheck_bloodpressure);
		heartbeatview = (TextView) findViewById(R.id.privacycheck_heartbeat);
		xrayview = (TextView) findViewById(R.id.privacycheck_xray);
		sightview = (TextView) findViewById(R.id.privacycheck_sight);
		hearview = (TextView) findViewById(R.id.privacycheck_hearing);
		holdview = (TextView) findViewById(R.id.privacycheck_hold);
		storenumview = (TextView)findViewById(R.id.privacycheck_storenum);

		
		
		nameview.setText(cd.getName());
		dateview.setText(cd.getDateOfBirth());
		bloodtypeview.setText(cd.getBloodType());
		bloodpressureview.setText(cd.getBloodPressure());
		heartbeatview.setText(cd.getHeartBeat());
		xrayview.setText(cd.getXray());
		sightview.setText(cd.getSight());
		hearview.setText(cd.getHearing());
		holdview.setText(cd.getHold());
		storenumview.setText(String.valueOf(cd.getStroeSec()));
		
		
		cancelButton = (Button) findViewById(R.id.privacycheck_cancelbutton);
		cancelButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(PrivacyCheckActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
			
		});
		updateButton = (Button) findViewById(R.id.privacycheck_upgradebutton);
		updateButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		sendButton = (Button) findViewById(R.id.privacycheck_sendbutton);
		sendButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				intent.setClass(PrivacyCheckActivity.this, BluetoothChat.class);
				Bundle bd = new Bundle();
				bd.putSerializable(SerializableKey, cd);
				intent.putExtras(bd);
				startActivity(intent);
				finish();
			}
			
		});
	}
	

}
