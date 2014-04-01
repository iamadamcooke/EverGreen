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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountsActivity.
 */
public class AccountsActivity extends Activity {

    /** The accounts. */
    private ArrayList<Account> accounts;

    /** The accounts adapter. */
    private AccountsAdapter accountsAdapter;

    /** The user id. */
    private int userId;

    /** The lv. */
    private ListView lv;

    /** The db manager. */
    private DBManager dbManager;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        dbManager = new DBManager(this);
        userId = getIntent().getExtras().getInt("userId");
        accounts = dbManager.getAccounts(userId);
        if (accounts == null) {
            accounts = new ArrayList<Account>();
        }
        accountsAdapter = new AccountsAdapter(this, accounts);
        lv = (ListView) findViewById(R.id.accountsList);
        lv.setAdapter(accountsAdapter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.accounts, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_newAccount:
                openNewAccountDialog();
                return true;
            case R.id.action_spendingReport:
                openViewSpendingReportDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * On account clicked.
     * 
     * @param view
     *            the view
     */
    public void onAccountClicked(View view) {
        final int position = lv.getPositionForView(view);
        Account acc = accounts.get(position);
        Intent intent = new Intent(getApplicationContext(),
            TransactionsActivity.class);
        intent.putExtra("account_id", dbManager.getAccountId(acc
            .getDisplayName(), userId));
        intent.putExtra("userId", userId);
        startActivity(intent);

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onResume()
     */
    public void onResume() {
        super.onResume();
        accounts = dbManager.getAccounts(userId);
        if (accounts == null) {
            accounts = new ArrayList<Account>();
        }
        accountsAdapter.setAccounts(accounts);
        accountsAdapter.notifyDataSetChanged();
    }

    /**
     * Open new account dialog.
     */
    private void openNewAccountDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
            AccountsActivity.this, AlertDialog.THEME_HOLO_DARK);

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
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String accName = name.getText().toString();
                String dispName = display.getText().toString();
                double balance = Double.parseDouble(bal.getText().toString());
                double intRate = Double.parseDouble(ir.getText().toString());
                dbManager.createAccount(userId, accName, dispName,
                    balance, intRate);
                accounts.add(new Account(accName, dispName, balance, intRate));
                accountsAdapter.notifyDataSetChanged();
            }
        });
        // Setting Negative "NO" Button
        builder.setNegativeButton("Cancel",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();
                }
            });

        // closed

        // Showing Alert Message
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView) dialog
            .findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.show();

    }

    /**
     * Open view spending report dialog.
     */
    private void openViewSpendingReportDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
            AccountsActivity.this, AlertDialog.THEME_HOLO_DARK);
        // Setting Dialog Title
        builder.setTitle("View Spending Report");

        // Setting Dialog Message
        builder.setMessage("Enter Date Range:");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        final TextView start = new TextView(this);
        start.setText("Start Date: ");
        start.setTextColor(Color.WHITE);
        start.setTextSize(25);
        LinearLayout startDateLayout = new LinearLayout(this);
        startDateLayout.setOrientation(LinearLayout.HORIZONTAL);
        startDateLayout.setGravity(Gravity.CENTER);
        final Spinner smonthSpinner = new Spinner(this);
        String[] monthsArray = {"01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12"};
        ArrayAdapter<String> smonthSpinnerAdapter = new ArrayAdapter<String>(
            this, R.layout.category_spinner, monthsArray);
        smonthSpinner.setAdapter(smonthSpinnerAdapter);
        smonthSpinner.setGravity(Gravity.CENTER);
        smonthSpinner.setLayoutParams(new Spinner.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));

        final Spinner sdaySpinner = new Spinner(this);
        String[] dayArray = {"01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31"};
        ArrayAdapter<String> sdaySpinnerAdapter = new ArrayAdapter<String>(
            this, R.layout.category_spinner, dayArray);
        sdaySpinner.setAdapter(sdaySpinnerAdapter);
        sdaySpinner.setGravity(Gravity.CENTER);
        sdaySpinner.setLayoutParams(new Spinner.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));

        final Spinner syearSpinner = new Spinner(this);
        String[] yearArray = {"2014"};
        ArrayAdapter<String> syearSpinnerAdapter = new ArrayAdapter<String>(
            this, R.layout.category_spinner, yearArray);
        syearSpinner.setAdapter(syearSpinnerAdapter);
        syearSpinner.setGravity(Gravity.CENTER);
        syearSpinner.setLayoutParams(new Spinner.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));

        startDateLayout.addView(start);
        startDateLayout.addView(smonthSpinner);
        startDateLayout.addView(sdaySpinner);
        startDateLayout.addView(syearSpinner);

        final TextView end = new TextView(this);
        end.setText("End Date: ");
        end.setTextColor(Color.WHITE);
        end.setTextSize(25);
        LinearLayout endDateLayout = new LinearLayout(this);
        endDateLayout.setOrientation(LinearLayout.HORIZONTAL);
        endDateLayout.setGravity(Gravity.CENTER);
        final Spinner emonthSpinner = new Spinner(this);
        new ArrayAdapter<String>(
            this, R.layout.category_spinner, monthsArray);
        emonthSpinner.setAdapter(smonthSpinnerAdapter);
        emonthSpinner.setGravity(Gravity.CENTER);
        emonthSpinner.setLayoutParams(new Spinner.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));

        final Spinner edaySpinner = new Spinner(this);

        new ArrayAdapter<String>(
            this, R.layout.category_spinner, dayArray);
        edaySpinner.setAdapter(sdaySpinnerAdapter);
        edaySpinner.setGravity(Gravity.CENTER);
        edaySpinner.setLayoutParams(new Spinner.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));

        final Spinner eyearSpinner = new Spinner(this);
        new ArrayAdapter<String>(
            this, R.layout.category_spinner, yearArray);
        eyearSpinner.setAdapter(syearSpinnerAdapter);
        eyearSpinner.setGravity(Gravity.CENTER);
        eyearSpinner.setLayoutParams(new Spinner.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));

        endDateLayout.addView(end);
        endDateLayout.addView(emonthSpinner);
        endDateLayout.addView(edaySpinner);
        endDateLayout.addView(eyearSpinner);

        layout.addView(startDateLayout);
        layout.addView(endDateLayout);

        builder.setView(layout);

        // Setting Positive "Yes" Button
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                String sdateAsString = syearSpinner.getSelectedItem()
                    .toString()
                    + "-"
                    + smonthSpinner.getSelectedItem().toString()
                    + "-"
                    + sdaySpinner.getSelectedItem().toString();
                String edateAsString = eyearSpinner.getSelectedItem()
                    .toString()
                    + "-"
                    + emonthSpinner.getSelectedItem().toString()
                    + "-"
                    + edaySpinner.getSelectedItem().toString();

                Intent intent = new Intent(getApplicationContext(),
                    SpendingReportActivity.class);
                intent.putExtra("startDate", sdateAsString);
                intent.putExtra("endDate", edateAsString);
                intent.putExtra("userId", userId);
                startActivity(intent);

            }
        });
        // Setting Negative "NO" Button
        builder.setNegativeButton("Cancel",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after
                    // dialog
                    dialog.cancel();
                }
            });

        // closed

        // Showing Alert Message
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView) dialog
            .findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.show();

    }

}
