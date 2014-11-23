package hr.mastermind.donor.rest;

import hr.mastermind.donor.MainActivity;
import hr.mastermind.donor.type.Donation;
import hr.mastermind.donor.type.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.AsyncTask;

public class RestDonations  extends AsyncTask<String, Donation, String>{

	/**
	 * Metoda koja izvršava provjeru korisnika uz pomoæ servisa
	 * @param korisnicko ime i odgovarajuæa lozinka
	 * @return popunjeni objekt tipa Korisnik(korisnickoIme, "", ime, prezime, email, telefon) 
	 * ukoliko je prijava uspjesna  ili null ukoliko prijava nije uspješna
	 */
	public Donation getDonations(String apiKey)
	{			
		this.execute(apiKey);
		String jsonRezultat = "";
		try {
			jsonRezultat = this.get();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		
		return  parsirajJson(jsonRezultat);			
	}

	/**
	 * Parsira json string dohvaæen s web servisa
	 * @param jsonRezultat
	 * @return popunjeni objekt tipa Korisnik(korisnickoIme, "", ime, prezime, email, telefon) 
	 * ukoliko je prijava uspjesna ili null ukoliko prijava nije uspjesna
	 */
	private Donation parsirajJson(String jsonRezultat) {		
		Donation donacija = null;
		//{"error":false,"active":"1","email":"test3@gmail.com","firstname":"testime","lastname":"testprezime",
		//"phone":"02233444","score":"0","api_key":"ab3030b30a1c211f82929cc7a056607a","birth_year":"1991","gender":"M",
		//"weight":"12","blood_type":"0+","piercing":"0","tattoo":"0","sickness":"0"}
		try {			
			
				JSONObject rezultat = new JSONObject(jsonRezultat);
				if(rezultat.getString("error").equalsIgnoreCase("false")){
					
					//donacija = new Donation(rezultat.getString("donations"), rezultat.getString(), rezultat.getString(), rezultat.getString());
					
				}
				else{
					donacija = null;
				}
			
		}			
		catch (JSONException e) {	
		/*
		 * izvršava se ukoliko prijava nije uspješna, odnosno ne postoji
		 *  korisnik sa traženim korisnièkim imenom i lozinkom
		 *  */
			donacija = null;
			e.printStackTrace();				
		}
		
		return donacija;
	}

	/**
	 * Metoda za asinkronu komunikaciju izmeðu aplikacije i servisa.
	 * @param email i lozinka u obliku ArrayList
	 * @return odgovor servisa u json obliku
	 * */
	protected String doInBackground(String... podaciPrijava) {
		HttpClient httpKlijent = new DefaultHttpClient();
	 
		HttpPost httpPostZahtjev = new HttpPost("http://www.mstrmnd.tk/rest/v1/login_mobile");
		String jsonResult = "";
		ResponseHandler<String> handler = new BasicResponseHandler();
		
		
		try {
			
			SharedPreferences settings = MainActivity.contxtMain.getSharedPreferences(MainActivity.PREFS_NAME, 0);
	        String apiKey = settings.getString("apiKey", "");
	        
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    nameValuePairs.add(new BasicNameValuePair("api_key", apiKey));
		
		    httpPostZahtjev.setEntity(new UrlEncodedFormEntity(nameValuePairs));			  
			jsonResult = httpKlijent.execute(httpPostZahtjev, handler);
		}
		catch(ClientProtocolException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		httpKlijent.getConnectionManager().shutdown();
		return jsonResult;
	}

}
