package com.example.fee_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class DescriptionOffers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_offers);


        if(getIntent().hasExtra("idOffer")){
            String idOffer ;
            idOffer = getIntent().getStringExtra("idOffer");
            Toast.makeText(this, "id "+ idOffer , Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "noo data", Toast.LENGTH_SHORT).show();
    }
}