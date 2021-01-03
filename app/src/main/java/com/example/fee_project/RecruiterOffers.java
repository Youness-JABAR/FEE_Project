package com.example.fee_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.database.Cursor;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class RecruiterOffers extends AppCompatActivity {

    RecyclerView recyclerView;
    DataBaseHelper db;
    ArrayList<String> titre,description;
    OffreAdapter offreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_offers);

        recyclerView = findViewById(R.id.rv_offre_cont);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecruiterOffers.this,offre_stage.class);
                startActivity(intent);
            }
        });
        db = new DataBaseHelper(RecruiterOffers.this);
        titre = new ArrayList<>();
        description = new ArrayList<>();

        displayData();
        offreAdapter = new OffreAdapter(RecruiterOffers.this,titre,description);
        recyclerView.setAdapter(offreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecruiterOffers.this));
    }
    void displayData(){
        //com.example.login is the preference file where we will store info
        //Context.MODE_PRIVATE can be accessed only within the app
        SharedPreferences sharedpreferences = getSharedPreferences("com.example.login", Context.MODE_PRIVATE);
        int id=sharedpreferences.getInt("idUser",0);

        Cursor cursor = db.readAllDataRec(id);
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                titre.add(cursor.getString(1));
                description.add(cursor.getString(4));
            }
        }
    }
}