package com.example.weatherapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

import com.bumptech.glide.Glide
import com.example.weatherapp.data.network.ApiEndPoint


class BindingUtils {
    companion object {
        @BindingAdapter("app:image")
        @JvmStatic
        fun bindImage(view: ImageView, image: String?) {
            if (image != null) {
                if (!image.startsWith("file"))
                    Glide.with(view.context).load(ApiEndPoint.iconURl + "/" + image + "@2x.png")
                        .into(view)
                else
                    Glide.with(view.context).load(image)
                        .into(view)
            }
        }
    }
}