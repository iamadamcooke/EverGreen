package edu.gatech.cs2340.evergreen;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TransactionsAdapter extends ArrayAdapter<Transaction> {
	private final Context context;
	private ArrayList<Transaction> transactions;
 
	public TransactionsAdapter(Context context, ArrayList<Transaction> transactions) {
		super(context, R.layout.list_transactions, transactions);
		this.context = context;
		this.transactions = transactions;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_transactions, parent, false);
		TextView transactionNameText = (TextView) rowView.findViewById(R.id.transaction_name_text);
		TextView amountText = (TextView) rowView.findViewById(R.id.amount_text);
		
		
		Transaction transaction = transactions.get(position);
		String amountAsString = "$" + String.format("%.2f", transaction.getAmount());
		int color = Color.BLACK;
		if(transaction.getAmount() > 0) {
			color = Color.GREEN;
			
		}
		else color = Color.RED;
		
		transactionNameText.setText(transaction.getName());
		
		amountText.setText(amountAsString);
		amountText.setTextColor(color);
		
		
 
		
 
		return rowView;
	}
}