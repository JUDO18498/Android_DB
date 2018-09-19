package com.example.admin.coontactdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "phonebook";
    private static final String TABLE_NAME = "labels";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_No = "no";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " + COLUMN_No + " TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db,int OldVersion,int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void insertLabel(String label,String label2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, label);
        values.put(COLUMN_No, label2);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public List<String> getAllLabels() {
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
    }
        public List<String> getNo(int id )
        {
            List<String> li = new ArrayList<String>();
            String selectquery = "SELECT * FROM " + TABLE_NAME;
            SQLiteDatabase dbb = this.getReadableDatabase();
            Cursor cursor1 = dbb.rawQuery(selectquery, null);
            if (cursor1.moveToFirst()) {
                do {
                    li.add(cursor1.getString(1));
                    li.add(cursor1.getString(2));
                } while (cursor1.moveToNext());
            }
        cursor.close();
        db.close();

        return list;
    }



