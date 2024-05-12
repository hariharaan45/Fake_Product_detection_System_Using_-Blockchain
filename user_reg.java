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

public class user_reg extends Activity {
	 Button regbtn ;
	 ProgressDialog pDialog;
	 EditText name,contact,email,pass1,pass2,address,area;
	 public static String nam="",fpass="",spass="",cont="",eml="",ran="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		 
		setContentView(R.layout.user_register1);
		regbtn = (Button) findViewById(R.id.reg);
		name = (EditText)findViewById(R.id.name);
	    contact = (EditText)findViewById(R.id.con);
	    email = (EditText)findViewById(R.id.email);
	    pass1 = (EditText)findViewById(R.id.pass);
	    pass2 = (EditText)findViewById(R.id.cpass);
	    address = (EditText)findViewById(R.id.addr);
	    area = (EditText)findViewById(R.id.area);
	     
		regbtn.setOnClickListener(new OnClickListener() 
	        {

				@Override
				public void onClick(View arg0) {
					nam=name.getText().toString();
					fpass=pass1.getText().toString();
					spass=pass2.getText().toString();
					cont=contact.getText().toString();
					eml=email.getText().toString();
					String ar=area.getText().toString();
					if((nam.isEmpty())||(ar.isEmpty()))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(fpass.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(spass.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(cont.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					else if(eml.isEmpty())
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					
					else if(fpass.equals(spass))
					{
						// Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_LONG).show();
		                 Log.i("Pass", "same");
						 new reg().execute();	
					}
					else
					{
						Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();	
					 
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
	  		 
       
	//  String userid=u.getText().toString();
	//  String password=p.getText().toString();
		String rnam=name.getText().toString();
		String	rfpass=pass1.getText().toString();
		String	rspass=pass2.getText().toString();
		String	rcont=contact.getText().toString();
		String	reml=email.getText().toString();
		String	address1=address.getText().toString();
		String	area1=area.getText().toString();
	        @Override
	        protected void onPreExecute() {
	        	// Toast.makeText(getApplicationContext(), "process", Toast.LENGTH_LONG).show();
	            super.onPreExecute();
	            pDialog = new ProgressDialog(user_reg.this);
	            pDialog.setMessage("Requesting " + rnam + ")...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }

	       

	        protected String doInBackground(String... args) {

	            String txt = "";
	            
	           

	            try {
	            	 String tm="";
	                 Random r1 = new Random();
	                Random r2 = new Random();
	                for (int i=1; i<2; i++) {
	                    BigInteger prime = BigInteger.probablePrime(r1.nextInt(2)+35, r2);
	                    //System.out.println(prime);
	                      tm=""+prime;
	                }
ran=tm;
	                String ur = "http://"+MainActivity.sip+"/user_register.php?"
+ "name=" +URLEncoder.encode( rnam , "UTF-8")  
+ "&pass=" +URLEncoder.encode( rfpass , "UTF-8") 
+ "&cont=" +URLEncoder.encode( rcont , "UTF-8")
+ "&email=" +URLEncoder.encode( reml , "UTF-8")
+"&address="+URLEncoder.encode( address1 , "UTF-8") 
+"&area="+URLEncoder.encode( area1 , "UTF-8") 
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
