package com.example.sharepreference05102020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText mEdtUserName,mEdtPassWord;
    CheckBox mCbSaveMe;
    Button mBtnLogin;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mEdtUserName = findViewById(R.id.edittextUsername);
        mEdtPassWord = findViewById(R.id.edittextPassword);
        mCbSaveMe = findViewById(R.id.checkboxSave);
        mBtnLogin = findViewById(R.id.buttonLogin);

        mSharedPreferences = getSharedPreferences("Appcache", MODE_PRIVATE);

        boolean isSave = mSharedPreferences.getBoolean("issave",false);
        if (isSave){
            String username = mSharedPreferences.getString("username","");
            String password = mSharedPreferences.getString("password","");

            mEdtUserName.setText(username);
            mEdtPassWord.setText(password);
            mCbSaveMe.setChecked(true);
        }

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEdtUserName.getText().toString();
                String password = mEdtPassWord.getText().toString();

                if (username.equals("android0510") && password.equals("123456")){
                    mEditor = mSharedPreferences.edit();
                    if (mCbSaveMe.isChecked()){
                        mEditor.putString("username",username);
                        mEditor.putString("password",password);
                        mEditor.putBoolean("issave",true);
                    }else{
                        mEditor.clear();
                    }
                    mEditor.commit();
                    Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}