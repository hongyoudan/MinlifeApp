    package com.example.minlifeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.minlifeapp.DetailActivity;
import com.example.minlifeapp.R;
import com.example.minlifeapp.entity.MerchantsEntity;
import com.example.minlifeapp.util.GlideUtil;

import java.util.List;

//写一个用于展示item数据的适配器
public class MerchantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;//声明上一个下文对象
    private List<MerchantsEntity> datas;
    Activity activity;
    private static View mHeaderView;
    //写一个构造器用于展示item列表
    public MerchantsAdapter(Context context, Activity activity, List<MerchantsEntity> datas) {
        this.mContext = context;
        this.datas = datas;
        this.activity = activity;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getHeaderView() {
        return mHeaderView;
    }

    @NonNull
    //创建列表项的视图持有者
    //将写好的item布局引进来
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(mHeaderView != null &&  viewType == 1)  return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_merchants_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //绑定列表项的视图持有者
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 1) return;
        if(holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            final int pos = getRealPosition(viewHolder);

            MerchantsEntity merchantsEntity = datas.get(pos);

            GlideUtil.load(mContext, activity, merchantsEntity.getMerchantsPicture(), viewHolder.imvMerchantsPicture,10);
//        viewHolder.imvMerchantsPicture.setImageResource(R.mipmap.imv_luckincoffee);
            viewHolder.tvMerchantsName.setText(merchantsEntity.getMerchantsName());
            viewHolder.tvMerchantsAddress.setText(merchantsEntity.getMerchantsAddress());
            viewHolder.tvMerchantsSendingFee.setText(merchantsEntity.getMerchantsSendingFee());
            viewHolder.tvMerchantsMessage.setText(merchantsEntity.getMerchantsMessage());
            viewHolder.setonclick(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, DetailActivity.class);
                    intent.putExtra("id", merchantsEntity.getId());
                    activity.startActivity(intent);
                }
            });
        }
    }

    //获取列表项的数量
    @Override
    public int getItemCount() {
        return mHeaderView == null ? datas.size() : datas.size() + 1;
    }
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return 1;
        else return 0;
    }

    //定义列表项的视图持有者
    //通过ViewHolder得到控件对象
    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMerchantsName, tvMerchantsAddress, tvMerchantsSendingFee, tvMerchantsMessage;
        private ImageView imvMerchantsPicture;
        View view;
        public ViewHolder(@NonNull View view) {
            super(view);
            if(itemView == mHeaderView) return;
            this.view = view;
            imvMerchantsPicture=view.findViewById(R.id.imv_merchantsPicture);
            tvMerchantsName = view.findViewById(R.id.tv_name);
            tvMerchantsAddress = view.findViewById(R.id.tv_cate);
            tvMerchantsSendingFee = view.findViewById(R.id.tv_price);
            tvMerchantsMessage = view.findViewById(R.id.tv_username);
        }
        public void setonclick (View.OnClickListener onClickListener){
            view.setOnClickListener(onClickListener);
        }
    }
}
