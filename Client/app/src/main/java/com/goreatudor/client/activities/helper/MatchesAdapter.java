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

public class MatchesAdapter extends RecyclerView.Adapter <MatchesAdapter.MatchesViewHolder>{

    Context context;
    ArrayList<User> users;

    public MatchesAdapter (Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row_matches, parent, false);
        return new MatchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {
        holder.txt_fname.setText(users.get(position).getFullName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder{

        TextView txt_fname;

        public MatchesViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_fname = itemView.findViewById(R.id.rv_matches_fname);
        }
    }
}
