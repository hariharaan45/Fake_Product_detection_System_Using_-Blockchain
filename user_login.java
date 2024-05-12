package com.example.fake_product;
 

import java.io.DataInputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
 

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class user_login extends Activity {
	 ProgressDialog pDialog;

	 Button loginbtn;
	 EditText useremail;
	 EditText password;	
	 TextView register;
	 public static String uemail="";
	 public static String area="";

	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login1); 
		loginbtn = (Button) findViewById(R.id.button1);
	    register = (TextView)findViewById(R.id.TextView01);

		useremail = (EditText)findViewById(R.id.u_name);
		password = (EditText)findViewById(R.id.pass);
		//useremail.setText("arun@gmail.com");
		//password.setText("123");
		loginbtn.setOnClickListener(new OnClickListener() 
	        {

				@Override
				public void onClick(View arg0) {
				  new userlogin().execute();	
				 				
				}
	        });
		
		 register.setOnClickListener(new OnClickListener() 
	        {

				@Override
				public void onClick(View arg0) {
				 	 
				  Intent i = new Intent(user_login.this,user_reg.class);
			    	 

				   startActivity(i);
			 
					
				}
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public class userlogin extends AsyncTask<String, String, String> {

	    
		 String lname=useremail.getText().toString();
		String	lpass=password.getText().toString();

		        @Override
		        protected void onPreExecute() {
		            super.onPreExecute();
		            pDialog = new ProgressDialog(user_login.this);
		            pDialog.setMessage("Requesting " + lname + ")...");
		            pDialog.setIndeterminate(false);
		            pDialog.setCancelable(true);
		            pDialog.show();
		        }

		        

		     
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        protected String doInBackground(String... args) {

		            String txt = "";
		            


		            try {
		           	 
		            	
		                String ur = "http://"+MainActivity.sip+"/user_login.php?"+ "uname=" + lname + "&pword=" +lpass;
		 
		               
		               
		                URL url = new URL(ur);
		                Log.i("URL", ""+url);
		                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
		                DataInputStream dis = new DataInputStream(uc.getInputStream());
		                String t = "";

		                while ((t = dis.readLine()) != null) {
		                    txt += t;
		                }
		                Log.i("Read", txt);
		              //  m=txt;
		                dis.close();
		                               
		            } catch (Exception e) {
		                Log.i("Login Ex", e.getMessage());
		            }
		            return txt;
		        }
		        protected void onPostExecute(String file_url) {
		      	  Log.i("file_url", file_url);
		          if (file_url.trim().equals("success")) {

		        	  uemail=lname;

		               	Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
			            //  Intent in = new Intent(getApplicationContext(), bus_id.class);
		              //   new userlogin1().execute();	
		               // Toast.makeText(getApplicationContext(),area, Toast.LENGTH_LONG).show();
		              Intent in = new Intent(getApplicationContext(), user_home.class);   
			                startActivity(in);
		                	finish();
			            
		          
		          }
		           else if(file_url.trim().equals("failed")) {
		              Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_LONG).show();
		          }
		          else
		          { Toast.makeText(getApplicationContext(), "Please Check Login...!", Toast.LENGTH_LONG).show();}

		          pDialog.dismiss();
		      }
		  
		}
		    
		   
		  
//////////////////////////////////////////////////////////////////////////
	public class userlogin1 extends AsyncTask<String, String, String> {

	    
		 String lname=useremail.getText().toString();
		String	lpass=password.getText().toString();

		        @Override
		        protected void onPreExecute() {
		            super.onPreExecute();
		           
		        }

		        

		        protected String doInBackground(String... args) {

		            String txt = "";
		            


		            try {
		           	 
		            	
		                String ur = "http://"+MainActivity.sip+"/user_login1.php?"+ "uname=" + lname + "&pword=" +lpass;
		 
		               
		               
		                URL url = new URL(ur);
		                Log.i("URL", ""+url);
		                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
		                DataInputStream dis = new DataInputStream(uc.getInputStream());
		                String t = "";

		                while ((t = dis.readLine()) != null) {
		                    txt += t;
		                }
		                Log.i("Read", txt);
		              //  m=txt;
		                dis.close();
		                               
		            } catch (Exception e) {
		                Log.i("Login Ex", e.getMessage());
		            }
		            return txt;
		        }
		        protected void onPostExecute(String file_url) {
		      	  Log.i("file_url", file_url);
		       
		      	uemail=file_url.trim();
		         
		               	finish();
		               	//Intent in = new Intent(getApplicationContext(), user_home.class);   
		               // startActivity(in);
		      }
		  
		}
}
