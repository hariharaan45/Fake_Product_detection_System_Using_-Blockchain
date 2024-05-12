package com.example.fake_product;
 
 
import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

 
 
 
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class user_home extends Activity {
	public static String id="1";
	static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
	    Button scan_qr,track_product,purchased;
	   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        
    

        scan_qr = (Button) findViewById(R.id.Button01);
        track_product = (Button) findViewById(R.id.button1);
        
        purchased = (Button) findViewById(R.id.button2);
 
//        scan_qr.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View arg0)
//            {
//                  Intent in = new Intent(getApplicationContext(), user_home_1.class);
//                  startActivity(in);           
//            }
//        });
        track_product.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), user_track_product.class);
                  startActivity(in);           
            }
        });
        purchased.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                  Intent in = new Intent(getApplicationContext(), user_purchased.class);
                  startActivity(in);           
            }
        });
 
 
    }

	 public void scanQR(View v) {
			try {
				Intent intent = new Intent(ACTION_SCAN);
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);
			} catch (ActivityNotFoundException anfe) {
				showDialog(user_home.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
			}
		}
	    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
			AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
			downloadDialog.setTitle(title);
			downloadDialog.setMessage(message);
			downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialogInterface, int i) {
					Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					try {
						act.startActivity(intent);
					} catch (ActivityNotFoundException anfe) {

					}
				}
			});
			downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialogInterface, int i) {
				}
			});
			return downloadDialog.show();
		}
		public void onActivityResult(int requestCode, int resultCode, Intent intent) {
			if (requestCode == 0) {
				if (resultCode == RESULT_OK) {
					String contents = intent.getStringExtra("SCAN_RESULT");
					String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
					Toast toast = Toast.makeText(this,  contents , Toast.LENGTH_LONG);
					toast.show();
					id=contents;
					Intent in = new Intent(getApplicationContext(), user_home_1.class);
	 		         startActivity(in);  
				}
			}
		} 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   
}
