package com.example.lenovo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button button , buttonMyProfile , buttonMap;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(this , LoginActivity.class));
        }

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        textView = (TextView)findViewById(R.id.textViewWelcome);



        String defaultMessage = textView.getText().toString().trim();
        textView.setText(firebaseUser.getEmail() + defaultMessage);

        button = (Button) findViewById(R.id.userLogOut);
        buttonMyProfile = (Button)findViewById(R.id.MyProfile);
        buttonMap = (Button)findViewById(R.id.dashboardMap);




        button.setOnClickListener(this);
        buttonMyProfile.setOnClickListener(this);
        buttonMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if(v == button){
            firebaseAuth.signOut();
            finish();
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        if(v==buttonMyProfile){
            finish();
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        if(v==buttonMap){
            finish();
            Intent intent = new Intent(DashboardActivity.this, MapsActivity.class);
            startActivity(intent);

        }

    }
}
