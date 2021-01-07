
package com.example.fee_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.DocumentsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String STUDENT = "STUDENT";
    public static final String RECRUITER = "RECRUITER";
    public static final String ENTREPRISE = "ENTREPRISE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_IMAGE = "IMAGE";
    public static final String COLUMN_FAMILY_NAME = "FAMILY_NAME";
    public static final String COLUMN_ENTREPRISE_ID = "ENTREPRISE_ID";
    ///////OFFRE/////////

    public static final String OFFRE = "OFFRE";
    public static final String COLUMN_TITRE = "TITRE";
    public static final String COLUMN_REMUNERATION = "REMUNERATION";
    public static final String COLUMN_DESCRIPTION_OFFRE = "DESCRIPTION";
    public static final String COLUMN_TYPE = "TYPE";
    public static final String COLUMN_PERIODE = "PERIODE";
    public static final String COLUMN_RECRUITER_ID = "RECRUITER_ID";
    public static final String DOCUMENTS = "DOCUMENTS";
    public static final String COLUMN_CVNAME = "CvName";
    public static final String COLUMN_LETTERNAME = "LetterName";
    public static final String COLUMN_STUDENT_ID = "STUDENT_ID";
    public static final String COLUMN_OFFER_ID = "OFFER_ID";

    private Context context;


    DataBaseHelper(@Nullable Context context) {
        super(context, "fee_project.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStudent = "CREATE TABLE " + STUDENT + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT ," + COLUMN_FAMILY_NAME + " TEXT , " + COLUMN_EMAIL + " TEXT , " + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableStudent);
        String createTableRecruiter = "CREATE TABLE " + RECRUITER + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT," + COLUMN_FAMILY_NAME + " TEXT , " + COLUMN_EMAIL + " TEXT , " + COLUMN_PASSWORD + " TEXT ," + COLUMN_ENTREPRISE_ID + " INTEGER NOT NULL,FOREIGN KEY ( " + COLUMN_ENTREPRISE_ID + " ) REFERENCES ENTREPRISE (ID))";
        db.execSQL(createTableRecruiter);
        String createTableEntreprise = "CREATE TABLE " + ENTREPRISE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT , " + COLUMN_IMAGE + " TEXT)";
        db.execSQL(createTableEntreprise);
        String createTableOffre = "CREATE TABLE " + OFFRE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITRE + " TEXT, " + COLUMN_TYPE + " TEXT , " + COLUMN_REMUNERATION + " TEXT , " + COLUMN_DESCRIPTION_OFFRE + " TEXT , " + COLUMN_PERIODE + " TEXT ," + COLUMN_RECRUITER_ID + " INTEGER NOT NULL , FOREIGN KEY ( " + COLUMN_RECRUITER_ID + " ) REFERENCES RECRUITER (ID))";
        db.execSQL(createTableOffre);
        String createTableDocuments = "CREATE TABLE " + DOCUMENTS + "  (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CVNAME + " TEXT, " + COLUMN_LETTERNAME + " TEXT ," + COLUMN_STUDENT_ID + " INTEGER NOT NULL ," + COLUMN_OFFER_ID + " INTEGER NOT NULL , FOREIGN KEY ( " + COLUMN_STUDENT_ID + " ) REFERENCES STUDENT (ID) , FOREIGN KEY ( " + COLUMN_OFFER_ID + " ) REFERENCES OFFRE (ID))";
        db.execSQL(createTableDocuments);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    int getEntrepriseIdFromOffer(int id_offre){
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT "+ COLUMN_ENTREPRISE_ID + " FROM "+ RECRUITER +" WHERE "+ COLUMN_ID + " IN (SELECT "+COLUMN_RECRUITER_ID+" FROM "+OFFRE+" WHERE "+COLUMN_ID+" = '"+id_offre+"')";
        Cursor cursor = db.rawQuery(query, null);
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
            cursor.close();
            db.close();
            return id;
        } else
            return -1;
    }

    Cursor getEntrepriseData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query ="SELECT * FROM " + ENTREPRISE + " WHERE " + COLUMN_ID + " = '"+ id + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }


    public boolean addDocuments(Documents documents) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_OFFER_ID, documents.getIdOffer());
        cv.put(COLUMN_STUDENT_ID, documents.getIdStudent());
        cv.put(COLUMN_CVNAME, documents.getCvName());
        cv.put(COLUMN_LETTERNAME, documents.getLetterName());
        long insert = db.insert(DOCUMENTS, null, cv);
        return insert != -1;

    }
    public boolean addOffre(Offre offre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITRE, offre.getTitre());
        cv.put(COLUMN_TYPE, offre.getType());
        cv.put(COLUMN_REMUNERATION, offre.getRemuneration());
        cv.put(COLUMN_DESCRIPTION_OFFRE, offre.getDescription());
        cv.put(COLUMN_PERIODE, offre.getPeriode());
        cv.put(COLUMN_RECRUITER_ID, offre.getRecruiterId());
        long insert = db.insert(OFFRE, null, cv);
        return insert != -1;
    }

    public boolean addStudent(StudentModel studentModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, studentModel.getName());
        cv.put(COLUMN_FAMILY_NAME, studentModel.getFamily_name());
        cv.put(COLUMN_EMAIL, studentModel.getEmail());
        cv.put(COLUMN_PASSWORD, studentModel.getPassword());
        long insert = db.insert(STUDENT, null, cv);
        return insert != -1;
    }

    public boolean checkEmail(String email, String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + user + " WHERE " + COLUMN_EMAIL + " = '" + email + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0)
            return false;
        else return true;
    }

    public boolean addEntreprise(Entreprise entreprise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, entreprise.getName());
        cv.put(COLUMN_DESCRIPTION, entreprise.getDescription());
        cv.put(COLUMN_IMAGE, entreprise.getImage());
        long insert = db.insert(ENTREPRISE, null, cv);
        return insert != -1;
    }


    public boolean addRecruiter(RecruiterModel recruiterModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, recruiterModel.getName());
        cv.put(COLUMN_FAMILY_NAME, recruiterModel.getFamily_name());
        cv.put(COLUMN_EMAIL, recruiterModel.getEmail());
        cv.put(COLUMN_PASSWORD, recruiterModel.getPassword());
        cv.put(COLUMN_ENTREPRISE_ID, recruiterModel.getEntrepriseId());
        long insert = db.insert(RECRUITER, null, cv);
        return insert != -1;
    }

    public int getEntrepriseId(String imageName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + ENTREPRISE + " WHERE " + COLUMN_IMAGE + " = '" + imageName + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            db.close();
            return id;
        } else
            return -1;
    }

    public boolean emailPassword(String email, String pwd, String USER) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER + " WHERE " + COLUMN_EMAIL + " = '" + email + "' AND " + COLUMN_PASSWORD + " = '" + pwd + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0)
            return true;
        else return false;
    }

    public int getUserId(String email, String pwd, String USER) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + USER + " WHERE " + COLUMN_EMAIL + " = '" + email + "' AND " + COLUMN_PASSWORD + " = '" + pwd + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            db.close();
            return id;
        } else
            return -1;

    }

    Cursor readAllDataRec(int recId) {
        String query = "SELECT * FROM " + OFFRE + " WHERE " + COLUMN_RECRUITER_ID + " = '" + recId + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataEtd() {
        String query = "SELECT * FROM " + OFFRE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String titre, String type, String remuneration, String description, String periode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITRE, titre);

        cv.put(COLUMN_TYPE, type);
        if (remuneration != null)
            cv.put(COLUMN_REMUNERATION, remuneration);
        cv.put(COLUMN_DESCRIPTION_OFFRE, description);
        cv.put(COLUMN_PERIODE, periode);

        //long result = db.update(OFFRE, cv,  " ID=?", new String[]{row_id});

        // long result=db.update(OFFRE, cv, COLUMN_ID + "='" + Integer.parseInt(row_id)+"'", null);
        //
        //
        long result = db.update(OFFRE, cv, "ID=" + row_id, null);
        //long result=db.execSQL("UPDATE "+OFFRE+" SET   "+ COLUMN_TITRE +"'"+s+"' "+ "WHERE salary = "+"'"+s1+"'");
        if (result == -1) {
            Toast.makeText(context, "pas modifie", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "bravoooo" + row_id, Toast.LENGTH_SHORT).show();
        }


    }

    void deleteOneData(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(OFFRE, "ID=" + row_id, null);
        if (result == -1) {
            Toast.makeText(context, "Pas supprimé", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Done" + row_id, Toast.LENGTH_SHORT).show();
        }


    }

    Cursor readdatacandidat() {
        String query = "SELECT * FROM " + STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }



}


