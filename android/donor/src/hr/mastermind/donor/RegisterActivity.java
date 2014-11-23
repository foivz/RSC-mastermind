package hr.mastermind.donor;



import hr.mastermind.donor.rest.RestRegistration;
import hr.mastermind.donor.type.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterActivity extends Activity {
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		
		//spinner za spol
		Spinner spinnerSex = (Spinner) findViewById(R.id.register_spnSex);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.sex_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerSex.setAdapter(adapter);
		
		
		//spinner za krvnu grupu
		Spinner spinnerBloodType = (Spinner) findViewById(R.id.register_spnBloodType);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterBloodType = ArrayAdapter.createFromResource(this,
		        R.array.blood_type_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapterBloodType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerBloodType.setAdapter(adapterBloodType);
		

		context = this;
		//dohvati tekstualna polja
		Button btnRegister = (Button)findViewById(R.id.register_btnUpdate);
		
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				register();
				
			}
		});
		
		
	}
	public void register(){

		EditText txtEmail = (EditText) findViewById(R.id.register_txtEmail);
		EditText txtPassword = (EditText) findViewById(R.id.register_txtPassword);
		EditText txtFirstName = (EditText) findViewById(R.id.register_txtFirstName);
		EditText txtLastName = (EditText) findViewById(R.id.register_txtLastName);
		EditText txtPhone = (EditText) findViewById(R.id.register_txtPhone);
		EditText txtCity = (EditText) findViewById(R.id.register_txtCity);
		Spinner spnSex = (Spinner) findViewById(R.id.register_spnSex);
		Spinner spnBloodType = (Spinner) findViewById(R.id.register_spnBloodType);
		EditText txtYear = (EditText) findViewById(R.id.register_txtYear);
		EditText txtWeight = (EditText) findViewById(R.id.register_intWeight);
		
		
		String tEmail= txtEmail.getText().toString();
		String tLozinka = txtPassword.getText().toString();
		String tLozinka2 = txtPassword.getText().toString();
		String tFirstName = txtFirstName.getText().toString();
		String tLastName = txtLastName.getText().toString();
		String tPhone = txtPhone.getText().toString();
		String tCity = txtCity.getText().toString();
		String tSex = (String) spnSex.getSelectedItem();
		String tYear = txtYear.getText().toString();
		String tBloodType = (String)spnBloodType.getSelectedItem();
		String tWeight = txtWeight.getText().toString();
		
		
		
		User user = new User(tEmail, tLozinka, tFirstName, tLastName, tPhone, tCity, tYear, tSex, tBloodType, tWeight);
		user.setRole(1);
		
		
		int feedback = validateParameters(user, tLozinka2);
		
		if(feedback == 0){
			//preusmjeri na pocetnu
			Intent i = new Intent(context, LoginActivity.class);     
	        startActivity(i);
	        
			Toast.makeText(context, "Uspješno ste registrirani!", Toast.LENGTH_SHORT).show();
		}
		else
		{
	
			Toast.makeText(context, "Neuspješna registracija!", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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

	
	
    public int validateParameters(User korisnik, String ponovljenaLozinka) {
		
		/*validacija korisnièkog unosa*/
		if (korisnik.getEmail().length() == 0 || korisnik.getEmail() == null) 
			return 8;
		if (ponovljenaLozinka.length() == 0 || ponovljenaLozinka == null)
			return 11;
		if(korisnik.getPassword().length() == 0 || korisnik.getPassword() == null)
			return 11;
		if (korisnik.getFirstName().length() == 0 || korisnik.getFirstName() == null)
			return 9;
		if (korisnik.getLastName().length() == 0 || korisnik.getLastName() == null)
			return 10;	
		if (korisnik.getBloodType().length() == 0 || korisnik.getBloodType() == null)
			return 7;
		if (korisnik.getCity().length() == 0 || korisnik.getCity() == null)
			return 7;
		if (korisnik.getPhone().length() == 0 || korisnik.getPhone() == null)
			return 7;
		if (korisnik.getSex().length() == 0 || korisnik.getSex() == null)
			return 7;
		if (korisnik.getWeight().length() == 0 || korisnik.getWeight() == null)
			return 7;		
		if (korisnik.getYear().length() == 0 || korisnik.getYear() == null)
			return 7;
		
		
		if(!ponovljenaLozinka.equals(korisnik.getPassword()))
			return 1;
		
		RestRegistration restReg = new RestRegistration();
		/*provjera na korisnièkoj strani je uspješno izvršena, izvršavamo registraciju putem post metode*/
		int uspjesnaRegistracija = restReg.register(korisnik);
		
		//ako je uspješna registracija vraæa se 0
		return uspjesnaRegistracija;
	}
	
	
}

