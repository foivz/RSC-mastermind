package hr.mastermind.donor;

import hr.mastermind.donor.rest.RestRegistration;
import hr.mastermind.donor.rest.RestUpdateProfile;
import hr.mastermind.donor.type.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileActivity extends Activity {
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		context = this;
		
		/**
		 * Spinner za spol
		 */
		Spinner spinnerSex = (Spinner) findViewById(R.id.profile_spnSex);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.sex_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerSex.setAdapter(adapter);
		
		
		/**
		 * spinner za krvnu grupu
		 */
		Spinner spinnerBloodType = (Spinner) findViewById(R.id.profile_spnBloodType);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterBloodType = ArrayAdapter.createFromResource(this,
		        R.array.blood_type_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapterBloodType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerBloodType.setAdapter(adapterBloodType);
		

		context = this;
		//dohvati tekstualna polja
		Button btnRegister = (Button)findViewById(R.id.profile_btnUpdate);
		
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				register();
				
			}
		});
		
		
	}
	public void register(){
		/**
		 * 
		 *  private String email;
			private String password;
			private String firstName;
			private String lastName;
			private String apiKey;
			private String image;
			private String phone;
			private String city;
			private String sex;
			private String bloodType;
			private int year;
			private int role;
		 * 
		 */
		EditText txtEmail = (EditText) findViewById(R.id.profile_txtEmail);
		EditText txtPassword = (EditText) findViewById(R.id.profile_txtPassword);
		EditText txtFirstName = (EditText) findViewById(R.id.profile_txtFirstName);
		EditText txtLastName = (EditText) findViewById(R.id.profile_txtLastName);
		EditText txtPhone = (EditText) findViewById(R.id.profile_txtPhone);
		EditText txtCity = (EditText) findViewById(R.id.profile_txtCity);
		Spinner spnSex = (Spinner) findViewById(R.id.profile_spnSex);
		Spinner spnBloodType = (Spinner) findViewById(R.id.profile_spnBloodType);
		EditText txtYear = (EditText) findViewById(R.id.profile_txtYear);
		EditText txtWeight = (EditText) findViewById(R.id.profile_intWeight);
		
		
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
		
		
		String feedback = validateParameters(user, tLozinka2);
		
	
		Toast.makeText(context, feedback, Toast.LENGTH_SHORT).show();
		
		
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

	
	
    public String validateParameters(User korisnik, String ponovljenaLozinka) {
		
		/*validacija korisnièkog unosa*/
		if (korisnik.getEmail().length() == 0 || korisnik.getEmail() == null) 
			return "Unesite email!";
		if (ponovljenaLozinka.length() == 0 || ponovljenaLozinka == null)
			return "Unesite lozinku";
		if(korisnik.getPassword().length() == 0 || korisnik.getPassword() == null)
			return "Unesite ponovljenu lozinku!";
		if (korisnik.getFirstName().length() == 0 || korisnik.getFirstName() == null)
			return "Unesite ime";
		if (korisnik.getLastName().length() == 0 || korisnik.getLastName() == null)
			return "Unesite prezime";	
		if (korisnik.getBloodType().length() == 0 || korisnik.getBloodType() == null)
			return "Unesite krvnu grupu";
		if (korisnik.getCity().length() == 0 || korisnik.getCity() == null)
			return "Unesite grad";
		if (korisnik.getPhone().length() == 0 || korisnik.getPhone() == null)
			return "Unesite broj telefona";
		if (korisnik.getSex().length() == 0 || korisnik.getSex() == null)
			return "Unesite spol";
		if (korisnik.getWeight().length() == 0 || korisnik.getWeight() == null)
			return "Unesite težinu";		
		if (korisnik.getYear().length() == 0 || korisnik.getYear() == null)
			return "Unesite godinu roðenja";
		
		
		if(!ponovljenaLozinka.equals(korisnik.getPassword()))
			return "Vaše lozinke se ne podudaraju";
		
		RestUpdateProfile restUpdate = new RestUpdateProfile();
		/*provjera na korisnièkoj strani je uspješno izvršena, izvršavamo registraciju putem post metode*/
		String uspjesnaRegistracija = restUpdate.update(korisnik);
		
		//ako je uspješna registracija vraæa se 0
		return uspjesnaRegistracija;
	}
	
	
}