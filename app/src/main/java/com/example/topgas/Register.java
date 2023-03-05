package com.example.topgas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    Button registerButton;
    EditText inputMailID,inputPassword, inputConfirmPassword;
    String apiPath = "https://sxy5732.uta.cloud/topgas%20api/register_customer_email.php";
    boolean check=false;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = (Button) findViewById(R.id.registerButton);
        inputMailID = (EditText) findViewById(R.id.inputMailID);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputConfirmPassword = (EditText) findViewById(R.id.inputConfirmPassword);

        registerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(!inputMailID.getText().toString().equals("") && !inputPassword.getText().toString().equals("") &&
                        !inputConfirmPassword.getText().toString().equals("")){
                    if(inputMailID.getText().toString().matches("^(.+)@(.+)$")) {
                        if ((inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString()))) {
                            String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
                            if (inputPassword.getText().toString().matches(pattern) && inputPassword.length() >= 8 ) {

                                new ServiceStubAsyncTask(Register.this, Register.this).execute();

//                                boolean checkFlag = checkIfEmailIsNew(Register.this);
//                                if(checkFlag) {
//                                    Intent registerInfoIntent = new Intent(Register.this, RegisterInformation.class);
//                                    registerInfoIntent.putExtra("password", inputPassword.getText().toString());
//                                    registerInfoIntent.putExtra("email", inputMailID.getText().toString());
//                                    startActivity(registerInfoIntent);
//                                }else{
//                                    Toast.makeText(getApplicationContext(),"Email ID already registered, please use another one",Toast.LENGTH_LONG).show();
//                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Password too weak, please use strong password",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Password and confirm password do not match",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Please provide valid email (abc@xyz.com)",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please provide all required details",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    synchronized public boolean checkIfEmailIsNew(Context mContext){


        if(check){
            return true;
        }else{
            return false;
        }
    }



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

            requestQueue= Volley.newRequestQueue(mContext);
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    apiPath,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), "Response: "+jsonObject.toString(), Toast.LENGTH_SHORT).show();
                                String status = jsonObject.getString("status");
                                if(status.equals("true")){
//                                    check=true;
                                    Intent registerInfoIntent = new Intent(Register.this, RegisterInformation.class);
                                    registerInfoIntent.putExtra("password", inputPassword.getText().toString());
                                    registerInfoIntent.putExtra("email", inputMailID.getText().toString());
                                    startActivity(registerInfoIntent);

                                }else{
                                    check = false;
                                    String msg = jsonObject.getString("msg");
                                    Toast.makeText(getApplicationContext(), "false response: "+msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            check = false;
                            Toast.makeText(getApplicationContext(), "cannot call API", Toast.LENGTH_SHORT).show();

                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", inputMailID.getText().toString());
                    params.put("password", inputPassword.getText().toString());
                    return params;
                }
            };
            requestQueue.add(stringRequest);

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }

}