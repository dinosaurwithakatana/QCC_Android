package com.dinosaurwithakatana.childcare;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author vishnu
 *
 */
public class CreateAccountActivity extends FragmentActivity implements TabListener {


	public static EditText username;
	public static EditText password;
	public static EditText confirmPassword;
	public static EditText txtFName;
	public static EditText txtMName;
	public static EditText txtLName;
	public static Spinner spnrChildren;
	public static ChildrenSelectedListener childrenListener;
	private static ArrayList<LinearLayout> dynamicLayouts;
	public static EditText txtEmailAddress;
	public static EditText txtPhoneNumer;

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
				 rootView = inflater.inflate(R.layout.login_fragment, container, false);
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
				 txtFName = ((EditText) rootView.findViewById(R.id.txtFirstName));
				 txtMName = ((EditText) rootView.findViewById(R.id.txtMiddleName));
				 txtLName = ((EditText) rootView.findViewById(R.id.txtLastName));
				 txtEmailAddress = ((EditText) rootView.findViewById(R.id.txtEmailAddress));
				 txtPhoneNumer = ((EditText) rootView.findViewById(R.id.txtPhoneNumber));
				 return rootView;
			 }
			 /*Display the experience screen*/
			 case 3: {
				 //inflate the required layout for the fragment
				 rootView  = inflater.inflate(R.layout.children_fragment, container, false);
				 Log.d(TAG,"Loaded experience creation screen");
				 spnrChildren = ((Spinner) rootView.findViewById(R.id.spnrNumOfChildren));

				 //Add a listener to the children spinner
				 childrenListener = new ChildrenSelectedListener();
				 spnrChildren.setOnItemSelectedListener(new OnItemSelectedListener() {

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
					 public void onNothingSelected(AdapterView<?> arg0) {
						 // TODO Auto-generated method stub

					 }

					 @Override
					 public void onItemSelected(AdapterView<?> arg0,
							 View arg1, int arg2, long arg3) {
						 // TODO Auto-generated method stub
						 numOfChildren = arg2 +1;

						 //Grab the layouts from the front
						 ScrollView sv = (ScrollView) rootView.findViewById(R.id.childrenScrlView);
						 Log.v(TAG,"grabbed sroll view in children fragment");

						 LinearLayout lytChildrenLayout = (LinearLayout)rootView.findViewById(R.id.children_linear);
						 LinearLayout lytDynamicLayoutBase= (LinearLayout)rootView.findViewById(R.id.dynamic_children_linear);
						 lytDynamicLayoutBase.removeAllViews();
						 Log.v(TAG,"grabbed linear layouts inside children fragment scroll view");

						 //New linear layout to display the dynamic stuff
						 LinearLayout lytNewLayout = new LinearLayout(getActivity().getBaseContext());
						 lytNewLayout.setId(R.id.dynamic_children_layout);
						 lytNewLayout.setOrientation(LinearLayout.VERTICAL);
						 lytNewLayout.removeAllViews();


						 dynamicLayouts = new ArrayList<LinearLayout>();

						 for(int i = 0;i<numOfChildren;i++){
							 //Horizontal Linear Layout for each of the children
							 LinearLayout lytTempLayout = new LinearLayout(getActivity().getBaseContext());
							 lytTempLayout.setOrientation(LinearLayout.VERTICAL);

							 //Add textview to horizontal layout to prompt user
							 TextView tvChildLabel= new TextView(getActivity().getBaseContext());
							 tvChildLabel.setText("Child "+(i+1));
							 
							 //Layout to hold name stuff
							 LinearLayout lytNameLayout = new LinearLayout(getActivity().getBaseContext());
							 lytNameLayout.setOrientation(LinearLayout.VERTICAL);
							 
							 //TextView to prompt the user for their child's name
							 TextView tvNamePrompt = new TextView(getActivity().getBaseContext());
							 tvNamePrompt.setText("Child's Name");
							 
							 //EditText to accept first name
							 EditText edtxtChildFirstName = new EditText(getActivity().getBaseContext());
							 edtxtChildFirstName.setHint("First Name");
							 edtxtChildFirstName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

							 //EditText to accept middle name
							 EditText edtxtChildMiddleName = new EditText(getActivity().getBaseContext());
							 edtxtChildMiddleName.setHint("Middle Name");
							 edtxtChildMiddleName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
							 
							 //EditText to accept first name
							 EditText edtxtChildLastName = new EditText(getActivity().getBaseContext());
							 edtxtChildLastName.setHint("Last Name");
							 edtxtChildLastName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
							 
							 //Add Name stuff to it's layout
							 lytNameLayout.addView(tvNamePrompt);
							 lytNameLayout.addView(edtxtChildFirstName);
							 lytNameLayout.addView(edtxtChildMiddleName);
							 lytNameLayout.addView(edtxtChildLastName);
							 
							 //Layout to hold the DOB stuff
							 LinearLayout lytAgeLayout = new LinearLayout(getActivity().getBaseContext());
							 lytAgeLayout.setOrientation(LinearLayout.HORIZONTAL);

							 //Textview to prompt the user to enter DOBs for their children
							 TextView tvAgePrompt = new TextView(getActivity().getBaseContext());
							 tvAgePrompt.setText("Child's Age");
							 tvAgePrompt.setId(R.id.txtvw_dob_prompt);

							 //Spinner to select age
							 Spinner spnrAgeSelect = new Spinner(getActivity().getBaseContext());
							 spnrAgeSelect.setOnItemSelectedListener(new OnItemSelectedListener(
									 ) {

								 @Override
								 public void onItemSelected(
										 AdapterView<?> arg0, View arg1,
										 int arg2, long arg3) {
									 // TODO Auto-generated method stub

								 }

								 @Override
								 public void onNothingSelected(
										 AdapterView<?> arg0) {
									 // TODO Auto-generated method stub

								 }
							 });

							 //Adapter to show the different age ranges
							 ArrayAdapter<CharSequence> childrenAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.age_list, android.R.layout.simple_spinner_dropdown_item);
							 childrenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							 spnrAgeSelect.setAdapter(childrenAdapter);
							 Log.v(TAG,"Spinner listener added and adapter added to children num");

							 //Add the DOB stuff to the DOB layout
							 lytAgeLayout.addView(tvAgePrompt);
							 lytAgeLayout.addView(spnrAgeSelect);




							 //Add the whole shebang to the main layout
							 lytTempLayout.addView(tvChildLabel);
							 lytTempLayout.addView(lytNameLayout);
							 lytTempLayout.addView(lytAgeLayout);
							 dynamicLayouts.add(lytTempLayout);

						 }
						 for(int i = 0; i<dynamicLayouts.size();i++){
							 lytNewLayout.addView(dynamicLayouts.get(i));
						 }
						 lytDynamicLayoutBase.addView(lytNewLayout);

					 }

				 });


				 //Add the string array for the number of children to the spinner
				 ArrayAdapter<CharSequence> ageAdapter= ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.num_of_children, android.R.layout.simple_spinner_dropdown_item);
				 ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				 spnrChildren.setAdapter(ageAdapter);
				 Log.v(TAG,"Spinner listener added and adapter added to children num");


				 //Action on the button
				 Button createAccount = ((Button)rootView.findViewById(R.id.btnCreate));
				 createAccount.setOnClickListener(new View.OnClickListener() {


					 private String usernameInput, passwordInput, confirmPasswordInput, fNameInput, mNameInput, lNameInput, emailAddress, phoneNumber;


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
						 fNameInput = txtFName.getText().toString();
						 mNameInput = txtMName.getText().toString();
						 lNameInput = txtLName.getText().toString();
						 emailAddress = txtEmailAddress.getText().toString();
						 phoneNumber = txtPhoneNumer.getText().toString();
								 
						 if(DEBUG){
							 Log.v(TAG,"create clicked" + usernameInput + " " + fNameInput + " " + mNameInput + " " + lNameInput + " " + emailAddress );
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
								 user.put("email_addr",emailAddress);
								 user.put("phone_number",phoneNumber);
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

		public static void setChildrenLayout(int num){

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
			case 2: return getString(R.string.tab_children).toUpperCase();
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