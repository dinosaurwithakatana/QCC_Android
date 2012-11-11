/**
 * 
 */
package com.dinosaurwithakatana.childcare;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author vishnu
 *
 */
public class ChildCareSelectionActivity extends Activity {

	private String businessName, businessId, contactPhoneNumber, contactEmail, contactWebsite;
	private String TAG = ChildCareSelectionActivity.class.getSimpleName();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_child_care_selection);		

		Intent i = getIntent();
		businessName = i.getStringExtra("Name");
		businessId = i.getStringExtra("id");
		
		Log.v(TAG,"Business Name: "+businessName);

		TextView txtBusinessName = (TextView)findViewById(R.id.txt_business_name);
		TextView txtTypeOfCare = (TextView)findViewById(R.id.txt_type_of_care);
		TextView txtStreetAddress = (TextView)findViewById(R.id.txt_street_address);
		TextView txtState = (TextView) findViewById(R.id.txt_state);
		TextView txtZip = (TextView) findViewById(R.id.txt_zip);
		TextView txtCity = (TextView) findViewById(R.id.txt_city);
		Button callContact = (Button) findViewById(R.id.btnCallContact);
		Button openWebsite = (Button) findViewById(R.id.btnWebsite);
		Button emailContact = (Button) findViewById(R.id.btnEmailContact);

		try{
			String responseJSON = new GetSelectedCare().execute(businessId).get();
			JSONArray o = new JSONArray(responseJSON);
			Log.v(TAG,o.toString(1));
			int responseLength = o.length();
			Log.v(TAG,""+responseLength);

			JSONObject responseObject = o.getJSONObject(0);
			Log.v(TAG,"child care selection: " + responseObject.toString(1));
			
			txtBusinessName.setText(businessName);
			txtTypeOfCare.setText(responseObject.getString("Type Of Care"));
			txtStreetAddress.setText(responseObject.getString("Street Address"));
			txtState.setText(responseObject.getString("State/Prov."));
			txtZip.setText(responseObject.getString("Zip"));
			txtCity.setText(responseObject.getString("City"));
			contactEmail = responseObject.getString("Email");
			


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	} 
	/**
	 * 
	 */
	public ChildCareSelectionActivity() {
		// TODO Auto-generated constructor stub
	}
	
	

}
