package hr.mastermind.donor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


import android.view.View.OnTouchListener;
import android.widget.ImageButton;


public class MainActivity extends Activity {
	public static final String PREFS_NAME = "MyPrefsFile";
	private Context context; 
	public static Context contxtMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        contxtMain = this;
        setContentView(R.layout.activity_main);
        
        final ImageButton btnProfile= (ImageButton) findViewById(R.id.pocetni_btnProfile);
        final ImageButton btnDonations = (ImageButton) findViewById(R.id.pocetna_btnDonations);
        final ImageButton btnSettings= (ImageButton) findViewById(R.id.pocetna_btnSettings);
        final ImageButton btnInstitutions= (ImageButton) findViewById(R.id.pocetna_btnInstitution);
       
   
        btnProfile.setOnTouchListener(new OnTouchListener(){            
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				 switch(arg1.getAction())
	                {
	                case MotionEvent.ACTION_DOWN :
	                	btnProfile.setImageResource(R.drawable.icon_clicked);
	                    break;
	                case MotionEvent.ACTION_UP :
	                	btnProfile.setImageResource(R.drawable.icon_statistic_profile);
	                	
	    				Intent i = new Intent(context, ProfileActivity.class);
	    				startActivity(i);
	                    break;
	                }	             
				return false;
			}
        });
        
        btnDonations.setOnTouchListener(new OnTouchListener(){            
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				 switch(arg1.getAction())
	                {
	                case MotionEvent.ACTION_DOWN :
	                	btnDonations.setImageResource(R.drawable.icon_clicked);
	                    break;
	                case MotionEvent.ACTION_UP :
	                	btnDonations.setImageResource(R.drawable.icon_statistic_main);
	                	
	    				Intent i = new Intent(context, DonationsActivity.class);
	    				startActivity(i);
	                    break;
	                }	             
				return false;
			}
        });
        
        btnInstitutions.setOnTouchListener(new OnTouchListener(){            
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				 switch(arg1.getAction())
	                {
	                case MotionEvent.ACTION_DOWN :
	                	btnInstitutions.setImageResource(R.drawable.icon_clicked);
	                    break;
	                case MotionEvent.ACTION_UP :
	                	btnInstitutions.setImageResource(R.drawable.icon_institution_main);
	                	
	    				Intent i = new Intent(context, InstitutionActivity.class);
	    				startActivity(i);
	                    break;
	                }	             
				return false;
			}
        });
        
        btnSettings.setOnTouchListener(new OnTouchListener(){            
     			@Override
     			public boolean onTouch(View arg0, MotionEvent arg1) {
     				 switch(arg1.getAction())
     	                {
     	                case MotionEvent.ACTION_DOWN :
     	                	btnSettings.setImageResource(R.drawable.icon_clicked);
     	                    break;
     	                case MotionEvent.ACTION_UP :
     	                	btnSettings.setImageResource(R.drawable.icon_statistic_settings);
     	                	
     	    				Intent i = new Intent(context, SettingsActivity.class);
     	    				startActivity(i);
     	                    break;
     	                }	             
     				return false;
     			}
             });
        
    }
    
    @Override
    public void onResume() {
        super.onResume(); 
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String apiKey = settings.getString("apiKey", "");
        
        if(apiKey.isEmpty()){
        	Intent i = new Intent(context, LoginActivity.class);  
			startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
