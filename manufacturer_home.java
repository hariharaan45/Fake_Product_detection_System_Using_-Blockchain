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

public class manufacturer_home extends Activity {
 
	Button add_product,product_details,sales_details; 
	   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturer_home);
        
        add_product = (Button) findViewById(R.id.Button01);
        product_details = (Button) findViewById(R.id.button1);
    
        sales_details = (Button) findViewById(R.id.Button02);
          
        
        
        add_product.setOnClickListener(new View.OnClickListener()
      {
          @Override
          public void onClick(View arg0)
          {
                Intent in = new Intent(getApplicationContext(), manufacturer_add_product.class);
                startActivity(in);           
          }
      });
        
        product_details.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), manufacturer_product_details.class);
                  startActivity(in);           
            }
        });
        
        sales_details.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), manufacturer_sales_details.class);
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
