package com.dinosaurwithakatana.childcare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;

public class PostUserRating extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		try {
			String userID = params[1];
			JSONObject userIDJSON = new JSONObject(userID);
			
//			URL url = new URL("https://api.mongolab.com/api/1/databases/qcc/collections/users?apiKey=507989d2e4b0e9396b4a4a90");
			
			String requestURL = "https://api.mongolab.com/api/1/databases/qcc/collections/users/" + userIDJSON.getString("$oid") +"?";
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//			nameValuePairs.add(new BasicNameValuePair("/", "{\"_id\": \""+userIDJSON.getString("$oid")+"\"}")); 
			nameValuePairs.add(new BasicNameValuePair("apiKey", "507989d2e4b0e9396b4a4a90"));
			String paramString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
			System.out.println(paramString);
			requestURL+=paramString;
			System.out.println(requestURL);
			URL url = new URL(requestURL);
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Content-Type","application/json");
			
			OutputStream out = conn.getOutputStream();
			System.out.println(params[0]);
			
			out.write(params[0].getBytes());
			out.flush();
		
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
	 
			String output;
			String response="";
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				response += output;
			}
	 
			conn.disconnect();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
