package com.goreatudor.mytinder_1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.goreatudor.mytinder_1.R;
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
            String fname = txt_fname.getText().toString();
            String uname = txt_uname.getText().toString();
            String mail = txt_mail.getText().toString();

            int age = Integer.parseInt(txt_age.getText().toString());
            int pwd = Arrays.hashCode(txt_pwd.getText().toString().toCharArray());
            int cpwd = Arrays.hashCode(txt_cpwd.getText().toString().toCharArray());

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

            if (pwd != cpwd) {
                Toast.makeText(RegisterActivity.this, "passwords don't match", Toast.LENGTH_SHORT).show();

            } else {
                UserModel_ user = new UserModel_(uname, fname, mail, age, pwd, g);
            }
        });
    }
}