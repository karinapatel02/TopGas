package com.example.topgas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FlashScreen extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String intentName, username, password;
    private String apiPath = "https://sxy5732.uta.cloud/topgas%20api/customer_login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("MySharedPref_login", MODE_PRIVATE);
        if (sharedPreferences.contains("email") && sharedPreferences.contains("password")) {
            username = sharedPreferences.getString("email", "");
            password = sharedPreferences.getString("password", "");

            new ServiceStubAsyncTask(this, this).execute();

        } else {
            Toast.makeText(getApplicationContext(),"key do not exist",Toast.LENGTH_SHORT).show();
            intentName = "LOGIN";
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(intentName.equals("LOGIN")){
                    Intent intent = new Intent(FlashScreen.this, LoginPage.class);
                    startActivity(intent);
                    finish();
                }else if(intentName.equals("CUSTOMERHOME")){
                    Intent intent = new Intent(FlashScreen.this, CustomerHome.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 5000);
    }


    //making an API call and getting the return.
    private class ServiceStubAsyncTask extends AsyncTask<Void, Void, Void> {

        private Context mContext;
        private Activity mActivity;
        RequestQueue requestQueue;

        public ServiceStubAsyncTask(Context context, Activity activity) {
            mContext = context;
            mActivity = activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            apiPath = apiPath+"?email="+username+"&password="+password;
            requestQueue= Volley.newRequestQueue(mContext);
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    apiPath,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject obj = new JSONObject(response.toString());
                                String status = obj.getString("status");
                                if(status.equals("true")){
                                    Toast.makeText(getApplicationContext(),"Server Response: success",Toast.LENGTH_SHORT).show();
                                    intentName = "CUSTOMERHOME";
                                }else{
                                    Toast.makeText(getApplicationContext(),"Server Response: failure",Toast.LENGTH_SHORT).show();
                                    intentName = "LOGIN";
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"cannot call API",Toast.LENGTH_SHORT).show();
                            intentName = "LOGIN";
                        }
                    }
            );

            requestQueue.add(objectRequest);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }

    }


}