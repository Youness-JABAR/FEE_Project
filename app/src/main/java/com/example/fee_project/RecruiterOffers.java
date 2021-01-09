package com.example.fee_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.database.Cursor;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
// cette page est affichée au recruteur et le permet d'ajouter une offre , ainsi que de voir la liste des offres qu'il avait deja ajouté auparavant

public class RecruiterOffers extends AppCompatActivity {

    RecyclerView recyclerView;
    DataBaseHelper db;
    ArrayList<String> idoff,titre, type,remuneration,description,periode;
    OffreAdapter offreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_offers);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Mes offres");
        }

        recyclerView = findViewById(R.id.rv_offre_cont);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecruiterOffers.this, offre_stage.class);
                startActivity(intent);
            }
        });
        db = new DataBaseHelper(RecruiterOffers.this);
        idoff = new ArrayList<>();
        titre = new ArrayList<>();
        type = new ArrayList<>();
        remuneration = new ArrayList<>();
        description = new ArrayList<>();
        periode = new ArrayList<>();

        displayData();
        offreAdapter = new OffreAdapter(RecruiterOffers.this
                ,RecruiterOffers.this, idoff,titre,type,remuneration,description,periode);
        recyclerView.setAdapter(offreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecruiterOffers.this));
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
        SharedPreferences sharedpreferences = getSharedPreferences("com.example.login", Context.MODE_PRIVATE);
        int id = sharedpreferences.getInt("idUser", 0);

        Cursor cursor = db.readAllDataRec(id);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                idoff.add(cursor.getString(0));
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
                Intent i2=new Intent(RecruiterOffers.this,Recruiter_accueil.class);
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

                Intent i=new Intent(RecruiterOffers.this,Login.class);
                startActivity(i);
                Toast.makeText(this, "Deconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

