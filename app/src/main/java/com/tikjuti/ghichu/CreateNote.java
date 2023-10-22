package com.tikjuti.ghichu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNote extends AppCompatActivity {

    ImageButton btnUndo;
    ImageButton btnConfirm;
    EditText txtTitle, txtContent;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);

        database = MainNote.database;
        btnUndo = findViewById(R.id.addUndoButton);
        btnConfirm = findViewById(R.id.addConfirmButton);
        txtTitle = findViewById(R.id.addNoteTitle);
        txtContent = findViewById(R.id.addNoteContent);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitle.getText().toString();
                String content = txtContent.getText().toString();
//                String sql = "INSERT INTO Notes VALUES (null, '" + title + "', '" + content + "')";
//                database.QueryData(sql);

                ContentValues values = new ContentValues();
                values.put("Title", title);
                values.put("Content", title);

                SQLiteDatabase db = database.getWritableDatabase();
                long newRowId = db.insert("Notes", null, values);

                if (newRowId != -1) {
                    Toast.makeText(CreateNote.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateNote.this, "Thêm bị lỗi", Toast.LENGTH_SHORT).show();
                }
                db.close();
                MainNote.getTitleData();
                Intent intent = new Intent(getApplicationContext(), MainNote.class);
                startActivity(intent);
            }
        });

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainNote.class);
                startActivity(intent);
            }
        });
    }
}
