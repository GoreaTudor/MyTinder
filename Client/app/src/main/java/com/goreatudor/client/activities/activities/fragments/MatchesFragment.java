package com.goreatudor.client.activities.activities.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goreatudor.client.R;
import com.goreatudor.client.activities.data.Gender;
import com.goreatudor.client.activities.data.User;
import com.goreatudor.client.activities.helper.MatchesAdapter;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;

    public MatchesFragment (Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        recyclerView = view.findViewById(R.id.matches_rv);

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("dummy1@gmail.com", 1111, "dummy1", 18, "male"));
        users.add(new User("dummy2@gmail.com", 2222, "dummy2", 19, "female"));
        users.add(new User("dummy3@gmail.com", 3333, "dummy3", 20, "other"));
        users.add(new User("dummy4@gmail.com", 4444, "dummy4", 21, "nope"));
        users.add(new User("dummy5@gmail.com", 5555, "dummy5", 22, "female"));

        MatchesAdapter adapter = new MatchesAdapter(context, users);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }
}