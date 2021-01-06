package com.example.fee_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DescriptionOffers extends AppCompatActivity {
    Button btn_postuler;
    TextView titre,type,periode,remuneration,description_offre,description_entreprise;
    String getTitre,getType,getPeriode,getRem,getDesc_offre;
    DataBaseHelper db;
    ArrayList<String> ident,descriptionEnt,image;

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
        String idOffer ;

        //appel de la méthode
        ident = new ArrayList<>();
        descriptionEnt = new ArrayList<>();
        image = new ArrayList<>();
        getAndSetIntentDataRec();
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("Information de l'offre");
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
             int id = Integer.parseInt("idOffer");
             int idEnt = db.getEntrepriseIdFromOffer(id);
             Cursor cursor = db.getEntrepriseData(idEnt);
             if (cursor.getCount() == 0) {
                Toast.makeText(this, "Erreur Entreprise", Toast.LENGTH_SHORT).show();
             } else {
                while (cursor.moveToNext()) {
                    ident.add(cursor.getString(0));
                    descriptionEnt.add(cursor.getString(2));
                    image.add(cursor.getString(3));
                }
             }

            description_entreprise.setText((CharSequence) descriptionEnt);


        }

    }

}