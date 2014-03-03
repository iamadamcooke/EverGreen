package edu.gatech.cs2340.evergreen;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TransactionsActivity extends Activity {

	private ArrayList<Transaction> transactions;
	private TransactionsAdapter transactionsAdapter;
	private ListView transactionsList;
	private DBManager dbManager;
	private int accountId;
	private Account acc;
	private TextView accName;
	private TextView accBalance;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transactions);
		dbManager = new DBManager(this);
		accountId = getIntent().getIntExtra("account_id", -1);
		transactions = dbManager.getTransactions(accountId);
		if(transactions == null) {
			transactions = new ArrayList<Transaction>();
		}
		transactionsAdapter = new TransactionsAdapter(this, transactions);
		transactionsList = (ListView)findViewById(R.id.transactionsList);
		transactionsList.setAdapter(transactionsAdapter);
		accName = (TextView)findViewById(R.id.display_name_text);
		accBalance = (TextView)findViewById(R.id.balance_text);
		acc = dbManager.getAccount(accountId);
		accName.setText(acc.getDisplayName());
		String balanceAsString = "$" + String.format("%.2f", acc.getBalance());
		accBalance.setText(balanceAsString);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transactions, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_newTransaction:
	            openNewTransactionDialog();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void openNewTransactionDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(TransactionsActivity.this, AlertDialog.THEME_HOLO_DARK);
		final String[] options = {"In", "Out"};
        // Setting Dialog Title
        builder.setTitle("New Transaction");

        // Setting Dialog Message
        builder.setMessage("Enter Transaction Details:");
        final RadioButton[] rb = new RadioButton[2];
        final RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.HORIZONTAL);
        for(int i = 0; i < 2; i++) {
        	rb[i]  = new RadioButton(this);
            rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(options[i]);
            rb[i].setTextColor(Color.WHITE);
            rb[i].setTextSize(30);

        }
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                              LinearLayout.LayoutParams.WRAP_CONTENT,
                              LinearLayout.LayoutParams.WRAP_CONTENT);
       
        rg.setLayoutParams(new RadioGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        rg.setOrientation(LinearLayout.HORIZONTAL);
        rg.setGravity(Gravity.CENTER);
        rg.check(rb[0].getId());
        final EditText name = new EditText(TransactionsActivity.this);  
        name.setTextColor(Color.WHITE);
        name.setHint("Transaction Name");
        final EditText amount = new EditText(TransactionsActivity.this);  
        amount.setTextColor(Color.WHITE);
        amount.setHint("Amount");
        amount.setInputType(0x00002002);
        
        layout.addView(rg);
        layout.addView(name);
        layout.addView(amount);
        builder.setView(layout);



        // Setting Positive "Yes" Button
        builder.setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                    	int selectedTypeId = rg.getCheckedRadioButtonId();
                    	View typeView = rg.findViewById(selectedTypeId);
                    	String type;
                    	int transactionType;
                    	if(typeView == rb[0]) {
                    		type = options[0];
                    		transactionType = 1;
                    	}
                    	else {
                    		type = options[1];
                    		transactionType = -1;
                    	}
                    	double transAmount = transactionType * Double.parseDouble(amount.getEditableText().toString());
                    	double balance = dbManager.getAccountBalance(accountId);
                    	balance += transAmount;
                    	String balanceAsString = "$" + String.format("%.2f", balance);
                    	accBalance.setText(balanceAsString);
                    	dbManager.updateAccountBalance(accountId, balance);
                    	dbManager.createTransaction(accountId, type, name.getEditableText().toString(), transAmount);
                    	transactions.add(0, new Transaction(type, name.getEditableText().toString(), transAmount));
                        transactionsAdapter.notifyDataSetChanged();
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
