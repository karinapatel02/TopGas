package com.example.topgas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class RegisterVehicle extends AppCompatActivity {
    Spinner sBrand, sFuel;
    Button bRegister, bSkip;
    EditText inputModel, inputPlate, inputMakeYear;
    String apiPath = "https://sxy5732.uta.cloud/topgas%20api/register_customer.php";
    private ProgressDialog processDialog;
    RequestQueue requestQueue;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicle);
        sFuel = (Spinner) findViewById(R.id.inputFuel);
        sBrand = (Spinner) findViewById(R.id.inputBrand);
        inputModel = (EditText) findViewById(R.id.inputModel);
        inputPlate = (EditText) findViewById(R.id.inputPlate);
        inputMakeYear = (EditText) findViewById(R.id.inputMakeYear);
        bRegister = (Button) findViewById(R.id.signInButton);
        bSkip = (Button) findViewById(R.id.skipButton);

        String[] brandList = new String[]{"Hyundai", "Toyota", "Ford", "Dodge", "GM"};
        String[] fuelTypeList = new String[]{"Petrol", "Diesel", "Lead Petrol", "Lead Diesel",};
        ArrayAdapter<String> myAdapterBrand = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, brandList);
        sBrand.setAdapter(myAdapterBrand);

        ArrayAdapter<String> myAdapterFuel = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, fuelTypeList);
        sFuel.setAdapter(myAdapterFuel);
        extras = getIntent().getExtras();
        registerOnClickListener(this, this);
        skipOnClickListener(this, this);

    }

   public void registerOnClickListener(Context mContext, Activity mAcitvity){
        bRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                processDialog = new ProgressDialog(RegisterVehicle.this);
                processDialog.setMessage("Please  Wait ...");
                processDialog.show();
                processDialog.setContentView(R.layout.progress_dialog);
                processDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
                );

                requestQueue = Volley.newRequestQueue(mContext);
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        apiPath,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String status = jsonObject.getString("status");
                                    if(status.equals("true")){
                                        Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_SHORT).show();
                                        Intent registerIntent = new Intent(RegisterVehicle.this, LoginPage.class);
                                        startActivity(registerIntent);
                                        finishAffinity();
                                        finish();
                                    }else{
                                        String msg = jsonObject.getString("msg");
                                        Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_SHORT).show();
                                        Intent registerIntent = new Intent(RegisterVehicle.this, Register.class);
                                        startActivity(registerIntent);
                                        finishAffinity();
                                        finish();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "cannot call API", Toast.LENGTH_SHORT).show();

                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", extras.getString("name"));
                        params.put("email", extras.getString("email"));
                        params.put("contact_no", extras.getString("contact_no"));
                        params.put("address1", extras.getString("address1"));
                        params.put("street", extras.getString("street"));
                        params.put("pincode", extras.getString("pincode"));
                        params.put("password", extras.getString("password"));
                        params.put("city", extras.getString("city"));
                        params.put("state", extras.getString("state"));
                        params.put("brand", sBrand.getSelectedItem().toString());
                        params.put("model", inputModel.getText().toString());
                        params.put("year", inputMakeYear.getText().toString());
                        params.put("plate_no", inputPlate.getText().toString());
                        params.put("vehicle_skip","false");
                        return params;
                    }
                };

                requestQueue.add(stringRequest);
                for(int i=0;i<100000;i++){

                }
                if (processDialog.isShowing()) {
                    processDialog.dismiss();
                }
            }
        });
    }

    public void skipOnClickListener(Context mContext, Activity mActivity){
        bSkip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GlobalParameter.vehicleSkip = true;
                Intent registerIntent = new Intent(RegisterVehicle.this, CustomerHome.class);
                registerIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(registerIntent);
                finishAffinity();
                finish();
            }
        });
    }
//    @Override
//    public void onBackPressed() {
////        super.onBackPressed();
////        Intent loginIntent = new Intent(RegisterVehicle.this, MainActivity.class);
////        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////        startActivity(loginIntent);
////        finish();
//    }
}