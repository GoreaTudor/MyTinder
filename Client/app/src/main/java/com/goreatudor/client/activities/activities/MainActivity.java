package com.goreatudor.client.activities.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.goreatudor.client.R;
import com.goreatudor.client.activities.activities.fragments.HomeFragment;
import com.goreatudor.client.activities.activities.fragments.MatchesFragment;
import com.goreatudor.client.activities.activities.fragments.ProfileFragment;
import com.goreatudor.client.activities.data.User;
import com.goreatudor.client.activities.helper.GLOBAL;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Fragment fragment_home;
    Fragment fragment_matches;
    Fragment fragment_profile;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_home = new HomeFragment(this);
        fragment_matches = new MatchesFragment(this);
        fragment_profile = new ProfileFragment();

        setCurrentFragment(fragment_home);
        navigationView = findViewById(R.id.main_navBar);

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.main_item_home:
                    setCurrentFragment(fragment_home);
                    break;

                case R.id.main_item_matches:
                    setCurrentFragment(fragment_matches);
                    break;

                case R.id.main_item_profile:
                    setCurrentFragment(fragment_profile);
                    break;
            }
            return true;
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
    }
}