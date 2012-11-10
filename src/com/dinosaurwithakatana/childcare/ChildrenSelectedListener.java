package com.dinosaurwithakatana.childcare;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class ChildrenSelectedListener implements OnItemSelectedListener {
	private static final String TAG = "Child Care" +JobItemSelectedListener.class.getSimpleName();                                                                                                                  
	private int numOfChildren;
	/**
	 * @return the education
	 */
	public int getNumberOfChildren() {
		return numOfChildren;
	}

	/**
	 * @param jobType the education to set
	 */
	public void setNumOfChildren(int num) {
		this.numOfChildren = num;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		Log.v(TAG,"Selected:"+arg2);

		numOfChildren = arg2 +1;
			

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
