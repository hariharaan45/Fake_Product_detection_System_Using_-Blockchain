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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class admin_login extends Activity {
	  TextView register,txt;
	    Button b1;
	    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        register = (TextView)findViewById(R.id.TextView01);
        txt = (TextView)findViewById(R.id.textView1);
        txt.setText("Admin Login");
        register.setVisibility(View.INVISIBLE);

        b1 = (Button) findViewById(R.id.button1);
        e1 = (EditText) findViewById(R.id.u_name);
        e2 = (EditText) findViewById(R.id.pass);
        e1.setHint("Username");
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
              else if((contact.equals("admin"))&&(password.equals("admin")))
              {
            	  Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
            	  finish();
                  Intent in = new Intent(getApplicationContext(), admin_home.class);
                  startActivity(in);
              }
              else
              {
                  Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
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
   
}
