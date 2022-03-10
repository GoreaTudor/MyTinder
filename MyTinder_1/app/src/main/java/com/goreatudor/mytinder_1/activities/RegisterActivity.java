package com.goreatudor.mytinder_1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.goreatudor.mytinder_1.R;
import com.goreatudor.mytinder_1.data.UserDataChecker;
import com.goreatudor.mytinder_1.data.UserModel_;
import com.goreatudor.mytinder_1.data.UserModel_.Gender;

import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setup();
    }

    private void setup() {
        EditText txt_fname = findViewById(R.id.reg_textBox_fname);
        EditText txt_mail = findViewById(R.id.reg_textBox_mail);
        EditText txt_age = findViewById(R.id.reg_textBox_age);
        EditText txt_uname = findViewById(R.id.reg_textBox_uname);
        EditText txt_pwd = findViewById(R.id.reg_textBox_pwd);
        EditText txt_cpwd = findViewById(R.id.reg_textBox_cpwd);

        RadioButton radio_male = findViewById(R.id.reg_radio_male);
        RadioButton radio_female = findViewById(R.id.reg_radio_female);
        RadioButton radio_other = findViewById(R.id.reg_radio_other);

        Button btn_register = findViewById(R.id.reg_btn_register);

        btn_register.setOnClickListener(view -> {
            /// GET DATA ///
            String fname = txt_fname.getText().toString();
            String uname = txt_uname.getText().toString();
            String mail = txt_mail.getText().toString();

            String age_s = txt_age.getText().toString();
            String pwd_s = txt_pwd.getText().toString();
            String cpwd_s = txt_cpwd.getText().toString();

            Gender g;
            if (radio_male.isChecked()) {
                g = Gender.male;
            } else if (radio_female.isChecked()) {
                g = Gender.female;
            } else if (radio_other.isChecked()) {
                g = Gender.other;
            } else {
                g = Gender.nope;
            }

            /// Check data ///
            if (!UserDataChecker.checkFullname(fname)) {
                Toast.makeText(RegisterActivity.this, "field FullName is empty", Toast.LENGTH_SHORT).show();

            } else if (!UserDataChecker.checkMail(mail)) {
                Toast.makeText(RegisterActivity.this, "field Mail is empty or invalid", Toast.LENGTH_SHORT).show();

            } else if (!UserDataChecker.checkAge(age_s)) {
                Toast.makeText(RegisterActivity.this, "field Age is empty or invalid", Toast.LENGTH_SHORT).show();

            } else if (!UserDataChecker.checkUsername(uname)) {
                Toast.makeText(RegisterActivity.this, "field Username is empty", Toast.LENGTH_SHORT).show();

            } else if (!UserDataChecker.checkPassword(pwd_s)) {
                Toast.makeText(RegisterActivity.this, "field Password is empty", Toast.LENGTH_SHORT).show();

            } else if (!UserDataChecker.checkPassword(cpwd_s)) {
                Toast.makeText(RegisterActivity.this, "field Check Password is empty", Toast.LENGTH_SHORT).show();

            } else if (!pwd_s.equals(cpwd_s)) {
                Toast.makeText(RegisterActivity.this, "passwords don't match", Toast.LENGTH_SHORT).show();

            } else {
                int age = Integer.parseInt(age_s);
                int pwd = Arrays.hashCode(pwd_s.toCharArray());

                UserModel_ user = new UserModel_(uname, fname, mail, age, pwd, g);
                Log.i("New User", user.toString());
                Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}