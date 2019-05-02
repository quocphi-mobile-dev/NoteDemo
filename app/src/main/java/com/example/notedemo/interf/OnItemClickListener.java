package com.example.notedemo.interf;

import com.example.notedemo.model.Note;

public interface OnItemClickListener {
    void onItemClick(Note note);
    void onItemDelete(int NoteID);
}
