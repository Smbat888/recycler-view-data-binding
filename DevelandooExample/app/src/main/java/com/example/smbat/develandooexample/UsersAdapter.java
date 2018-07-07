package com.example.smbat.develandooexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private Context context;
    private List<User> usersList;

    UsersAdapter(Context context, List<User> categoryObjectList) {
        this.context = context;
        this.usersList = categoryObjectList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User categoryObject = usersList.get(position);
        holder.getBindings().setVariable(BR.user, categoryObject);
        holder.getBindings().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}