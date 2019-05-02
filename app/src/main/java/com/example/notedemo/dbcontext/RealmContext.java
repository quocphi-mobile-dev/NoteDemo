package com.example.notedemo.dbcontext;

import com.example.notedemo.model.Note;

import java.util.List;

import io.realm.Realm;

public class RealmContext {
    private Realm realm;
    private static RealmContext instance;
    private RealmContext(){
        realm = Realm.getDefaultInstance();

    }
    public static RealmContext getInstance(){
        if (instance == null){
            instance = new RealmContext();
        }
        return instance;
    }
    public List<Note> getAllNote(){
        return realm.copyFromRealm(realm.where(Note.class).findAll());
    }

    private Note getNoteById(int id){
        return realm.where(Note.class).equalTo("id",id).findFirst();
    }

    public void addNote(Note note){
        List<Note> noteList = getAllNote();
        if (noteList==null|noteList.isEmpty()){
            note.setId(0);
        }
        else {
            int lastID= noteList.get(noteList.size()-1).getId();
            note.setId(lastID+1);
        }

        realm.beginTransaction();
        Note newNote = realm.createObject(Note.class);
        newNote.copyFrom(note);
        realm.copyFromRealm(newNote);
        realm.commitTransaction();
    }
    public void deleteNote(int id){
        Note oldNote = getNoteById(id);

        if (oldNote==null) return;
        realm.beginTransaction();
        oldNote.deleteFromRealm();
        realm.commitTransaction();
    }
    public void updateNote(int oldID, Note newNote){
        Note oldNote = getNoteById(oldID);
        if(oldNote == null) return;
        realm.beginTransaction();
        oldNote.copyFrom(newNote);

        realm.commitTransaction();


    }

}
