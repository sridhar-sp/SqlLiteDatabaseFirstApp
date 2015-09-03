package com.example.half_blood_prince.sqlfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Half-Blood-Prince on 02-09-2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "first_database";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "student_details";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_SEX = "sex";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_PHONE+" TEXT,"+KEY_SEX+" TEXT);";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }


    public void insert(Bean currentBean){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,currentBean.getmName());
        values.put(KEY_PHONE,currentBean.getmPhone());
        values.put(KEY_SEX,currentBean.getmSex());
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public void Update(Bean currentBean){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,currentBean.getmName());
        values.put(KEY_PHONE, currentBean.getmPhone());
        values.put(KEY_SEX, currentBean.getmSex());

        String Where = KEY_ID+" LIKE ?";
        String[] WhereArg = { String.valueOf(currentBean.getmID()) };
        db.update(TABLE_NAME, values, Where, WhereArg);
    }

    public void Delete(Bean currentBean){
        SQLiteDatabase db = this.getWritableDatabase();

        String WhereClasue = KEY_ID+" = ?";
        String[] WhereArgs = { String.valueOf(currentBean.getmID()) };

        db.delete(TABLE_NAME, WhereClasue, WhereArgs);
    }

    public Cursor Read(Bean currentBean){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID+" = ?";
       // String[] selectionArgs = { KEY_ID+" = "+currentBean.getmID() };
        Cursor currentCursor = db.rawQuery(selectQuery, new String[] { String.valueOf(currentBean.getmID())} );
        return currentCursor;

    }

    public List<Bean> display(){
        List<Bean> studentDetails = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase(); // remove this and see what happens
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
        do {
                Bean currentBean = new Bean();
//                currentBean.setmID(Integer.parseInt(cursor.getString(0)));
//                currentBean.setmName(cursor.getString(1));
//                currentBean.setmPhone(cursor.getString(2));
//                currentBean.setmSex(cursor.getString(3));
                currentBean.setmID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                currentBean.setmName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                currentBean.setmPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
                currentBean.setmSex(cursor.getString(cursor.getColumnIndex(KEY_SEX)));
                studentDetails.add(currentBean);
            }while (cursor.moveToNext());
        }

        return studentDetails;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
