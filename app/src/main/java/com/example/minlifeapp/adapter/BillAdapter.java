package com.example.minlifeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.example.minlifeapp.R;
import com.example.minlifeapp.entity.BillEntity;
import com.example.minlifeapp.util.GlideUtil;
import com.example.minlifeapp.util.SharedUtil;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static android.content.ContentValues.TAG;
import static com.example.minlifeapp.api.ApiConfig.BASE_URl;

//写一个用于展示item数据的适配器
public class BillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;//声明上一个下文对象
    private List<BillEntity> datas;
    Activity activity;
    //写一个构造器用于展示item列表
    public BillAdapter(Context context, Activity activity, List<BillEntity> datas) {
        this.mContext = context;
        this.datas = datas;
        this.activity = activity;
    }

    @NonNull
    //创建列表项的视图持有者
    //将写好的item布局引进来
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bill_layout, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==3){
                JSONObject jsonObject = JSONObject.parseObject((String) msg.obj);
                Log.d(TAG, "handleMessage: code" + jsonObject.getIntValue("code"));
                if(jsonObject.getString("code").equals("1") || jsonObject.getIntValue("code") == 1){
                    Toast.makeText(activity,"谢谢支持,请等候发货！",Toast.LENGTH_SHORT).show();

                }
            }
            if(msg.what==4){
                JSONObject jsonObject = JSONObject.parseObject((String) msg.obj);
                Log.d(TAG, "handleMessage: code" + jsonObject.getIntValue("code"));
                if(jsonObject.getString("code").equals("1") || jsonObject.getIntValue("code") == 1){
                    Toast.makeText(activity,"谢谢支持,订单已完成！",Toast.LENGTH_SHORT).show();

                }
            }
            if(msg.what==0){
                Toast.makeText(activity, "get cate failed", Toast.LENGTH_SHORT).show();
            }
        }
    };
    //绑定列表项的视图持有者
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        BillEntity billentity = datas.get(position);

        GlideUtil.load(mContext, activity, BASE_URl + billentity.getMerchantsPicture(), ((ViewHolder) holder).imvMerchantsPicture, 10);
        viewHolder.tvMerchantsName.setText(billentity.getMerchantsName());
        viewHolder.tvMerchantsSendingFee.setText(billentity.getMerchantsSendingFee());
        viewHolder.tvMerchantsMessage.setText(billentity.getMerchantsMessage());
        viewHolder.tv_cate.setText(billentity.getMerchantsAddress());
        if (viewHolder.tvMerchantsMessage.equals("待付款")) {
            viewHolder.ctrlbtn.setText("付款");
        }
        if (viewHolder.tvMerchantsMessage.equals("已发货")) {
            viewHolder.ctrlbtn.setText("完成");
        }
        if (viewHolder.tvMerchantsMessage.equals("已付款")) {
            viewHolder.ctrlbtn.setText("待发货");
        }
        viewHolder.ctrlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.tvMerchantsMessage.equals("待付款")) {
                    dopay(billentity.getId());
                }
                if (viewHolder.tvMerchantsMessage.equals("已发货")) {
                    finishbill(billentity.getId());
                }
            }
        });
    }

    void dopay (int id) {
        OkHttpClient client = new OkHttpClient();
        String url = "";
        String uname = SharedUtil.getIntance(activity).readShared("user","ddd");
        url= BASE_URl + "bill/paybill?id="+id;
        Log.d(TAG, "getProduct: " + url);
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message ms   = new Message();
                ms.what=0;
                handler.sendMessage(ms);
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();
                Log.d(TAG, "onResponse: add to bill "+res);
                Message ms   = new Message();
                ms.what=3;
                ms.obj = res;
                handler.sendMessage(ms);
            }
        });
    }


    void finishbill (int id) {
        OkHttpClient client = new OkHttpClient();
        String url = "";
        String uname = SharedUtil.getIntance(activity).readShared("user","ddd");
        url= BASE_URl + "bill/finish?id="+id;
        Log.d(TAG, "getProduct: " + url);
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message ms   = new Message();
                ms.what=0;
                handler.sendMessage(ms);
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();
                Log.d(TAG, "onResponse: add to bill "+res);
                Message ms   = new Message();
                ms.what=4;
                ms.obj = res;
                handler.sendMessage(ms);
            }
        });
    }
    //获取列表项的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //定义列表项的视图持有者
    //通过ViewHolder得到控件对象
    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMerchantsName, tvMerchantsSendingFee, tvMerchantsMessage,tv_cate;
        private ImageView imvMerchantsPicture;
        private Button ctrlbtn;
        View view;
        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            imvMerchantsPicture = view.findViewById(R.id.imgurl);
            tvMerchantsName = view.findViewById(R.id.tv_name);
            tvMerchantsSendingFee = view.findViewById(R.id.tv_price);
            tv_cate = view.findViewById(R.id.tv_cate);
            tvMerchantsMessage = view.findViewById(R.id.tv_info);
            ctrlbtn = view.findViewById(R.id.ctrlbtn);
        }
        public void setonclick (View.OnClickListener onClickListener){
            view.setOnClickListener(onClickListener);
        }
    }
}
