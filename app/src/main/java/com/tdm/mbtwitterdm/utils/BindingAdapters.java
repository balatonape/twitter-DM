package com.tdm.mbtwitterdm.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingAdapters {

    @BindingAdapter("thumbUrl")
    public static void setThumbUrl(final ImageView imageView, String url) {
        if (!url.isEmpty()) {
            Context context = imageView.getContext();
            Glide.with(context).load(url).into(imageView);
        }
    }
}
