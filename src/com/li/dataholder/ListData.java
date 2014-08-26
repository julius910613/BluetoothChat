package com.li.dataholder;

import android.R.string;

public class ListData{
	String name;
	String dateOfBirth;
	ListData(String dateOfBirth, String name){
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	public ListData(){
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public String getDate(){
		return dateOfBirth;
	}
	public ListData[] buildMainListData(){
		ListData[] dataqueue = new ListData[]{
				new ListData("show personal information","Personal Info"),
				new ListData("check your own history","Check history case"),
				new ListData("send your case history to doctor","Send info"),
				new ListData("Viewing received packet","receive packet")
		};
		return dataqueue;
	}
}