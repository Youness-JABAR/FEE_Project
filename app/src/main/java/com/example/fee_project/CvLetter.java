    package com.example.fee_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

    public class CvLetter extends AppCompatActivity {
        Button btn_cv,btn_letter,btn_envoyer;
        TextView test;
        Uri cvUri,letterUri;
        StorageReference storageReference;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cv_letter);

            String idOffer;
            if(getIntent().hasExtra("idOffer")) {

                idOffer = getIntent().getStringExtra("idOffer");
                Toast.makeText(this, "id " + idOffer, Toast.LENGTH_SHORT).show();

            SharedPreferences sharedpreferences = getSharedPreferences("com.example.login", Context.MODE_PRIVATE);
            // The value will be default as empty 0
            int idUser=sharedpreferences.getInt("idUser",0);
            Toast.makeText(this, String.valueOf(idUser), Toast.LENGTH_SHORT).show();

            btn_cv=findViewById(R.id.btn_cv);
            btn_letter=findViewById(R.id.btn_letter);
            btn_envoyer=findViewById(R.id.btn_envoyer);
            test=findViewById(R.id.test);
            //reference in the storage in firebase
            storageReference= FirebaseStorage.getInstance().getReference();

            btn_cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPDF(12);
                }
            });
            btn_letter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPDF(114);
                }
            });
            btn_envoyer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cvName,letterName;
                    cvName="cvEtd"+idUser+"Ofr"+idOffer+".pdf";
                    letterName="letterEtd"+idUser+"Ofr"+idOffer+".pdf";
                    Toast.makeText(CvLetter.this, cvName, Toast.LENGTH_SHORT).show();
                    uploadPDFFileFirebase(cvUri,cvName);
                    uploadPDFFileFirebase(letterUri,letterName);

                    DataBaseHelper db = new DataBaseHelper(CvLetter.this);
                    Documents documents=new Documents(-1,idUser,Integer.parseInt(idOffer),cvName,letterName);
                    Toast.makeText(CvLetter.this, documents.toString(), Toast.LENGTH_SHORT).show();
                    if(db.addDocuments(documents)){
                        Toast.makeText(CvLetter.this, "good job", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(CvLetter.this, "bad job :|", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

        private void uploadPDFFileFirebase(Uri cvUri, String cvName) {
            final ProgressDialog progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("File is loading ...");
            progressDialog.show();
            StorageReference fileRef=storageReference.child("PDF/"+cvName);
            fileRef.putFile(cvUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(CvLetter.this, "success", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress=(100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                    progressDialog.setMessage("File Uploaded.."+(int)progress+"%");
                    if((int)progress==100)
                        progressDialog.dismiss();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CvLetter.this, "failure", Toast.LENGTH_SHORT).show();

                }
            });


        }

        private void selectPDF(int num) {
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.setAction(intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            try {
                startActivityForResult(Intent.createChooser(intent, "Select Your .pdf File"), num);
                test.setText("TEST  :");

            } catch (ActivityNotFoundException e) {
                Toast.makeText(CvLetter.this, "Please Install a File Manager",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==12){
                if(data.getData()!=null){
                    Toast.makeText(CvLetter.this, "start activity  "+requestCode,Toast.LENGTH_SHORT).show();
                    btn_cv.setText("Enregistré");
                    btn_cv.setEnabled(false);
                    cvUri=data.getData();

                }


            }
            else if(requestCode==114 ){
                btn_letter.setText("Enregistré");
                btn_letter.setEnabled(false);
                letterUri=data.getData();

            }
        }
    }