package com.example.smbat.develandooexample;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CustomImageBinding {

    @BindingAdapter({"imageUrl"})
    public static void setImageViewResource(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}
