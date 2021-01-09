package com.example.fee_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.lang.reflect.Type;
// cette page est affichée chez le recruteur pour voir le contenu des cv et des lettres evoyées de la part des étudiant

public class ShowPdf extends AppCompatActivity {
    private PDFView pdfView;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pdf);
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("FEE7");
        }
        pdfView=(PDFView) findViewById(R.id.pdf);
        String idOffer=getIntent().getStringExtra("idOffer");
        String idStudent=getIntent().getStringExtra("idStudent");
        String type=getIntent().getStringExtra("type");


        //Upload pdf
        String nameFile=type +"Etd"+idStudent+"Ofr"+idOffer+".pdf";
        Toast.makeText(this, nameFile, Toast.LENGTH_SHORT).show();
        storageReference = FirebaseStorage.getInstance().getReference().child("PDF/"+nameFile );
        final File localFile;
        try {
            localFile = File.createTempFile("tempo","jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            pdfView.fromFile(localFile).load();
//                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            //image_ent.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ShowPdf.this,"pdf Failed",Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            e.printStackTrace();
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
                Intent i2=new Intent(ShowPdf.this,Recruiter_accueil.class);
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

                Intent i=new Intent(ShowPdf.this,Login.class);
                startActivity(i);
                Toast.makeText(this, "Deconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}