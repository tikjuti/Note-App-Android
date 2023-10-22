package com.tikjuti.ghichu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private MainNote context;

    private int layout;
    private List<Note> noteList;

    public NoteAdapter(MainNote context, int layout, List<Note> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class  ViewHolder{
        TextView txtTextView;
        ImageButton imgEdit, imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtTextView = (TextView) view.findViewById(R.id.txtTextView);
            viewHolder.imgDelete = (ImageButton) view.findViewById(R.id.btnDeleteButton);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Note note = noteList.get(i);
        viewHolder.txtTextView.setText(note.getTitle());

//        Bắt sự kiện xóa
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.dialogDelete(note.getId());
            }
        });

        viewHolder.txtTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.updateNote(note.getId(), note.getTitle(), note.getContent());
            }
        });

        return view;
    }
}
