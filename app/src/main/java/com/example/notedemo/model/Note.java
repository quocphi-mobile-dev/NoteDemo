package com.example.notedemo.model;

import java.io.Serializable;

import io.realm.RealmObject;

public class Note extends RealmObject implements Serializable {
    private int id;
    private String title;
    private String description;
    private String createDate;

    public Note() {
    }

    public Note(int id, String title, String description, String createDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note(String title, String description, String createDate) {
        this.title = title;
        this.description = description;
        this.createDate = createDate;
    }
    public void copyFrom (Note note){
        this.setId(note.getId());
        this.setDescription(note.getDescription());
        this.setCreateDate(note.getCreateDate());
        this.setTitle(note.getTitle());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return title;
    }


}
