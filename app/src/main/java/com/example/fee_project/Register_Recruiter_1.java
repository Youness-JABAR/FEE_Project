package com.example.fee_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Register_Recruiter_1 extends AppCompatActivity {
    EditText insre_nom,insre_prenom,insre_email,insre_password,insre_password2,insre_password1,insre_entreprise,insre_description;
    ImageView iv_ent_photo;
    Button btn_ent_photo,btn_insre;
    StorageReference storageReference;
    Uri ImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__recruiter_1);

        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle("");
        }

        insre_nom = findViewById(R.id.insre_nom);
        insre_prenom = findViewById(R.id.insre_prenom);
        insre_email = findViewById(R.id.insre_email);
        insre_password1 = findViewById(R.id.insre_password);
        insre_password2 = findViewById(R.id.insre_password2);
        //entreprise
        insre_entreprise = findViewById(R.id.insre_entreprise);
        insre_description = findViewById(R.id.insre_description);
        btn_ent_photo = findViewById(R.id.btn_ent_photo);
        iv_ent_photo = findViewById(R.id.iv_ent_photo);

        btn_insre=findViewById(R.id.btn_insre);

        storageReference= FirebaseStorage.getInstance().getReference();



        btn_insre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entreprise entreprise;
                RecruiterModel recruiterModel;
                String imageName;
                String nom=insre_nom.getText().toString();
                String prenom=insre_prenom.getText().toString();
                String email=insre_email.getText().toString();
                String pwd1=insre_password1.getText().toString();
                String pwd2=insre_password2.getText().toString();
                String entrepriseName=insre_entreprise.getText().toString();
                String description=insre_description.getText().toString();

                if (email.equals("")||nom.equals("")||prenom.equals("")||pwd1.equals("")||entrepriseName.equals("")||description.equals("")){
                    Toast.makeText(Register_Recruiter_1.this, "champs sont vides", Toast.LENGTH_SHORT).show();
                }
                else{
                    if ( pwd1.equals(pwd2) ){
                        DataBaseHelper db = new DataBaseHelper(Register_Recruiter_1.this);
                        if(db.checkEmail(email,"RECRUITER")){
                            try {
                                imageName=entrepriseName+nom+prenom;
                                uploadImageToFirebase(ImageUri,imageName);

                                entreprise=new Entreprise(-1,entrepriseName,description,imageName);
                                Toast.makeText(Register_Recruiter_1.this, entreprise.toString(), Toast.LENGTH_SHORT).show();
                                //function you should add in dbhelper
                                if (db.addEntreprise(entreprise)){
                                    int entrepriseId =db.getEntrepriseId(imageName);
                                    if(entrepriseId!=-1){
                                        Toast.makeText(Register_Recruiter_1.this, "entreprise créer avec succès", Toast.LENGTH_SHORT).show();
                                        //add the recruiter****************************

                                        try {
                                            recruiterModel=new RecruiterModel(-1,prenom,nom,email,pwd1, entrepriseId);
                                            Toast.makeText(Register_Recruiter_1.this, recruiterModel.toString(), Toast.LENGTH_SHORT).show();

                                            if (db.addRecruiter(recruiterModel)){
                                                Toast.makeText(Register_Recruiter_1.this, "compte créer avec succès", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Register_Recruiter_1.this,Login.class);
                                                startActivity(intent);
                                            }

                                            else
                                                Toast.makeText(Register_Recruiter_1.this, "Erreur lors de la creation de compte", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (Exception e){
                                            Toast.makeText(Register_Recruiter_1.this, "Erreur lors de la creation de compte", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else
                                        Toast.makeText(Register_Recruiter_1.this, "Erreur lors de la creation de l'entreprise", Toast.LENGTH_SHORT).show();

                                }

                                else
                                    Toast.makeText(Register_Recruiter_1.this, "Erreur lors de la creation de l'entreprise", Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception e){
                                Toast.makeText(Register_Recruiter_1.this, "Erreur lors de la creation de compte", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                            Toast.makeText(Register_Recruiter_1.this, "l'email existe déjà", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        Toast.makeText(Register_Recruiter_1.this, "Mots de passe non identiques!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });





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
                ImageUri = data.getData();
                iv_ent_photo.setImageURI(ImageUri);

            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri ,String imageName) {
        StorageReference fileRef=storageReference.child("Entreprise/"+imageName);
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Register_Recruiter_1.this, "img uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register_Recruiter_1.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}