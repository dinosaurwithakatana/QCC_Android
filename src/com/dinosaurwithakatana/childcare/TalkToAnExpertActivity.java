/**
 * 
 */
package com.dinosaurwithakatana.childcare;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author vishnu
 *
 */
public class TalkToAnExpertActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);

		Button btnCallExpert = (Button)findViewById(R.id.btnCallExpert);
		btnCallExpert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String expert_num = "877-255-4254";
				String uri = "tel:" + expert_num.trim() ;
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse(uri));
				startActivity(intent);
			}
		});
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
            	// This is called when the Home (Up) button is pressed
                // in the Action Bar.
                Intent parentActivityIntent = new Intent(this, MainActivity.class);
                parentActivityIntent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    } 
}
