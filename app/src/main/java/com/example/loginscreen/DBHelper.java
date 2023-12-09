package com.example.loginscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbname = "creds.db";

    public DBHelper(Context context) {
        super(context, "creds.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDb.insert("users", null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }
    public Boolean checkUsername(String username){
        SQLiteDatabase myDb = getWritableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * from users where username = ? ", new String[]{username});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean validateLogin(String username, String password){
        SQLiteDatabase myDb = getWritableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT * from users where username = ? and password = ?", new String[] {username, password});
        int count = cursor.getCount();
        System.out.println(count);
        if ( count > 0){

            return true;
        }
        else{
            return false;
        }

    }

}
