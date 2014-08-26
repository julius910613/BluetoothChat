package com.li.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


import com.li.dataholder.ClientData;
import com.li.dataholder.PersonalInfo;

public class databaseAccesser {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_BIRTH = "person_birthday";
	public static final String KEY_ADDRESS = "person_address";
	public static final String KEY_CONTACT = "person_contact";
	public static final String KEY_HIGHT = "person_height";
	public static final String KEY_WEIGHT = "person_weight";

	private static final String DATABASE_NAME = "Persondb";
	private static final String DATABASE_TABLE = "personinfoTable";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

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
					KEY_ADDRESS + " TEXT NOT NULL, " +
					KEY_CONTACT + " TEXT NOT NULL, " +
					KEY_HIGHT + " TEXT NOT NULL, " +
					KEY_WEIGHT + " TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	public databaseAccesser(Context c) {
		ourContext = c;
	}

	public databaseAccesser open() {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public long insertData() {

		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, "Fanchen Li");
		cv.put(KEY_BIRTH, "1991-6-13");
		cv.put(KEY_ADDRESS, "earth");
		cv.put(KEY_CONTACT, "070-3732576");
		cv.put(KEY_HIGHT, "196.0");
		cv.put(KEY_WEIGHT, "98.0");
		return ourDatabase.insert(DATABASE_TABLE, null, cv);

	}
	


	public PersonalInfo getData(){
		String[] columns = new String[]{KEY_NAME,KEY_BIRTH,KEY_ADDRESS,KEY_CONTACT,KEY_HIGHT,KEY_WEIGHT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		PersonalInfo pi = new PersonalInfo();
		
	
		int iName = c.getColumnIndex(KEY_NAME);
		int iBirth = c.getColumnIndex(KEY_BIRTH);
		int iAddress = c.getColumnIndex(KEY_ADDRESS);
		int iContact = c.getColumnIndex(KEY_CONTACT);
		int iHight = c.getColumnIndex(KEY_HIGHT);
		int iWeight = c.getColumnIndex(KEY_WEIGHT);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			pi.setName(c.getString(iName));
			pi.setDate(c.getString(iBirth));
			pi.setAddress(c.getString(iAddress));
			pi.setContact(c.getString(iContact));
			pi.setHight(c.getString(iHight));
			pi.setWeight(c.getString(iWeight));
			
			
			result = result  + " " + c.getString(iName) + " " + c.getString(iBirth) + " " + c.getString(iAddress) + " " +c.getString(iContact) + " "+ c.getString(iHight) + " " + c.getString(iWeight) + "\n";
		}
		
		return pi;
	}

	public void close() {
		ourHelper.close();
	}
}
