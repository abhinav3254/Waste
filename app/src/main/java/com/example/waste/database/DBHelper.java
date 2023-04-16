package com.example.waste.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "ONE";
    private String COLUMN_ID = "_id";
    private String COLUMN_TITLE = "_title";
    private String COLUMN_DATE = "_date";
    private String COLUMN_AMOUNT = "_amount";
    private String COLUMN_DESC = "_desc";
    private Context context;
    public DBHelper(@Nullable Context context) {
        super(context, "save.db", null, 3);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+
                " ("+COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TITLE + " TEXT, "+
                COLUMN_DATE + " TEXT, "+
                COLUMN_DESC + " TEXT, "+
                COLUMN_AMOUNT + " REAL); ";

        String query2 = "CREATE TABLE "+"PROFILE"+
                " ("+COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "NAME" + " TEXT); ";

        sqLiteDatabase.execSQL(query);

        sqLiteDatabase.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean addData(Pojo pojo) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE,pojo.getTitle());
        contentValues.put(COLUMN_DATE,pojo.getDate());
        contentValues.put(COLUMN_DESC,pojo.getDesc());
        contentValues.put(COLUMN_AMOUNT,pojo.getPrice());

        long res = database.insert(TABLE_NAME,null,contentValues);

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addProfile (ProfilePojo pojo) {
        String TABLE_NAME_1 = "NAME";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_NAME_1,pojo.getName());
        long res = database.insert("PROFILE",null,contentValues);

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor readProfile () {
        Cursor cursor = null;
        String query = "SELECT * FROM PROFILE";
        SQLiteDatabase database = this.getReadableDatabase();

        if (database!=null) {
            cursor = database.rawQuery(query,null);
        }

        return cursor;
    }

    public Cursor readAll () {
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        if (database!=null) {
            cursor = database.rawQuery(query,null);
        }
        return cursor;
    }

    public void deleteOne (Pojo pojo) {
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = "_id=?";
         String[] whereArgs = {pojo.getId()};
         database.delete(TABLE_NAME,whereClause,whereArgs);
    }
}
