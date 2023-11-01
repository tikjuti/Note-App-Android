package com.tikjuti.ghichu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainNote extends AppCompatActivity {

    NoteDataSource dataSource;
    ListView listNote;
    static ArrayList<Note> arrayNote;
    static NoteAdapter adapter;
    ImageButton btnAddNote;
    static Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_note);

        database = new Database(this, "sqlite.db", null, 1);
////      Tạo bảng công việc
        database.QueryData("CREATE TABLE IF NOT EXISTS Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, Title VARCHAR(255), Content TEXT)");

        btnAddNote = (ImageButton) findViewById(R.id.addNoteButton);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateNote.class);
                startActivity(intent);
            }
        });

        listNote = (ListView) findViewById(R.id.listViewNote);
        arrayNote = new ArrayList<>();

        adapter = new NoteAdapter(this, R.layout.line_note, arrayNote);
        listNote.setAdapter(adapter);
////
////      Khởi tạo database
//        dataSource = new NoteDataSource(this);
//        dataSource.open();

//////      insert data
//        dataSource.createNote("Ghi chú 1", "Học bài đi");
////
//        arrayNote = (ArrayList<Note>) dataSource.getAllNotes();

//        String sql = "INSERT INTO Notes VALUES (null, 'Ghi chú', 'Content')";
//        database.QueryData(sql);
        getTitleData();
    }

    public static void getTitleData() {
        Cursor dataTitle = database.GetData("SELECT * FROM Notes");
        arrayNote.clear();
        while (dataTitle.moveToNext()) {
            String title = dataTitle.getString(1);
            String content = dataTitle.getString(2);
            int id = dataTitle.getInt(0);
            arrayNote.add(new Note(id, title, content));
        }
        adapter.notifyDataSetChanged();
    }

    public void dialogDelete(int id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Bạn chắc muốn xóa?");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                database.QueryData("DELETE FROM Notes WHERE Id = '"+id+"'");

                SQLiteDatabase db = database.getWritableDatabase();
                db.delete("Notes", "Id = ?", new String[]{String.valueOf(id)});
                Toast.makeText(MainNote.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                getTitleData();
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    public void updateNote(int id, String title, String content) {
        Intent iGetContactInfo = new Intent(getApplicationContext(), UpdateNote.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("title", title);
        bundle.putString("content", content);
        iGetContactInfo.putExtras(bundle);

        startActivity(iGetContactInfo);
    }
}