package com.example.topgas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class FAQ extends AppCompatActivity {
    TextView q1,q2,q3,q4,q5,q6,q7,q8,q9;
    TextView a1,a2,a3,a4,a5,a6,a7,a8,a9;
    SharedPreferences sharedPreferences;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.menu);
        navigationView.setCheckedItem(R.id.nav_setting);

        {
            a1 = (TextView) findViewById(R.id.answer1);
            a2 = (TextView) findViewById(R.id.answer2);
            a3 = (TextView) findViewById(R.id.answer3);
            a4 = (TextView) findViewById(R.id.answer4);
            a5 = (TextView) findViewById(R.id.answer5);
            a6 = (TextView) findViewById(R.id.answer6);
            a7 = (TextView) findViewById(R.id.answer7);
            a8 = (TextView) findViewById(R.id.answer8);
            a9 = (TextView) findViewById(R.id.answer9);

            q1 = (TextView) findViewById(R.id.question1);
            q2 = (TextView) findViewById(R.id.question2);
            q3 = (TextView) findViewById(R.id.question3);
            q4 = (TextView) findViewById(R.id.question4);
            q5 = (TextView) findViewById(R.id.question5);
            q6 = (TextView) findViewById(R.id.question6);
            q7 = (TextView) findViewById(R.id.question7);
            q8 = (TextView) findViewById(R.id.question8);
            q9 = (TextView) findViewById(R.id.question9);

            a1.setVisibility(View.GONE);
            a2.setVisibility(View.GONE);
            a3.setVisibility(View.GONE);
            a4.setVisibility(View.GONE);
            a5.setVisibility(View.GONE);
            a6.setVisibility(View.GONE);
            a7.setVisibility(View.GONE);
            a8.setVisibility(View.GONE);
            a9.setVisibility(View.GONE);
        }

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a1.getVisibility()== View.VISIBLE){
                    a1.setVisibility(View.GONE);
                    q1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0);
                }else{
                    a1.setVisibility(View.VISIBLE);
                    q1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0);
                }
            }
        });

        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a2.getVisibility()== View.VISIBLE){
                    a2.setVisibility(View.GONE);
                    q2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0);
                }else{
                    a2.setVisibility(View.VISIBLE);
                    q2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0);
                }
            }
        });

        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a3.getVisibility()== View.VISIBLE){
                    a3.setVisibility(View.GONE);
                    q3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0);
                }else{
                    a3.setVisibility(View.VISIBLE);
                    q3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0);
                }
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a4.getVisibility()== View.VISIBLE){
                    a4.setVisibility(View.GONE);
                    q4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0);
                }else{
                    a4.setVisibility(View.VISIBLE);
                    q4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0);
                }
            }
        });

        q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a5.getVisibility()== View.VISIBLE){
                    a5.setVisibility(View.GONE);
                    q5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0);
                }else{
                    a5.setVisibility(View.VISIBLE);
                    q5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0);
                }
            }
        });

        q6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a6.getVisibility()== View.VISIBLE){
                    a6.setVisibility(View.GONE);
                    q6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0);
                }else{
                    a6.setVisibility(View.VISIBLE);
                    q6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0);
                }
            }
        });

        q7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a7.getVisibility()== View.VISIBLE){
                    a7.setVisibility(View.GONE);
                    q7.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0);
                }else{
                    a7.setVisibility(View.VISIBLE);
                    q7.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0);
                }
            }
        });

        q8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a8.getVisibility()== View.VISIBLE){
                    a8.setVisibility(View.GONE);
                    q8.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0);
                }else{
                    a8.setVisibility(View.VISIBLE);
                    q8.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0);
                }
            }
        });

        q9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a9.getVisibility()== View.VISIBLE){
                    a9.setVisibility(View.GONE);
                    q9.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0);
                }else{
                    a9.setVisibility(View.VISIBLE);
                    q9.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0);
                }
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id= item.getItemId();

                switch(id){
                    case R.id.nav_home:
//                        Toast.makeText(getApplicationContext(),"nav_home",Toast.LENGTH_SHORT).show();
                        intent = new Intent(FAQ.this, CustomerHome.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.nav_setting:
//                        Toast.makeText(getApplicationContext(),"nav_setting",Toast.LENGTH_SHORT).show();
                        intent = new Intent(FAQ.this, ProfileSetting.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.nav_vehicle:
//                        Toast.makeText(getApplicationContext(),"nav_vehicle",Toast.LENGTH_SHORT).show();
                        intent = new Intent(FAQ.this, ManageVehicle.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_history:
//                        Toast.makeText(getApplicationContext(),"nav_history",Toast.LENGTH_SHORT).show();
                        intent = new Intent(FAQ.this, OrderHistory.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_faq:
                        Toast.makeText(getApplicationContext(),"nav_faq",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_rateus:
                        Toast.makeText(getApplicationContext(),"nav_rateus",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_help:
                        intent = new Intent(FAQ.this, HowTo.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_logout:
                        sharedPreferences = getSharedPreferences("MySharedPref_login", MODE_PRIVATE);
                        sharedPreferences.edit().clear().commit();
                        Toast.makeText(getApplicationContext(),"preferences cleared",Toast.LENGTH_SHORT).show();
                        intent = new Intent(FAQ.this, LoginPage.class);
                        startActivity(intent);
                        finish();
//                        Toast.makeText(getApplicationContext(),"nav_logout",Toast.LENGTH_SHORT).show();

                        break;
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();

        }
    }
}