package com.example.fee_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
// cette page permet à l'utilisateur de choisir s'il veut se connecter en tant qu'étudiant ou en tant que recruteur

public class Inscription_choice extends AppCompatActivity {
    Button btn_etd,btn_rct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("FEE7");
        }


        setContentView(R.layout.activity_inscription_choice);
        btn_etd=findViewById(R.id.btn_etd);
        btn_rct= findViewById(R.id.btn_rct);

        //student shoosed
        btn_etd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Inscription_choice.this,Register_Student.class);
                startActivity(intent);

            }
        });
        //recruiter shoosed
        btn_rct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Inscription_choice.this,Register_Recruiter_1.class);
                startActivity(intent);

            }
        });

    }
}