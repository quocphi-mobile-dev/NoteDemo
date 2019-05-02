package com.example.notedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notedemo.R;
import com.example.notedemo.dbcontext.RealmContext;
import com.example.notedemo.interf.OnItemClickListener;
import com.example.notedemo.model.Note;
import com.example.notedemo.model.NoteUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtTitle;
    EditText edtDes;
    Button btnSave;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        addListener();

    }

    private void addListener() {
        btnSave.setOnClickListener(this);
    }

    private void init() {
        edtTitle = findViewById(R.id.edt_title);
        edtDes = findViewById(R.id.edt_description);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSave = findViewById(R.id.btn_save);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_save:
                String title = edtTitle.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(AddActivity.this, " Title is emptry !",Toast.LENGTH_SHORT).show();
                }else {
                    DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();


                    Note note = new Note(title, edtDes.getText().toString(), dateFormat.format(date));
                    RealmContext.getInstance().addNote(note);
                    Toast.makeText(AddActivity.this,"Add Note Successfully",Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case R.id.btn_cancel:
                finish();
            break;
        }

    }
}
