package com.example.fake_product;

 
 

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.fake_product.user_login.userlogin;
 
 
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class user_home_1 extends Activity {
  
	ProgressDialog pd;
	 ProgressDialog pDialog;
	 TextView product_name,category,price,description,sts;
	 EditText qty;
	 Button btn;
	 ImageView img;
	 int loader = R.drawable.loader;
	 public static String product_name1="0";
	 public static String price1="0";
	 public static String category1="0";
	 public static String description1="0";
	 public static String quantity="0";
	 public static String report="0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_home_1);
		product_name = (TextView)findViewById(R.id.textView3);
		category = (TextView)findViewById(R.id.textView5);
		price = (TextView)findViewById(R.id.textView7);
		description = (TextView)findViewById(R.id.textView9);
		sts = (TextView)findViewById(R.id.textView91);
		qty = (EditText)findViewById(R.id.editText1);
		btn = (Button)findViewById(R.id.button1);
		
		
		
		
		img = (ImageView) findViewById(R.id.imageView1);
		 pd = new ProgressDialog(this);
	        pd.setMessage("Loading please wait");
	        pd.setCancelable(false);
		commonRequestThread(); 
		  if(report.equals("0"))
          {
          	sts.setText("Fake");
          	 new userlogin().execute();
          }
          else
          {
          	sts.setText("Original");
          } 
		product_name.setText(""+""+product_name1);
        category.setText(""+""+category1);
        price.setText(""+""+price1);
        description.setText(""+""+description1);
		btn.setOnClickListener(new OnClickListener() 
        {

			@Override
			public void onClick(View arg0) 
			{
			  String q=qty.getText().toString().trim();
			  if(q.isEmpty())
			  {
					Toast.makeText(getApplicationContext(), "Enter Quantity", Toast.LENGTH_LONG).show();  
			  }
			  else
			  {
				quantity=q;
				Intent in = new Intent(getApplicationContext(), user_home_2.class);
				startActivity(in);
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
	public void commonRequestThread() {
        status = "Please try again later";

        pd.show();
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                Runnable runnable = new Runnable() {
                    public void run() {
                        pd.dismiss();
                        if(user_id.size()<1){
                            if(isError) {
                                toast("No result found");
                                finish();
                            }else{
                                toast("No data found");
                            }
                            finish();
                        }else{
    						adapter = new ArrayAdapter<String>(context,
    								android.R.layout.simple_list_item_1,
    								android.R.id.text1, values);
    					 	//listView.setAdapter(adapter);
    					 
    					 
                        }
                    }
                };
                try {
                    runOnUiThread(runnable);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        };
        Thread checkUpdate = new Thread() {
            public void run() {
                try {
                    commonRequest();
                } catch (Exception e) {
                    System.out.println("Error in fetching json : "
                            + e.toString());
                }
                handler.sendEmptyMessage(0);
            }
        };
        checkUpdate.start();
    }


    Boolean isError = true;
    public void commonRequest()
    {
        isError = true;
        System.out.println("Common request sent : ");
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://"+MainActivity.sip+"/customer_search_product_1.php");
        InputStream is = null;
        String result = "";
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("id",user_home.id));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity httpEntity = response.getEntity();
            is = httpEntity.getContent();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            System.out.println("Error 1 : "+e.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error 2 : "+e.toString());
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            System.out.println("Error 2 : "+e.toString());
        }
        System.out.println("result : "+result);
        res = result;
        JSONObject food_object;

//	        TextView txtFirstName = (TextView) rootView.findViewById(R.id.txtFirstName);
//	        txtFirstName.setText(""+res);

        try {
            //food_object = new JSONObject(result);
            if(result.contains("result")){
                isError = false;
            }
            food_object = new JSONObject(result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1));
            JSONArray foo_array = food_object.getJSONArray("result");

			values = new String[foo_array.length()];
            for (int i = 0; i < foo_array.length(); i++) {
                JSONObject js = foo_array.getJSONObject(i);
 

              user_id.add(js.getString("product_name"));
              
              product_name1=js.getString("product_name");
              category1=js.getString("manufacturer");
              price1=js.getString("price");
              description1=js.getString("description");
             
              product_name.setText(""+""+product_name1);
              category.setText(""+""+category1);
              price.setText(""+""+price1);
              description.setText(""+""+description1);
                report=js.getString("iso_status");
                if(report.equals("0"))
                {
                	sts.setText("Fake");
                	 new userlogin().execute();
                }
                else
                {
                	sts.setText("Original");
                } 
                
           
             
      		///description .setText( ""+js.getString("description"));
            //  String uprof=js.getString("file_name");
 			// String image_url = "http://"+MainActivity.sip+"/uploads/"+uprof;
 		        
 		        // ImageLoader class instance
 		       // ImageLoader imgLoader = new ImageLoader(getApplicationContext());
 		        
 		        // whenever you want to load an image from url
 		        // call DisplayImage function
 		        // url - image url to load
 		        // loader - loader image, will be displayed before getting image
 		        // image - ImageView 
 		        //imgLoader.DisplayImage(image_url, loader, img);
		    
		        // whenever you want to load an image from url
		        // call DisplayImage function
		        // url - image url to load
		        // loader - loader image, will be displayed before getting image
		        // image - ImageView 
		          
             // String d=js.getString("id")+"-"+js.getString("job");
             // qry_list[i]=js.getString("id");
				//values[i] = d;
			     // categories.add(d);
			  //   categories1.add(js.getString("tname"));

		    // System.out.println("value q : "+values[i]);

            }
           
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            System.out.println("Error 3 : "+e.toString());
            e.printStackTrace();
        }
    }

    String res = "", status = "";

	ArrayAdapter<String> adapter;
	String values[];

    List<String> user_id = new ArrayList<String>();
    List<String> ownername = new ArrayList<String>();
    
    Context context = this;
    
    Toast toast;
    public void toast(String str) {
        try {
            toast.cancel();
        } catch (Exception e) {
            // TODO: handle exception
        }
        toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    } 
    public class userlogin extends AsyncTask<String, String, String> {

	     

		        @Override
		        protected void onPreExecute() {
		            super.onPreExecute();
		            pDialog = new ProgressDialog(user_home_1.this);
		            pDialog.setMessage("Requesting ...");
		            pDialog.setIndeterminate(false);
		            pDialog.setCancelable(true);
		            pDialog.show();
		        }

		        

		     
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        protected String doInBackground(String... args) {

		            String txt = "";
		            


		            try {
		           	 
		            	
		            	   String ur = "http://"+MainActivity.sip+"/customer_scan_fake.php?"
		            			   + "name=" +URLEncoder.encode( user_login.uemail , "UTF-8")  
		            			   + "&pid=" +URLEncoder.encode( user_home.id , "UTF-8") 
		            			     
		            			   ;
		               
		               
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

		        	 // uemail=lname;

		               	Toast.makeText(getApplicationContext(), "Fake Product", Toast.LENGTH_LONG).show();
			            //  Intent in = new Intent(getApplicationContext(), bus_id.class);
		              //   new userlogin1().execute();	
		               // Toast.makeText(getApplicationContext(),area, Toast.LENGTH_LONG).show();
		              Intent in = new Intent(getApplicationContext(), user_home.class);   
			                startActivity(in);
		                	finish();
			            
		          
		          }
		           else if(file_url.trim().equals("failed")) {
		              Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_LONG).show();
		          }
		          else
		          { Toast.makeText(getApplicationContext(), "Please Check Login...!", Toast.LENGTH_LONG).show();}

		          pDialog.dismiss();
		      }
		  
		}
		    
		   
}
