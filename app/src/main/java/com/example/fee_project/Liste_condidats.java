package com.example.fee_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Liste_condidats extends AppCompatActivity {
    Button download;
RecyclerView recyclerView;
    ArrayList<String> nom,prenom;
    DataBaseHelper db;
    CandidatAdapter candidatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_condidats);
        recyclerView = findViewById(R.id.recycleViewEcandidat);
        download=findViewById(R.id.download);






        db = new DataBaseHelper(Liste_condidats.this);
        //initialiser les arraylist
        nom=new ArrayList<>();
        prenom=new ArrayList<>();

        //link=new ArrayList<>();
        displayData();
        candidatAdapter = new CandidatAdapter(Liste_condidats.this,Liste_condidats.this, nom,prenom);
        recyclerView.setAdapter(candidatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Liste_condidats.this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();//refresh the activity
        }
    }

    void displayData() {
        //com.example.login is the preference file where we will store info
        //Context.MODE_PRIVATE can be accessed only within the app
        //SharedPreferences sharedpreferences = getSharedPreferences("com.example.login", Context.MODE_PRIVATE);
        //int id = sharedpreferences.getInt("idUser", 0);

        Cursor cursor = db.readdatacandidat();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                nom.add(cursor.getString(1));
                prenom.add(cursor.getString(2));
               // link.add(cursor.getString(1));
                //Toast.makeText(this, "yes1", Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
    }

}