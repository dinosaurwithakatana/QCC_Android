package com.dinosaurwithakatana.childcare;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class JobItemSelectedListener implements OnItemSelectedListener {
	private static final String TAG = "Roaming Recruiter " +JobItemSelectedListener.class.getSimpleName();                                                                                                                  
	private String jobType;
	/**
	 * @return the education
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * @param jobType the education to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		Log.v(TAG,"Selected:"+arg2);
		switch (arg2) {
		case 0:
			jobType="11-0000";
			break;
		case 1:
			jobType="13-0000";
			break;
		case 2:
			jobType="15-0000";
			break; 
		case 3:
			jobType="17-0000";
			break;
		case 4:
			jobType="19-0000";
			break;
		case 5:
			jobType="21-0000";
			break;
		case 6:
			jobType="23-0000";
			break;
		case 7:
			jobType="25-0000";
			break;
		case 8:
			jobType="27-0000";
			break;
		case 9:
			jobType="29-0000";
			break;
		case 10:
			jobType="31-0000";
			break;
		case 11:
			jobType="33-0000";
			break;
		case 12:
			jobType="35-0000";
			break;
		case 13:
			jobType="37-0000";
			break;
		case 14:
			jobType="39-0000";
			break;
		case 15:
			jobType="41-0000";
			break;
		case 16:
			jobType="43-0000";
			break;
		case 17:
			jobType="45-0000";
			break;
		case 18:
			jobType="47-0000";
			break;
		case 19:
			jobType="49-0000";
			break;
		case 20:
			jobType="51-0000";
			break;
		case 21:
			jobType="53-0000";
			break;
		case 22:
			jobType="55-0000";
			break;
			
		default:
			break;
		}
//		jobType = arg0.getItemAtPosition(arg2).toString();
		Log.v(TAG,jobType);

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
