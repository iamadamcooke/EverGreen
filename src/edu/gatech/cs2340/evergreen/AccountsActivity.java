package edu.gatech.cs2340.evergreen;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AccountsActivity extends Activity {

	private ArrayList<Account> accounts = new ArrayList<Account>();
	private AccountsAdapter accountsAdapter;
	private int userId;
	private ListView lv;
	private DBManager dbManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accounts);
		dbManager = new DBManager(this);
		userId = getIntent().getExtras().getInt("userId");
		accounts = dbManager.getAccounts(userId);
		if(accounts == null) {
			accounts = new ArrayList<Account>();
		}
		accountsAdapter = new AccountsAdapter(this, accounts);
		lv = (ListView)findViewById(R.id.accountsList);
		lv.setAdapter(accountsAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.accounts, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_newAccount:
	            openNewAccountDialog();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void OnAccountClicked(View view) {
		final int position = lv.getPositionForView(view);
		Account acc = accounts.get(position);
		Intent intent = new Intent(getApplicationContext(), TransactionsActivity.class);
		intent.putExtra("account_id", dbManager.getAccountId(acc.getDisplayName(), userId));
		startActivity(intent);
 
	}
	
	public void onResume() {
		super.onResume();
		accounts = dbManager.getAccounts(userId);
		if(accounts == null) {
			accounts = new ArrayList<Account>();
		}
		accountsAdapter.setAccounts(accounts);
		accountsAdapter.notifyDataSetChanged();
	}
	
	private void openNewAccountDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(AccountsActivity.this, AlertDialog.THEME_HOLO_DARK);

        // Setting Dialog Title
        builder.setTitle("New Account");

        // Setting Dialog Message
        builder.setMessage("Enter Account Details:");
        final EditText name = new EditText(AccountsActivity.this);  
        name.setTextColor(Color.WHITE);
        name.setHint("Account Name");
        final EditText display = new EditText(AccountsActivity.this);  
        display.setTextColor(Color.WHITE);
        display.setHint("Display Name");
        final EditText bal = new EditText(AccountsActivity.this);  
        bal.setTextColor(Color.WHITE);
        bal.setHint("Balance");
        bal.setInputType(0x00002002);
        final EditText ir = new EditText(AccountsActivity.this);  
        ir.setTextColor(Color.WHITE);
        ir.setHint("Interest Rate");
        ir.setInputType(0x00002002);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                              LinearLayout.LayoutParams.MATCH_PARENT,
                              LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30, 0, 30, 0);
        layout.addView(name, lp);
        layout.addView(display, lp);
        layout.addView(bal, lp);
        layout.addView(ir, lp);
        builder.setView(layout);



        // Setting Positive "Yes" Button
        builder.setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                    	String accName = name.getText().toString();
                    	String dispName = display.getText().toString();
                    	double balance = Double.parseDouble(bal.getText().toString());
                    	double intRate = Double.parseDouble(ir.getText().toString());
                    	long i = dbManager.createAccount(userId, accName, dispName, balance, intRate);
                        accounts.add(new Account(accName, dispName, balance, intRate));
                        accountsAdapter.notifyDataSetChanged();
                    }});
        // Setting Negative "NO" Button
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });

        // closed

        // Showing Alert Message
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.show();
      
    }

}
