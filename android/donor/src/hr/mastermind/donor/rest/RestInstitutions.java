package hr.mastermind.donor.rest;

import hr.mastermind.donor.type.Institution;
import hr.mastermind.donor.type.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class RestInstitutions extends AsyncTask<String, List<Institution>, String>{

	
	public List<Institution> getUser()
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

	
	private List<Institution> parsirajJson(String jsonRezultat) {		
		List<Institution> institucije = new ArrayList<Institution>();
		Institution institucija;
		//
		try {			
			JSONArray usersJson = new JSONArray(jsonRezultat);
			int n = usersJson.length();
			for(int i=0; i<n; i++)
			{
				JSONObject rezultat =  usersJson.getJSONObject(i);
				if(rezultat.getString("error").equalsIgnoreCase("false")){
					
					//institucija =new institucija(rezultat.getInt(""));
					//institucije.add(institucija);
				}
			}
				
			
		}			
		catch (JSONException e) {	
		/*
		 * izvršava se ukoliko prijava nije uspješna, odnosno ne postoji
		 *  korisnik sa traženim korisnièkim imenom i lozinkom
		 *  */
			institucija = null;
			e.printStackTrace();				
		}
		
		return institucije;
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