package com.example.waste.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBProfile extends SQLiteOpenHelper {

    private String COLUMN_ID = "id";
    private String COLUMN_TITLE = "name";
    private String TABLE_NAME = "profile";

    private Context context;

    public DBProfile(@Nullable Context context) {
        super(context, "profile.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+
                " ("+COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TITLE + " TEXT); ";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addProfile(ProfilePojo pojo) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE,pojo.getName());

        long res = database.insert(TABLE_NAME,null,contentValues);

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor readProfile () {
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        if (database!=null) {
            cursor = database.rawQuery(query,null);
        }
        return cursor;
    }
}
