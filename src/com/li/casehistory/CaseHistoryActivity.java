package com.li.casehistory;

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

public class CaseHistoryActivity extends Activity {
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
	private CaseHistory cd;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.casehistory);

		Bundle bd = this.getIntent().getExtras();
		cd = (CaseHistory)bd.get(SerializableKey);
		
		handler = new Handler(){
			public void handleMessage(Message msg){
				
				AlertDialog.Builder buliter = new Builder(CaseHistoryActivity.this);
				buliter.setMessage("Time out! ready to be canceled!");
				buliter.setTitle("hint");
				buliter.setNegativeButton("OK", new DialogInterface.OnClickListener(){

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						CaseHistoryActivity.this.finish();
					}
					
				});
				buliter.create().show();
			}
		};
		

		
		if(cd.getHold().equals("no")){
			CloseThread ct = new CloseThread();
			t1 = new Thread(ct);
			t1.start();
		}

		nameview = (TextView) findViewById(R.id.casehistory_name);
		dateview = (TextView) findViewById(R.id.casehistory_dateofbirth);
		bloodtypeview = (TextView) findViewById(R.id.casehistory_bloodtype);
		bloodpressureview = (TextView) findViewById(R.id.casehistory_bloodpressure);
		heartbeatview = (TextView) findViewById(R.id.casehistory_heartbeat);
		xrayview = (TextView) findViewById(R.id.casehistory_xray);
		sightview = (TextView) findViewById(R.id.casehistory_sight);
		hearview = (TextView) findViewById(R.id.casehistory_hearing);
		holdview = (TextView) findViewById(R.id.casehistory_hold);

		nameview.setText(cd.getName());
		dateview.setText(cd.getDateOfBirth());
		bloodtypeview.setText(cd.getBloodType());
		bloodpressureview.setText(cd.getBloodPressure());
		heartbeatview.setText(cd.getHeartBeat());
		xrayview.setText(cd.getXray());
		sightview.setText(cd.getSight());
		hearview.setText(cd.getHearing());
		holdview.setText(cd.getHold());

		backButton = (Button) findViewById(R.id.personalInfo_backBT);

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cd.getHold().equals("no")) {					
					try {
						t1.interrupt();
						t1.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (cd.getHold().equals("yes")) {
					CaseHistoryDatabase chd = new CaseHistoryDatabase(
							CaseHistoryActivity.this);
					chd.open();
					chd.insertReceivedData(cd);
					chd.close();
				}
				finish();
			}

		});

	}
	public class CloseThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				long l = new Double(cd.getStroeSec()).longValue();
				Thread.sleep(l*60000);
				 Message message = new Message();
	                message.what = 1;
	                handler.sendMessage(message);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
