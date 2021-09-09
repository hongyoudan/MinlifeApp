package com.example.minlifeapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.example.minlifeapp.util.BaseActivity;
import com.example.minlifeapp.util.DBOpenHelper;
import com.example.minlifeapp.util.SharedUtil;
import com.example.minlifeapp.util.StringUtils;
import com.example.minlifeapp.util.User;
import com.example.minlifeapp.util.ValidUtils;
import java.util.ArrayList;

public class LoginActivity extends BaseActivity {

    private TextView tvRegisterLoginAct, tvForgetLoginAct;
    private EditText etPhoneLoginAct, etPasswordLoginAct;
    private Button btnLoginLoginAct;
    private DBOpenHelper mDBOpenHelper;
    private CheckBox cbRememberLoginAct, cbAutomaticLoginAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegisterLoginAct = findViewById(R.id.tv_register_LoginAct);
        tvForgetLoginAct = findViewById(R.id.tv_forget_LoginAct);
        etPhoneLoginAct = findViewById(R.id.et_phone_LoginAct);
        etPasswordLoginAct = findViewById(R.id.et_password_LoginAct);
        btnLoginLoginAct = findViewById(R.id.btn_login_LoginAct);
        cbRememberLoginAct = findViewById(R.id.cb_remember_LoginAct);
        cbAutomaticLoginAct = findViewById(R.id.cb_automatic_LoginAct);
        setListeners(); //调用setListeners()方法
        mDBOpenHelper = new DBOpenHelper(this);

        boolean isRemember = SharedUtil.getIntance(getApplicationContext()).getBoolean("remember_password", false);
        boolean isAutomatic = SharedUtil.getIntance(getApplicationContext()).getBoolean("automatic_login", false);

        if (isRemember && isAutomatic) {

            String phone = SharedUtil.getIntance(getApplicationContext()).readShared("user", "");
            String password = SharedUtil.getIntance(getApplicationContext()).readShared("password", "");
            etPhoneLoginAct.setText(phone);
            etPasswordLoginAct.setText(password);
            cbRememberLoginAct.setChecked(true);
            cbAutomaticLoginAct.setChecked(true);

            navigateTo(HomeActivity.class);
            finish();

        } else if (isRemember) {
            String phone = SharedUtil.getIntance(getApplicationContext()).readShared("user", "");
            String password = SharedUtil.getIntance(getApplicationContext()).readShared("password", "");
            etPhoneLoginAct.setText(phone);
            etPasswordLoginAct.setText(password);
            cbRememberLoginAct.setChecked(true);

        }

    }

    private void setListeners() {
        OnClick onClick = new OnClick();  //实例化OnClick
        tvRegisterLoginAct.setOnClickListener(onClick);
        tvForgetLoginAct.setOnClickListener(onClick);
        btnLoginLoginAct.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.tv_register_LoginAct:
                    navigateTo(RegisterActivity.class);
                    finish();
                    break;

                case R.id.tv_forget_LoginAct:
                    navigateTo(ForgetActivity.class);
                    finish();
                    break;

                case R.id.btn_login_LoginAct:
                    String phone = etPhoneLoginAct.getText().toString().trim();
                    String password = etPasswordLoginAct.getText().toString().trim();

                    if (ValidUtils.isPhoneValid(phone) && !StringUtils.isEmpty(password)) {
                        ArrayList<User> data = mDBOpenHelper.getAllData();
                        boolean match = false;
                        for (int i = 0; i < data.size(); i++) {
                            User user = data.get(i);
                            if (phone.equals(user.getPhone()) && password.equals(user.getPassword())) {
                                SharedUtil.getIntance(getApplicationContext()).writeShared("user", phone);
                                SharedUtil.getIntance(getApplicationContext()).writeShared("password", password);
                                match = true;
                                break;
                            } else {
                                match = false;
                            }
                        }

                        if (match) {

                            if (cbRememberLoginAct.isChecked() && cbAutomaticLoginAct.isChecked()) {
                                SharedUtil.getIntance(getApplicationContext()).writeShared("user", phone);
                                SharedUtil.getIntance(getApplicationContext()).writeShared("password", password);
                                SharedUtil.getIntance(getApplicationContext()).putBoolean("remember_password", true);
                                SharedUtil.getIntance(getApplicationContext()).putBoolean("automatic_login", true);

                            } else if (cbRememberLoginAct.isChecked()) {
                                SharedUtil.getIntance(getApplicationContext()).writeShared("password", password);
                                SharedUtil.getIntance(getApplicationContext()).putBoolean("remember_password", true);

                            }
                            else {
                                SharedUtil.getIntance(getApplicationContext()).putBoolean("remember_password", false);

                            }

                            navigateTo(HomeActivity.class);
                            showToast("登录成功");
                            finish();//销毁此Activity
                        } else {
                            showToast("手机号或密码错误");
                        }
                    } else {
                        showToast("手机号或密码错误");
                    }
                    break;

            }
        }
    }

}