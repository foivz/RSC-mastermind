package hr.mastermind.donor.rest;

import hr.mastermind.donor.MainActivity;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.AsyncTask;

public class RestTopTen  extends AsyncTask<String, List<User>, String>{

	public List<User> getUser()
	{			
		this.execute();
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


	private List<User> parsirajJson(String jsonRezultat) {		
		List<User> korisnici = new ArrayList<User>();
		User korisnik;
		//
		try {			
			JSONArray usersJson = new JSONArray(jsonRezultat);
			int n = usersJson.length();
			for(int i=0; i<n; i++)
			{
				JSONObject rezultat =  usersJson.getJSONObject(i);
				if(rezultat.getString("error").equalsIgnoreCase("false")){
						korisnik =new User(rezultat.getString("email"), "", rezultat.getString("firstname"), rezultat.getString("lastname"), rezultat.getString("phone"), rezultat.getString("city"),
								rezultat.getString("birth_year"), rezultat.getString("gender"), rezultat.getString("city"), rezultat.getString("weight"),rezultat.getString("piercing"), rezultat.getString("tattoo"),rezultat.getString("sickness"), rezultat.getInt("score"));
						korisnici.add(korisnik);
				}
			}
				
			
		}			
		catch (JSONException e) {	
		/*
		 * izvršava se ukoliko prijava nije uspješna, odnosno ne postoji
		 *  korisnik sa traženim korisnièkim imenom i lozinkom
		 *  */
			korisnik = null;
			e.printStackTrace();				
		}
		
		return korisnici;
	}

	protected String doInBackground(String... podaciPrijava) {
		HttpClient httpKlijent = new DefaultHttpClient();
	 
		HttpGet httpPostZahtjev = new HttpGet("http://www.mstrmnd.tk/rest/v1/top_ten");
		String jsonResult = "";
		ResponseHandler<String> handler = new BasicResponseHandler();
		
		
		try {
					  
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
