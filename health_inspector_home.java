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

public class health_inspector_home extends Activity {
 
	Button view_alert,track_product,logout; 
	   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_inspector_home);
        
        view_alert = (Button) findViewById(R.id.Button01);
        track_product = (Button) findViewById(R.id.button1);
        logout = (Button) findViewById(R.id.button2);
     
          
        
        
        view_alert.setOnClickListener(new View.OnClickListener()
      {
          @Override
          public void onClick(View arg0)
          {
                Intent in = new Intent(getApplicationContext(), admin_fake_product_alert.class);
                startActivity(in);           
          }
      });
        
        track_product.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), user_track_product.class);
                  startActivity(in);           
            }
        });
        
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                 finish();          
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
