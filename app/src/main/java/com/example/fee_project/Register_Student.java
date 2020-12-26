package com.example.fee_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Student extends AppCompatActivity {
    EditText inset_nom,inset_prenom,inset_email,inset_password1,inset_password2;
    Button btn_inset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__student);
        inset_nom=findViewById(R.id.inset_nom);
        inset_prenom=findViewById(R.id.inset_prenom);
        inset_email=findViewById(R.id.inset_email);
        inset_password1=findViewById(R.id.inset_password1);
        inset_password2=findViewById(R.id.inset_password2);
        btn_inset=findViewById(R.id.button_inset);
        btn_inset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel studentModel ;
                String nom=inset_nom.getText().toString();
                String prenom=inset_prenom.getText().toString();
                String email=inset_email.getText().toString();
                String pwd1=inset_password1.getText().toString();
                String pwd2=inset_password2.getText().toString();
                if (email.equals("")||nom.equals("")||prenom.equals("")||pwd1.equals("")){
                    Toast.makeText(Register_Student.this, "champs sont vides", Toast.LENGTH_SHORT).show();
                }
                else{
                    if ( pwd1.equals(pwd2) ){
                        DataBaseHelper db = new DataBaseHelper(Register_Student.this);
                        if(db.checkEmail(email,"STUDENT")){
                            try {
                                studentModel=new StudentModel(-1,prenom,nom,email,pwd1);
                                Toast.makeText(Register_Student.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                                if (db.addStudent(studentModel))
                                    Toast.makeText(Register_Student.this, "compte créer avec succès", Toast.LENGTH_SHORT).show();

                                else
                                    Toast.makeText(Register_Student.this, "Erreur lors de la creation de compte", Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception e){
                                Toast.makeText(Register_Student.this, "Erreur lors de la creation de compte", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(Register_Student.this, "l'email existe déjà", Toast.LENGTH_SHORT).show();
                        

                    }
                    else {
                        Toast.makeText(Register_Student.this, "Mots de passe non identiques!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}