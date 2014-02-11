package edu.gatech.cs2340.evergreen;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_USERS = "create table Users" +
    		"(id integer primary key AUTO_INCREMENT, username text not null, password text not null);";
	
    
    public  DBHelper(Context context) {
		super(context, "EverGreen", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_USERS);
		db.execSQL("INSERT INTO " +
                "Users" +
                " Values ('admin','pass123');");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
