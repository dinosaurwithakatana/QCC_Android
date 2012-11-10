package com.dinosaurwithakatana.childcare;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author vishnu
 *
 */
public class CreateAccountActivity extends FragmentActivity implements TabListener {


	public static EditText username;
	public static EditText password;
	public static EditText confirmPassword;
	public static EditText fName;
	public static EditText mName;
	public static EditText lName;

	/**
	 * Displays each section
	 */
	public static class CreateAcctFragment extends Fragment {
		private View rootView;

		public CreateAcctFragment() {
		}

		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			Bundle args = getArguments();
			int screen = args.getInt(ARG_SECTION_NUMBER);
			switch (screen){
/*			
 * Display the login screen
*/			case 1: {
				//inflate the required layout for the fragment
rootView = inflater.inflate(R.layout.loginfragment, container, false);
Log.d(TAG,"Loaded login creation screen");
username = ((EditText)rootView.findViewById(R.id.txtCreateUsername));
password = ((EditText)rootView.findViewById(R.id.txtCreatePassword));
confirmPassword = ((EditText)rootView.findViewById(R.id.txtConfirmCreatePassword));
return rootView;
}
/*Display the personal information screen*/
case 2: {
				//inflate the required layout for the fragment
	rootView = inflater.inflate(R.layout.personal_fragment, container, false);
	Log.d(TAG,"Loaded personal creation screen");
	fName = ((EditText) rootView.findViewById(R.id.txtFirstName));
	mName = ((EditText) rootView.findViewById(R.id.txtMiddleName));
	lName = ((EditText) rootView.findViewById(R.id.txtLastName));
	return rootView;
}
/*Display the experience screen*/
case 3: {
				//inflate the required layout for the fragment
	rootView  = inflater.inflate(R.layout.experience_fragment, container, false);
	Log.d(TAG,"Loaded experience creation screen");


				//Action on the button
	Button createAccount = ((Button)rootView.findViewById(R.id.btnCreate));
	createAccount.setOnClickListener(new View.OnClickListener() {


		private String usernameInput, passwordInput, confirmPasswordInput, fNameInput, mNameInput, lNameInput;


		public void onClick(View v) {
						// TODO Auto-generated method stub

			usernameInput = username.getText().toString();
			try {
				passwordInput = AeSimpleSHA1.SHA1("salt"+password.getText().toString());
			} catch (NoSuchAlgorithmException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				confirmPasswordInput = AeSimpleSHA1.SHA1("salt"+confirmPassword.getText().toString());
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			fNameInput = fName.getText().toString();
			mNameInput = mName.getText().toString();
			lNameInput = lName.getText().toString();
			if(DEBUG){
				Log.v(TAG,"create clicked" + usernameInput + " " + fNameInput + " " + mNameInput + " " + lNameInput );
			}
						//Send the informationt to the DB only if passwords match
			if(passwordInput.equals(confirmPasswordInput)){
				JSONObject user = new JSONObject();
				try{
					user.put("username",usernameInput);
					user.put("password",passwordInput);
					user.put("f_name",fNameInput);
					user.put("m_name",mNameInput);
					user.put("l_name",lNameInput);
				} catch (JSONException e){
					e.printStackTrace();
				}
				new PostNewAcct().execute(user.toString());
			}
			else{
							//Display an alert dialog to tell the user the passwords don't match
				Toast toast = Toast.makeText(getActivity().getBaseContext(), "Passwords don't match!"  , Toast.LENGTH_SHORT);
				toast.show();

			}
						//Toast to show new user was created
			Toast toast = Toast.makeText(getActivity().getBaseContext(), "New User " + usernameInput+ " Created!"  , Toast.LENGTH_SHORT);
			toast.show();
			Intent i = new Intent(getActivity().getBaseContext(),LoginActivity.class);
			startActivity(i);

		}
	});
return rootView;
}
}
return null;
}
}

public class SectionsPagerAdapter extends FragmentPagerAdapter{

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new CreateAcctFragment();
		Bundle args = new Bundle();
		args.putInt(CreateAcctFragment.ARG_SECTION_NUMBER, i + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0: return getString(R.string.tab_login_info).toUpperCase();
			case 1: return getString(R.string.tab_p_info).toUpperCase();
			case 2: return getString(R.string.tab_experience).toUpperCase();
		}
		return null;
	}
}

private static final String TAG = "Child Care" +CreateAccountActivity.class.getSimpleName();                                                                                                                  
private static Spinner spnrEducation;
private static OnItemSelectedListener spnrListener;
private static boolean DEBUG = true;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
	 * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
	 * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
	 * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	// Create the adapter that will return a fragment for each of the three primary sections
	// of the app.
	/**
	 * Creates the activity
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setOffscreenPageLimit(3);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding tab.
		// We can also use ActionBar.Tab#select() to do this if we have a reference to the
		// Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by the adapter.
			// Also specify this Activity object, which implements the TabListener interface, as the
			// listener for when this tab is selected.
			actionBar.addTab(
				actionBar.newTab()
				.setText(mSectionsPagerAdapter.getPageTitle(i))
				.setTabListener(this));
		}
	}


	public void onTabReselected(Tab arg0, android.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}


	public void onTabSelected(Tab arg0, android.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(arg0.getPosition());
		
	}


	public void onTabUnselected(Tab arg0, android.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
}