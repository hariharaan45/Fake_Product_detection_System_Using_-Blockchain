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

public class admin_home extends Activity {
 
	Button view_product,fake_product_alert,add_health_inspector,user_details,manufacturer; 
	   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        
        view_product = (Button) findViewById(R.id.Button01);
        fake_product_alert = (Button) findViewById(R.id.button1);
        add_health_inspector = (Button) findViewById(R.id.button2);
        user_details = (Button) findViewById(R.id.Button02);
        manufacturer = (Button) findViewById(R.id.Button03);
          
        
        
        view_product.setOnClickListener(new View.OnClickListener()
      {
          @Override
          public void onClick(View arg0)
          {
                Intent in = new Intent(getApplicationContext(), admin_view_product.class);
                startActivity(in);           
          }
      });
        
        fake_product_alert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), admin_fake_product_alert.class);
                  startActivity(in);           
            }
        });
        
        add_health_inspector.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), admin_add_health_inspector.class);
                  startActivity(in);           
            }
        });
        user_details.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), admin_view_user.class);
                  startActivity(in);           
            }
        });
        manufacturer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), admin_manufacturer_details.class);
                  startActivity(in);           
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
