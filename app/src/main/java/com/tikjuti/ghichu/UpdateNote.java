package com.tikjuti.ghichu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateNote extends AppCompatActivity {

    ImageButton btnUndo;
    Button btnConfirm;
    EditText txtTitle, txtContent;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_note);

        database = MainNote.database;
        btnUndo = findViewById(R.id.editUndoButton);
        btnConfirm = findViewById(R.id.editConfirmButton);
        txtTitle = findViewById(R.id.editNoteTitle);
        txtContent = findViewById(R.id.editNoteContent);
//
        Bundle bundle = getIntent().getExtras();

        int id = bundle.getInt("id");
        String title = bundle.getString("title");
        String content = bundle.getString("content");

        txtTitle.setText(title);
        txtContent.setText(content);
//
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle = txtTitle.getText().toString();
                String newContent = txtContent.getText().toString();
                if (newTitle.equals("") || newContent.equals("")) {
                    Toast.makeText(UpdateNote.this, "Vui lòng nhập đầy đủ các mục", Toast.LENGTH_SHORT).show();
                    return; // Add this line to prevent data insertion when title or content is empty
                }
//                String sql = "UPDATE Notes SET Title = '"+newTitle+"', Content = '"+newContent+"' WHERE Id = '"+id+"'";
//                database.QueryData(sql);
                ContentValues values = new ContentValues();
                values.put("Title", newTitle);
                values.put("Content", newContent);

                SQLiteDatabase db = database.getWritableDatabase();
                db.update("Notes", values, "Id=?", new String[]{String.valueOf(id)});
                db.close();

                Toast.makeText(UpdateNote.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
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
