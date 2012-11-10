/**
 * 
 */
package com.dinosaurwithakatana.childcare;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author vishnu
 *
 */
public class TalkToAnExpertActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);

		Button btnCallExpert = (Button)findViewById(R.id.btnCallExpert);
		btnCallExpert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String expert_num = "877-255-4254";
				String uri = "tel:" + expert_num.trim() ;
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse(uri));
				startActivity(intent);
			}
		});
	}
}
