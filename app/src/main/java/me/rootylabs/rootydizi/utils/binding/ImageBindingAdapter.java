package me.rootylabs.rootydizi.utils.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class ImageBindingAdapter {

    @BindingAdapter(value = {"imageUrl", "placeHolder"}, requireAll = false)
    public static void loadImage(ImageView imageView,
                                 String imageUrl,
                                 Drawable placeHolder) {

        if (imageUrl != null && !imageUrl.isEmpty()) {
            RequestCreator requestCreator =
                    Picasso.with(imageView.getContext())
                    .load(imageUrl)
                    .placeholder(placeHolder)
                    .centerCrop()
                    .fit();
            requestCreator.into(imageView);
        }
    }

    @BindingAdapter(value = {"url"})
    public static void loadOriginalImage(ImageView imageView, String imageUrl) {

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.with(imageView.getContext())
                    .load(imageUrl)
                    .into(imageView);
        }
    }


}
