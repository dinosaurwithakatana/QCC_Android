/**
 * 
 */
package com.dinosaurwithakatana.childcare;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;

/**
 * @author vishnu
 *
 */
public class CheckListActivity extends FragmentActivity{
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    final ActionBar actionBar = getActionBar();
	    
	 // Specify that a dropdown list should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

	    actionBar.setListNavigationCallbacks(
	            // Specify a SpinnerAdapter to populate the dropdown list.
	            new ArrayAdapter(
	                    actionBar.getThemedContext(),
	                    android.R.layout.simple_list_item_1,
	                    android.R.id.text1,
	                    new String[]{ "Tab 1", "Tab 2", "Tab 3" }),

	            // Provide a listener to be called when an item is selected.
	            new ActionBar.OnNavigationListener() {
	                public boolean onNavigationItemSelected(
	                        int position, long id) {
	                    // Take action here, e.g. switching to the
	                    // corresponding fragment.
	                    return true;
	                }
	            });
	}

	/**
	 * 
	 */
	public CheckListActivity() {
		// TODO Auto-generated constructor stub
	}

}
