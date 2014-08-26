package com.li.selectActivity;

import java.util.ArrayList;

import com.example.android.BluetoothChat.R;
import com.li.casehistory.CaseHistoryActivity;
import com.li.database.CaseHistoryDatabase;
import com.li.dataholder.CaseHistory;
import com.li.dataholder.ClientData;
import com.li.dataholder.DataStorge;
import com.li.dataholder.ViewHolder;
import com.li.listActivity.ListViewActivity;
import com.li.resultActivity.ResultActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SelectActivity extends Activity {

	private ListView lv;
	private MyAdapter mAdapter;
	private ArrayList<String> list;
	private Button bt_selectall;
	private Button bt_newact;
	private Button bt_deselectall;
	private int checkNum; // ��¼ѡ�е���Ŀ����
	private String SerializableKey = "data";
	private ArrayList selectedItem = new ArrayList();
	private String[] HeathlyList = { "bloodtype", "Xray", "hearing",
			"boold pressure", "heartbeat", "sight" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Bundle bd = this.getIntent().getExtras();

		final CaseHistory casehistory1 = (CaseHistory) bd.get(SerializableKey);

		lv = (ListView) findViewById(R.id.listview);
		bt_selectall = (Button) findViewById(R.id.result_back);
		bt_deselectall = (Button) findViewById(R.id.result_send);
		bt_newact = (Button) findViewById(R.id.button3);

		list = new ArrayList<String>();

		// ΪAdapter׼�����
		initDate();
		// ʵ���Զ����MyAdapter
		mAdapter = new MyAdapter(list, this);
		// ��Adapter
		lv.setAdapter(mAdapter);

		// ȫѡ��ť�Ļص��ӿ�
		bt_selectall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ����list�ĳ��ȣ���MyAdapter�е�mapֵȫ����Ϊtrue
				for (int i = 0; i < list.size(); i++) {
					MyAdapter.getIsSelected().put(i, true);
				}
				// ������Ϊlist�ĳ���
				checkNum = sumOfchecked();
				// ˢ��listview��TextView����ʾ
				dataChanged();

			}
		});
		// ȡ��ť�Ļص��ӿ�

		bt_newact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				DataStorge ds = new DataStorge();
				CaseHistoryDatabase cd = new CaseHistoryDatabase(
						SelectActivity.this);
				cd.open();
				// cd.insertData();
				ArrayList<CaseHistory> casehistory = cd.getlineData();
				cd.close();
				ds.getCaseHistoryline(casehistory);
				CaseHistory ch = new CaseHistory();

				ArrayList<String> selectedData = new ArrayList<String>();
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
				ch.setName(casehistory1.getName());
				ch.setDateofbirth(casehistory1.getDateOfBirth());
				Intent intent = new Intent();
				intent.setClass(SelectActivity.this, ResultActivity.class);
				Bundle bd = new Bundle();
				bd.putSerializable(SerializableKey, ch);
				intent.putExtras(bd);
				startActivity(intent);

			}

		});

		// ��ѡ��ť�Ļص��ӿ�
		bt_deselectall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ����list�ĳ��ȣ�����ѡ����Ϊδѡ��δѡ����Ϊ��ѡ
				for (int i = 0; i < list.size(); i++) {
					MyAdapter.getIsSelected().put(i, false);
				}
				// ������Ϊlist�ĳ���
				checkNum = sumOfchecked();
				// ˢ��listview��TextView����ʾ
				dataChanged();
			}
		});

		// ��listView�ļ�����
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				// ȡ��ViewHolder���������ʡȥ��ͨ�����findViewByIdȥʵ��������Ҫ��cbʵ��Ĳ���
				ViewHolder holder = (ViewHolder) arg1.getTag();
				// �ı�CheckBox��״̬
				holder.check.toggle();
				// ��CheckBox��ѡ��״����¼����
				MyAdapter.getIsSelected().put(arg2, holder.check.isChecked());
				// ����ѡ����Ŀ
				if (holder.check.isChecked() == true) {
					checkNum++;
				} else {
					checkNum--;
				}
				// ��TextView��ʾ
				checkNum = sumOfchecked();
				// ˢ��listview��TextView����ʾ
				dataChanged();

			}
		});
	}

	// ��ʼ�����
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

	// ˢ��listview��TextView����ʾ
	private void dataChanged() {
		// ֪ͨlistViewˢ��
		mAdapter.notifyDataSetChanged();
		// TextView��ʾ���µ�ѡ����Ŀ

	}

}