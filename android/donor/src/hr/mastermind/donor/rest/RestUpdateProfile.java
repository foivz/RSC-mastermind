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

public class RestUpdateProfile extends AsyncTask<User, Integer, String> {
	
	public String update(User user)
	{			
		this.execute(user);
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

	private String parsirajJson(String jsonRezultat) {		

		String error = "";
		JSONObject rezultat;
		try {
			 rezultat = new JSONObject(jsonRezultat);		
				error = rezultat.getString("error");				
			}
		
		catch (JSONException e) {
			e.printStackTrace();
			return "Error";
		}
		if(error.equalsIgnoreCase("true"))
			try {
				return rezultat.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Error";
			}
		else return "Uspješno ažuriranje korisnièkog profila!";
		
	}

	protected String doInBackground(User... user) {
		HttpClient httpKlijent = new DefaultHttpClient();	 
		HttpPost httpPostZahtjev = new HttpPost("http://mstrmnd.tk/rest/v1/update_mobile");
		String jsonResult = "";
		ResponseHandler<String> handler = new BasicResponseHandler();		
	
		try {
			/*definiranje POST parametara*/
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	
		    nameValuePairs.add(new BasicNameValuePair("email", user[0].getEmail()));
		    nameValuePairs.add(new BasicNameValuePair("password", user[0].getPassword()));
		    nameValuePairs.add(new BasicNameValuePair("firstname", user[0].getFirstName()));
		    nameValuePairs.add(new BasicNameValuePair("lastname", user[0].getLastName()));
		    nameValuePairs.add(new BasicNameValuePair("role", "mobile"));
		    nameValuePairs.add(new BasicNameValuePair("phone", user[0].getPhone()));
		    nameValuePairs.add(new BasicNameValuePair("blood_type", user[0].getBloodType()));
		    nameValuePairs.add(new BasicNameValuePair("birthyear", user[0].getYear()));
		    nameValuePairs.add(new BasicNameValuePair("gender", user[0].getSex()));
		    nameValuePairs.add(new BasicNameValuePair("weight", user[0].getWeight()));
		    
		    String pomocniBoolS = "";
		    
		    if(user[0].isTattoo()) pomocniBoolS = "true";
		    else pomocniBoolS = "false";
		    
		    nameValuePairs.add(new BasicNameValuePair("tattoo", pomocniBoolS));
		    
		    if(user[0].isTattoo()) pomocniBoolS = "true";
		    else pomocniBoolS = "false";
		    
		    nameValuePairs.add(new BasicNameValuePair("sickness", pomocniBoolS));
		    
		    if(user[0].isTattoo()) pomocniBoolS = "true";
		    else pomocniBoolS = "false";
		    
		    nameValuePairs.add(new BasicNameValuePair("piercing", pomocniBoolS));
		    
		    
		    httpPostZahtjev.setEntity(new UrlEncodedFormEntity(nameValuePairs));		  
		    
		    /*slanje uz pomoæ POST zahtjeva*/
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
