package com.example.fake_product;
 
 
import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

 
 
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class manufacturer_login extends Activity {
	  TextView register,txt;
	    Button b1;
	    EditText e1,e2;
	    ProgressDialog pDialog;
	    public static String uemail="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        register = (TextView)findViewById(R.id.TextView01);
        txt = (TextView)findViewById(R.id.textView1);
        txt.setText("Manufacturer Login");
        //register.setVisibility(View.INVISIBLE);

        b1 = (Button) findViewById(R.id.button1);
        e1 = (EditText) findViewById(R.id.u_name);
        e2 = (EditText) findViewById(R.id.pass);
        e1.setHint("Company Name");
        b1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                String  contact= e1.getText().toString().trim();
                String  password= e2.getText().toString().trim();
              if(contact.isEmpty())
              {
                  Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_LONG).show();
              }
              else if(password.isEmpty())
              {
                  Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_LONG).show();
              }
              else
              {
            	  new userlogin().execute();	
              }
               
            }
        });
   	 register.setOnClickListener(new OnClickListener() 
     {

			@Override
			public void onClick(View arg0) {
			 	 
			  Intent i = new Intent(manufacturer_login.this,manufacturer_register.class);
		    	 

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

	    
		 String lname=e1.getText().toString();
		String	lpass=e2.getText().toString();

		        @Override
		        protected void onPreExecute() {
		            super.onPreExecute();
		            pDialog = new ProgressDialog(manufacturer_login.this);
		            pDialog.setMessage("Requesting " + lname + ")...");
		            pDialog.setIndeterminate(false);
		            pDialog.setCancelable(true);
		            pDialog.show();
		        }

		        

		        protected String doInBackground(String... args) {

		            String txt = "";
		            


		            try {
		           	 
		            	
		                String ur = "http://"+MainActivity.sip+"/manufacturer_login.php?"+ "uname=" + lname + "&pword=" +lpass;
		 
		               
		               
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
		               	finish();
			           //  Intent in = new Intent(getApplicationContext(), bus_id.class);
			             Intent in = new Intent(getApplicationContext(), manufacturer_home.class);
		          startActivity(in);
		          
		          }
		           else if(file_url.trim().equals("failed")) {
		              Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
		          }
		          else
		          { Toast.makeText(getApplicationContext(), "Please Check Login...!", Toast.LENGTH_LONG).show();}

		          pDialog.dismiss();
		      }
		  
		}
		    
		 
}
