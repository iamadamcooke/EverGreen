package edu.gatech.cs2340.evergreen;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_USERS = "create table Users" +
    		"(_id integer primary key, username text unique, password text not null);";
    private static final String DATABASE_ACCOUNTS = "create table Accounts" + 
    		"(_id integer, account_name text unique, display_name text unique, " +
    		"balance real not null, interest_rate  real not null, foreign key(_id) references users(_id));";
	
    
    public  DBHelper(Context context) {
		super(context, "EverGreen", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("PRAGMA foreign_keys=ON;");
		db.execSQL(DATABASE_USERS);
		db.execSQL(DATABASE_ACCOUNTS);
		db.execSQL("INSERT INTO " +
                "Users" +
                " Values (1,'admin','pass123');");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
