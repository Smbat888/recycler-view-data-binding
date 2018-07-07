package com.example.smbat.develandooexample;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class UserViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding bindings;

    UserViewHolder(View itemView) {
        super(itemView);
        bindings = DataBindingUtil.bind(itemView);
    }
    ViewDataBinding getBindings(){
        return bindings;
    }
}
