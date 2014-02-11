package edu.gatech.cs2340.evergreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	private DBHelper dbHelper; 
	private SQLiteDatabase db;
	public final static String USER_TABLE="Users";
	public final static String USER_NAME="username"; 
	public final static String USER_PASS="password"; 
	
	public DBManager(Context context){
		dbHelper = new DBHelper(context);
		db = dbHelper.getWritableDatabase();
	}
	
	public long createUser(String user, String pass){  
		   ContentValues values = new ContentValues();  
		   values.put(USER_NAME, user);  
		   values.put(USER_PASS, pass);  
		   return db.insert(USER_TABLE, null, values);  
		}    
	
	public String getPass(String user){
		 Cursor c = db.rawQuery("SELECT password FROM " +
                 USER_TABLE +
                 "where username = " + user, null);
		 if(c != null) {
			 if  (c.moveToFirst()) {
                    return c.getString(c.getColumnIndex("Password"));
                 }
             } 
		 
	return null;
	}
}