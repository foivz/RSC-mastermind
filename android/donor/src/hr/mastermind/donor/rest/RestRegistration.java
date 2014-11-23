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



public class RestRegistration extends AsyncTask<User, Integer, String> {


	/**
	 * Metoda koja služi za registraciju usera.
	 * @param popunjen objekt tipa user sa podacima za prijavu usera
	 * @return identifikacijski broj poruke o uspješnosti registracije
	 */
	public int register(User user)
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

	/**
	 * Parsira json string dohvaæen s web servisa
	 * @param jsonRezultat web servisa
	 * @return identifikacijski broj poruke o uspješnosti registracije
	 */
	private int parsirajJson(String jsonRezultat) {		
		int povratnaInformacijaId = 7;
		String error = "";
		try {
			JSONObject rezultat = new JSONObject(jsonRezultat);			
						
				error = rezultat.getString("error");				
			}
				
		catch (JSONException e) {
			e.printStackTrace();
			return 7;
		}
		if(error.equalsIgnoreCase("true"))povratnaInformacijaId = 2;
		else povratnaInformacijaId = 0;
		
		return povratnaInformacijaId;
	}

	/**
	 * Metoda za asinkronu komunikaciju izmeðu aplikacije i web servisa
	 * @param popunjen objekt tipa user sa podacima za prijavu usera
	 * @return rezultat web servisa u json formatu
	 */
	protected String doInBackground(User... user) {
		HttpClient httpKlijent = new DefaultHttpClient();	 
		HttpPost httpPostZahtjev = new HttpPost("http://mstrmnd.tk/rest/v1/register_mobile");
		String jsonResult = "";
		ResponseHandler<String> handler = new BasicResponseHandler();		
		
		/** primjer resta parametara
		 * 
		 * 
		 * $email = $app->request->post('email');
            $password = $app->request->post('password');
            $firstname = $app->request->post('firstname');
            $lastname = $app->request->post('lastname');
          
            $phone = $app->request->post('phone');
            $blood_type = $app->request->post('blood_type'); 
            $piercing = $app->request->post('piercing');
            $tattoo = $app->request->post('tattoo');  
            $sickness = $app->request->post('sickness');
            $birthyear = $app->request->post('birthyear');
            $gender = $app->request->post('gender');
            $weight = $app->request->post('weight');
            [email=12121, password=12, firstname=12, lastname=12, role=mobile, phone=12, blood_type=0+, birthyear=1991, gender=M, weight=12, tattoo=false, sickness=false, piercing=false]
		 */
		
		
		
		try {
			/*definiranje POST parametara*/
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    nameValuePairs.add(new BasicNameValuePair("email", user[0].getEmail()));
		    nameValuePairs.add(new BasicNameValuePair("password", user[0].getPassword()));
		    nameValuePairs.add(new BasicNameValuePair("firstname", user[0].getFirstName()));
		    nameValuePairs.add(new BasicNameValuePair("lastname", user[0].getLastName()));
		    
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
