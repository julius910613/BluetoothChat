package com.li.listActivity;


import com.example.android.BluetoothChat.R;
import com.li.dataholder.ListData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<ListData> {
	private LayoutInflater mInflater;

	public ListViewAdapter(Context context, int textViewResourceId, ListData[] obj) {
		super(context, textViewResourceId, obj);
		// TODO Auto-generated constructor stub
		
		this.mInflater = LayoutInflater.from(context);
	}

		
	public View getView(int position, View converView, ViewGroup parent){
		if(converView == null){
			converView = mInflater.inflate(R.layout.main_activity_listitem, null);
		}
		
		ViewHolder vh = null;
		if (vh == null){
			vh = new ViewHolder();
			vh.name = (TextView) converView.findViewById(R.id.item_name);
			vh.dateOfBirht = (TextView) converView.findViewById(R.id.item_date);
			
			converView.setTag(vh);
		}
		else{
			vh = (ViewHolder)converView.getTag();
		}
		ListData data = getItem(position);
		
		vh.name.setText(data.getName());
		vh.dateOfBirht.setText(data.getDate());
		
		return converView;
		
	}
	
	
	private static class ViewHolder{
		TextView name;
		TextView dateOfBirht;
		
	}
	

}
