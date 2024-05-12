package com.example.fake_product;

 
 
import java.io.DataInputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

 
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class manufacturer_add_product extends Activity {
	 Button regbtn ;
	 ProgressDialog pDialog;
	 EditText product_name,description,quantity,price;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		setContentView(R.layout.manufacturer_add_product);
		
		regbtn = (Button) findViewById(R.id.reg);
		
		
		product_name = (EditText)findViewById(R.id.cname);
		description = (EditText)findViewById(R.id.mname);
		quantity = (EditText)findViewById(R.id.cdesc);
		price = (EditText)findViewById(R.id.con);
	 
	     
		regbtn.setOnClickListener(new OnClickListener() 
	        {

				@Override
				public void onClick(View arg0) {
					String product_name1=product_name.getText().toString();
					String description1=description.getText().toString();
					String quantity1=quantity.getText().toString();
					String price1=price.getText().toString();
				 
					 
					if((product_name1.isEmpty())||(description1.isEmpty()))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(quantity1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(price1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					 
				 
					else
					{
						 new reg().execute();	
					}
					
				}
	        });
	}
	 public class reg extends AsyncTask<String, String, String> {

		 String product_name1=product_name.getText().toString();
			String description1=description.getText().toString();
			String quantity1=quantity.getText().toString();
			String price1=price.getText().toString();
	        @Override
	        protected void onPreExecute() {
	        	// Toast.makeText(getApplicationContext(), "process", Toast.LENGTH_LONG).show();
	            super.onPreExecute();
	            pDialog = new ProgressDialog(manufacturer_add_product.this);
	            pDialog.setMessage("Requesting ...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

	       

	        protected String doInBackground(String... args) {

	            String txt = "";
	            
	           

	            try {
	 
	                String ur = "http://"+MainActivity.sip+"/manufacturer_add_product.php?"
+ "product_name=" +URLEncoder.encode( product_name1 , "UTF-8")  
+ "&description=" +URLEncoder.encode( description1 , "UTF-8") 
+ "&quantity=" +URLEncoder.encode( quantity1 , "UTF-8")
+ "&price=" +URLEncoder.encode( price1 , "UTF-8")
+ "&manufacturer=" +URLEncoder.encode( manufacturer_login.uemail , "UTF-8")
;
	                Log.i("URL", ur);
	                URL url = new URL(ur);
	                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
	                DataInputStream dis = new DataInputStream(uc.getInputStream());
	                String t = "";

	                while ((t = dis.readLine()) != null) {
	                    txt += t;
	                }
	                //Log.i("Read", txt);
	               // m=txt;
	                dis.close();
	                               
	            } catch (Exception e) {
	                Log.i("Login Ex", e.getMessage());
	            }
	            return txt;
	        }
	        protected void onPostExecute(String file_url) {
	        	
	      	  Log.i("file_url", file_url);
	      	  String tmp=file_url;
	      	  
	          if (file_url.trim().equals("success")) {

	             
	          	Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
	          	
	              finish();
	          
	          }
	          	//  muser =uid.getText().toString();
	          

	           else if(file_url.trim().equals("failed")) {
	              Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
	          }
	          else
	          { Toast.makeText(getApplicationContext(), file_url, Toast.LENGTH_LONG).show();
	          
	         // Toast.makeText(getApplicationContext(), "Please Check Login...!", Toast.LENGTH_LONG).show();
	          
	          }

	          pDialog.dismiss();
	      }
	  
	}
	    
}
