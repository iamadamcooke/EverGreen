package edu.gatech.cs2340.evergreen;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

// TODO: Auto-generated Javadoc
/**
 * The Class Success.
 */
public class Success extends Activity {

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.success, menu);
        return true;
    }

}
