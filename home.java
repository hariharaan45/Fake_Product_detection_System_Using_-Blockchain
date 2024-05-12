package com.example.fake_product;
 
 
 
 
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class home extends Activity {
	 ImageView admin,health_inspector,manufacturer,retailer,user;
	    TextView admin1,manufacturer1,health_inspector1,retailer1,user1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		admin = (ImageView) findViewById(R.id.i1);
		health_inspector = (ImageView) findViewById(R.id.i3);
		manufacturer = (ImageView) findViewById(R.id.i2);
		retailer = (ImageView) findViewById(R.id.i5);
		user= (ImageView) findViewById(R.id.i4);
		
		admin1 = (TextView) findViewById(R.id.t1);
		health_inspector1 = (TextView) findViewById(R.id.t3);
		manufacturer1 = (TextView) findViewById(R.id.t2);
		retailer1 = (TextView) findViewById(R.id.t5);
		user1 = (TextView) findViewById(R.id.t4);
		admin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {

                Intent i = new Intent(getApplicationContext(),admin_login.class);
                startActivity(i);
            }
        });
	admin1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {

                Intent i = new Intent(getApplicationContext(),admin_login.class);
                startActivity(i);
            }
        });
	
	health_inspector.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View arg0)
        {

            Intent i = new Intent(getApplicationContext(),health_inspector_login.class);
            startActivity(i);
        }
    });
	health_inspector1.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View arg0)
        {

            Intent i = new Intent(getApplicationContext(),health_inspector_login.class);
            startActivity(i);
        }
    });
	manufacturer.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View arg0)
    {

        Intent i = new Intent(getApplicationContext(),manufacturer_login.class);
        startActivity(i);
    }
});
	manufacturer1.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View arg0)
    {

        Intent i = new Intent(getApplicationContext(),manufacturer_login.class);
        startActivity(i);
    }
});
	retailer.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View arg0)
    {

        Intent i = new Intent(getApplicationContext(),retailer_login.class);
        startActivity(i);
    }
});
	retailer1.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View arg0)
    {

        Intent i = new Intent(getApplicationContext(),retailer_login.class);
        startActivity(i);
    }
});	
	
	user.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View arg0)
    {

        Intent i = new Intent(getApplicationContext(),user_login.class);
        startActivity(i);
    }
});
	user1.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View arg0)
    {

        Intent i = new Intent(getApplicationContext(),user_login.class);
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

}
