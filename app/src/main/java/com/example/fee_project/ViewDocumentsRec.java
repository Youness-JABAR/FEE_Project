package com.example.fee_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class ViewDocumentsRec extends AppCompatActivity {
    Button btn_viewCv,btn_viewLetter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_documents_rec);
        String idOffer=getIntent().getStringExtra("idOffer");
        String idStudent=getIntent().getStringExtra("idStudent");
        Toast.makeText(this, "iffer"+idOffer+"std"+idStudent, Toast.LENGTH_SHORT).show();
        btn_viewCv=findViewById(R.id.btn_view_cv);
        btn_viewLetter=findViewById(R.id.btn_viewletter);

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

    }
}