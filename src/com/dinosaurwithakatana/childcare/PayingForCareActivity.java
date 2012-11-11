/**
 * 
 */
package com.dinosaurwithakatana.childcare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author vishnu
 *
 */
public class PayingForCareActivity extends Activity {
	private static final String TAG = "Child Care" +PayingForCareActivity.class.getSimpleName();                                                                                                                  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_for_care);

		final AlertDialog.Builder builderCaps= new AlertDialog.Builder(this);
		Button btnCaps = (Button)findViewById(R.id.btn_LearnAboutCaps);
		btnCaps.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 2. Chain together various setter methods to set the dialog characteristics
				builderCaps.setMessage("The Childcare and Parent Services (CAPS) program is operated by Bright from the Start: Georgia Department of Early Care and Learning and helps very low income families afford quality child care. Subsidized care is available for children with ages ranging from birth to 13 years. This can be extended to age 18 if the child has special needs.\n\nParents or guardians may qualify to receive subsidized care if they: \n\t have limited income; and \n\t need child care to work, attend school, or attend training")
				.setTitle("CAPS program")
				.setPositiveButton("Apply!", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.compass.ga.gov"));
						startActivity(browserIntent);
						
					}
				});

				// 3. Get the AlertDialog from create()
				AlertDialog dialog = builderCaps.create();
				dialog.show();

			}
		});
		final AlertDialog.Builder builderTanf= new AlertDialog.Builder(this);
		Button btnTanf = (Button)findViewById(R.id.btn_learn_tanf);
		btnTanf.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 2. Chain together various setter methods to set the dialog characteristics
				builderTanf.setMessage("The Temporary Assistance for Needy Families (TANF) Program is operated by the Department of Family and Children Services. Subsidized care is available for children ages birth to 13 years. This can be extended to age 18 if the child has special needs. All adult recipients have a work requirement, and are required to participate in work activities and training for at least 30 hours weekly.") 
				.setTitle("TANF program")
				.setPositiveButton("Apply!", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.compass.ga.gov"));
						startActivity(browserIntent);
						
					}
				});

				// 3. Get the AlertDialog from create()
				AlertDialog tanf_dialog = builderTanf.create();
				tanf_dialog.show();

			}
		});
	}

}
