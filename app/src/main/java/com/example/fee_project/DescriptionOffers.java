package com.example.fee_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DescriptionOffers extends AppCompatActivity {
    Button btn_postuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_offers);
        btn_postuler=findViewById(R.id.btn_postuler1);
        String idOffer ;
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
}