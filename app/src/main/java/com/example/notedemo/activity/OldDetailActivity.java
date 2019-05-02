package com.example.notedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notedemo.R;
import com.example.notedemo.utils.Constant;


public class OldDetailActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_activity_detail);
        innit();

        Intent intent = getIntent();

        String nameDetail = intent.getStringExtra(Constant.mykey1);
        Toast.makeText(this, "Lấy dữ liệu từ: " + nameDetail, Toast.LENGTH_LONG).show();

        textView.setText(nameDetail);

    }

    private void innit() {
        textView = findViewById(R.id.tv_detail);
    }


}
