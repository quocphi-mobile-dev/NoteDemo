package com.example.notedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notedemo.R;
import com.example.notedemo.dbcontext.RealmContext;
import com.example.notedemo.model.Note;
import com.example.notedemo.model.NoteUtils;
import com.example.notedemo.utils.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity  implements View.OnClickListener {
    EditText edtTitle;
    EditText edtDes;
    Button btnSave;
    Button btnCancel;
    Note currentNote;




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

        currentNote = (Note) getIntent().getSerializableExtra(Constant.update_key);

        edtTitle.setSelection(edtTitle.getText().length());

        edtTitle.setText(currentNote.getTitle());
        edtDes.setText(currentNote.getDescription());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_save:
                String title = edtTitle.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(UpdateActivity.this, " Title is emptry !",Toast.LENGTH_SHORT).show();
                }else {
                    currentNote.setTitle(edtTitle.getText().toString());
                    currentNote.setDescription(edtDes.getText().toString());
                    RealmContext.getInstance().updateNote(currentNote.getId(),currentNote);
                    Toast.makeText(UpdateActivity.this,"Update Successfuly ", Toast.LENGTH_SHORT).show();

                    finish();
                }
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
}
