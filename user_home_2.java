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
import android.widget.TextView;
import android.widget.Toast;

public class user_home_2 extends Activity {
	 
	 Button regbtn ;
	 ProgressDialog pDialog;
	 EditText card,holder,cvv,exdate;
	 TextView amount;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_home_2);
		amount = (TextView) findViewById(R.id.textView2);
		int a=Integer.parseInt(user_home_1.price1);
		int b=Integer.parseInt(user_home_1.quantity);
		int c=a*b;
		amount.setText(""+c);
		

		regbtn = (Button) findViewById(R.id.reg);

		card = (EditText)findViewById(R.id.cardno);
		holder = (EditText)findViewById(R.id.holder);
		cvv = (EditText)findViewById(R.id.cvv);
		exdate = (EditText)findViewById(R.id.exdate);
 
		regbtn.setOnClickListener(new OnClickListener() 
	        {

				@Override
				public void onClick(View arg0) {
					String card1=card.getText().toString();
					String holder1=holder.getText().toString();
					String cvv1=cvv.getText().toString();
					String exdate1=exdate.getText().toString();
				 
					if((card1.isEmpty())||(holder1.isEmpty()))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(cvv1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(exdate1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
				  
					
					 
					else
					{
						 new reg().execute();	
					 
					}
					
					
					//Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
					 							 
					//Intent i = new Intent(Registration.this,MainActivity.class); 	 
				   	//startActivity(i);
					 // Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_LONG).show();
				//mEdit.setText(""+m);

					
				}
	        });
	}
	 public class reg extends AsyncTask<String, String, String> {

	        //capture values from EditText
			 
			// String accno,name1,address,gen,pass,dob;
	  		 
			String card1=card.getText().toString();
			String holder1=holder.getText().toString();
			String cvv1=cvv.getText().toString();
			String exdate1=exdate.getText().toString();
	        @Override
	        protected void onPreExecute() {
	        	// Toast.makeText(getApplicationContext(), "process", Toast.LENGTH_LONG).show();
	            super.onPreExecute();
	            pDialog = new ProgressDialog(user_home_2.this);
	            pDialog.setMessage("Requesting...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

	       

	        protected String doInBackground(String... args) {

	            String txt = "";
	            
	           

	            try {
	            	 
	                String ur = "http://"+MainActivity.sip+"/customer_payment.php?"
+ "name=" +URLEncoder.encode( user_login.uemail , "UTF-8")  
+ "&pid=" +URLEncoder.encode( user_home.id , "UTF-8") 
+ "&quantity=" +URLEncoder.encode( user_home_1.quantity , "UTF-8")
 
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
	          { 
	        	  Toast.makeText(getApplicationContext(), file_url, Toast.LENGTH_LONG).show();
	          
	         // Toast.makeText(getApplicationContext(), "Please Check Login...!", Toast.LENGTH_LONG).show();
	          
	          }

	          pDialog.dismiss();
	      }
	  
	}
	    
}
