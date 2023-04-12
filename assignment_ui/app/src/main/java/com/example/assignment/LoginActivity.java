package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //anh xa
        TextInputLayout input1 = findViewById(R.id.textInput1);
        TextInputLayout input2 = findViewById(R.id.textInput2);
        TextInputEditText edtUser = findViewById(R.id.edtUserName);
        TextInputEditText edtPass = findViewById(R.id.edtPassWord);
        CheckBox chkRemember = findViewById(R.id.chkRemember);
        Button btnLogin = findViewById(R.id.btnLogin);


        //check dieu kien
        edtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    edtUser.setError("Bạn phải nhập Username");
                }else{
                    edtUser.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    edtPass.setError("Bạn phải nhập Password");
                }else{
                    edtPass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //check gia tri Remember
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("isRemember", false);
        if(isRemember){
           // startActivity(new Intent(LoginActivity.this, MainActivity.class));
            String user = sharedPreferences.getString("user", "");
            String pass = sharedPreferences.getString("pass", "");
            edtUser.setText(user);
            edtPass.setText(pass);
            chkRemember.setChecked(isRemember);
        }
        //xu ly login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();

                if(user.length() >0 && pass.length()> 0){
                    //xu ly tiep tuc
                    if(user.equals("admin") && pass.equals("123")){
                        //dang nhap thanh cong
                        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isRemember", chkRemember.isChecked());
                        editor.putString("user", user);
                        editor.putString("pass", pass);
                        editor.apply();
                        //chuyen man hinh
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "User or password empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static class RegisterActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
        }
    }
}