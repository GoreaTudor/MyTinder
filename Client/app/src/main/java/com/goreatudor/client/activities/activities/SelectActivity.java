package com.goreatudor.client.activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.goreatudor.client.R;
import com.goreatudor.client.activities.data.CurrentUser;

public class SelectActivity extends AppCompatActivity {

    SeekBar seekBar_ageMin;
    SeekBar seekBar_ageMax;
    TextView lbl_ageMin;
    TextView lbl_ageMax;
    Button btn_search;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Toast.makeText(this, CurrentUser.getInstance().toString(), Toast.LENGTH_LONG).show();

        seekBar_ageMin = findViewById(R.id.sel_seekBar_ageMin);
        seekBar_ageMax = findViewById(R.id.sel_seekBar_ageMax);
        lbl_ageMin = findViewById(R.id.sel_lbl_ageMin);
        lbl_ageMax = findViewById(R.id.sel_lbl_ageMax);
        btn_search = findViewById(R.id.sel_btn_search);

        seekBar_ageMin.setMin(18);
        seekBar_ageMax.setMin(18);

        seekBar_ageMin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lbl_ageMin.setText(String.valueOf(i));

                if(i > seekBar_ageMax.getProgress()) {
                    seekBar_ageMax.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_ageMax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lbl_ageMax.setText(String.valueOf(i));

                if(i < seekBar_ageMin.getProgress()) {
                    seekBar_ageMin.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_search.setOnClickListener(view -> {
            ;
        });
    }
}