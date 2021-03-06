package com.example.fee_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

// cette page est affichée chez l'étudiant et affiche l'ensemble des offres publiées par les autres recruteurs
public class StudentOffers extends AppCompatActivity {
    RecyclerView recycleViewEtd;
    DataBaseHelper db;
    ArrayList<String> titre,type,remuneration,description,periode,id;
    OffreEtdAdapter offreEtdAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_offers);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Les offres");
        }



        recycleViewEtd = findViewById(R.id.recycleViewEtd);

        db = new DataBaseHelper(StudentOffers.this);
        titre = new ArrayList<>();
        type = new ArrayList<>();
        remuneration = new ArrayList<>();
        description = new ArrayList<>();
        periode = new ArrayList<>();
        id = new ArrayList<>();

        displayData();
        offreEtdAdapter = new OffreEtdAdapter(StudentOffers.this,titre,type,remuneration,description,periode,id);
        recycleViewEtd.setAdapter(offreEtdAdapter);
        recycleViewEtd.setLayoutManager(new LinearLayoutManager(StudentOffers.this));
    }

    void displayData(){
        Cursor cursor = db.readAllDataEtd();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                id.add(cursor.getString(0));
                Toast.makeText(this, id.get(0), Toast.LENGTH_SHORT).show();
                titre.add(cursor.getString(1));
                type.add(cursor.getString(2));
                remuneration.add(cursor.getString(3));
                description.add(cursor.getString(4));
                periode.add(cursor.getString(5));

            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_acceuil_deconnexion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent i2=new Intent(StudentOffers.this,Student_accueil.class);
                startActivity(i2);
                return true;
            case R.id.item2:
                //com.example.login is the preference file where we will store info
                //Context.MODE_PRIVATE can be accessed only within the app
                SharedPreferences sharedpreferences = getSharedPreferences("com.example.login", Context.MODE_PRIVATE);
                //editor that will help us to store, retrieve and save info
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("idUser");
                editor.commit();

                Intent i=new Intent(StudentOffers.this,Login.class);
                startActivity(i);
                Toast.makeText(this, "Deconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}