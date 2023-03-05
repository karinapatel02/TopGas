package com.example.topgas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterInformation extends AppCompatActivity {

    Spinner sISDCode;
    Button bLogin;
    EditText inputName,inputContactNumber, inputAddress1, inputStreet, inputCity, inputState, inputZipcode;
    String email, password;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_information);
        sISDCode = (Spinner)findViewById(R.id.ISDCode);
        bLogin = (Button)findViewById(R.id.signInButton);

        inputName = (EditText)findViewById(R.id.inputName);
        inputContactNumber = (EditText)findViewById(R.id.inputContactNumber);
        inputAddress1 = (EditText)findViewById(R.id.inputAddress1);
        inputStreet = (EditText)findViewById(R.id.inputStreet);
        inputCity = (EditText)findViewById(R.id.inputCity);
        inputState = (EditText)findViewById(R.id.inputState);
        inputZipcode = (EditText)findViewById(R.id.inputZipcode);



        bLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(!inputName.getText().toString().equals("") &&
                        !inputContactNumber.getText().toString().equals("") &&
                        !inputAddress1.getText().toString().equals("") &&
                        !inputStreet.getText().toString().equals("") &&
                        !inputCity.getText().toString().equals("") &&
                        !inputState.getText().toString().equals("") &&
                        !inputZipcode.getText().toString().equals("") ) {
                        if(inputContactNumber.getText().toString().length()==10 && inputContactNumber.getText().toString().charAt(0)!='0'){

                            Intent registerIntent = new Intent(RegisterInformation.this, RegisterVehicle.class);
                            extras = getIntent().getExtras();
                            registerIntent.putExtra("password",extras.getString("password"));
                            registerIntent.putExtra("email",extras.getString("email"));
                            registerIntent.putExtra("contact_no",inputContactNumber.getText().toString());
                            registerIntent.putExtra("name",inputName.getText().toString());
                            registerIntent.putExtra("address1",inputAddress1.getText().toString());
                            registerIntent.putExtra("street",inputStreet.getText().toString());
                            registerIntent.putExtra("city",inputCity.getText().toString());
                            registerIntent.putExtra("state",inputState.getText().toString());
                            registerIntent.putExtra("pincode",inputZipcode.getText().toString());
                            startActivity(registerIntent);
                        }
                    else{
                            Toast.makeText(getApplicationContext(),"Please provide valid number",Toast.LENGTH_SHORT).show();
                        }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please provide all the details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}