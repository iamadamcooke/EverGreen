package edu.gatech.cs2340.evergreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	private DBManager dbManager;
	private Button registerButton;
	private EditText userNameText;
	private EditText password;
	private EditText confirmPassword;
	private TextView errorText;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		dbManager = new DBManager(this);
		registerButton = (Button) findViewById(R.id.registerButton);
		userNameText = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		confirmPassword = (EditText) findViewById(R.id.confirmPassword);
		errorText = (TextView) findViewById(R.id.errorText);
		errorText.setVisibility(TextView.INVISIBLE);
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//switching to register screen
				if(password.getEditableText().toString().equals(confirmPassword.getEditableText().toString()))
				{
					long result = dbManager.createUser(userNameText.getEditableText().toString(), password.getEditableText().toString());
					if(result > -1) {
						Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
						startActivity(loginIntent);
					}
					else {
						errorText.setText("Username already exists.");
						errorText.setVisibility(TextView.VISIBLE);
					}
					
				}
				else {
					errorText.setText("Passwords do not match.");
					errorText.setVisibility(TextView.VISIBLE);
				}

				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
