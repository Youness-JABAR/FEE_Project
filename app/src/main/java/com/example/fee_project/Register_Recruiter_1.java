package com.example.fee_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class Register_Recruiter_1 extends AppCompatActivity {
    TextView tv_rct_name,tv_rct_prenom,tv_rct_email,tv_rct_pass1,tv_rct_pass2,tv_ent,tv_ent_photo,tv_ent_desc;
    EditText insre_nom,insre_prenom,insre_email,insre_password,insre_password2,insre_entreprise,insre_description;
    ImageView iv_ent_photo,iv_rct_logo;
    Button btn_ent_photo,btn_insre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__recruiter_1);
        tv_rct_name = findViewById(R.id.tv_rct_name);
        tv_rct_prenom = findViewById(R.id.tv_rct_prenom);
        tv_rct_email = findViewById(R.id.tv_rct_email);
        tv_rct_pass1 = findViewById(R.id.tv_rct_pass1);
        tv_rct_pass2 = findViewById(R.id.tv_rct_pass2);
        //entreprise
        tv_ent = findViewById(R.id.tv_ent);
        tv_ent_photo = findViewById(R.id.tv_ent_photo);
        tv_ent_desc = findViewById(R.id.tv_ent_desc);


        btn_ent_photo = findViewById(R.id.btn_ent_photo);
        iv_ent_photo = findViewById(R.id.iv_ent_photo);

        btn_ent_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri ImageUri = data.getData();
                iv_ent_photo.setImageURI(ImageUri);
            }
        }
    }
}