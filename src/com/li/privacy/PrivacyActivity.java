package com.li.privacy;

import java.util.ArrayList;

import com.example.android.BluetoothChat.R;
import com.li.casehistory.CaseHistoryActivity;
import com.li.database.CaseHistoryDatabase;
import com.li.dataholder.CaseHistory;
import com.li.dataholder.ClientData;
import com.li.dataholder.DataStorge;
import com.li.dataholder.ViewHolder;
import com.li.receive.ReceiveActivity;
import com.li.resultActivity.ResultActivity;
import com.li.selectActivity.MyAdapter;
import com.li.selectActivity.SelectActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PrivacyActivity extends Activity {
	private ListView lv;
	private MyAdapter mAdapter;
	private ArrayList<String> list;
	CaseHistory casehistory1;
	CaseHistory ch;

	private int checkNum;
	private ArrayList selectedItem = new ArrayList();
	private boolean holdyes = false;
	private boolean holdno = false;

	private EditText edittext;
	private RadioGroup radiogroup;
	private RadioButton yesButton, noButton;
	private Button bt_selectall;
	private Button bt_send;
	private Button bt_back;
	private Button bt_deselectall;
	private String SerializableKey = "data";
	private String[] HeathlyList = { "bloodtype", "Xray", "hearing",
			"boold pressure", "heartbeat", "sight" };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.privacyactivity);

		Bundle bd = this.getIntent().getExtras();

		casehistory1 = (CaseHistory) bd.get(SerializableKey);
		ch = new CaseHistory();

		lv = (ListView) findViewById(R.id.privacy_listView);
		bt_selectall = (Button) findViewById(R.id.privacy_selectallbutton);
		bt_deselectall = (Button) findViewById(R.id.privacy_unselectallbutton);
		bt_back = (Button) findViewById(R.id.privacy_backbutton);
		bt_send = (Button) findViewById(R.id.privacy_sendbutton);

		radiogroup = (RadioGroup) findViewById(R.id.privacy_radiogroup);
		yesButton = (RadioButton) findViewById(R.id.privacy_yesradio);
		noButton = (RadioButton) findViewById(R.id.privacy_noradio);

		edittext = (EditText) findViewById(R.id.privacy_edittext);

		list = new ArrayList<String>();

		initDate();

		mAdapter = new MyAdapter(list, this);

		lv.setAdapter(mAdapter);

		bt_selectall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ����list�ĳ��ȣ���MyAdapter�е�mapֵȫ����Ϊtrue
				for (int i = 0; i < list.size(); i++) {
					MyAdapter.getIsSelected().put(i, true);
				}

				checkNum = sumOfchecked();

				dataChanged();

			}
		});
		// ȡ��ť�Ļص��ӿ�

		bt_send.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int correctflag = 1;
				ArrayList<String> wrongmessage = new ArrayList<String>();
				wrongmessage
						.add("your message has some Security vulnerabilities!\n");
				if (holdyes == false && holdno == false) {
					wrongmessage.add("please select hold status!\n");
					correctflag = 0;
				}
				String inputmin = edittext.getText().toString();
				if (holdno == true && inputmin.isEmpty() == true) {
					wrongmessage
							.add("please set hold time if you don't want your data be stored on doctor's terminal!\n");
					correctflag = 0;
				}
				getCheckedItem();
				if (selectedItem.size() == 0) {
					wrongmessage.add("you haven't select any data!\n");
					correctflag = 0;
				}

				if (correctflag == 1) {
					packetdata();
					showSendInfo();
				}
				if (correctflag == 0) {
					showErrorMeg(wrongmessage);
				}

			}

		});

		bt_deselectall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				for (int i = 0; i < list.size(); i++) {
					MyAdapter.getIsSelected().put(i, false);
				}

				checkNum = sumOfchecked();

				dataChanged();
			}
		});

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				ViewHolder holder = (ViewHolder) arg1.getTag();
				holder.check.toggle();

				MyAdapter.getIsSelected().put(arg2, holder.check.isChecked());

				if (holder.check.isChecked() == true) {
					checkNum++;
				} else {
					checkNum--;
				}

				checkNum = sumOfchecked();

				dataChanged();

			}
		});

		radiogroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub

						if (checkedId == yesButton.getId()) {
							holdyes = true;
							holdno = false;
						} else {
							holdyes = false;
							holdno = true;
						}

					}
				});

		bt_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	private void initDate() {
		for (int i = 0; i < HeathlyList.length; i++) {
			list.add(HeathlyList[i]);
		}
	}

	private int sumOfchecked() {
		checkNum = 0;

		for (int i = 0; i < list.size(); i++) {
			if (MyAdapter.getIsSelected().get(i) == true)
				checkNum++;
		}

		return checkNum;
	}

	public void getCheckedItem() {
		for (int i = 0; i < list.size(); i++) {
			if (MyAdapter.getIsSelected().get(i) == true) {
				selectedItem.add(i);
			}
		}
	}

	private void dataChanged() {

		mAdapter.notifyDataSetChanged();

	}

	protected void showSendInfo() {
		AlertDialog.Builder builder = new Builder(PrivacyActivity.this);

		builder.setMessage("packet has been successful packed,do you want to send it now?");
		builder.setTitle("hint");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent();
				intent.setClass(PrivacyActivity.this, PrivacyCheckActivity.class);
				Bundle bd = new Bundle();
				bd.putSerializable(SerializableKey, ch);
				intent.putExtras(bd);
				startActivity(intent);

			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	protected void showErrorMeg(ArrayList<String> wronglist) {
		AlertDialog.Builder builder = new Builder(PrivacyActivity.this);
		String wrongmessage = "";
		for (int i = 0; i < wronglist.size(); i++) {
			wrongmessage = wrongmessage += wronglist.get(i);
		}

		builder.setMessage(wrongmessage);
		builder.setTitle("hint");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});

		builder.create().show();

	}

	@SuppressLint("NewApi")
	protected void packetdata() {
		ch.refresh();
		if (holdyes == true) {
			ch.setHold("yes");
		}
		if (holdno == true) {
			ch.setHold("no");
		}
		getCheckedItem();
		for (int i = 0; i < selectedItem.size(); i++) {
			String str = HeathlyList[(Integer) selectedItem.get(i)];
			if (str.equals("bloodtype") == true) {

				ch.setBloodType(casehistory1.getBloodType());
			} else if (str.equals("Xray")) {
				ch.setXray(casehistory1.getXray());
			} else if (str.equals("hearing")) {
				ch.setHearing(casehistory1.getHearing());
			} else if (str.equals("boold pressure")) {
				ch.setBloodPressure(casehistory1.getBloodPressure());

			} else if (str.equals("heartbeat")) {
				ch.setHeartbeat(casehistory1.getHeartBeat());

			} else if (str.equals("sight")) {
				ch.setSight(casehistory1.getSight());

			}
		}
		selectedItem.clear();
		String inputmin = edittext.getText().toString();
		if (inputmin.isEmpty() == false) {
			double inputSec = Double.parseDouble(inputmin);

			ch.setStoreSec(inputSec);
			
		}
		ch.setName(casehistory1.getName());
		ch.setDateofbirth(casehistory1.getDateOfBirth());

	}

}
