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

public class manufacturer_register extends Activity {
	 Button regbtn ;
	 ProgressDialog pDialog;
	 EditText company_name,manufacturer_name,company_description,contact,email,area,address,password,cpassword,iosid;
	 //public static String nam="",fpass="",spass="",cont="",eml="",ran="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		setContentView(R.layout.activity_manufacturer_register);
		
		regbtn = (Button) findViewById(R.id.reg);
		
		
		company_name = (EditText)findViewById(R.id.cname);
		manufacturer_name = (EditText)findViewById(R.id.mname);
		company_description = (EditText)findViewById(R.id.cdesc);
		contact = (EditText)findViewById(R.id.con);
		email = (EditText)findViewById(R.id.email);
		area = (EditText)findViewById(R.id.area);
		address = (EditText)findViewById(R.id.addr);
		password = (EditText)findViewById(R.id.pass);
		cpassword = (EditText)findViewById(R.id.cpass);
		iosid = (EditText)findViewById(R.id.isoid);
	     
		regbtn.setOnClickListener(new OnClickListener() 
	        {

				@Override
				public void onClick(View arg0) {
					String company_name1=company_name.getText().toString();
					String manufacturer_name1=manufacturer_name.getText().toString();
					String company_description1=company_description.getText().toString();
					String contact1=contact.getText().toString();
					String email1=email.getText().toString();
					String area1=area.getText().toString();
					String address1=address.getText().toString();
					String password1=password.getText().toString();
					String cpassword1=cpassword.getText().toString();
					String iosid1=iosid.getText().toString();
					 
					if((company_name1.isEmpty())||(manufacturer_name1.isEmpty()))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(company_description1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(contact1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(email1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(area1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(address1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(iosid1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					
					else if(password1.equals(cpassword1))
					{
						// Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_LONG).show();
		                 Log.i("Pass", "same");
						 new reg().execute();	
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Password Not Matched", Toast.LENGTH_LONG).show();	
					 
					}
					
				}
	        });
	}
	 public class reg extends AsyncTask<String, String, String> {

		 String company_name1=company_name.getText().toString();
			String manufacturer_name1=manufacturer_name.getText().toString();
			String company_description1=company_description.getText().toString();
			String contact1=contact.getText().toString();
			String email1=email.getText().toString();
			String area1=area.getText().toString();
			String address1=address.getText().toString();
			String password1=password.getText().toString();
			String cpassword1=cpassword.getText().toString();
			String iosid1=iosid.getText().toString();
	        @Override
	        protected void onPreExecute() {
	        	// Toast.makeText(getApplicationContext(), "process", Toast.LENGTH_LONG).show();
	            super.onPreExecute();
	            pDialog = new ProgressDialog(manufacturer_register.this);
	            pDialog.setMessage("Requesting ...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

	       

	        protected String doInBackground(String... args) {

	            String txt = "";
	            
	           

	            try {
	 
	                String ur = "http://"+MainActivity.sip+"/manufacturer_register.php?"
+ "company_name=" +URLEncoder.encode( company_name1 , "UTF-8")  
+ "&manufacturer_name=" +URLEncoder.encode( manufacturer_name1 , "UTF-8") 
+ "&company_description=" +URLEncoder.encode( company_description1 , "UTF-8")
+ "&contact=" +URLEncoder.encode( contact1 , "UTF-8")
+"&email="+URLEncoder.encode( email1 , "UTF-8") 
+"&area="+URLEncoder.encode( area1 , "UTF-8") 
+"&address="+URLEncoder.encode( address1 , "UTF-8") 
+"&password="+URLEncoder.encode( password1 , "UTF-8") 
+"&iosid="+URLEncoder.encode( iosid1 , "UTF-8") 
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
