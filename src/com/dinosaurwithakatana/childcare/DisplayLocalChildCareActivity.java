/**
 * 
 */
package com.dinosaurwithakatana.childcare;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

/**
 * @author vishnu
 *
 */
public class DisplayLocalChildCareActivity extends ListActivity {

	ArrayList<String> childCareNames;
	private Bundle b;
	private String zipCode;
	private final String TAG = DisplayLocalChildCareActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		childCareNames = new ArrayList<String>();
		setContentView(R.layout.activity_display_local_care);
		
		Intent intent = getIntent();
		b = intent.getExtras();
		
		zipCode = b.getString("zip");
		
		try {
			String responseJSON = new GetLocalCare().execute(zipCode).get();
			JSONArray o = new JSONArray(responseJSON);
			Log.v(TAG,o.toString(1));
			int responseLength = o.length();
			Log.v(TAG,"Response Length "+responseLength);
			
			for(int i = 0; i<responseLength;i++){
				childCareNames.add(o.getJSONObject(i).getString("Business Name"));
				}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ArrayAdapter<String> arrayAdapter =   new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,childCareNames);
		setListAdapter(arrayAdapter);
		
	}
	/**
	 * 
	 */
	public DisplayLocalChildCareActivity() {
		// TODO Auto-generated constructor stub
	}

}
