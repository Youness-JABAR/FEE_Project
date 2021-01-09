package com.example.fee_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
// cette page est affichée chez le recruteur pour voir le cv et la lettre de l'étudiant chosi

public class ViewDocumentsRec extends AppCompatActivity {
    Button btn_viewCv,btn_viewLetter,btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_documents_rec);
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("");
        }


        String idOffer=getIntent().getStringExtra("idOffer");
        String idStudent=getIntent().getStringExtra("idStudent");
        Toast.makeText(this, "iffer"+idOffer+"std"+idStudent, Toast.LENGTH_SHORT).show();
        btn_viewCv=findViewById(R.id.btn_view_cv);
        btn_viewLetter=findViewById(R.id.btn_viewletter);
        btn_send=findViewById(R.id.btn_contacter);

        btn_viewCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ViewDocumentsRec.this,ShowPdf.class);
                intent.putExtra("idOffer",idOffer);
                intent.putExtra("idStudent",idStudent);
                intent.putExtra("type","cv");

                startActivity(intent);
            }
        });
        btn_viewLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ViewDocumentsRec.this,ShowPdf.class);
                intent.putExtra("idOffer",idOffer);
                intent.putExtra("idStudent",idStudent);
                intent.putExtra("type","letter");

                startActivity(intent);

            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper db = new DataBaseHelper(ViewDocumentsRec.this);
                String email = db.getEmailByID(idStudent);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                intent.setData(Uri.parse("mailto:"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(ViewDocumentsRec.this, "Y a pas d'applications qui supportent cette action", Toast.LENGTH_SHORT).show();
                }



            }
        });


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
                Intent i2=new Intent(ViewDocumentsRec.this,Recruiter_accueil.class);
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

                Intent i=new Intent(ViewDocumentsRec.this,Login.class);
                startActivity(i);
                Toast.makeText(this, "Deconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}