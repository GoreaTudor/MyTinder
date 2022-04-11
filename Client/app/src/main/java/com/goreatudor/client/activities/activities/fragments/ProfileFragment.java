package com.goreatudor.client.activities.activities.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goreatudor.client.R;
import com.goreatudor.client.activities.data.CurrentUser;
import com.goreatudor.client.activities.data.User;
import com.goreatudor.client.activities.helper.ProfileAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;

    TextView txt_fname;
    TextView txt_age;
    TextView txt_gender;
    TextView txt_mail;

    public ProfileFragment (Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txt_fname = view.findViewById(R.id.profile_txt_fname);
        txt_age = view.findViewById(R.id.profile_txt_age);
        txt_gender = view.findViewById(R.id.profile_txt_gender);
        txt_mail = view.findViewById(R.id.profile_txt_mail);
        recyclerView = view.findViewById(R.id.profile_rv);

        txt_fname.setText(CurrentUser.getInstance().getFullName());
        txt_age.setText(String.valueOf(CurrentUser.getInstance().getAge()));
        txt_gender.setText(CurrentUser.getInstance().getGender().toString());
        txt_mail.setText(CurrentUser.getInstance().getMail());

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("dummy1@gmail.com", 1111, "dummy1", 18, "male"));
        users.add(new User("dummy2@gmail.com", 2222, "dummy2", 19, "female"));
        users.add(new User("dummy3@gmail.com", 3333, "dummy3", 20, "other"));
        users.add(new User("dummy4@gmail.com", 4444, "dummy4", 21, "nope"));
        users.add(new User("dummy5@gmail.com", 5555, "dummy5", 23, "male"));
        users.add(new User("dummy6@gmail.com", 6666, "dummy6", 24, "female"));
        users.add(new User("dummy7@gmail.com", 7777, "dummy7", 25, "other"));
        users.add(new User("dummy8@gmail.com", 8888, "dummy8", 26, "nope"));
        users.add(new User("dummy9@gmail.com", 9999, "dummy9", 27, "female"));

        ProfileAdapter adapter = new ProfileAdapter(context, users);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }
}