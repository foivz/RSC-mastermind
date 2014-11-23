package hr.mastermind.donor.rest;


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

import android.os.AsyncTask;

public class RestReset extends AsyncTask<String, String, String>{

	/**
	 * Metoda koja izvršava provjeru korisnika uz pomoæ servisa
	 * @param korisnicko ime i odgovarajuæa lozinka
	 * @return popunjeni objekt tipa Korisnik(korisnickoIme, "", ime, prezime, email, telefon) 
	 * ukoliko je prijava uspjesna  ili null ukoliko prijava nije uspješna
	 */
	public int reset(String email)
	{			
		this.execute(email);
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
	private int parsirajJson(String jsonRezultat) {		
	
		//
		try {			
			
				JSONObject rezultat = new JSONObject(jsonRezultat);
				if(rezultat.getString("error").equalsIgnoreCase("false")){
						return 1;
				}
				else{
					return  0;
				}
			
		
		}			
		catch (JSONException e) {	
		/*
		 * izvršava se ukoliko prijava nije uspješna, odnosno ne postoji
		 *  korisnik sa traženim korisnièkim imenom i lozinkom
		 *  */
			
			e.printStackTrace();				
		}
		
		return 0;
	}

	/**
	 * Metoda za asinkronu komunikaciju izmeðu aplikacije i servisa.
	 * @param email i lozinka u obliku ArrayList
	 * @return odgovor servisa u json obliku
	 * */
	protected String doInBackground(String... podaciPrijava) {
		HttpClient httpKlijent = new DefaultHttpClient();
	 
		HttpPost httpPostZahtjev = new HttpPost("http://www.mstrmnd.tk/rest/v1/reset");
		String jsonResult = "";
		ResponseHandler<String> handler = new BasicResponseHandler();
		
		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    nameValuePairs.add(new BasicNameValuePair("email", podaciPrijava[0]));

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
