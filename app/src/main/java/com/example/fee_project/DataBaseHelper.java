package com.example.fee_project;

import android.content.ContentValues;
import android.content.Context;
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

    public DataBaseHelper(@Nullable Context context) {
        super(context, "fee_project.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStudent = "CREATE TABLE " + STUDENT + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT ," + COLUMN_FAMILY_NAME + " TEXT , " + COLUMN_EMAIL + " TEXT , " + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableStudent);
        String createTableRecruiter = "CREATE TABLE " + RECRUITER + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT," + COLUMN_FAMILY_NAME + " TEXT , " + COLUMN_EMAIL + " TEXT , " + COLUMN_PASSWORD + " TEXT ," + COLUMN_ENTREPRISE_ID + " INEGER NOT NULL,FOREIGN KEY ( " + COLUMN_ENTREPRISE_ID + " ) REFERENCES ENTREPRISE (ID))";
        db.execSQL(createTableRecruiter);
        String createTableEntreprise = "CREATE TABLE " + ENTREPRISE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT , " + COLUMN_IMAGE + " BLOB)";
        db.execSQL(createTableEntreprise);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
    public boolean checkemail(String email){
        return true;
    }

}
