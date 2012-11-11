package com.dinosaurwithakatana.childcare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class GetRating extends AsyncTask<String, Void, String>{

	private JSONObject u_id_object;

	@Override
	protected String doInBackground(String... params) {
		//Create the GET request for logging in
		String u_id= params[0].toString();
		try {
			u_id_object = new JSONObject(u_id);
			String requestURL = "https://api.mongolab.com/api/1/databases/qcc/collections/users/"+u_id_object.getString("$oid")+"?";
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//			nameValuePairs.add(new BasicNameValuePair("q", "{\"_id\": \""+u_id_object.getString("$oid")+"\"}")); 
			nameValuePairs.add(new BasicNameValuePair("apiKey", "507989d2e4b0e9396b4a4a90"));
			HttpClient httpclient = new DefaultHttpClient();
			String paramString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
			System.out.println(paramString);
			requestURL+=paramString;
			System.out.println(requestURL);
			HttpGet httpget = new HttpGet(requestURL);
			HttpResponse response;
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			System.out.println("Handling Response");
			InputStream in = entity.getContent();
			String json = convertStreamToString(in);
			System.out.println("Creating JSON object");
			JSONArray o = new JSONArray (json);
			System.out.println("This is it"+ o.toString(1));
			return o.toString();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String convertStreamToString(InputStream inputStream) throws IOException {
		if (inputStream != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				inputStream.close();
			}
			return sb.toString();
		} else {
			return "";
		}
	}

}