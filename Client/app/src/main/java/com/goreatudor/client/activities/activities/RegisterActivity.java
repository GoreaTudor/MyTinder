package com.goreatudor.client.activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.goreatudor.client.R;
import com.goreatudor.client.activities.data.Gender;
import com.goreatudor.client.activities.data.User;
import com.goreatudor.client.activities.helper.DataChecker;
import com.goreatudor.client.activities.threads.RegistrationThread;

import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {

    EditText txt_mail;
    EditText txt_pwd;
    EditText txt_cpwd;
    EditText txt_fname;
    EditText txt_age;

    RadioButton radio_male;
    RadioButton radio_female;
    RadioButton radio_other;

    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setup();
    }

    private void setup() {
        txt_mail = findViewById(R.id.reg_textBox_mail);
        txt_pwd = findViewById(R.id.reg_textBox_pwd);
        txt_cpwd = findViewById(R.id.reg_textBox_cpwd);
        txt_fname = findViewById(R.id.reg_textBox_fname);
        txt_age = findViewById(R.id.reg_textBox_age);

        radio_male = findViewById(R.id.reg_radio_male);
        radio_female = findViewById(R.id.reg_radio_female);
        radio_other = findViewById(R.id.reg_radio_other);

        btn_register = findViewById(R.id.reg_btn_register);

        btn_register.setOnClickListener(view -> {
            String mail = txt_mail.getText().toString();
            String pwd = txt_pwd.getText().toString();
            String cpwd = txt_cpwd.getText().toString();
            String fullName = txt_fname.getText().toString();
            String age = txt_age.getText().toString();

            String gender;
            if (radio_male.isChecked()) {
                gender = "male";
            } else if (radio_female.isChecked()) {
                gender = "female";
            } else if (radio_other.isChecked()) {
                gender = "other";
            } else {
                gender = "nope";
            }

            if (!DataChecker.checkMail(mail)) {
                Toast.makeText(RegisterActivity.this, "Mail field empty or invalid", Toast.LENGTH_SHORT).show();

            } else if (!DataChecker.checkPwd(pwd)) {
                Toast.makeText(RegisterActivity.this, "Password field empty or invalid", Toast.LENGTH_SHORT).show();

            } else if (!DataChecker.checkPwd(cpwd)) {
                Toast.makeText(RegisterActivity.this, "Confirm password field empty or invalid", Toast.LENGTH_SHORT).show();

            } else if (!DataChecker.checkPasswords(pwd, cpwd)) {
                Toast.makeText(RegisterActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();

            } else if (!DataChecker.checkFullName(fullName)) {
                Toast.makeText(RegisterActivity.this, "FullName field empty or invalid", Toast.LENGTH_SHORT).show();

            } else if (!DataChecker.checkAge(age)) {
                Toast.makeText(RegisterActivity.this, "Age field empty or invalid", Toast.LENGTH_SHORT).show();

            } else {
                int pwd_i = Arrays.hashCode(pwd.toCharArray());
                int age_i = Integer.parseInt(age);

                User user = new User(mail, pwd_i, fullName, age_i, gender);
                Toast.makeText(RegisterActivity.this, "Processing...", Toast.LENGTH_SHORT).show();
                Log.d("New Account", "User{" + user.toString() + "}");

                Handler handler = new Handler();

                new RegistrationThread(user, handler, RegisterActivity.this).start();
            }
        });
    }


}