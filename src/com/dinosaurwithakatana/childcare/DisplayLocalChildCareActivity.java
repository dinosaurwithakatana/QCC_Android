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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author vishnu
 *
 */
public class DisplayLocalChildCareActivity extends ListActivity {

	ArrayList<String> childCareNames, childCareIds;
	private Bundle b;
	private String zipCode;
	private final String TAG = DisplayLocalChildCareActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		childCareNames = new ArrayList<String>();
		childCareIds = new ArrayList<String>();
		
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
				childCareIds.add(o.getJSONObject(i).getString("_id"));
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
	
	@Override 
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Do something when a list item is clicked

//		Toast toast = Toast.makeText(getApplicationContext(), "You just clicked " + l.getItemAtPosition(position), Toast.LENGTH_SHORT);
//		toast.show();

		Intent intent = new Intent(DisplayLocalChildCareActivity.this,ChildCareSelectionActivity.class);
		intent.putExtra("Name", (String)l.getItemAtPosition(position));
		intent.putExtra("id",childCareIds.get(position));
		startActivity(intent);

		super.onListItemClick(l, v, position, id);
	}
	/**
	 * 
	 */
	public DisplayLocalChildCareActivity() {
		// TODO Auto-generated constructor stub
	}

}
