package com.example.fee_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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


    public DataBaseHelper(@Nullable Context context) {
        super(context, "fee_project.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStudent = "CREATE TABLE " + STUDENT + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT ," + COLUMN_FAMILY_NAME + " TEXT , " + COLUMN_EMAIL + " TEXT , " + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableStudent);
        String createTableRecruiter = "CREATE TABLE " + RECRUITER + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT," + COLUMN_FAMILY_NAME + " TEXT , " + COLUMN_EMAIL + " TEXT , " + COLUMN_PASSWORD + " TEXT ," + COLUMN_ENTREPRISE_ID + " INEGER NOT NULL,FOREIGN KEY ( " + COLUMN_ENTREPRISE_ID + " ) REFERENCES ENTREPRISE (ID))";
        db.execSQL(createTableRecruiter);
        String createTableEntreprise = "CREATE TABLE " + ENTREPRISE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT , " + COLUMN_IMAGE + " TEXT)";
        db.execSQL(createTableEntreprise);
        String createTableOffre = "CREATE TABLE " + OFFRE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITRE + " TEXT, " + COLUMN_TYPE + " TEXT , " + COLUMN_REMUNERATION + " TEXT , " + COLUMN_DESCRIPTION_OFFRE + " TEXT , " + COLUMN_PERIODE + " TEXT)";
        db.execSQL(createTableOffre);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOffre(Offre offre){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITRE,offre.getTitre());
        cv.put(COLUMN_TYPE,offre.getType());
        cv.put(COLUMN_REMUNERATION,offre.getRemuneration());
        cv.put(COLUMN_DESCRIPTION_OFFRE,offre.getDescription());
        cv.put(COLUMN_PERIODE,offre.getPeriode());
        long insert=db.insert(OFFRE,null,cv);
        return insert != -1;
    }
    public boolean addStudent(StudentModel studentModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,studentModel.getName());
        cv.put(COLUMN_FAMILY_NAME,studentModel.getFamily_name());
        cv.put(COLUMN_EMAIL,studentModel.getEmail());
        cv.put(COLUMN_PASSWORD,studentModel.getPassword());
        long insert=db.insert(STUDENT,null,cv);
        return insert != -1;
    }
    public boolean checkEmail(String email,String user){
        SQLiteDatabase db=this.getReadableDatabase();
        String query ="SELECT * FROM "+user+" WHERE "+COLUMN_EMAIL+ " = '"+ email +"'" ;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount()>0)
            return false;
        else return true;
    }

    public boolean addEntreprise(Entreprise entreprise) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,entreprise.getName());
        cv.put(COLUMN_DESCRIPTION,entreprise.getDescription());
        cv.put(COLUMN_IMAGE,entreprise.getImage());
        long insert=db.insert(ENTREPRISE,null,cv);
        return insert != -1;
    }


    public boolean addRecruiter(RecruiterModel recruiterModel) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,recruiterModel.getName());
        cv.put(COLUMN_FAMILY_NAME,recruiterModel.getFamily_name());
        cv.put(COLUMN_EMAIL,recruiterModel.getEmail());
        cv.put(COLUMN_PASSWORD,recruiterModel.getPassword());
        cv.put(COLUMN_ENTREPRISE_ID,recruiterModel.getEntrepriseId());
        long insert=db.insert(RECRUITER,null,cv);
        return insert != -1;
    }

    public int getEntrepriseId(String imageName) {
        SQLiteDatabase db=this.getReadableDatabase();
        String query ="SELECT " +COLUMN_ID+ " FROM "+ENTREPRISE+" WHERE "+COLUMN_IMAGE+ " = '"+ imageName +"'" ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            int id=cursor.getInt(0);
            cursor.close();
            db.close();
            return id;
        }
        else
            return -1;
    }

    public boolean emailPassword(String email, String pwd, String USER) {
        SQLiteDatabase db=this.getReadableDatabase();
        String query ="SELECT * FROM "+USER+" WHERE "+COLUMN_EMAIL+ " = '"+ email +"' AND "+COLUMN_PASSWORD+ " = '"+ pwd +"'" ;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount()>0)
            return true;
        else return false;
    }


    Cursor readAllData(){
        String query = "SELECT * FROM "+ OFFRE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null ;
        if(db != null){
            cursor = db.rawQuery(query,null);
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
    public int getUserId( String email, String pwd, String USER) {
        SQLiteDatabase db=this.getReadableDatabase();
        String query ="SELECT " +COLUMN_ID+ " FROM "+USER+" WHERE "+COLUMN_EMAIL+ " = '"+ email +"' AND "+COLUMN_PASSWORD+ " = '"+ pwd +"'" ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            int id=cursor.getInt(0);
            cursor.close();
            db.close();
            return id;
        }
        else
            return -1;

    }
}
