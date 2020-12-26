package com.example.fee_project;

import androidx.appcompat.app.AppCompatActivity;

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

public class offre_stage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText offre_titre,offre_description,offre_periode;
    RadioButton offre_btnoui,offre_btnnon;
     RadioGroup roleRadioGroup;
    Button offre_ajout;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre_stage);
        ///retrouver les id
        offre_titre=findViewById(R.id.offre_titre);
        offre_description=findViewById(R.id.offre_description);
        /// les boutons oui et non dyal la rémunaratin
        offre_btnoui=findViewById(R.id.offre_btnoui);
        offre_btnnon=findViewById(R.id.offre_btnnon);
        roleRadioGroup=findViewById(R.id.roleRadioGroup);
        ////
        offre_periode=findViewById(R.id.offre_periode);
        ////bouton ajouter
        offre_ajout=findViewById(R.id.offre_ajout);
//// spinner1 est le type de stage

        spinner=findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this,R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


///////////////////////////////////////////////////////
    offre_ajout.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                           Offre offre;
                                           try {
                                               offre = new Offre(-1, offre_titre.getText().toString(), offre_description.getText().toString(), offre_periode.getText().toString(), offre_btnoui.getText().toString(), offre_btnnon.getText().toString());
                                               Toast.makeText(offre_stage.this, offre.toString(), Toast.LENGTH_SHORT).show();
                                               DataBaseHelper db = new DataBaseHelper(offre_stage.this);
                                               if (db.addOffre(offre))
                                                   Toast.makeText(offre_stage.this, "l'offre est créee avec succes", Toast.LENGTH_SHORT).show();

                                               else
                                                   Toast.makeText(offre_stage.this, "Erreur lors de la creation de l'offre", Toast.LENGTH_SHORT).show();
                                           }
                                           catch (Exception e) {
                                               Toast.makeText(offre_stage.this, "Erreur lors de la creation de l'offre", Toast.LENGTH_SHORT).show();
                                           }

                                       }
                                   });

        //////:::::::::::::::::::::::::::::::::::::::::

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

