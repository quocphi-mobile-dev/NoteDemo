package com.example.notedemo.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notedemo.R;
import com.example.notedemo.interf.OnItemClickListener;
import com.example.notedemo.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private List<Note> noteData;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public NoteAdapter(OnItemClickListener onItemClickListener, List<Note> noteData, Context context) {
        this.onItemClickListener = onItemClickListener;
        this.noteData = noteData;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // tao 1 cai View tu 1 cai layout, LayoutInflater dung de cang cai view
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_note, viewGroup, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.bindView(noteData.get(i));
    }


    @Override
    // tra ve so luong . size
    public int getItemCount() {
        return noteData.size();
    }
    public void updateDate(List<Note> noteList){
        noteData.clear();
        noteData.addAll(noteList);
        this.notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDes;
        TextView txtDate;
        Note note;
        ImageView ivDelete;
        Dialog dialog;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDes = itemView.findViewById(R.id.tv_Description);
            txtTitle = itemView.findViewById(R.id.tv_title);
            txtDate = itemView.findViewById(R.id.tv_dateTime);
            ivDelete = itemView.findViewById(R.id.iv_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(note);
                }
            });
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    onItemClickListener.onItemDelete(note.getId());
                }
            });
        }

        public void bindView(Note note) {
            this.note = note;
            txtTitle.setText(note.getTitle());
            txtDes.setText(note.getDescription());
            txtDate.setText(note.getCreateDate());
        }

    }


    }


