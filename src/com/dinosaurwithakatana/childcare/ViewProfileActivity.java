/**
 * 
 */
package com.dinosaurwithakatana.childcare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author vishnu
 *
 */
public class ViewProfileActivity extends Activity {

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.view_profile_activity);
	        
	        Intent i = getIntent();
	        Bundle b = i.getExtras();
	        
	        TextView fName = (TextView)findViewById(R.id.txt_view_first_name);
	        TextView mName = (TextView)findViewById(R.id.txt_view_middle_name);
	        TextView lName = (TextView)findViewById(R.id.txt_view_last_name);
	        TextView emailAddress = (TextView)findViewById(R.id.txt_view_email_address);
	        TextView phoneNumber = (TextView)findViewById(R.id.txt_view_phone_number);
	        
	        fName.setText(b.getString("fName"));
	        mName.setText(b.getString("mName"));
	        lName.setText(b.getString("lName"));
	        emailAddress.setText(b.getString("email"));
	        phoneNumber.setText(b.getString("phone"));
	 }
}
