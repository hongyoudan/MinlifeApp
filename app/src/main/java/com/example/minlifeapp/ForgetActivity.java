package com.example.minlifeapp;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.example.minlifeapp.util.BaseActivity;
import com.example.minlifeapp.util.DBOpenHelper;
import com.example.minlifeapp.util.StringUtils;
import com.example.minlifeapp.util.User;
import com.example.minlifeapp.util.ValidUtils;
import java.util.ArrayList;

public class ForgetActivity extends BaseActivity {

    private Toolbar tbForgetAct;
    private EditText etPhoneForgetAct, etNumberForgetAct,
            etNameForgetAct, etDormitoryForgetAct, etVerificationCodeForgetAct;
    private TextView tvCollegeForgetAct, tvShowForgetAct;
    private DBOpenHelper mDBOpenHelper;
    private Button btnConfirmForgetAct;
    private ImageView imvVerificationCodeForgetAct;
    private String realCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        tbForgetAct = findViewById(R.id.toolbar_ForgetAct);//返回按钮
        tvCollegeForgetAct = findViewById(R.id.tv_college_ForgetAct);//二级学院
        etNumberForgetAct = findViewById(R.id.et_number_ForgetAct);//学号
        etNameForgetAct = findViewById(R.id.et_name_ForgetAct);//姓名
        etPhoneForgetAct = findViewById(R.id.et_phone_ForgetAct);//手机号
        etDormitoryForgetAct = findViewById(R.id.et_dormitory_ForgetAct);//宿舍号
        tvShowForgetAct = findViewById(R.id.tv_show_ForgetAct);//密码显示
        btnConfirmForgetAct = findViewById(R.id.btn_confirm_ForgetAct);//确定按钮

        //验证码
        etVerificationCodeForgetAct = findViewById(R.id.et_verificationCode_ForgetAct);
        imvVerificationCodeForgetAct = findViewById(R.id.imv_verificationCode_ForgetAct);
        //将验证码用图片的形式显示出来
        imvVerificationCodeForgetAct.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();

        setListeners(); //调用setListeners()方法
        mDBOpenHelper = new DBOpenHelper(this);
        //返回按钮
        tbForgetAct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(LoginActivity.class);
            }
        });
    }

    private void setListeners() {
        OnClick onClick = new OnClick();  //实例化OnClick
        tvCollegeForgetAct.setOnClickListener(onClick);
        btnConfirmForgetAct.setOnClickListener(onClick);
        imvVerificationCodeForgetAct.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //二级学院
                case R.id.tv_college_ForgetAct:
                    String[] array_college = getResources().getStringArray(R.array.array_college);
                    AlertDialog.Builder builder_college = new AlertDialog.Builder(ForgetActivity.this);
                    builder_college.setTitle("请选择二级学院")
                            .setIcon(R.mipmap.ic_launcher)
                            .setItems(array_college, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tvCollegeForgetAct.setText(array_college[which]);
                                    dialog.dismiss();//dialog.dismiss()：当选择内容时推出对话框
                                }
                            })
                            .show();
                    break;

                //验证码
                case R.id.imv_verificationCode_ForgetAct:
                    //改变随机验证码的生成
                    imvVerificationCodeForgetAct.setImageBitmap(Code.getInstance().createBitmap());
                    realCode = Code.getInstance().getCode().toLowerCase();
                    break;

                //确定按钮
                case R.id.btn_confirm_ForgetAct:
                    String college = tvCollegeForgetAct.getText().toString().trim();
                    String number = etNumberForgetAct.getText().toString().trim();
                    String name = etNameForgetAct.getText().toString().trim();
                    String phone = etPhoneForgetAct.getText().toString().trim();
                    String dormitory = etDormitoryForgetAct.getText().toString().trim();
                    String verificationCode = etVerificationCodeForgetAct.getText().toString().toLowerCase();

                    if (!StringUtils.isEmpty(college) && !StringUtils.isEmpty(number) && !StringUtils.isEmpty(name)
                            && ValidUtils.isPhoneValid(phone) && !StringUtils.isEmpty(dormitory) && !StringUtils.isEmpty(verificationCode)) {
                        if (verificationCode.equals(realCode)) {
                            ArrayList<User> data = mDBOpenHelper.getAllData();
                            boolean match = true;
                            for (int i = 0; i < data.size(); i++) {
                                User user = data.get(i);
                                if (college.equals(user.getCollege()) && number.equals(user.getNumber()) && name.equals(user.getName())
                                        && phone.equals(user.getPhone()) && dormitory.equals(user.getDormitory())) {
                                    match = true;
                                    tvShowForgetAct.setText(user.getPassword());
                                    break;
                                } else {
                                    match = false;
                                }
                            }
                            if (match) {
                                showToast("找回密码成功");
                            } else {
                                tvShowForgetAct.setText("未查询到相关信息");
                                showToast("找回密码失败");
                            }

                        } else showToast("验证码错误！");

                    } else {
                        tvShowForgetAct.setText("请正确填写相关信息");
                        showToast("找回密码失败");
                    }

                    break;

            }
        }
    }
}