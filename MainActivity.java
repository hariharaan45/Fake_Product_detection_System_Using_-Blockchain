package com.example.fake_product;

 
import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
 
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
import android.widget.Toast;

public class MainActivity extends Activity {
	ProgressDialog pDialog;
	Button sbtn ;
	EditText ip_address1;
	public static String sip="";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sbtn = (Button) findViewById(R.id.button1);
		ip_address1 = (EditText)findViewById(R.id.editText1); 
		ip_address1.setText("192.168.1.10");
		 
		sbtn.setOnClickListener(new OnClickListener() 
        {

			public void onClick(View arg0) {
				sip="";
				MainActivity.sip=ip_address1.getText().toString().trim();
				 if(MainActivity.sip.equals(""))
				 {
			      Toast.makeText(getApplicationContext(), "Enter Ip Address", Toast.LENGTH_LONG).show();

				 }
				 else
				 {
						MainActivity.sip=MainActivity.sip+"/android_fake_product";

				  //Intent in = new Intent(getApplicationContext(), user_home.class);
				  //startActivity(in); 
				  new userlogin().execute();	
				 }
				 
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

	    
		 

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Connect...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        

        protected String doInBackground(String... args) {

            String txt = "";
            


            try {
           	 
            	
                String ur = "http://"+MainActivity.sip+"/server_check.php";
 
               
               
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
          if ((file_url.trim().equals("202403"))||file_url.trim().equals("202404")||file_url.trim().equals("202405")||file_url.trim().equals("202406")||file_url.trim().equals("202401")) {

         	 

             	Toast.makeText(getApplicationContext(), "Connected Successfully", Toast.LENGTH_LONG).show();
           Intent in = new Intent(getApplicationContext(), home.class);
         startActivity(in);
        
        }
         else if(file_url.trim().equals("failed")) {
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
        }
        else
        { Toast.makeText(getApplicationContext(), "Connection Failed - Check Server..", Toast.LENGTH_LONG).show();}

          pDialog.dismiss();
      }
  
} 
}
