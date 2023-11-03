package com.tikjuti.ghichu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sqlite.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTES = "Notes";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_CONTENT = "Content";
    private static final String TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NOTES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " VARCHAR(255), " +
                    COLUMN_CONTENT + " TEXT " +
                    ");";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }
}
