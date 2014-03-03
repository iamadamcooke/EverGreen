package edu.gatech.cs2340.evergreen;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class AccountsAdapter extends ArrayAdapter<Account> {
	private final Context context;
	private ArrayList<Account> accounts;
 
	public AccountsAdapter(Context context, ArrayList<Account> accounts) {
		super(context, R.layout.list_accounts, accounts);
		this.context = context;
		this.accounts = accounts;
	}
	
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_accounts, parent, false);
		TextView accountNameText = (TextView) rowView.findViewById(R.id.account_name_text);
		TextView displayNameText = (TextView) rowView.findViewById(R.id.display_name_text);
		TextView balanceText = (TextView) rowView.findViewById(R.id.balance_text);
		TextView interestRateText = (TextView) rowView.findViewById(R.id.interest_rate_text);
		
		Account acc = accounts.get(position);
		String balAsString = "$" + String.format("%.2f", acc.getBalance());
		String irAsString = String.format("%.2f", acc.getInterestRate()) + "%";
		
		accountNameText.setText(acc.getAccountName());
		displayNameText.setText(acc.getDisplayName());
		balanceText.setText(balAsString);
		interestRateText.setText(irAsString);
		
 
		
 
		return rowView;
	}
	
}
	
	
	
	