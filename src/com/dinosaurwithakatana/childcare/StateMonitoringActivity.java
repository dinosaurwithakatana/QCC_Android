/**
 * 
 */
package com.dinosaurwithakatana.childcare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * @author vishnu
 *
 */
public class StateMonitoringActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_state_monitoring);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

		Button btnComplaints = (Button)findViewById(R.id.btnComplaints);
		Button btnAbuse = (Button)findViewById(R.id.btnAbuse);
		Button btnInspection = (Button)findViewById(R.id.btnViewInspection);
		Button btnInquiries = (Button)findViewById(R.id.btnOtherInquiries);

		//Alert dialog to show the user about compaints
		final AlertDialog.Builder builderComplaints = new AlertDialog.Builder(this);
		btnComplaints.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				builderComplaints.setMessage(R.string.complaints_text)
				.setTitle(R.string.complaints)
				.setPositiveButton("Call Now!", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Call the number
						String expert_num = "404-463-0703";
						String uri = "tel:" + expert_num.trim() ;
						Intent intent = new Intent(Intent.ACTION_CALL);
						intent.setData(Uri.parse(uri));
						startActivity(intent);

					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				
				AlertDialog complaintsDialog = builderComplaints.create();
				complaintsDialog.show();

			}
		});
		
		//Alert Dialog to show user information about abuse
		final AlertDialog.Builder builderAbuse= new AlertDialog.Builder(this);
		btnAbuse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				builderAbuse.setMessage(R.string.report_abuse_text)
				.setTitle(R.string.report_abuse)
				.setPositiveButton("Call Now!", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Call the number
						String expert_num = "404-463-0703";
						String uri = "tel:" + expert_num.trim() ;
						Intent intent = new Intent(Intent.ACTION_CALL);
						intent.setData(Uri.parse(uri));
						startActivity(intent);

					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				
				AlertDialog abuseDialog= builderAbuse.create();
				abuseDialog.show();

			}
		});
		
		//alert dialog to show user about inspection reports
		final AlertDialog.Builder builderInspection= new AlertDialog.Builder(this);
		btnInspection.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				builderInspection.setMessage(R.string.view_inspection_text)
				.setTitle(R.string.view_inspection)
				.setPositiveButton("Open Site!", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Open website in browser
						Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.decal.ga.gov"));
						startActivity(browserIntent);

					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				
				AlertDialog inspectionDialog= builderInspection.create();
				inspectionDialog.show();

			}
		});
		
		//alert dialog to show user about other inquiries
		final AlertDialog.Builder builderInquiries= new AlertDialog.Builder(this);
		btnInquiries.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				builderInquiries.setMessage(R.string.other_inquiries_text)
				.setTitle(R.string.other_inquiries)
				.setPositiveButton("Call Now!", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Call the number
						String expert_num = "877-255-4254";
						String uri = "tel:" + expert_num.trim() ;
						Intent intent = new Intent(Intent.ACTION_CALL);
						intent.setData(Uri.parse(uri));
						startActivity(intent);

					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				
				AlertDialog inquiriesDialog= builderInquiries.create();
				inquiriesDialog.show();

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
