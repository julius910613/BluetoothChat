package com.li.dataholder;

import java.io.Serializable;

import com.li.casehistory.CaseHistoryActivity.CloseThread;

import android.os.Handler;
import android.os.Message;

public class ClientData implements Serializable {
	private String Name;
	private String Bloodtype;
	private String Xray;
	private String Dateofbirth;
	private String Hearing;
	private String Hold;
	private Thread t1;
	
	private Double HoldSec;


	private String Sight;

	private String Bloodpressure;
	private String Heartbeat;
	private boolean Readable;

	public ClientData(String name, String dateofbirth, String bloodtype,
			String Xray, String hearing, String sight, String bloodpressure,
			String heartbeat) {
		super();
		this.Bloodtype = bloodtype;
		this.Name = name;
		this.Dateofbirth = dateofbirth;
		this.Xray = Xray;
		this.Hearing = hearing;
		this.Bloodpressure = bloodpressure;
		this.Heartbeat = heartbeat;
		this.Sight = sight;
		this.Readable = true;

	}

	public ClientData(String name, String dateofbirth, String bloodtype,
			String Xray, String hearing, String sight, String bloodpressure,
			String heartbeat, String hold) {
		super();
		this.Bloodtype = bloodtype;
		this.Name = name;
		this.Dateofbirth = dateofbirth;
		this.Xray = Xray;
		this.Hearing = hearing;
		this.Bloodpressure = bloodpressure;
		this.Heartbeat = heartbeat;
		this.Sight = sight;
		this.Hold = hold;
		this.Readable = true;

	}

	public ClientData() {
		// TODO Auto-generated constructor stub

		this.Bloodtype = null;
		this.Name = null;
		this.Dateofbirth = null;
		this.Xray = null;
		this.Hearing = null;
		this.Bloodpressure = null;
		this.Heartbeat = null;
		this.Sight = null;
		this.Readable = true;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getName() {
		return Name;
	}

	public void setBloodType(String bloodtype) {
		Bloodtype = bloodtype;
	}

	public String getBloodType() {
		return Bloodtype;
	}

	public void setXray(String xray) {
		Xray = xray;
	}

	public String getXray() {
		return Xray;
	}

	public void setHearing(String hearing) {
		Hearing = hearing;
	}

	public String getHearing() {
		return Hearing;
	}

	public void setSight(String sight) {
		Sight = sight;
	}

	public String getSight() {
		return Sight;
	}

	public void setBloodPressure(String bloodpressure) {
		Bloodpressure = bloodpressure;
	}

	public String getBloodPressure() {
		return Bloodpressure;
	}

	public void setHeartbeat(String heartbeat) {
		Heartbeat = heartbeat;
	}

	public String getHeartBeat() {
		return Heartbeat;
	}

	public void setDateofbirth(String dateofbirth) {
		Dateofbirth = dateofbirth;
	}

	public String getDateOfBirth() {
		return Dateofbirth;
	}

	public void setHold(String hold) {
		Hold = hold;
	}

	public String getHold() {
		return Hold;
	}

	public void startThread() {
		if(this.Hold.equals("yes")){
			NothingThread nt = new NothingThread();
			t1 = new Thread(nt);
			t1.start();
			
		}
		else if(this.Hold.equals("no")){
			CloseThread ct = new CloseThread();
			t1 = new Thread(ct);
			t1.start();
		}
		

	}
	public void stopThread(){
		
		if(this.Hold.equals("no")){
			try {
				t1.interrupt();
				t1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(this.Hold.equals("yes")){
			try {
				t1.interrupt();
				t1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public void setHoldSec(double holdsec){
		HoldSec = holdsec;
	}
	public double getHoldSec(){
		return HoldSec;
	}
	public void setUnreadable(){
		this.Readable = false;
	}
	public boolean getReadable(){
		return Readable;
	}
	public boolean getThreadAlive(){
		if(this.Hold.equals("no")){
			return t1.isAlive();
		
		}
		else{
			return t1.isAlive();
		
		}
		
		
	}
		

	public class CloseThread implements Runnable, Serializable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				long l = new Double(HoldSec).longValue();
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
	public class NothingThread implements Runnable,Serializable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			setUnreadable();
		}
	};

}
