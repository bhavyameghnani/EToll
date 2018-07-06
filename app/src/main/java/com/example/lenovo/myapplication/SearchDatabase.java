package com.example.lenovo.myapplication;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchDatabase extends AppCompatActivity {

    EditText searchBar;
    RecyclerView searchView;
    DatabaseReference dataReference;
    FirebaseUser fireUser;
    ArrayList<String> ALaddress;
    ArrayList<String> ALcell;
    ArrayList<String> ALlicense;
    ArrayList<String> ALfullname;
    SearchAdapter searchAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_database);

        searchBar = (EditText)findViewById(R.id.searchBar);
        searchView = (RecyclerView)findViewById(R.id.searchView);

        dataReference = FirebaseDatabase.getInstance().getReference();
        fireUser = FirebaseAuth.getInstance().getCurrentUser();

        searchView.setHasFixedSize(true);
        searchView.setLayoutManager(new LinearLayoutManager(this));
        searchView.addItemDecoration(new DividerItemDecoration(this , LinearLayoutManager.VERTICAL));

        ALaddress = new ArrayList<>();
        ALfullname = new ArrayList<>();
        ALlicense = new ArrayList<>();
        ALcell = new ArrayList<>();

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }
                else{
                    ALfullname.clear();
                    ALaddress.clear();
                    ALcell.clear();
                    ALlicense.clear();
                    searchView.removeAllViews();
                }

            }
        });
    }

    private void setAdapter(final String searchedString) {

        dataReference.child("profile").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ALfullname.clear();
                ALaddress.clear();
                ALcell.clear();
                ALlicense.clear();
                searchView.removeAllViews();

                int counter=0;

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String uid = snapshot.getKey();
                    String full_name = snapshot.child("profileName").getValue(String.class);
                    String cell_number = snapshot.child("profileNumber").getValue(String.class);
                    String address_name = snapshot.child("profileAddress").getValue(String.class);
                    String license_number = snapshot.child("profileLicense").getValue(String.class);

                    if(full_name.toLowerCase().contains(searchedString.toLowerCase())){
                        ALfullname.add(full_name);
                        ALcell.add(cell_number);
                        ALaddress.add(address_name);
                        ALlicense.add(license_number);
                        counter++;
                    }
                    else if(full_name.toLowerCase().contains(searchedString.toLowerCase())){
                        ALfullname.add(full_name);
                        ALcell.add(cell_number);
                        ALaddress.add(address_name);
                        ALlicense.add(license_number);
                        counter++;
                    }

                    if(counter == 15)
                        break;
                }

                searchAdapter = new SearchAdapter(SearchDatabase.this , ALfullname , ALcell , ALaddress , ALlicense);
                searchView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
