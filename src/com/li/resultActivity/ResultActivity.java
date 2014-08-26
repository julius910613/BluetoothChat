package com.li.resultActivity;

import java.util.ArrayList;

import com.example.android.BluetoothChat.R;
import com.li.database.CaseHistoryDatabase;
import com.li.database.TempoaryDatabase;
import com.li.dataholder.CaseHistory;
import com.li.dataholder.ClientData;
import com.li.dataholder.DataStorge;
import com.li.receive.ReceiveActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private String SerializableKey = "data";

	private Button backButton;
	private Button updateButton;
	private Button deleteButton;
	private TextView nameview;
	private TextView dateview;
	private TextView bloodtypeview;
	private TextView bloodpressureview;
	private TextView heartbeatview;
	private TextView xrayview;
	private TextView sightview;
	private TextView hearview;
	private TextView holdview;
	int dataID;
	Handler handler;
	Thread t1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_activity);

		Bundle bd = this.getIntent().getExtras();
		final CaseHistory cd = (CaseHistory) bd.get(SerializableKey);

		nameview = (TextView) findViewById(R.id.result_name);
		dateview = (TextView) findViewById(R.id.result_dateofbirth);
		bloodtypeview = (TextView) findViewById(R.id.result_bloodtype);
		bloodpressureview = (TextView) findViewById(R.id.result_bloodpressure);
		heartbeatview = (TextView) findViewById(R.id.result_heartbeat);
		xrayview = (TextView) findViewById(R.id.result_xray);
		sightview = (TextView) findViewById(R.id.result_sight);
		hearview = (TextView) findViewById(R.id.result_hearing);
		holdview = (TextView) findViewById(R.id.result_hold);

		nameview.setText(cd.getName());
		dateview.setText(cd.getDateOfBirth());
		bloodtypeview.setText(cd.getBloodType());
		bloodpressureview.setText(cd.getBloodPressure());
		heartbeatview.setText(cd.getHeartBeat());
		xrayview.setText(cd.getXray());
		sightview.setText(cd.getSight());
		hearview.setText(cd.getHearing());
		

		backButton = (Button) findViewById(R.id.result_backBT);
		
		backButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
	}

}
