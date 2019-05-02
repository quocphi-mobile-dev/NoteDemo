package com.example.notedemo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notedemo.R;
import com.example.notedemo.adapter.NoteAdapter;

import com.example.notedemo.dbcontext.RealmContext;
import com.example.notedemo.interf.OnItemClickListener;
import com.example.notedemo.model.Note;
import com.example.notedemo.utils.Constant;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements OnItemClickListener {
    RecyclerView recyclerView;

    TextView tvTitle;
    TextView tvDes;
    TextView tvDate;
    NoteAdapter noteAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        addListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //  AdapterView.AdapterContextMenuInfo info  = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.item_1:
                Toast.makeText(HomeActivity.this, "Chức năng này chưa làm!", Toast.LENGTH_SHORT).show();
                // Gọi màn hình AditionSportActivity
            case R.id.item_2:
                Toast.makeText(HomeActivity.this, "Chức năng này chưa làm!", Toast.LENGTH_SHORT).show();
            case R.id.item_Logout:
                finish();
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.updateDate(RealmContext.getInstance().getAllNote());
    }

    private void addListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }


    private void init() {

        tvDate = findViewById(R.id.tv_dateTime);
        recyclerView = findViewById(R.id.rv_notes);
        tvTitle = findViewById(R.id.tv_title);
        tvDes = findViewById(R.id.tv_Description);
        fab = findViewById(R.id.fab);
        noteAdapter = new NoteAdapter(this, RealmContext.getInstance().getAllNote(), this);
        recyclerView.setAdapter(noteAdapter);


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(linearLayoutManager);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }


    @Override
    public void onItemClick(Note note) {
        Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);
        intent.putExtra(Constant.update_key, note);
        startActivity(intent);

    }

    @Override
    public void onItemDelete(int noteID) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("My Note");
        builder.setMessage("Bạn có muốn xóa bản ghi này không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ứ chịu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(HomeActivity.this, "Bản nghi vẫn đang được lưu", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RealmContext.getInstance().deleteNote(noteID);
                noteAdapter.updateDate(RealmContext.getInstance().getAllNote());
                // dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}


