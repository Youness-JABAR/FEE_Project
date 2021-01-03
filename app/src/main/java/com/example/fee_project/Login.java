package com.example.fee_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btn_sinscrire,btn_login;
    EditText e_email,p_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //com.example.login is the preference file where we will store info
        //Context.MODE_PRIVATE can be accessed only within the app
        SharedPreferences sharedpreferences = getSharedPreferences("com.example.login", Context.MODE_PRIVATE);
        //editor that will help us to store, retrieve and save info
        SharedPreferences.Editor editor = sharedpreferences.edit();

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
            DataBaseHelper db = new DataBaseHelper(Login.this);

            String email=e_email.getText().toString();
            String pwd=p_password.getText().toString();
            if(db.emailPassword(email,pwd,"STUDENT")){
                //get the id of the user
                int id=db.getUserId(email,pwd,"STUDENT");
                editor.putInt("idUser",id);
                editor.commit();


                Toast.makeText(Login.this, "successfully login as student", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this,Student_accueil.class);
                startActivity(intent);
            }
            else if (db.emailPassword(email,pwd,"RECRUITER")){
                //get the id of the user
                int id=db.getUserId(email,pwd,"RECRUITER");
                editor.putInt("idUser",id);
                editor.commit();

                Toast.makeText(Login.this, "successfully login as recruiter", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this,Recruiter_accueil.class);
                startActivity(intent);
            }
            else
                Toast.makeText(Login.this, "mot de passe ou email incorrect", Toast.LENGTH_SHORT).show();

        }
    });



    }
}