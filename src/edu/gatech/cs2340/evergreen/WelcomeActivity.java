package edu.gatech.cs2340.evergreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		Button registerScreen = (Button) findViewById(R.id.btnLogin);
        
        //listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//switching to register screen
				Intent registerIntent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(registerIntent);
				
			}
		});

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

}
