package com.li.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.li.dataholder.ClientData;
import com.li.dataholder.PersonalInfo;

public class TempoaryDatabase {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_BIRTH = "person_birthday";
	public static final String KEY_BLOODTYPE = "person_bloodtype";
	public static final String KEY_XRAY = "person_xray";
	public static final String KEY_HEARING = "person_hearing";
	public static final String KEY_SIGHT = "person_sight";
	public static final String KEY_BLOODPRESSURE = "person_pressure";
	public static final String KEY_HEARTBEAT = "person_heartbeat";
	public static final String KEY_HOLD = "person_hold";

	private static final String DATABASE_NAME = "Tempoarydb";
	private static final String DATABASE_TABLE = "personcasehistoryTable";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private ClientData[] dataqueue = new ClientData[]{
			new ClientData("Fanchen Li", "2012-6-13", "O", "good","79","80","80-60","60","yes"),
			new ClientData("Fanchen Li", "2012-7-13", "O", "noraml","32","90","100-60","76","no"),
			
	};

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + 			
					KEY_ROWID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " + 
					KEY_BIRTH+ " TEXT NOT NULL, " +
					KEY_BLOODTYPE + " TEXT NOT NULL, " +
					KEY_XRAY + " TEXT NOT NULL, " +
					KEY_HEARING + " TEXT NOT NULL, " +
					KEY_SIGHT + " TEXT NOT NULL, " + 
					KEY_BLOODPRESSURE + " TEXT NOT NULL, " + 
					KEY_HOLD + " TEXT NOT NULL, " +
					KEY_HEARTBEAT + " TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	public TempoaryDatabase(Context c) {
		ourContext = c;
	}

	public TempoaryDatabase open() {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	

	public void insertData() {
		
		for(int i = 0; i < dataqueue.length; i ++){
			ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, dataqueue[i].getName());
		cv.put(KEY_BIRTH, dataqueue[i].getDateOfBirth());
		cv.put(KEY_BLOODTYPE, dataqueue[i].getBloodType());
		cv.put(KEY_XRAY, dataqueue[i].getXray());
		cv.put(KEY_HEARING, dataqueue[i].getHearing());
		cv.put(KEY_SIGHT, dataqueue[i].getSight());
		cv.put(KEY_BLOODPRESSURE, dataqueue[i].getBloodPressure());
		cv.put(KEY_HEARTBEAT, dataqueue[i].getHeartBeat());
		cv.put(KEY_HOLD, dataqueue[i].getHold());
		ourDatabase.insert(DATABASE_TABLE, null, cv);
		}
	}
	


	public ArrayList<ClientData> getData(){
		String[] columns = new String[]{KEY_NAME,KEY_BIRTH,KEY_BLOODTYPE,KEY_XRAY,KEY_HEARING,KEY_SIGHT, KEY_BLOODPRESSURE,KEY_HEARTBEAT,KEY_HOLD};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		PersonalInfo pi = new PersonalInfo();
		ArrayList<ClientData> casehistoryqueue = new ArrayList<ClientData>();
	
		int iName = c.getColumnIndex(KEY_NAME);
		int iBirth = c.getColumnIndex(KEY_BIRTH);
		int iBloodtype = c.getColumnIndex(KEY_BLOODTYPE);
		int iXray = c.getColumnIndex(KEY_XRAY);
		int iHearing = c.getColumnIndex(KEY_HEARING);
		int iSight = c.getColumnIndex(KEY_SIGHT);
		int iBloodpressure = c.getColumnIndex(KEY_BLOODPRESSURE);
		int iHeartbeat = c.getColumnIndex(KEY_HEARTBEAT);
		int iHold = c.getColumnIndex(KEY_HOLD);
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			
			ClientData cd = new ClientData();
			
			cd.setName(c.getString(iName));
			cd.setDateofbirth(c.getString(iBirth));
			cd.setBloodType(c.getString(iBloodtype));
			cd.setXray(c.getString(iXray));
			cd.setHearing(c.getString(iHearing));
			cd.setSight(c.getString(iSight));
			cd.setBloodPressure(c.getString(iBloodpressure));
			cd.setHeartbeat(c.getString(iHeartbeat));
			cd.setHold(c.getString(iHold));
			casehistoryqueue.add(cd);
			
		}
		
		return casehistoryqueue;
	}
	
	public void delectData(int id){
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + id, null);
	}
	public void delectAll(){
		ourDatabase.delete(DATABASE_TABLE, null, null);
	}

	public void close() {
		ourHelper.close();
	}
}
