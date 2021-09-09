package com.example.minlifeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.minlifeapp.util.BaseActivity;
import com.example.minlifeapp.util.DBOpenHelper;
import com.example.minlifeapp.util.SharedUtil;
import com.example.minlifeapp.util.StringUtils;
import com.example.minlifeapp.util.User;
import com.example.minlifeapp.util.ValidUtils;
import java.util.ArrayList;

public class ModifyDataActivity extends BaseActivity {
    private Toolbar tbModifyAct;
    private EditText etPhoneModifyAct, etPassword1ModifyAct, etNumberModifyAct,
            etNameModifyAct, etDormitoryModifyAct, etPassword2ModifyAct, etVerificationCodeModifyAct;
    private TextView tvCollegeModifyAct, tvGenderModifyAct;
    private DBOpenHelper mDBOpenHelper;
    private Button btnSaveModifyAct;
    private ImageView imvVerificationCodeModifyAct;
    private String realCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_data);

        tbModifyAct = findViewById(R.id.toolbar_ModifyAct);//返回按钮
        tvCollegeModifyAct = findViewById(R.id.tv_college_ModifyAct);//二级学院
        etNumberModifyAct = findViewById(R.id.et_number_ModifyAct);//学号
        etNameModifyAct = findViewById(R.id.et_name_ModifyAct);//姓名
        tvGenderModifyAct = findViewById(R.id.tv_gender_ModifyAct);//性别
        etPhoneModifyAct = findViewById(R.id.et_phone_ModifyAct);//手机号
        etDormitoryModifyAct = findViewById(R.id.et_dormitory_ModifyAct);//宿舍号
        etPassword1ModifyAct = findViewById(R.id.et_password1_ModifyAct);//密码
        etPassword2ModifyAct = findViewById(R.id.et_password2_ModifyAct);//确认密码
        btnSaveModifyAct = findViewById(R.id.btn_save_ModifyAct);//确认按钮

        //验证码
        etVerificationCodeModifyAct = findViewById(R.id.et_verificationCode_ModifyAct);
        imvVerificationCodeModifyAct = findViewById(R.id.imv_verificationCode_ModifyAct);
        //将验证码用图片的形式显示出来
        imvVerificationCodeModifyAct.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();

        setListeners(); //调用setListeners()方法

        mDBOpenHelper = new DBOpenHelper(this);
        //返回按钮
        tbModifyAct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(HomeActivity.class);
                finish();
            }
        });


        String userPhone = SharedUtil.getIntance(context).readShared("user", "");
        ArrayList<User> data = mDBOpenHelper.getAllData();
        for (int i = 0; i < data.size(); i++) {
            User user = data.get(i);
            if (userPhone.equals(user.getPhone())) {

                tvCollegeModifyAct.setText(user.getCollege());
                etNumberModifyAct.setText(user.getNumber());
                etNameModifyAct.setText(user.getName());
                tvGenderModifyAct.setText(user.getGender());
                etPhoneModifyAct.setText(user.getPhone());
                etDormitoryModifyAct.setText(user.getDormitory());
                etPassword1ModifyAct.setText(user.getPassword());
                return;
            }

        }

    }

    private void setListeners() {
        OnClick onClick = new OnClick();  //实例化OnClick
        tvCollegeModifyAct.setOnClickListener(onClick);
        tvGenderModifyAct.setOnClickListener(onClick);
        btnSaveModifyAct.setOnClickListener(onClick);
        imvVerificationCodeModifyAct.setOnClickListener(onClick);

    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                //二级学院
                case R.id.tv_college_ModifyAct:
                    String[] array_college = getResources().getStringArray(R.array.array_college);
                    AlertDialog.Builder builder_college = new AlertDialog.Builder(ModifyDataActivity.this);
                    builder_college.setTitle("请选择二级学院")
                            .setIcon(R.mipmap.ic_launcher)
                            .setItems(array_college, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tvCollegeModifyAct.setText(array_college[which]);
                                    dialog.dismiss();//dialog.dismiss()：当选择内容时推出对话框
                                }
                            })
                            .show();
                    break;

                //性别
                case R.id.tv_gender_ModifyAct:
                    String[] array_gender = getResources().getStringArray(R.array.array_gender);
                    AlertDialog.Builder builder_gender = new AlertDialog.Builder(ModifyDataActivity.this);
                    builder_gender.setTitle("请选择性别")
                            .setIcon(R.mipmap.ic_launcher)
                            .setItems(array_gender, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tvGenderModifyAct.setText(array_gender[which]);
                                    dialog.dismiss();//dialog.dismiss()：当选择内容时推出对话框
                                }
                            })
                            .show();
                    break;

                //验证码
                case R.id.imv_verificationCode_ModifyAct:
                    //改变随机验证码的生成
                    imvVerificationCodeModifyAct.setImageBitmap(Code.getInstance().createBitmap());
                    realCode = Code.getInstance().getCode().toLowerCase();
                    break;

                //确认按钮
                case R.id.btn_save_ModifyAct:
                    String college = tvCollegeModifyAct.getText().toString().trim();
                    String number = etNumberModifyAct.getText().toString().trim();
                    String name = etNameModifyAct.getText().toString().trim();
                    String gender = tvGenderModifyAct.getText().toString().trim();
                    String phone = etPhoneModifyAct.getText().toString().trim();
                    String dormitory = etDormitoryModifyAct.getText().toString().trim();
                    String password = etPassword1ModifyAct.getText().toString().trim();
                    String password2 = etPassword2ModifyAct.getText().toString().trim();
                    String verificationCode = etVerificationCodeModifyAct.getText().toString().toLowerCase();

                    String userPhone = SharedUtil.getIntance(context).readShared("user", "");

                    //确认验证
                    if (StringUtils.isEmpty(college)) {
                        showToast("请选择二级学院");
                    } else if (StringUtils.isEmpty(number)) {
                        showToast("请输入学号");
                    } else if (StringUtils.isEmpty(name)) {
                        showToast("请输入姓名");
                    } else if (StringUtils.isEmpty(gender)) {
                        showToast("请选择性别");
                    } else if (!ValidUtils.isPhoneValid(phone)) {
                        showToast("请输入正确的手机号");
                    } else if (StringUtils.isEmpty(dormitory)) {
                        showToast("请输入宿舍号");
                    } else if (StringUtils.isEmpty(password)) {
                        showToast("请输入密码");
                    } else if (password.length() != 6 && password2.length() != 6) {
                        showToast("请输入6位数密码");
                    } else if (StringUtils.isEmpty(password2)) {
                        showToast("请再次输入密码");
                    } else if (!password.equals(password2)) {
                        showToast("两次密码输入不一致");
                    } else if(verificationCode.equals(realCode)){
                        if (!phone.equals(userPhone)) {
                            mDBOpenHelper.delete(userPhone);
                            mDBOpenHelper.add(college, number, name, gender, phone, dormitory, password);
                            navigateTo(LoginActivity.class);
                            SharedUtil.getIntance(context).putBoolean("remember_password", false);
                            SharedUtil.getIntance(context).putBoolean("automatic_login", false);
                            SharedUtil.getIntance(context).writeShared("user", "");
                            SharedUtil.getIntance(context).writeShared("password", "");
                            showToast("用户信息已更改，请重新登录！");
                        } else {
                            mDBOpenHelper.update(college, number, name, gender, dormitory, password, phone);
                            SharedUtil.getIntance(getApplicationContext()).writeShared("password", password);
                            navigateTo(HomeActivity.class);
                            showToast("修改成功！");
                        }
                        finish();
                    }else showToast("验证码错误！");

                    break;


            }
        }
    }
}