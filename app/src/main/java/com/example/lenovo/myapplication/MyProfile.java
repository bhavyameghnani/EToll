package com.example.lenovo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyProfile extends AppCompatActivity implements View.OnClickListener{

    EditText MPname , MPaddress , MPlicense , MPnumber;
    Button MPsend , MPback;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference("profile");
        MPname = (EditText)findViewById(R.id.mpName);
        MPaddress = (EditText) findViewById(R.id.mpAddress);
        MPlicense = (EditText) findViewById(R.id.mpLicense);
        MPnumber = (EditText) findViewById(R.id.mpCell);

        MPsend = (Button)findViewById(R.id.mpSend);
        MPback = (Button)findViewById(R.id.mpBack);

        MPsend.setOnClickListener(this);
        MPback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==MPsend){
            addProfile();

        }

        if(v==MPback){
            finish();
            Intent intent = new Intent(MyProfile.this, DashboardActivity.class);
            startActivity(intent);
        }
    }

    public void addProfile(){
        String fullname = MPname.getText().toString().trim();
        String address = MPaddress.getText().toString().trim();
        String license = MPlicense.getText().toString().trim();
        String cell = MPnumber.getText().toString().trim();

        if(!TextUtils.isEmpty(fullname) && (!TextUtils.isEmpty(address)) && !TextUtils.isEmpty(license) && !TextUtils.isEmpty(cell) ){
            String ID = databaseReference.push().getKey();

            Profile profile = new Profile(ID , fullname , address , license , cell);

            databaseReference.child(ID).setValue(profile);
            Toast.makeText(this, " All The Details Added Successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Please Fill-up All The Details", Toast.LENGTH_SHORT).show();
        }
    }
}
