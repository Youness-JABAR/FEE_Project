package com.example.fee_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
//cette page est affichée chez le recruteur quand il clique sur l'une des offres qu'il avait dejà publié dans recruiter offers , et conttient le bouton modifier , supprimer et voir le condidat
public class UpdateOffre extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText titre,description,periode;
    Spinner type;
    RadioButton radioButton;


    //Spinner type;
    Button btn_update,btn_delete,btn_see;
    RadioButton oui,non;
   RadioGroup rdgrp2;
    String title_up, description_up,id_up,periode_up,type_up,remuneration_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_offre);





        titre=findViewById(R.id.offre_titre2);
        type=findViewById(R.id.spinner2);


        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this,R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(this);


        periode=findViewById(R.id.offre_periode2);
        description=findViewById(R.id.offre_description2);
        oui=findViewById(R.id.offre_btnoui2);
        non=findViewById(R.id.offre_btnnon2);
        rdgrp2=findViewById(R.id.roleRadioGroup2);
        btn_see=findViewById(R.id.btn_see);
        btn_update=findViewById(R.id.offre_update);
        btn_delete = findViewById(R.id.btn_delete);
        //appel de la méthode
        getAndSetIntentDataRec();
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle(title_up);
        }

        btn_update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

               String nvtitre=titre.getText().toString();// cas titre qui est dans la page xml de updateoffre , sa valeur qui est  vide va etre modifié par la valeur qu'ajouté le recruteur q"on a récuépér par intent , et put extra
                String nvdescription=description.getText().toString();
                String nvperiode=periode.getText().toString();

                DataBaseHelper db =new DataBaseHelper(UpdateOffre.this);
                //appel de la fct de modification

                db.updateData(id_up, nvtitre,nvtype,nvrem,nvdescription,nvperiode);

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();

            }
        });

        btn_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UpdateOffre.this,Liste_condidats.class);
                intent.putExtra("idOffer", id_up);
                Toast.makeText(UpdateOffre.this, "OUAAIIIS"+id_up, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
    ////on recupere les donnees qu'on a fait passer par intent - putextra)
    void getAndSetIntentDataRec(){
        if (getIntent().hasExtra("idoff")){
            ////on recupere les donnees qu'on a fait passer par intent - putextra)
///// and store the information inside the strings
            id_up=getIntent().getStringExtra("idoff");
            Toast.makeText(this,id_up,Toast.LENGTH_SHORT).show();
            title_up=getIntent().getStringExtra("title");
           type_up=getIntent().getStringExtra("type");
           remuneration_up=getIntent().getStringExtra("remuneration");

            Toast.makeText(this,remuneration_up,Toast.LENGTH_SHORT).show();
            description_up=getIntent().getStringExtra("description");

            periode_up=getIntent().getStringExtra("periode");

            int valeurtype=getnumtype(type_up);
            //SETTING DATA
            titre.setText(title_up);// cas titre qui est dans la page xml de updateoffre , sa valeur qui est  vide va etre modifié par la valeur qu'ajouté le recruteur q"on a récuépér par intent , et put extra
            description.setText(description_up);
            type.setSelection(valeurtype);

            if(remuneration_up.compareTo("oui")==0){
                rdgrp2.check(R.id.offre_btnoui2);
            }
            else {
                rdgrp2.check(R.id.offre_btnnon2);
            }



            periode.setText(periode_up);

        }
        else{  Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();}
    }
    //Fonction pour afficher pop-up de suppression
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer "+title_up+" ?");
        builder.setMessage("Ëtes-vous sûre de vouloir supprimer cet offre :"+title_up+"?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBaseHelper db =new DataBaseHelper(UpdateOffre.this);
                db.deleteOneData(id_up);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

    private int getnumtype(String type_up) {
        if(type_up=="OBSERVATION")
            return 0;
        else if(type_up=="PFA")
        return 1;
        else if(type_up=="PFE")
            type.setSelection(2);
        return 2;
    }

    String nvtype;
    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nvtype = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), nvtype , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    String nvrem;
    public void checkButton(View v) {
        int radioId = rdgrp2.getCheckedRadioButtonId();
        radioButton =findViewById(radioId);
        nvrem=radioButton.getText().toString();
        Toast.makeText(this,"le bouton selectionne est :"+radioButton.getText(),Toast.LENGTH_SHORT).show();


    }
}

