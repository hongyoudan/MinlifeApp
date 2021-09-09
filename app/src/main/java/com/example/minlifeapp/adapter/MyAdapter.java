package com.example.minlifeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.minlifeapp.R;
import com.example.minlifeapp.entity.MerchantsEntity;
import com.example.minlifeapp.util.GlideUtil;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    Context context;
    Activity activity;
    public MyAdapter(Context context, Activity activity){
        this.context = context;
        this.activity = activity;
    }
    private ArrayList<MerchantsEntity> mDatas = new ArrayList<>();

    private View mHeaderView;

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void addDatas(ArrayList<MerchantsEntity> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override // viewtype 视图的类型 ,
    public int getItemViewType(int position) {
        if(mHeaderView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_merchants_layout, parent, false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        // 绑定对应的子 view item的相应数据
        if(getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(viewHolder);
        if(viewHolder instanceof Holder) {
            {

                MerchantsEntity merchantsEntity = mDatas.get(pos);

                MyAdapter.Holder holder = (MyAdapter.Holder) viewHolder;
                GlideUtil.load(context, activity, merchantsEntity.getMerchantsPicture(), holder.imvMerchantsPicture, 10);
                holder.tvMerchantsName.setText(merchantsEntity.getMerchantsName());
                holder.tvMerchantsAddress.setText(merchantsEntity.getMerchantsAddress());
                holder.tvMerchantsSendingFee.setText(merchantsEntity.getMerchantsSendingFee());
                holder.tvMerchantsMessage.setText(merchantsEntity.getMerchantsMessage());
                if(mListener == null) return;
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(pos,merchantsEntity);
                    }
                });
            }

        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text;
        public TextView tvMerchantsName, tvMerchantsAddress, tvMerchantsSendingFee, tvMerchantsMessage;
        public ImageView imvMerchantsPicture;
        View view;
        public Holder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView) return;
            view = itemView;
            imvMerchantsPicture=itemView.findViewById(R.id.imv_merchantsPicture);
            tvMerchantsName = itemView.findViewById(R.id.tv_name);
            tvMerchantsAddress = itemView.findViewById(R.id.tv_cate);
            tvMerchantsSendingFee = itemView.findViewById(R.id.tv_price);
            tvMerchantsMessage = itemView.findViewById(R.id.tv_username);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, MerchantsEntity merchantsEntity);
    }
}