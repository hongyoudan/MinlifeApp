package com.example.minlifeapp.adapter;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.minlifeapp.util.GlideUtil;
import com.youth.banner.adapter.BannerAdapter;
import java.util.List;
import static android.content.ContentValues.TAG;
import static com.example.minlifeapp.api.ApiConfig.BASE_URl;

public class ImageAdapter extends BannerAdapter<String, ImageAdapter.BannerViewHolder> {

    Context context;
    Activity activity;
    public ImageAdapter(List<String> mDatas, Context context, Activity activity) {
        super(mDatas);
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        this.activity =activity;
        this.context =context;

    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, String data, int position, int size) {
//        holder.imageView.setImageResource(data);
        Log.d(TAG, "onBindView: "+BASE_URl+ data);
        GlideUtil.load(context,activity,BASE_URl+ data, holder.imageView,0);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}