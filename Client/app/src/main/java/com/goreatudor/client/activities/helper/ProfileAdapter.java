package com.goreatudor.client.activities.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goreatudor.client.R;
import com.goreatudor.client.activities.data.User;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter <ProfileAdapter.ProfileViewHolder> {

    Context context;
    ArrayList<User> users;

    public ProfileAdapter (Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row_profile, parent, false);
        return new ProfileAdapter.ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        holder.txt_fname.setText(users.get(position).getFullName());
        holder.txt_age.setText(String.valueOf(users.get(position).getAge()));
        holder.txt_gender.setText(users.get(position).getGender().toString());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{

        TextView txt_fname;
        TextView txt_age;
        TextView txt_gender;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_fname = itemView.findViewById(R.id.rv_profile_fname);
            txt_age = itemView.findViewById(R.id.rv_profile_age);
            txt_gender = itemView.findViewById(R.id.rv_profile_gender);
        }
    }
}
