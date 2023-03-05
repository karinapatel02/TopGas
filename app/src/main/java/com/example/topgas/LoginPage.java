package com.example.topgas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button singinButton;
    TextView registgerUser;
    TextView forgotPassword;
    SharedPreferences sharedPreferences; //= getSharedPreferences("MySharedPref", MODE_PRIVATE);
    SharedPreferences.Editor myEdit; //= sharedPreferences.edit();
    private String apiPath = "https://sxy5732.uta.cloud/topgas%20api/customer_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        userName = (EditText)findViewById(R.id.inputName);
        password = (EditText) findViewById(R.id.inputPassword);
        singinButton = (Button) findViewById(R.id.loginButton);
        registgerUser = (TextView) findViewById(R.id.register);
        forgotPassword = (TextView)findViewById(R.id.forgotPassword);
        forgotPasswordClickListener();
        signInClickListener(this,this);
        registerClickListener();
    }

    public void signInClickListener(Context mContext, Activity mActivity){
        singinButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(!userName.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    if(password.getText().toString().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}") && password.length() >= 8 ){
                        new ServiceStubAsyncTask(mContext, mActivity).execute();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Please does not meets criteria",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Please provide login details",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void forgotPasswordClickListener(){
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPasswordIntent = new Intent(LoginPage.this, ForgotPassword.class);
                startActivity(forgotPasswordIntent);
            }
        });
    }

    public void registerClickListener(){
        registgerUser.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent registerIntent = new Intent(LoginPage.this, Register.class);
                startActivity(registerIntent);
            }
        });
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

            apiPath = apiPath+"?email="+ userName.getText().toString()+"&password="+password.getText().toString();
            requestQueue= Volley.newRequestQueue(mContext);
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    apiPath,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                String status = jsonObject.getString("status");
                                String userType = jsonObject.getString("user_type");
                                if(status.equals("true")){
                                    sharedPreferences = getSharedPreferences("MySharedPref_login", MODE_PRIVATE);
                                    myEdit = sharedPreferences.edit();
                                    myEdit.putString("email", userName.getText().toString());
                                    myEdit.putString("password", password.getText().toString());
                                    myEdit.apply();
//                                    if(userType.equals("MANAGER")){
                                        Intent signInIntent = new Intent(LoginPage.this, ManagerHome.class);
                                        startActivity(signInIntent);
//                                    }else {
//                                        Intent signInIntent = new Intent(LoginPage.this, CustomerHome.class);
//                                        startActivity(signInIntent);
//                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
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