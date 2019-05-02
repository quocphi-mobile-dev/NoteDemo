package com.example.notedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notedemo.R;
import com.example.notedemo.utils.Constant;
import com.example.notedemo.utils.PreferenceUtils;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUsername;
    EditText edtPasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        addlistener();


        String username = PreferenceUtils.getInstance(this).getUsername();
                if(!username.isEmpty()){
                    goToHomeActivity();
                }
    }

    private void init() {
        btnLogin = findViewById(R.id.btn_login);
        edtUsername = findViewById(R.id.edt_username);
        edtPasword = findViewById(R.id.edt_password);
    }

    private void addlistener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCorrect = checkLogin(edtUsername.getText().toString(),edtPasword.getText().toString());
                if(isCorrect){


                    PreferenceUtils.getInstance(LoginActivity.this).addOrUpdateUsername(edtUsername.getText().toString());
                    goToHomeActivity();


                } else {
                    Toast.makeText(LoginActivity.this,"Tai khoản hoặc mk k đúng !",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean checkLogin(String Username, String Password){
        if(Username.equals(Constant.USER_NAME)&& Password.equals(Constant.PASSWORD)){
            return true;
        }else return false;
    }


    private  void goToHomeActivity(){
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra(Constant.mykey, edtUsername.getText().toString());
        startActivity(intent);
        // sau khi load đến HomeActivity thì nó end  => khi back lại thì k ở giao diện này nữa !
        finish();

    }

}
