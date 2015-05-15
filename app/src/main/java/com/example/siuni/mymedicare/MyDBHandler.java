package com.example.siuni.mymedicare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by SiUni on 23/04/2015.
 */

public class MyDBHandler {

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "_firstname";
    public static final String COLUMN_SURNAME = "_surname";
    public static final String COLUMN_TELEPHONENO = "_telephoneno";
    public static final String COLUMN_POSTCODE = "_postcode";
    private static final String TAG = "MyDBHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mymedicare.db";
    private static final String TABLE_DATABASEHANDLER = "Databasehandler";
    private static final String DATABASE_CREATE =
            "create table Databasehandler (_id integer primary key autoincrement, " +
                    "name text not null, surname not null, telephoneno notnull, postcode not null);";
    public final Context context;
    private DatabaseHandler DBHandler;
    public SQLiteDatabase db;

    public MyDBHandler(Context ctx) {
        this.context = ctx;
        DBHandler = new DatabaseHandler(context);
    }

    private static class DatabaseHandler extends SQLiteOpenHelper {
        DatabaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL("CREATE TABLE " + TABLE_DATABASEHANDLER +" ("+COLUMN_ID+
                        "INTEGER NOT NULL AUTOINCREMENT, "+COLUMN_FIRSTNAME +" varchar NOT NULL, "
                        + COLUMN_SURNAME +" varchar NOT NULL," +COLUMN_POSTCODE +
                        " varchar NOT NULL," +COLUMN_TELEPHONENO+" long NOT NULL");
                } catch (android.database.SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }

    }


    public MyDBHandler open() throws SQLException {
        db = DBHandler.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHandler.close();
    }

    public long insertContact(String Firstname, String Surname, String Telephone,
                              String Postcode) {

        ContentValues insertValues = new ContentValues();
        insertValues.put(COLUMN_FIRSTNAME, Firstname);
        insertValues.put(COLUMN_SURNAME, Surname);
        insertValues.put(COLUMN_TELEPHONENO, Telephone);
        insertValues.put(COLUMN_POSTCODE, Postcode);
        return db.insert(TABLE_DATABASEHANDLER, null, insertValues);
    }

    public Cursor getAllContacts ()
    {
        return db.query(TABLE_DATABASEHANDLER, new String [] {COLUMN_ID, COLUMN_FIRSTNAME,
    COLUMN_SURNAME}, null, null, null, null, null);
    }

    public Cursor getContact (long rowId) throws android.database.SQLException
    {
        Cursor mCursor =
                db.query(true, TABLE_DATABASEHANDLER, new String[] {COLUMN_ID, COLUMN_FIRSTNAME,
                COLUMN_SURNAME}, COLUMN_ID + "=" + rowId, null, null,null,null,null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return  mCursor;
    }

}





