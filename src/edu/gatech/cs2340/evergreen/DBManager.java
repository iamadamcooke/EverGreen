package edu.gatech.cs2340.evergreen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
	public final static String USER_ID="_id";
	public final static String ACCOUNT_NAME = "account_name";
	public final static String DISPLAY_NAME = "display_name";
	public final static String BALANCE = "balance";
	public final static String INTEREST_RATE = "interest_rate";
	public final static String ACCOUNTS_TABLE = "Accounts";
	public final static String TRANSACTIONS_TABLE = "Transactions";
	
	
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
	
	public long createTransaction(int id, String type, String name, double amount) {
		ContentValues values = new ContentValues();
		values.put("account_id", id);
		values.put("transaction_type", type);
		values.put("transaction_name", name);
		values.put("amount", amount);
		return db.insert(TRANSACTIONS_TABLE, null, values);
	}
	
	public long createAccount(int id, String accountName, String displayName, double balance, double interestRate) {
		ContentValues values = new ContentValues();
		values.put("user_id",id);
		values.put(ACCOUNT_NAME, accountName);
		values.put(DISPLAY_NAME, displayName);
		values.put(BALANCE, balance);
		values.put(INTEREST_RATE,	interestRate);
		return db.insert(ACCOUNTS_TABLE, null, values);
	}
	
	public int getUserId(String user) {
		String[] args = {user};
		 Cursor c = db.rawQuery("SELECT _id FROM " +
                USER_TABLE +
                " where username" + "=?", args);
		 if(c != null) {
			 if  (c.moveToFirst()) {
                   return c.getInt(c.getColumnIndex(USER_ID));
                }
            } 
		 
		 return -1;
	}
	
	public int getAccountId(String accountName, int userId) {
		String[] args = {String.valueOf(userId), accountName};
		Cursor c = db.rawQuery("SELECT _id FROM Accounts where user_id=? AND display_name=?",args);
		if(c != null) {
			if(c.moveToFirst()) {
				return c.getInt(c.getColumnIndex("_id"));
			}
		}
		return -1;
	}
	
	public String getPass(String user){
		 String[] args = {user};
		 Cursor c = db.rawQuery("SELECT password FROM " +
                 USER_TABLE +
                 " where username" + "=?", args);
		 if(c != null) {
			 if  (c.moveToFirst()) {
                    return c.getString(c.getColumnIndex(USER_PASS));
                 }
             } 
		 
	return null;
	}
	
	public long updateAccountBalance(int accountId, double newBalance) {
		ContentValues values = new ContentValues();
		values.put("balance", newBalance);
		String where = "_id=?";
		return db.update(ACCOUNTS_TABLE, values, where, new String[]{String.valueOf(accountId)});
	}
	
	public double getAccountBalance(int accountId) {
		String[] args = {String.valueOf(accountId)};
		Cursor c = db.rawQuery("SELECT balance FROM Accounts where _id=?", args);
		if(c != null) {
			if(c.moveToFirst()) {
				return c.getDouble(c.getColumnIndex("balance"));
			}
		}
		return 0;
	}
	
	public Account getAccount(int accountId) {
		String[] args = {String.valueOf(accountId)};
		Cursor c = db.rawQuery("SELECT * FROM Accounts where _id=?", args);
		if(c != null) {
			if(c.moveToFirst()) {
				String accName = c.getString(c.getColumnIndex(ACCOUNT_NAME));
				String dispName = c.getString(c.getColumnIndex(DISPLAY_NAME));
				double bal = c.getDouble(c.getColumnIndex(BALANCE));
				double ir = c.getDouble(c.getColumnIndex(INTEREST_RATE));
				return new Account(accName, dispName, bal, ir);
			}
		}
		return null;
	}
	
	
	public ArrayList<Account> getAccounts(int userId) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		String[] args = {String.valueOf(userId)};
		Cursor c = db.rawQuery("SELECT * FROM " +
				ACCOUNTS_TABLE + 
				" where user_id" + "=?", args);
		if(c != null) {
			if(c.moveToFirst()) {
				while(c.isAfterLast() == false) {
					String accName = c.getString(c.getColumnIndex(ACCOUNT_NAME));
					String dispName = c.getString(c.getColumnIndex(DISPLAY_NAME));
					double bal = c.getDouble(c.getColumnIndex(BALANCE));
					double ir = c.getDouble(c.getColumnIndex(INTEREST_RATE));
					accounts.add(new Account(accName, dispName, bal, ir));
					c.moveToNext();
				}
			}
			else return null;
		}
		else return null;
		
		return accounts;
		
	}
	
	public ArrayList<Transaction> getTransactions(int accountId) {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		String[] args = {String.valueOf(accountId)};
		Cursor c = db.rawQuery("SELECT * FROM Transactions where account_id" + "=?", args);
		if(c != null) {
			if(c.moveToFirst()) {
				while(c.isAfterLast() == false) {
					String type = c.getString(c.getColumnIndex("transaction_type"));
					String name = c.getString(c.getColumnIndex("transaction_name"));
					double amount = c.getDouble(c.getColumnIndex("amount"));
					
					transactions.add(new Transaction(type, name, amount));
					c.moveToNext();
				}
			}
			else return null;
		}
		else return null;
		Collections.reverse(transactions);
		return transactions;
	}
}
