package com.li.dataholder;

import java.io.Serializable;

public class CaseHistory implements Serializable {
	private String Name;
	private String Bloodtype;
	private String Xray;
	private String Dateofbirth;
	private String Hearing;
	private String Hold;


	private String Sight;

	private String Bloodpressure;
	private String Heartbeat;
	
	private double StoreSec;
	public CaseHistory() {
		// TODO Auto-generated constructor stub

		this.Bloodtype = null;
		this.Name = null;
		this.Dateofbirth = null;
		this.Xray = null;
		this.Hearing = null;
		this.Bloodpressure = null;
		this.Heartbeat = null;
		this.Sight = null;

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
	public void setStoreSec(double sec){
		StoreSec = sec;
	}
	public double getStroeSec(){
		return StoreSec;
	}
	
	public void refresh(){
		this.Bloodtype = null;
		this.Name = null;
		this.Dateofbirth = null;
		this.Xray = null;
		this.Hearing = null;
		this.Bloodpressure = null;
		this.Heartbeat = null;
		this.Sight = null;
		this.Hold = null;
		this.StoreSec = 0;
		
	}
	

}
