package com.example.fee_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
// cette page est affichée chez le recruteur quand il clique sur le bouton ajouter , et le permet de saisir les informations necessaires pour décrire l'offre

public class offre_stage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText titre,description,periode;
    RadioButton btn_oui,btn_non;
    RadioButton radioButton;
    RadioGroup type;
    Button btn_ajout;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre_stage);
        ///retrouver les id
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("");
        }


        titre=findViewById(R.id.offre_titre);
        description=findViewById(R.id.offre_description);
        /// les boutons oui et non dyal la rémunaratin
        btn_oui=findViewById(R.id.offre_btnoui);
        btn_non=findViewById(R.id.offre_btnnon);
        type=findViewById(R.id.roleRadioGroup);
        ////
        periode=findViewById(R.id.offre_periode);
        ////bouton ajouter
        btn_ajout=findViewById(R.id.offre_ajout);
//// spinner1 est le type de stage

        spinner=findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this,R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


///////////////////////////////////////////////////////
        btn_ajout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Offre offre;
            String offre_titre=titre.getText().toString();
            String offre_periode=periode.getText().toString();
            String offre_description=description.getText().toString();

            if (offre_titre.equals("")||offre_periode.equals("")||offre_description.equals("")){
                Toast.makeText(offre_stage.this, "champs sont vides", Toast.LENGTH_SHORT).show();
            }else{
                DataBaseHelper db = new DataBaseHelper(offre_stage.this);
                        try {
                            //com.example.login is the preference file where we will store info
                            //Context.MODE_PRIVATE can be accessed only within the app
                            SharedPreferences sharedpreferences = getSharedPreferences("com.example.login", Context.MODE_PRIVATE);
                            // The value will be default as empty 0
                            int id=sharedpreferences.getInt("idUser",0);

                            offre=new Offre(-1,offre_titre,valeur_spinner,valeur_remuneration,offre_description,offre_periode, id);
                            Toast.makeText(offre_stage.this, offre.toString(), Toast.LENGTH_SHORT).show();
                            if (db.addOffre(offre)) {
                                Toast.makeText(offre_stage.this, "offre créé avec succès", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(offre_stage.this, RecruiterOffers.class);
                                startActivity(intent);
                            }else
                                Toast.makeText(offre_stage.this, "Erreur lors de la creation de l'offre", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            Toast.makeText(offre_stage.this, "Erreur lors de la creation de l'offre", Toast.LENGTH_SHORT).show();
                        }


            }

        }

    });

        //////:::::::::::::::::::::::::::::::::::::::::

    }
    String valeur_spinner;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        valeur_spinner = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), valeur_spinner, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    ///FONCTION DU BOUTON
    String valeur_remuneration;
    public void checkButton(View v) {
        int radioId = type.getCheckedRadioButtonId();
        radioButton =findViewById(radioId);
         valeur_remuneration=radioButton.getText().toString();
        Toast.makeText(this,"le bouton selectionne est :"+radioButton.getText(),Toast.LENGTH_SHORT).show();
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
                Intent i2=new Intent(offre_stage.this,Recruiter_accueil.class);
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

                Intent i=new Intent(offre_stage.this,Login.class);
                startActivity(i);
                Toast.makeText(this, "Deconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

