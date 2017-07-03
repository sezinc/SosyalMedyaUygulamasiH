package com.example.hlymcr.gelecegiyazanlar;

import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;




public class DBAdapter {

    private DatabaseHelper DBHelper;
    SQLiteDatabase db;
    private final Context context;
    public static final String KEY_ROWID = "id";
    public static final String KEY_ISIM = "isim";
    public static final String KEY_PASS = "parola";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_CINSIYET = "cinsiyet";
    public static final String KEY_TARIH = "tarih";
    public static final String KEY_URL = "url";
    private static final String TAG = "DBAdapter";


    private static final String DATABASE_NAME = "Veritabani1.db";

    private static final String DATABASE_TABLE = "kisiler";

    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_CREATE = "CREATE TABLE `kisiler` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `isim` TEXT NOT NULL, `parola` TEXT NOT NULL, `email` TEXT NOT NULL, `cinsiyet` TEXT NOT NULL, `tarih` TEXT NOT NULL, `url` TEXT NOT NULL );";


    // Constructor

    public DBAdapter(Context context) {

        this.context = context;

        DBHelper = new DatabaseHelper(context);

    }




    // To create and upgrade a database in an Android application SQLiteOpenHelper subclass is usually created

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }



        @Override

        public void onCreate(SQLiteDatabase db) {

            // onCreate() is called by the framework, if the database does not exist

            Log.d("Create", "Creating the database");



            try {

                db.execSQL(DATABASE_CREATE);

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        @Override

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // Sends a Warn log message

            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "

                    + newVersion + ", which will destroy all old data");



            // Method to execute an SQL statement directly

            db.execSQL("DROP TABLE IF EXISTS contacts");

            onCreate(db);


        }


    }

    // Opens the database

    public DBAdapter open() throws SQLException {

        // Create and/or open a database that will be used for reading and writing

        db = DBHelper.getWritableDatabase();

        // Use if you only want to read data from the database

        //db = DBHelper.getReadableDatabase();

        return this;

    }

    // Closes the database

    public void close() {

        // Closes the database

        DBHelper.close();

    }
    public Cursor getIsimler(int id) throws SQLException {
        // rawQuery() directly accepts an SQL select statement as input.
        // query() provides a structured interface for specifying the SQL query.
        // A query returns a Cursor object. A Cursor represents the result of a query
        //-- Bütün kişileri listeler.---


        //---TextWatcher arama tuşuna basmadan arama yapması--
        // and basically points to one row of the query result. This way Android can buffer
        // the query results efficiently; as it does not have to load all data into memory
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {

                KEY_ROWID,KEY_ISIM, KEY_PASS,KEY_EMAIL,KEY_CINSIYET,KEY_TARIH,KEY_URL}, KEY_ROWID+ " like '" + id +"%'", null, null, null, null, null);

        if (mCursor != null) {

            mCursor.moveToFirst();

        }

        return mCursor;

    }
    public String getSinlgeEntry(String userName)
    {

        //username in şifresini döndürür geriye
       Cursor cursor=db.query("kisiler", null, " isim=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("parola"));
        cursor.close();
        return password;


    }
    public String getUrl(String userName)
    {

        //username in url bilgisini döndürür.
        Cursor cursor=db.query("kisiler", null, " isim=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String url= cursor.getString(cursor.getColumnIndex("url"));
        cursor.close();
        return url;


    }
    public void insertKisi(Kisi k) {

        // The class ContentValues allows to define key/values. The "key" represents the

        // table column identifier and the "value" represents the content for the table

        // record in this column. ContentValues can be used for inserts and updates of database entries.

        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_ISIM, k.getIsim());
        initialValues.put(KEY_PASS, k.getPassword());
        initialValues.put(KEY_EMAIL, k.getEmail());
        initialValues.put(KEY_CINSIYET, k.getCinsiyet());
        initialValues.put(KEY_TARIH, k.getTarih());
        initialValues.put(KEY_URL, k.getUrl());
        db.insert(DATABASE_TABLE, null, initialValues);


    }

}