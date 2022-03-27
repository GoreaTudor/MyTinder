package com.goreatudor.client.activities.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.goreatudor.client.R;
import com.goreatudor.client.activities.data.User;

import java.util.List;

public class MyItemAdapter extends ArrayAdapter<User> {

    public MyItemAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.swipe_item, parent, false);
        }

        TextView txt_fname = convertView.findViewById(R.id.swipe_txt_fname);
        TextView txt_gender = convertView.findViewById(R.id.swipe_txt_gender);
        TextView txt_age = convertView.findViewById(R.id.swipe_txt_age);

        txt_fname.setText(user.getFullName());
        txt_gender.setText(user.getGender().toString());
        txt_age.setText(String.valueOf(user.getAge()));

        return convertView;
    }
}
