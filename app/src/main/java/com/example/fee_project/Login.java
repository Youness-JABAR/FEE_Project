package com.example.fee_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    Button btn_sinscrire,btn_login;
    EditText e_email,p_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    btn_login=findViewById(R.id.btn_login);
    btn_sinscrire=findViewById(R.id.btn_sinscrire);
    e_email=findViewById(R.id.e_email);
    p_password=findViewById(R.id.p_password);


    //if you wanna register
    btn_sinscrire.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Login.this,Inscription_choice.class);
            startActivity(intent);
        }
    });
    //if you wanna connect
    btn_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });



    }
}