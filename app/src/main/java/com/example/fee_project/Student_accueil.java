package com.example.fee_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Student_accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_accueil);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.accueil_etd_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent i1=new Intent(Student_accueil.this,StudentOffers.class);
                startActivity(i1);
                return true;
            case R.id.item2:
                //com.example.login is the preference file where we will store info
                //Context.MODE_PRIVATE can be accessed only within the app
                SharedPreferences sharedpreferences = getSharedPreferences("com.example.login", Context.MODE_PRIVATE);
                //editor that will help us to store, retrieve and save info
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("idUser");
                editor.commit();
                Intent i=new Intent(Student_accueil.this,Login.class);
                startActivity(i);
                Toast.makeText(this, "Déconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}