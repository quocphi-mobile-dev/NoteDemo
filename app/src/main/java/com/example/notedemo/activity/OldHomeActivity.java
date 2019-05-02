package com.example.notedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.notedemo.R;
import com.example.notedemo.model.Note;
import com.example.notedemo.utils.Constant;

import java.util.ArrayList;

public class OldHomeActivity extends AppCompatActivity {

    Button btnNext;

    ListView listView;
    ArrayAdapter<Note> adapter; // kết nối listView + array , quyết định xem design ntn
    ArrayList<Note> noteData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_activity_home);

        init();

        addListener();
    }

    private void addListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OldHomeActivity.this, noteData.get(position).getTitle(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(OldHomeActivity.this, OldDetailActivity.class);
                intent.putExtra(Constant.mykey1, noteData.get(position).getTitle());
                startActivity(intent);


            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OldHomeActivity.this, " ĐANG giữ " + noteData.get(position).getTitle(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

    private void init() {
        listView = findViewById(R.id.lv_notes);

        Intent intent = getIntent();
        String username = intent.getStringExtra(Constant.mykey);
        Toast.makeText(OldHomeActivity.this, "Chuyển sang Giao Diện DS Của Tk: " + username, Toast.LENGTH_SHORT).show();
        dumyData();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, noteData);
        listView.setAdapter(adapter);

    }

    private void dumyData() {
        noteData = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            noteData.add(new Note("Lession" + i, "Đây là lời mô tả " + i, "01/04/2019"+i));
        }
    }
}
