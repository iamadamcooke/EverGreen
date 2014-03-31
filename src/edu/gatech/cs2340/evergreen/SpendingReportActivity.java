package edu.gatech.cs2340.evergreen;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class SpendingReportActivity.
 */
public class SpendingReportActivity extends Activity {

	/** The start date. */
	private String startDate;
	
	/** The end date. */
	private String endDate;
	
	/** The user id. */
	private int userId;
	
	/** The title. */
	private TextView title;
	
	/** The db manager. */
	private DBManager dbManager;
	
	/** The transactions. */
	private ArrayList<Transaction> transactions;
	
	/** The total text. */
	private TextView totalText;
	
	/** The food text. */
	private TextView foodText;
	
	/** The entertainment text. */
	private TextView entertainmentText;
	
	/** The clothing text. */
	private TextView clothingText;
	
	/** The rent text. */
	private TextView rentText;
	
	/** The other text. */
	private TextView otherText;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending_report);
		dbManager = new DBManager(this);
		userId = getIntent().getExtras().getInt("userId");
		startDate = getIntent().getExtras().getString("startDate");
		endDate = getIntent().getExtras().getString("endDate");
		title = (TextView) findViewById(R.id.spendingReportTitle);
		totalText = (TextView) findViewById(R.id.totalAmount);
		foodText = (TextView) findViewById(R.id.foodAmount);
		entertainmentText = (TextView) findViewById(R.id.entertainmentAmount);
		otherText = (TextView) findViewById(R.id.otherAmount);
		rentText = (TextView) findViewById(R.id.rentAmount);
		clothingText = (TextView) findViewById(R.id.clothingAmount);
		String[] startDateParts = startDate.split("-");
		String[] endDateParts = endDate.split("-");
		String startDateText = startDateParts[2] + "-" + startDateParts[1] + "-" + startDateParts[0];
		String endDateText = endDateParts[2] + "-" + endDateParts[1] + "-" + endDateParts[0];
		String newSD =  startDateParts[0] + startDateParts[1]  + startDateParts[2];
		String newED = endDateParts[0] +  endDateParts[1] + endDateParts[2];
		title.setText("Spending Report for " + startDateText + " to " + endDateText);
		transactions = dbManager.getTransactions(userId, newSD, newED);
		if (transactions == null) {
			transactions = new ArrayList<Transaction>();
		}
		calculateSpendingReport();
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spending_report, menu);
		return true;
	}
	
	/**
	 * Calculate spending report.
	 */
	private void calculateSpendingReport() {
		double total = 0;;
		double ent = 0;;
		double food = 0;
		double rent = 0;
		double clothing = 0;
		double other = 0;
		if  (!transactions.isEmpty()) {
			for (Transaction t: transactions) {
				String c = t.getCategory();
				if (c.equals("Food")) {
					food += (-1 * t.getAmount());
				}
				else if (c.equals("Rent")) {
					rent += (-1 * t.getAmount());
				}
				else if (c.equals("Entertainment")) {
					ent += (-1 * t.getAmount());
				}
				else if  (c.equals("Other")) {
					other += (-1 * t.getAmount());
				}
				else if  (c.equals("Clothing")) {
					clothing += (-1 * t.getAmount());
				}
				else {
					//nothing now
				}
			}
			total = rent + ent + other + clothing + food;
			String totalAsString = "$" + String.format("%.2f", total);
			totalText.setText(totalAsString);
			String rentAsString = "$" + String.format("%.2f", rent);
			rentText.setText(rentAsString);
			String foodAsString = "$" + String.format("%.2f", food);
			foodText.setText(foodAsString);
			String entAsString = "$" + String.format("%.2f", ent);
			entertainmentText.setText(entAsString);
			String clothingAsString = "$" + String.format("%.2f", clothing);
			clothingText.setText(clothingAsString);
			String otherAsString = "$" + String.format("%.2f", other);
			otherText.setText(otherAsString);
		}
	}

}
