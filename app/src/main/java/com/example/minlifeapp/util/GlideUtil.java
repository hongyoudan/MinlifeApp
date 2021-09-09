package com.example.minlifeapp.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.minlifeapp.R;
import com.example.minlifeapp.util.glide.GlideCircleTransform;
import com.example.minlifeapp.util.glide.GlideRoundTransform;

public class GlideUtil {

    private static GlideCircleTransform circleTransform;
    private static GlideRoundTransform roundTransform;

    private static GlideCircleTransform transform(Context context) {
        if (circleTransform == null) {
            circleTransform = new GlideCircleTransform(context);
        }
        return circleTransform;
    }

    private static GlideRoundTransform transform(Context context, int dp) {
        if (roundTransform == null) {
            roundTransform = new GlideRoundTransform(context, dp);
        }
        return roundTransform;
    }

    public static void load(Context context, Activity activity, String url, ImageView imageView, int dp) {
        Glide.with(activity).load(url == null ? "" : url).error(R.color.color_2c97De)
                .placeholder(R.color.color_2c97De)
                .transform(transform(context, dp))
                .into(imageView);
    }

    public static void load(Context context, Activity activity, String url, ImageView imageView) {
        Glide.with(activity).load(url == null ? "" : url).error(R.color.color_2c97De)
                .placeholder(R.color.color_2c97De)
                .transform(transform(context))
                .into(imageView);
    }

    public static void load(Activity activity, String url, ImageView imageView) {
        Glide.with(activity).load(url == null ? "" : url).error(R.color.color_2c97De)
                .placeholder(R.color.color_2c97De)
                .into(imageView);
    }

    public static void loadCategory(Activity activity, String url, ImageView imageView) {
        Glide.with(activity).load(url == null ? "" : url).error(Color.WHITE)
                .placeholder(Color.WHITE)
                .into(imageView);
    }
}
