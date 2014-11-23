package hr.mastermind.donor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		final Context context = this;
		
		Button btnOdjava = (Button)findViewById(R.id.settings_btnLogoff);
		btnOdjava.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE).edit();
				editor.clear();
				editor.commit();
				
			        	Intent i = new Intent(context, LoginActivity.class);  
						startActivity(i);
			       
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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
