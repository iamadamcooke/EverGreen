package edu.gatech.cs2340.evergreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

// TODO: Auto-generated Javadoc
/**
 * The Class WelcomeActivity.
 */
public class WelcomeActivity extends Activity {

    /** The login button. */
    private Button loginButton;

    /** The register button. */
    private Button registerButton;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        loginButton = (Button) findViewById(R.id.btnLogin);
        registerButton = (Button) findViewById(R.id.btnRegister);
        // listening to register new account link
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // switching to register screen
                Intent loginIntent = new Intent(getApplicationContext(),
                    LoginActivity.class);
                startActivity(loginIntent);

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // switching to register screen
                Intent registerIntent = new Intent(getApplicationContext(),
                    RegisterActivity.class);
                startActivity(registerIntent);

            }
        });

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

}
