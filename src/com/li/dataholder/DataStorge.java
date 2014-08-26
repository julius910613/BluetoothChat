package com.li.dataholder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import com.li.database.databaseAccesser;


public class DataStorge {
	ListData[] dataQueue;
	ListData[] dataQueueline;
	
	private ArrayList<ClientData> casehistory = new ArrayList<ClientData>();
	private ArrayList<CaseHistory> caseHistoryline = new ArrayList<CaseHistory>();

	
	private HashMap<String,ClientData>datamap;
	
	
	public void getCaseHistory(ArrayList<ClientData> al){
		casehistory = al;
		for(int i = 0; i < casehistory.size(); i ++){
			
				casehistory.get(i).startThread();
			
		}
	}
	public void getCaseHistoryline(ArrayList<CaseHistory> a){
		caseHistoryline = a;
	}
	public ClientData getCaseHistoryData(int i){
		return casehistory.get(i);
	}
	public CaseHistory getCaseHistorylineData(int i){
		return caseHistoryline.get(i);
	}
	
	public ListData[] returnList(){
		dataQueue = new ListData[casehistory.size()];
		for(int i = 0; i< casehistory.size() ; i++){
			ListData ld = new ListData(casehistory.get(i).getDateOfBirth(),casehistory.get(i).getName());
			dataQueue[i] = ld;
		}
		return dataQueue;
	}
	public ListData[] returnlineList(){
		dataQueueline = new ListData[caseHistoryline.size()];
		for(int i = 0; i< caseHistoryline.size() ; i++){
			ListData ld = new ListData(caseHistoryline.get(i).getDateOfBirth(),caseHistoryline.get(i).getName());
			dataQueueline[i] = ld;
		}
		return dataQueueline;
	}

}
