package hr.mastermind.donor;

import hr.mastermind.donor.rest.RestLogin;
import hr.mastermind.donor.rest.RestReset;
import hr.mastermind.donor.type.User;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		context = this;
		
		//dohvati tekstuala polja
		Button btnRegister = (Button)findViewById(R.id.login_btnRegister);
		
		btnRegister.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, RegisterActivity.class);
				startActivity(i);				
			}
		});
		
		Button btnLogin = (Button) findViewById(R.id.login_btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				login();				
			}
		});
		
		TextView t = (TextView)findViewById(R.id.login_lblResetPassword);
	    t.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//pozovi metodu za reset
				reset();
			}
		});
	}

	/**
	 * Metoda
	 * @return
	 */
	private void login(){
		
		EditText txtEmail = (EditText) findViewById(R.id.login_txtEmail);
		EditText txLozinka = (EditText)findViewById(R.id.login_txtPassword);
		 
		String tEmail= txtEmail.getText().toString();
		String tLozinka = txLozinka.getText().toString();
		
		
		if(tEmail == null || tEmail.length() ==0)
			Toast.makeText(context, "Unesite email ", Toast.LENGTH_SHORT).show();
		
		else if(tLozinka == null || tLozinka.length() ==0)
			Toast.makeText(context, "Unesite lozinku", Toast.LENGTH_SHORT).show();
		
		else{			
			RestLogin newLogin = new RestLogin();
			
			User prijava = newLogin.login(tEmail, tLozinka);
			
			
			if(!prijava.getEmail().isEmpty())
			{
				
				//spremi u shared preferences key
				 SharedPreferences.Editor editor = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE).edit();
				 editor.putString("apiKey", prijava.getApiKey());
				 editor.putString("email", prijava.getEmail());
				 editor.putString("firstName", prijava.getFirstName());
				 editor.putString("lastName", prijava.getLastName());
				 editor.putInt("role", prijava.getRole());
				 editor.putString("phone", prijava.getPhone());
				 editor.putString("gender", prijava.getSex());
				 editor.putString("year", prijava.getYear());
				 editor.putString("weight", prijava.getWeight());
				 editor.putString("city", prijava.getCity());
			
				 editor.commit();
				
				
				//preusmjeri na pocetnu
				Intent i = new Intent(context, MainActivity.class);     
		        startActivity(i);
		        
				Toast.makeText(context, "Uspješno ste prijavljeni!", Toast.LENGTH_SHORT).show();
			
			}
			else
			{
			
				Toast.makeText(context, prijava.getApiKey(), Toast.LENGTH_SHORT).show();
			}
		}	
	}
	
	/**
	 * Metoda
	 * @return
	 */
	private void reset(){
		
		EditText txtEmail = (EditText) findViewById(R.id.login_txtEmail);	
		 
		String tEmail= txtEmail.getText().toString();	
		
		if(tEmail == null || tEmail.length() ==0){
			Toast.makeText(context, "Unesite email ", Toast.LENGTH_SHORT).show();
	
		}
		else{			
			RestReset newReset = new RestReset();
			
			int reset = newReset.reset(tEmail);
			
		    if(reset == 1){   
				Toast.makeText(context, "Uspješno ste resetirali ", Toast.LENGTH_SHORT).show();			
			}
			else
			{			
				Toast.makeText(context, "Uspješno ste resetirali lozinku! Molimo Vas pogledajte email!", Toast.LENGTH_SHORT).show();
			}
		}	
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
