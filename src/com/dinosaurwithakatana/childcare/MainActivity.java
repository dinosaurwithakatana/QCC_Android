package com.dinosaurwithakatana.childcare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private CurrentUser curUser;
	private Bundle b;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent i = getIntent();
        b = i.getExtras();
        
        Button btnTalkToAnExpert = (Button)findViewById(R.id.btnTalkToExpert);
        btnTalkToAnExpert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,TalkToAnExpertActivity.class);
                startActivity(i);
            }
        });
        
        Button btnPayForCare = (Button)findViewById(R.id.btnPayingForCare);
        btnPayForCare.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,PayingForCareActivity.class);
				startActivity(i);
				
			}
		});
        
        //Load Activity to inform user about state monitoring
        Button btnStateMonitoring = (Button)findViewById(R.id.btnStateMonitoring);
        btnStateMonitoring.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,StateMonitoringActivity.class);
				startActivity(i);
			}
		});
        
        //Load activity to find local care sites
        Button btnFindCare = (Button)findViewById(R.id.btnFind);
        btnFindCare.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,DisplayLocalChildCareActivity.class);
				i.putExtras(b);
				startActivity(i);
			}
		});
        
        Button btnChecklist = (Button)findViewById(R.id.btnChecklist);
        btnChecklist.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,CheckListList.class);
				startActivity(i);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings:
                return true;
            case R.id.menu_profile:
            	Intent i = new Intent(MainActivity.this, ViewProfileActivity.class);
            	i.putExtras(b);
            	startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    } 
}
