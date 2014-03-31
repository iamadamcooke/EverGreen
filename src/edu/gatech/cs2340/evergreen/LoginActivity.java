package edu.gatech.cs2340.evergreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginActivity.
 */
public class LoginActivity extends Activity {

	/** The db manager. */
	private DBManager dbManager;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbManager = new DBManager(this);
		Button loginbtn = (Button) findViewById(R.id.btnLogin);
		final EditText passwordtxt = (EditText) findViewById(R.id.passwordText);
		final EditText usernametxt = (EditText) findViewById(R.id.usernameText);
		final TextView incorrectLogin = (TextView) findViewById(R.id.textView1);

		incorrectLogin.setVisibility(TextView.INVISIBLE);

		loginbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// switching to register screen
				String passForUser = dbManager.getPass(usernametxt.getText()
						.toString());
				if ((passForUser != null)
						&& (passForUser
								.equals(passwordtxt.getText().toString()))) {
					Intent loginIntent = new Intent(getApplicationContext(),
							AccountsActivity.class);
					loginIntent.putExtra("userId", dbManager
							.getUserId(usernametxt.getText().toString()));
					startActivity(loginIntent);
				} else {
					incorrectLogin.setVisibility(TextView.VISIBLE);
				}

			}
		});

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
