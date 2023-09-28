package com.example.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user_profile";
    public  static final String TABLE_NAME = "users";
    public  static final String COLUMN_ID = "_id";
    public  static final String COLUMN_NAME = "NAME";
    public  static final String COLUMN_DOB = "DOB";
    public static final String COLUMN_EMAIL = "EMAIL";

    //column store the path of the image
    public static final String COLUMN_IMAGE = "IMAGE";

    private SQLiteDatabase database;

    // Create table SQL query
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_DOB + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_IMAGE + " TEXT"
                    + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertUser(String name, String dob, String email, String image) {
        ContentValues rowValues = new ContentValues();
        rowValues.put(COLUMN_NAME, name);
        rowValues.put(COLUMN_DOB, dob);
        rowValues.put(COLUMN_EMAIL, email);
        rowValues.put(COLUMN_IMAGE, image);

        return database.insertOrThrow(TABLE_NAME, null, rowValues);
    }

    public String[] getAllUser() {
        String[] columns = new String[] {COLUMN_ID, COLUMN_NAME, COLUMN_DOB, COLUMN_EMAIL, COLUMN_IMAGE};
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);

        ArrayList<String> userList = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String dob = cursor.getString(2);
            String email = cursor.getString(3);
            String image = cursor.getString(4);

            String userString = id + " " + name + " " + dob + " " + email + " " + image;
            userList.add(userString);

            cursor.moveToNext();
        }

        return userList.toArray(new String[0]);
    }
}
