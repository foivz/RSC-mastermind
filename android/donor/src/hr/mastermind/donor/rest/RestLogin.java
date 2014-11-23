package hr.mastermind.donor.rest;


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

import android.os.AsyncTask;



public class RestLogin extends AsyncTask<String, User, String>{

	
	public User login(String email, String lozinka)
	{			
		this.execute(email,lozinka );
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

	
	private User parsirajJson(String jsonRezultat) {		
		User korisnik = null;
		//{"error":false,"active":"1","email":"test3@gmail.com","firstname":"testime","lastname":"testprezime",
		//"phone":"02233444","score":"0","api_key":"ab3030b30a1c211f82929cc7a056607a","birth_year":"1991","gender":"M",
		//"weight":"12","blood_type":"0+","piercing":"0","tattoo":"0","sickness":"0"}
		try {			
			
				JSONObject rezultat = new JSONObject(jsonRezultat);
				if(rezultat.getString("error").equalsIgnoreCase("false")){
						String email = rezultat.getString("email");
						/*lozinka se ne dohvaæa putem web servisa*/
						String lozinka = "";
						String apiKey = rezultat.getString("api_key");
						String ime = rezultat.getString("firstname");
						String prezime = rezultat.getString("lastname");
						int role = 0;
						
					
						/*unošenje primljenih korisnièkih podataka u objekt*/
						korisnik = new User(email, lozinka, ime, prezime, rezultat.getString("phone"), "Zagreb", rezultat.getString("birth_year"), rezultat.getString("gender"), rezultat.getString("blood_type"), rezultat.getString("weight"));
						korisnik.setApiKey(apiKey);
						//korisnik =  new User(email, lozinka, ime, prezime,apiKey,role);
				}
				else{
					korisnik =  new User("", "", "", "",rezultat.getString("message"),0);
					
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
		
		return korisnik;
	}

	
	protected String doInBackground(String... podaciPrijava) {
		HttpClient httpKlijent = new DefaultHttpClient();
	 
		HttpPost httpPostZahtjev = new HttpPost("http://www.mstrmnd.tk/rest/v1/login_mobile");
		String jsonResult = "";
		ResponseHandler<String> handler = new BasicResponseHandler();
		
		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    nameValuePairs.add(new BasicNameValuePair("email", podaciPrijava[0]));
		    nameValuePairs.add(new BasicNameValuePair("password", podaciPrijava[1]));
		    
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
