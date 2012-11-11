/**
 * 
 */
package com.dinosaurwithakatana.childcare;

import java.security.Provider;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author vishnu
 *
 */
public class CheckListList extends ListActivity {

	ArrayList<String> checkListTitles;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checklist);
		
		checkListTitles = new ArrayList<String>();
		
		checkListTitles.add("Provider/Teacher");
		checkListTitles.add("Field Trips and Transportations");
		checkListTitles.add("Your Involvement");
		checkListTitles.add("Your feeling");
		checkListTitles.add("Inside Environment");
		checkListTitles.add("Outside Environment");
		checkListTitles.add("Toys");
		checkListTitles.add("Activities");
		checkListTitles.add("Children w/ Special Needs");
		checkListTitles.add("Meals");
		checkListTitles.add("Handwashing");
		checkListTitles.add("Illness");
		
		ArrayAdapter<String> arrayAdapter =   new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,checkListTitles);
		setListAdapter(arrayAdapter);
	}
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Do something when a list item is clicked

//		Toast toast = Toast.makeText(getApplicationContext(), "You just clicked " + l.getItemAtPosition(position), Toast.LENGTH_SHORT);
//		toast.show();

		Intent intent = new Intent(CheckListList.this,ProviderActivity.class);
		startActivity(intent);

		super.onListItemClick(l, v, position, id);
	}
	/**
	 * 
	 */
	public CheckListList() {
		// TODO Auto-generated constructor stub
	}

}
