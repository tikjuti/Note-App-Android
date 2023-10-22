package com.tikjuti.ghichu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoteDataSource {
    private SQLiteDatabase database;
    private Database dbHelper;

    public NoteDataSource(Context context) {
//        dbHelper = new Database(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createNote(String title, String content) {
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_TITLE, title);
        values.put(Database.COLUMN_CONTENT, content);
        database.insert(Database.TABLE_NOTES, null, values);
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        Cursor cursor = database.query(Database.TABLE_NOTES, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note note = cursorToNote(cursor);
            notes.add(note);
            cursor.moveToNext();
        }
        cursor.close();
        return notes;
    }

    public void updateNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_CONTENT, note.getContent());
        database.update(Database.TABLE_NOTES, values, Database.COLUMN_ID + " = ?", new String[] { String.valueOf(note.getId()) });
    }

    public void deleteNote(long noteId) {
        database.delete(Database.TABLE_NOTES, Database.COLUMN_ID + " = " + noteId, null);
    }

    @SuppressLint("Range")
    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getInt(cursor.getColumnIndex(Database.COLUMN_ID)));
        note.setTitle(cursor.getString(cursor.getColumnIndex(Database.COLUMN_TITLE)));
        note.setContent(cursor.getString(cursor.getColumnIndex(Database.COLUMN_CONTENT)));
        return note;
    }
}
