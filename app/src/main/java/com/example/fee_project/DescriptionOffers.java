package com.example.fee_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
// cette page est affichée pour l'étudiant afin de voir les informations de l'offre seléctionnée

public class DescriptionOffers extends AppCompatActivity {
    Button btn_postuler;
    TextView titre,type,periode,remuneration,description_offre,description_entreprise,entreprise;
    ImageView image_ent;
    StorageReference storageReference;


    String getTitre,getType,getPeriode,getRem,getDesc_offre;
    DataBaseHelper db= new DataBaseHelper(DescriptionOffers.this);
    String nameEnt,descriptionEnt,image;
    String idOffer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_offers);


        titre = findViewById(R.id.tv_title1);
        type = findViewById(R.id.tv_type1);
        periode = findViewById(R.id.tv_period1);
        remuneration = findViewById(R.id.tv_remun1);
        description_offre = findViewById(R.id.tv_desc_offre);
        description_entreprise = findViewById(R.id.tv_desc_entreprise);
        btn_postuler=findViewById(R.id.btn_postuler1);
        entreprise=findViewById(R.id.tv_namEnt);
        image_ent=findViewById(R.id.img_entreprise);



        getAndSetIntentDataRec();
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("");
        }





        if(getIntent().hasExtra("idOffer")) {

            idOffer = getIntent().getStringExtra("idOffer");
            Toast.makeText(this, "id " + idOffer, Toast.LENGTH_SHORT).show();

            btn_postuler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DescriptionOffers.this, CvLetter.class);
                    intent.putExtra("idOffer", idOffer);
                    startActivity(intent);

                }
            });
        }
    }

    private void getAndSetIntentDataRec() {
        if (getIntent().hasExtra("idOffer")){
            //On récupère les données

            getTitre = getIntent().getStringExtra("titre");
            getType = getIntent().getStringExtra("type");
            getPeriode = getIntent().getStringExtra("periode");
            getRem = getIntent().getStringExtra("remuneration");
            getDesc_offre = getIntent().getStringExtra("description");

            //Setting data
             titre.setText(getTitre);
             type.setText(getType);
             periode.setText(getPeriode);
             remuneration.setText(getRem);
             description_offre.setText(getDesc_offre);

             //Setting Data about entreprise
            idOffer = getIntent().getStringExtra("idOffer");
            int id = Integer.parseInt(idOffer);
            Toast.makeText(this, "id1   "+id, Toast.LENGTH_SHORT).show();
            //int idRec = db.getRecId(1);
            //Toast.makeText(this, "ID 2"+idRec, Toast.LENGTH_SHORT).show();
            //int idEnt = db.getEntIdFromRec(idRec);
            //Toast.makeText(this, "ID 3"+idEnt, Toast.LENGTH_SHORT).show();

            int idEnt = db.getEntrepriseIdFromOffer(id);
            Toast.makeText(this, "id2"+idEnt, Toast.LENGTH_SHORT).show();
            Cursor cursor = db.getEntrepriseData(idEnt);
            if (cursor == null ) {
                Toast.makeText(this, "Erreur Entreprise", Toast.LENGTH_SHORT).show();
            } else {
                if (cursor.moveToFirst()) {
                    Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
                    nameEnt=cursor.getString(1);
                    descriptionEnt=cursor.getString(2);
                    image=cursor.getString(3);
                }
            }







            entreprise.setText(nameEnt);
            description_entreprise.setText(descriptionEnt);
            //description_entreprise.setText(descriptionEnt);


            //*****************************
            //Upload Image
            storageReference = FirebaseStorage.getInstance().getReference().child("Entreprise/"+image);

            try {
                final File localFile = File.createTempFile(image,"jpg");
                storageReference.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(DescriptionOffers.this,"Image Retrieved",Toast.LENGTH_SHORT).show();
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                image_ent.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DescriptionOffers.this,"Image Failed",Toast.LENGTH_SHORT).show();

                    }
                });
            }catch (Exception e){
                e.printStackTrace();
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
                Intent i2=new Intent(DescriptionOffers.this,Student_accueil.class);
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

                Intent i=new Intent(DescriptionOffers.this,Login.class);
                startActivity(i);
                Toast.makeText(this, "Deconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}