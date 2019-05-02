package com.example.notedemo.model;

import java.util.ArrayList;

public class NoteUtils {
    private  static  NoteUtils instance;
    private ArrayList<Note> noteData;

    private NoteUtils() {
        noteData = new ArrayList<>();

    }

    public  static  NoteUtils getInstance() {
        if (instance == null) {
            instance = new NoteUtils();

        }
        return instance;
    }

    public void  addNote(Note note){
        note.setId(noteData.size());
        noteData.add(note);
    }

public void deleteNote(int noteID){
        for (int i  =0 ; i< noteData.size();i++){
            if (noteData.get(i).getId() == noteID){
                noteData.remove(i);
                return;
            }
        }
}
public void updateNote(int oldID, Note newNote){
        for (int i= 0; i < noteData.size();i++){
            if (noteData.get(i).getId() == oldID){
                noteData.set(i,newNote);
                return;
            }
        }
}
    public ArrayList<Note> getAllNote(){
        return noteData;
    }

}
