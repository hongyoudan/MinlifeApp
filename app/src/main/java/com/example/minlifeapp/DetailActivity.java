package com.example.minlifeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.minlifeapp.entity.MerchantsEntity;
import com.example.minlifeapp.util.BaseActivity;
import com.example.minlifeapp.util.GlideUtil;
import com.example.minlifeapp.util.SharedUtil;
import com.example.minlifeapp.util.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static android.content.ContentValues.TAG;
import static com.example.minlifeapp.api.ApiConfig.BASE_URl;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.price)
    TextView tvPrice;
    @BindView(R.id.cate)
    TextView cate;
    @BindView(R.id.btn_buyer)
    TextView buy;

    @BindView(R.id.totalprice)
    TextView totalprice;
    private ImageView imvMinusDetailAct, imvAddDetailAct;
    private EditText etCountDetailAct;
    private String price;
    private String productname;
    private String billid = "";
    private Activity activity;
    private Context context;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 3) {
                JSONObject jsonObject = JSONObject.parseObject((String) msg.obj);
                Log.d(TAG, "handleMessage: code" + jsonObject.getIntValue("code"));
                if (jsonObject.getString("code").equals("1") || jsonObject.getIntValue("code") == 1) {
                    showToast("谢谢支持,外卖小哥正飞奔而来！");
//                    Toast.makeText(activity, "谢谢支持,外卖小哥正飞奔而来！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            if (msg.what == 2) {
                JSONObject jsonObject = JSONObject.parseObject((String) msg.obj);
                Log.d(TAG, "handleMessage: code" + jsonObject.getIntValue("code"));
                if (jsonObject.getString("code").equals("1") || jsonObject.getIntValue("code") == 1) {
                    billid = jsonObject.getString("info");

                    askbuy();
                }
            }
            if (msg.what == 1) {
                JSONObject jsonObject = JSONObject.parseObject((String) msg.obj);
                parseJson(jsonObject);
            }
            if (msg.what == 0) {
                Toast.makeText(activity, "get cate failed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    void parseJson(JSONObject jsonObject) {
        try {
            Log.d(TAG, "parseJson: ");
            JSONArray jsonArray = jsonObject.getJSONArray("info");
            List<MerchantsEntity> datas = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                productname = jsonArray.getJSONObject(0).getString("name");
                price = jsonArray.getJSONObject(0).getString("price");
                name.setText(jsonArray.getJSONObject(0).getString("name"));
                info.setText(jsonArray.getJSONObject(0).getString("info"));
                tvPrice.setText("￥" + jsonArray.getJSONObject(0).getString("price"));
                totalprice.setText(jsonArray.getJSONObject(0).getString("price"));
                GlideUtil.load(activity, BASE_URl + jsonArray.getJSONObject(0).getString("imgurl"), pic);
                cate.setText(jsonArray.getJSONObject(0).getString("cate"));

            }
        } catch (Exception exception) {
            Log.e(TAG, "handleMessage:  parse error", exception);
        }
    }

    int product_id = 0;
    int product_count = 1;
    private Toolbar tbDetailAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        imvMinusDetailAct = findViewById(R.id.imv_minus_DetailAct);
        imvAddDetailAct = findViewById(R.id.imv_add_DetailAct);
        etCountDetailAct = findViewById(R.id.et_count_DetailAct);

        tbDetailAct = findViewById(R.id.toolbar_DetailAct);//返回按钮
        //返回按钮
        tbDetailAct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(HomeActivity.class);
                finish();
            }
        });
        etCountDetailAct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String count=etCountDetailAct.getText().toString().trim();
                if (!StringUtils.isEmpty(count)){
                    product_count = Integer.parseInt(etCountDetailAct.getText().toString());

                    totalprice.setText(Integer.parseInt(price) * product_count + "");
                }

            }
        });
        imvMinusDetailAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product_count = Integer.parseInt(etCountDetailAct.getText().toString());
                if (product_count >= 1) product_count--;

                etCountDetailAct.setText(product_count + "");
                totalprice.setText(Integer.parseInt(price) * product_count + "");
            }
        });
        imvAddDetailAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product_count = Integer.parseInt(etCountDetailAct.getText().toString());
                product_count++;
                etCountDetailAct.setText(product_count + "");
                totalprice.setText(Integer.parseInt(price) * product_count + "");
            }
        });

        activity = this;
        context = getApplicationContext();

        Intent intent = getIntent();
        product_id = (int) intent.getIntExtra("id", 1);
        getdata();
        initView();

    }

    private void initView() {
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count=etCountDetailAct.getText().toString().trim();
                if (!StringUtils.isEmpty(count)&&!count.equals("0")){

                    addtocart();

                }else showToast("请选择您要购买的数量！");

            }
        });

    }

    void addtocart() {
        OkHttpClient client = new OkHttpClient();
        String url = "";
        String uname = SharedUtil.getIntance(getApplicationContext()).readShared("user", "");
        product_count = Integer.parseInt(etCountDetailAct.getText().toString());
        url = BASE_URl + "bill/billproduct?username=" + uname + "&product_id=" + product_id + "&count=" + product_count;
        Log.d(TAG, "getProduct: " + url);
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message ms = new Message();
                ms.what = 0;
                handler.sendMessage(ms);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();
                Log.d(TAG, "onResponse: add to bill " + res);
                Message ms = new Message();
                ms.what = 2;
                ms.obj = res;
                handler.sendMessage(ms);
            }
        });
    }

    void dopay() {
        OkHttpClient client = new OkHttpClient();
        String url = "";
        String uname = SharedUtil.getIntance(getApplicationContext()).readShared("user", "");
        url = BASE_URl + "bill/paybill?id=" + billid;
        Log.d(TAG, "getProduct: " + url);
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message ms = new Message();
                ms.what = 0;
                handler.sendMessage(ms);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();
                Log.d(TAG, "onResponse: add to bill " + res);
                Message ms = new Message();
                ms.what = 3;
                ms.obj = res;
                handler.sendMessage(ms);
            }
        });
    }

    void askbuy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setTitle("提示：")
                .setMessage("您已下单，是否付款？")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)//点击对话框以外的区域不让对话框消失

                //设置正面按钮
                .setPositiveButton("付款", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dopay();
                        dialog.dismiss();
                    }
                })

                //设置反面按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DetailActivity.this, "请尽快付款！", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    void getdata() {
        OkHttpClient client = new OkHttpClient();
        String url = "";
        url = BASE_URl + "product/getproduct?id=" + product_id;
        Log.d(TAG, "getProduct: " + url);
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message ms = new Message();
                ms.what = 0;
                handler.sendMessage(ms);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();
                Log.d(TAG, "onResponse: " + res);
                Message ms = new Message();
                ms.what = 1;
                ms.obj = res;
                handler.sendMessage(ms);
            }
        });
    }


    int mCount = 0;

    public void addCart(long goods_id, String name) {
        mCount++;
        // 把购物车中的商品数量写入共享参数

        Toast.makeText(this, "添加成功，请前往订单页付款！", Toast.LENGTH_SHORT).show();
    }
}
