package com.goreatudor.client.activities.activities.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goreatudor.client.R;
import com.goreatudor.client.activities.data.User;
import com.goreatudor.client.activities.helper.GLOBAL;
import com.goreatudor.client.activities.helper.MyItemAdapter;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private Context context;
    private ArrayList<User> list;
    private MyItemAdapter adapter;
    private SwipeFlingAdapterView swipeFlingAdapterView;

    public HomeFragment (Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = (ArrayList<User>) GLOBAL.usersList.clone();

        adapter = new MyItemAdapter(context, R.layout.swipe_item, list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        swipeFlingAdapterView = view.findViewById(R.id.home_swipeCards);
        swipeFlingAdapterView.setAdapter(adapter);

        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            User user;

            @Override
            public void removeFirstObjectInAdapter() {
                user = list.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                Log.d("SWIPE", "Liked " + user.getFullName());
            }

            @Override
            public void onRightCardExit(Object o) {
                Log.d("SWIPE", "Ignored " + user.getFullName());
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                ;
            }

            @Override
            public void onScroll(float v) {
                ;
            }
        });

        return view;
    }
}