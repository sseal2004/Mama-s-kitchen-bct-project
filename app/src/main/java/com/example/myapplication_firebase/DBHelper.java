package com.example.myapplication_firebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor; // Fix: Correct import for Cursor
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "register.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        sqliteDatabase.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int i, int i1) {
        sqliteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqliteDatabase); // Ensure table is recreated after upgrade
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);
        return result != -1;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username}); // Fix: Initialize Cursor
        boolean exists = cursor.getCount() > 0; // Use getCount() on the Cursor object
        cursor.close(); // Fix: Close Cursor to prevent memory leaks
        return exists;
    }
    public boolean CheckUser(String username, String pad){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password =?",new String[]{username,pad});
        if(cursor.getCount() >0){
            return true;
        }
        else{
            return false;
        }
    }
}

