package com.goreatudor.client.activities.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.goreatudor.client.R;
import com.goreatudor.client.activities.threads.LogInThread;

import java.util.Arrays;

public class LogInActivity extends AppCompatActivity {

    EditText txt_mail;
    EditText txt_pwd;
    TextView lbl_reg;
    Button btn_login;

    Button demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        txt_mail = findViewById(R.id.login_textBox_mail);
        txt_pwd = findViewById(R.id.login_textBox_pwd);
        lbl_reg = findViewById(R.id.login_lbl_reg);
        btn_login = findViewById(R.id.login_btn_login);

        btn_login.setOnClickListener(view -> {
            String mail = txt_mail.getText().toString();
            int pwd = Arrays.hashCode(txt_pwd.getText().toString().toCharArray());
            Handler handler = new Handler();

            new LogInThread(mail, pwd, this, handler, () -> {
                Intent intent = new Intent(LogInActivity.this, SelectActivity.class);
                startActivity(intent);
            }).start();
        });

        lbl_reg.setOnClickListener(view -> {
            Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
            startActivity(intent);
        });


        demo = findViewById(R.id.DEMO1);
        demo.setOnClickListener(view -> {
            Intent intent = new Intent(this, SelectActivity.class);
            startActivity(intent);
        });
    }
}