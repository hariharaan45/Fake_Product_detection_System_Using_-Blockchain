package com.example.fake_product;

 
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

 
 
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class retailer_search_product extends Activity {
	 Button regbtn ;
	 ProgressDialog pDialog;
	 EditText user_name;
	 ProgressDialog pd;
	 ListView listview;
		public static String id="";
	 List<String> categories = new ArrayList<String>();
//	 public static String nam="",fpass="",spass="",cont="",eml="",ran="";
	 public static String product="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		
		setContentView(R.layout.retailer_search_product);
		regbtn = (Button) findViewById(R.id.reg);
		 listview = (ListView) findViewById(R.id.listView1);
		user_name = (EditText)findViewById(R.id.name);
		 
	 
		 pd = new ProgressDialog(this);
	        pd.setMessage("Loading please wait");
	        pd.setCancelable(false);
	 
	      
	        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
		             public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
		                       long arg3) {  
		                  String test = (String) listview.getAdapter().getItem(arg2);  
		 
		                  final String[] arrSplit = test.split(":");
		                  Log.i("id", arrSplit[0]); 
		                  id=arrSplit[0];
		                  
		                  final Dialog dialog = new Dialog(retailer_search_product.this);
			                dialog.setContentView(R.layout.ask_buy_product);
			                dialog.setCancelable(true);
			               
			                dialog.setTitle("Payment");
			               
			                Button send1 = (Button) dialog.findViewById(R.id.button1);
			                send1.setOnClickListener(new View.OnClickListener() {
			                    @Override
			                    public void onClick(View v) 
			                    {
			                    	dialog.dismiss();
			                    	 
			                    	 new userlogin().execute();  
			                         	                       
			                    }
			                });
			             
			                dialog.show();    
		             
			            
		             }  
		        });	
	        
	            	 
	            	 
	     
		regbtn.setOnClickListener(new OnClickListener() 
	        {

				@Override
				public void onClick(View arg0) {
					String user_name_1=user_name.getText().toString();
					 
					product=user_name_1;
					if(user_name_1.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Enter all fileds", Toast.LENGTH_LONG).show();	
					}
					 
				 
				 
					else  
					{
						// Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_LONG).show();
		                 Log.i("Pass", "same");
		                 commonRequestThread();	 	
					}
				 
					
					
					//Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
					 							 
					//Intent i = new Intent(Registration.this,MainActivity.class); 	 
				   	//startActivity(i);
					 // Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_LONG).show();
				//mEdit.setText(""+m);

					
				}
	        });
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
		                            //finish();
		                        }else{
		                            toast("No data found");
		                        }
		                        //finish();
		                    }else{
		                        adapter = new ArrayAdapter<String>(context,
		                                android.R.layout.simple_list_item_1,
		                                android.R.id.text1, values);
		                        listview.setAdapter(adapter);


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
		    HttpPost httppost = new HttpPost("http://"+MainActivity.sip+"/retailer_search_product.php");
		    InputStream is = null;
		    String result = "";

		    try {
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		       // System.out.println("user_login.uemail: "+user_login.uemail);
		         nameValuePairs.add(new BasicNameValuePair("product",product));
		      //  nameValuePairs.add(new BasicNameValuePair("driver",login.uemail));
		       // nameValuePairs.add(new BasicNameValuePair("l2",a2));
		      //  nameValuePairs.add(new BasicNameValuePair("product",product));
//		        nameValuePairs.add(new BasicNameValuePair("grp",group));
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
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
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

//		        TextView txtFirstName = (TextView) rootView.findViewById(R.id.txtFirstName);
//		        txtFirstName.setText(""+res);

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

		            user_id.add(js.getString("id"));


		         //   String sts=js.getString("username");
		         //   String contact=js.getString("contact");



		            String d=js.getString("id")+":"+js.getString("product_name")
		            		+"\nManufacturer : "+js.getString("manufacturer")
		            		+"\nQuantity : "+js.getString("quantity")
		            		+"\nPrice : "+js.getString("price");

		            values[i] = d;
		            categories.add(d);
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
			            pDialog = new ProgressDialog(retailer_search_product.this);
			            pDialog.setMessage("Requesting...");
			            pDialog.setIndeterminate(false);
			            pDialog.setCancelable(true);
			            pDialog.show();
			        }

			        
	 
			        protected String doInBackground(String... args) {

			            String txt = "";
			            


			            try {
			           	 
			            	
			                String ur = "http://"+MainActivity.sip+"/retailer_search_product_payment.php?"+ "id=" + id + "&retailer=" +retailer_login.uemail;
			 
			               
			               
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

			               	Toast.makeText(getApplicationContext(), "Payment Success", Toast.LENGTH_LONG).show();
				           //  Intent in = new Intent(getApplicationContext(), bus_id.class);
				            // Intent in = new Intent(getApplicationContext(), staff_home.class);
			         // startActivity(in);
			          
			          }
			           else if(file_url.trim().equals("failed")) {
			              Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
			          }
			          else
			          { Toast.makeText(getApplicationContext(), ""+file_url, Toast.LENGTH_LONG).show();}

			          pDialog.dismiss();
			      }
			  
			}	
}
