package com.example.schoolofrock;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // initialize constants for DB version and name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "schoolofrock.db";

    // initialize constants for student table
    private static final String TABLE_SONG_VOTE = "voting";
    private static final String COLUMN_VOTER_ID = "_id";
    private static final String COLUMN_VOTER_NAME = "name";
    private static final String COLUMN_SONG_NAME = "song";
    private static final String COLUMN_VOTE = "vote";

    public DBHandler (Context context, SQLiteDatabase.CursorFactory cursorFactory) {

        // call superclass constructor
        super(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // define create statement for student table
        String query = "CREATE TABLE " + TABLE_SONG_VOTE + "(" +
                COLUMN_VOTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SONG_NAME + " TEXT, " +
                COLUMN_VOTER_NAME + " TEXT, " +
                COLUMN_VOTE + " INTEGER" +
                ");";

        // execute create statement
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        // define drop statement
        String query = "DROP TABLE IF EXISTS " + TABLE_SONG_VOTE;

        // execute drop statement that drops student table
        sqLiteDatabase.execSQL(query);

        onCreate(sqLiteDatabase);
    }

    public void like(String song, String voter) {

        // get reference to chc database
        SQLiteDatabase db = getWritableDatabase();

        // initialize a ContentValues object
        ContentValues values = new ContentValues();

        // put data into ContentValues object
        values.put(COLUMN_SONG_NAME, song);
        values.put(COLUMN_VOTER_NAME, voter);


        // insert data in ContentValues object into student table
        db.insert(TABLE_SONG_VOTE, null, values);

        // close database reference
        db.close();
    }

    public void dislike(String song, String voter) {

        // get reference to chc database
        SQLiteDatabase db = getWritableDatabase();

        // initialize a ContentValues object
        ContentValues values = new ContentValues();

        // put data into ContentValues object
        values.put(COLUMN_SONG_NAME, song);
        values.put(COLUMN_VOTER_NAME, voter);


        // insert data in ContentValues object into student table
        db.insert(TABLE_SONG_VOTE, null, values);

        // close database reference
        db.close();
    }

    public int count (String vote) {

        // get reference to chc database
        SQLiteDatabase db = getWritableDatabase();

        // define select statement
        String query = "SELECT * FROM " + TABLE_SONG_VOTE +
                " WHERE " + COLUMN_VOTE + " = " + "'" + vote + "'";


        // execute select statement and return count of rows in Cursor
        return db.rawQuery(query,  null).getCount();

    }

}
