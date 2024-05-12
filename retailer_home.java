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

public class retailer_home extends Activity {
 
	Button search_product,sales; 
	   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_home);
        
        search_product = (Button) findViewById(R.id.Button01);
         
        sales = (Button) findViewById(R.id.button2);
           
        
        
        search_product.setOnClickListener(new View.OnClickListener()
      {
          @Override
          public void onClick(View arg0)
          {
                Intent in = new Intent(getApplicationContext(), retailer_search_product.class);
                startActivity(in);           
          }
      });
        
        sales.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), retailer_sales.class);
                  startActivity(in);           
            }
        });
        
//        add_health_inspector.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View arg0)
//            {
//                  Intent in = new Intent(getApplicationContext(), admin_add_health_inspector.class);
//                  startActivity(in);           
//            }
//        });
//        user_details.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View arg0)
//            {
//                  Intent in = new Intent(getApplicationContext(), admin_view_user.class);
//                  startActivity(in);           
//            }
//        });
//        manufacturer.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View arg0)
//            {
//                  Intent in = new Intent(getApplicationContext(), admin_manufacturer_details.class);
//                  startActivity(in);           
//            }
//        }); 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   
}
