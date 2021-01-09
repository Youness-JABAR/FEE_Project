package com.example.fee_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
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
// cette page est affichée pour le recruteur et contient la liste des condidats qui ont postulé à ses offres
public class Liste_condidats extends AppCompatActivity {
    Button download;
RecyclerView recyclerView;
    ArrayList<String> idStudent,nom,prenom;
    DataBaseHelper db;
    CandidatAdapter candidatAdapter;
    String id_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Liste des candidats");
        }


        setContentView(R.layout.activity_liste_condidats);
        recyclerView = findViewById(R.id.recycleViewEcandidat);
        download=findViewById(R.id.download);

        id_up=getIntent().getStringExtra("idOffer");
        Toast.makeText(this,"yes"+id_up, Toast.LENGTH_SHORT).show();

        db = new DataBaseHelper(Liste_condidats.this);
        //initialiser les arraylist
        idStudent=new ArrayList<>();
        nom=new ArrayList<>();
        prenom=new ArrayList<>();

        //link=new ArrayList<>();
        displayData();
        candidatAdapter = new CandidatAdapter(Liste_condidats.this,Liste_condidats.this, nom,prenom,id_up,idStudent);
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

        Cursor cursor = db.readdatacandidat(Integer.parseInt(id_up));
       // Cursor cursor = db.readdatacandidat(Integer.parseInt(id_up));
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                idStudent.add(cursor.getString(0));
                nom.add(cursor.getString(1));
                prenom.add(cursor.getString(2));
               // link.add(cursor.getString(1));
                //Toast.makeText(this, "yes1", Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
    }

}