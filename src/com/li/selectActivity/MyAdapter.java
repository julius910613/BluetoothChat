package com.li.selectActivity;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.android.BluetoothChat.R;
import com.li.dataholder.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ArrayList<String> list;

	private static HashMap<Integer, Boolean> isSelected;

	private Context context;

	private LayoutInflater inflater = null;

	public MyAdapter(ArrayList<String> list, Context context) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		isSelected = new HashMap<Integer, Boolean>();

		initDate();
	}

	private void initDate() {
		for (int i = 0; i < list.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			// ���ViewHolder����
			holder = new ViewHolder();
			// ���벼�ֲ���ֵ��convertview
			convertView = inflater.inflate(R.layout.checkbox, null);
			holder.text = (TextView) convertView.findViewById(R.id.text_1);
			holder.check = (CheckBox) convertView.findViewById(R.id.check_1);
			// Ϊview���ñ�ǩ
			convertView.setTag(holder);
		} else {
			// ȡ��holder
			holder = (ViewHolder) convertView.getTag();
		}

		// ����list��TextView����ʾ
		holder.text.setText(list.get(position));
		// ���isSelected������checkbox��ѡ��״��
		holder.check.setChecked(getIsSelected().get(position));
		return convertView;
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;

	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		MyAdapter.isSelected = isSelected;
	}

}
