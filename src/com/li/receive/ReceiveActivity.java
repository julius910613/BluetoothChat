package com.li.receive;

import java.util.ArrayList;

import com.example.android.BluetoothChat.R;
import com.li.casehistory.CaseHistoryActivity;
import com.li.database.CaseHistoryDatabase;
import com.li.database.TempoaryDatabase;
import com.li.dataholder.CaseHistory;
import com.li.dataholder.ClientData;
import com.li.dataholder.DataStorge;
import com.li.dataholder.ListData;
import com.li.listActivity.ListViewActivity;
import com.li.listActivity.ListViewAdapter;
import com.li.resultActivity.ResultActivity;
import com.li.selectActivity.SelectActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ReceiveActivity extends Activity {
	private String SerializableKey = "data";
	private ListData[] dataQueue;
	ListView lv;
	DataStorge ds = new DataStorge();
	public static ArrayList<ClientData> casehistory = new ArrayList<ClientData>();
	private CaseHistory cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receiveactivity);
		Bundle bd = this.getIntent().getExtras();
		cd = (CaseHistory) bd.get(SerializableKey);
		
		ClientData cdata = new ClientData();
		cdata.setBloodPressure(cd.getBloodPressure());
		cdata.setBloodType(cd.getBloodType());
		cdata.setDateofbirth(cd.getDateOfBirth());
		cdata.setHearing(cd.getHearing());
		cdata.setHeartbeat(cd.getHeartBeat());
		cdata.setHold(cd.getHold());
		cdata.setName(cd.getName());
		cdata.setSight(cd.getSight());
		cdata.setXray(cd.getXray());
		cdata.setHoldSec(cd.getStroeSec());
		casehistory.add(cdata);

		ds.getCaseHistory(casehistory);
		dataQueue = ds.returnList();
		lv = (ListView) findViewById(R.id.receiver_listView);
		setListView();
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ClientData cd = casehistory.get(arg2);
				if(cd.getReadable() == false){
					AlertDialog.Builder buliter = new Builder(ReceiveActivity.this);
					buliter.setMessage("Time out! ready to be canceled!");
					buliter.setTitle("hint");
					buliter.setNegativeButton("OK", new DialogInterface.OnClickListener(){

						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
							dialog.dismiss();
							
						}
						
					});
					buliter.create().show();
					casehistory.remove(arg2);
					ds.getCaseHistory(casehistory);
					dataQueue = ds.returnList();
					setListView();
										
					//tranSerializableObject(cd);
					if(cd.getThreadAlive()){
						cd.stopThread();
					}
				}
				else{
					if(cd.getThreadAlive()){
						cd.stopThread();
					}
					casehistory.remove(arg2);
					ds.getCaseHistory(casehistory);
					dataQueue = ds.returnList();
					setListView();
					tranSerializableObject(cd);
					
					
				}
				

			}

		});

	}

	private void setListView() {
		// TODO Auto-generated method stub
		

		ListViewAdapter adapter1 = new ListViewAdapter(this,
				R.layout.clientdataitem, dataQueue);

		// ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1);

		lv.setAdapter(adapter1);
	}

	public void tranSerializableObject(ClientData clientData) {
		// TODO Auto-generated method stub
		Intent in = new Intent();
		in.setClass(ReceiveActivity.this, CaseHistoryActivity.class);

		// 实例化一个SerializableBook对象
		CaseHistory ch = new CaseHistory();
		ch.setBloodPressure(clientData.getBloodPressure());
		ch.setBloodType(clientData.getBloodType());
		ch.setDateofbirth(clientData.getDateOfBirth());
		ch.setHearing(clientData.getHearing());
		ch.setHeartbeat(clientData.getHearing());
		ch.setHold(clientData.getHold());
		ch.setName(clientData.getName());
		ch.setSight(clientData.getSight());
		ch.setXray(clientData.getXray());
		ch.setStoreSec(clientData.getHoldSec());

		Bundle extras = new Bundle();
		extras.putSerializable(SerializableKey, ch);

		in.putExtras(extras);
		startActivity(in);

	}

}
