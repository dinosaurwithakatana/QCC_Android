package com.dinosaurwithakatana.childcare;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity{
	private static final String TAG = "Child Care" +LoginActivity.class.getSimpleName();                                                                                                                  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginlinear);

		final EditText username = (EditText)findViewById(R.id.txtUsernameInput);
		final EditText password = (EditText)findViewById(R.id.txtPasswordInput);

		Button signIn = (Button) findViewById(R.id.btnSignIn);
		
			// TODO Just for now, this way we can skip logging for a bit
//		Intent i = new Intent(LoginActivity.this,MainActivity.class);
//		startActivity(i);
		
		signIn.setOnClickListener(new View.OnClickListener() {

			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String loginUsername = username.getText().toString();
				String loginPassword = null;
				try {
					loginPassword = AeSimpleSHA1.SHA1("salt"+password.getText().toString());
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Log.v(TAG,"Logging in with "+ loginUsername);
				try {
					String responseJSON = new GetLoginAcct().execute(loginUsername).get();
					JSONArray o = new JSONArray(responseJSON);
					Log.v(TAG,o.toString(1));
					int responseLength = o.length();
					Log.v(TAG,""+responseLength);

					JSONObject responseObject = o.getJSONObject(0);
					Log.v(TAG,responseObject.toString(1));

					if(responseObject.getString("username").equals(loginUsername)){
						if(responseObject.getString("password").equals(loginPassword)){
							System.out.println("Login Successful!");
							Log.d(TAG,"Login Successful!");
							String fName = responseObject.getString("f_name");
							String mName = responseObject.getString("m_name");
							String lName = responseObject.getString("l_name");
							String emailAddress = responseObject.getString("email_addr");
							String phoneNumber = responseObject.getString("phone_number");
							String zipCode = responseObject.getString("zip");
							String userID = responseObject.getString("_id");
							
							Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
							
							CurrentUser curUser = new CurrentUser(fName,mName,lName,emailAddress,phoneNumber);
							
							Intent i = new Intent(LoginActivity.this,MainActivity.class);
							Bundle b = new Bundle();
							b.putString("fName", fName);
							b.putString("mName",mName);
							b.putString("lName",lName);
							b.putString("email",emailAddress);
							b.putString("phone",phoneNumber);
							b.putString("username", responseObject.getString("username"));
							b.putString("zip", zipCode);
							b.putString("u_id", userID);
							i.putExtras(b);
							startActivity(i);
						}
						else{
							System.out.println("Bad password");
							Log.d(TAG,"Bad Password");
							Toast.makeText(getApplicationContext(), "Bad Password!",Toast.LENGTH_SHORT).show();
						}
					}
					else{
						Log.d(TAG,"Login Unsuccessful");
						Toast.makeText(getApplicationContext(), "Bad Username!", Toast.LENGTH_SHORT).show();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
						Log.d(TAG,"Login Unsuccessful");
						Toast.makeText(getApplicationContext(), "Bad Username!", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}


			}
		});
		
		

		Button newUser = (Button) findViewById(R.id.btnNewUser);
		newUser.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LoginActivity.this, CreateAccountActivity.class);
				startActivity(i);

			}
		});
	}
}