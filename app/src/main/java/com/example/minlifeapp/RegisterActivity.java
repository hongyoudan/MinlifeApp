package com.example.minlifeapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import com.example.minlifeapp.util.BaseActivity;
import com.example.minlifeapp.util.DBOpenHelper;
import com.example.minlifeapp.util.StringUtils;
import com.example.minlifeapp.util.User;
import com.example.minlifeapp.util.ValidUtils;
import java.util.ArrayList;

public class RegisterActivity extends BaseActivity {

    private Toolbar tbRegisterAct;
    private EditText etPhoneRegisterAct, etPassword1RegisterAct, etNumberRegisterAct,
            etNameRegisterAct, etDormitoryRegisterAct, etPassword2RegisterAct;
    private TextView tvCollegeRegisterAct, tvGenderRegisterAct, tvStatementRegisterAct;
    private DBOpenHelper mDBOpenHelper;
    private Button btnRegisterRegisterAct;
    private CheckBox cbStatementRegisterAct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tbRegisterAct = findViewById(R.id.toolbar_RegisterAct);//返回按钮
        tvCollegeRegisterAct = findViewById(R.id.tv_college_RegisterAct);//二级学院
        etNumberRegisterAct = findViewById(R.id.et_number_RegisterAct);//学号
        etNameRegisterAct = findViewById(R.id.et_name_RegisterAct);//姓名
        tvGenderRegisterAct = findViewById(R.id.tv_gender_RegisterAct);//性别
        etPhoneRegisterAct = findViewById(R.id.et_phone_RegisterAct);//手机号
        etDormitoryRegisterAct = findViewById(R.id.et_dormitory_RegisterAct);//宿舍号
        etPassword1RegisterAct = findViewById(R.id.et_password1_RegisterAct);//密码
        etPassword2RegisterAct = findViewById(R.id.et_password2_RegisterAct);//确认密码
        btnRegisterRegisterAct = findViewById(R.id.btn_register_RegisterAct);//注册按钮
        tvStatementRegisterAct = findViewById(R.id.tv_statement_RegisterAct);//阅读声明
        cbStatementRegisterAct = findViewById(R.id.cb_statement_RegisterAct);//checkbox

        setListeners(); //调用setListeners()方法

        mDBOpenHelper = new DBOpenHelper(this);
        //返回按钮
        tbRegisterAct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(LoginActivity.class);
            }
        });
    }

    private void setListeners() {
        OnClick onClick = new OnClick();  //实例化OnClick
        tvCollegeRegisterAct.setOnClickListener(onClick);
        tvGenderRegisterAct.setOnClickListener(onClick);
        btnRegisterRegisterAct.setOnClickListener(onClick);
        tvStatementRegisterAct.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                //二级学院
                case R.id.tv_college_RegisterAct:
                    String[] array_college = getResources().getStringArray(R.array.array_college);
                    AlertDialog.Builder builder_college = new AlertDialog.Builder(RegisterActivity.this);
                    builder_college.setTitle("请选择二级学院")
                            .setIcon(R.mipmap.ic_launcher)
                            .setItems(array_college, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tvCollegeRegisterAct.setText(array_college[which]);
                                    dialog.dismiss();//dialog.dismiss()：当选择内容时推出对话框
                                }
                            })
                            .show();
                    break;

                //性别
                case R.id.tv_gender_RegisterAct:
                    String[] array_gender = getResources().getStringArray(R.array.array_gender);
                    AlertDialog.Builder builder_gender = new AlertDialog.Builder(RegisterActivity.this);
                    builder_gender.setTitle("请选择性别")
                            .setIcon(R.mipmap.ic_launcher)
                            .setItems(array_gender, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tvGenderRegisterAct.setText(array_gender[which]);
                                    dialog.dismiss();//dialog.dismiss()：当选择内容时推出对话框
                                }
                            })
                            .show();
                    break;

                //阅读声明
                case R.id.tv_statement_RegisterAct:
                    navigateTo(StatementActivity.class);
                    break;

                //注册按钮
                case R.id.btn_register_RegisterAct:
                    String college = tvCollegeRegisterAct.getText().toString().trim();
                    String number = etNumberRegisterAct.getText().toString().trim();
                    String name = etNameRegisterAct.getText().toString().trim();
                    String gender = tvGenderRegisterAct.getText().toString().trim();
                    String phone = etPhoneRegisterAct.getText().toString().trim();
                    String dormitory = etDormitoryRegisterAct.getText().toString().trim();
                    String password = etPassword1RegisterAct.getText().toString().trim();
                    String password2 = etPassword2RegisterAct.getText().toString().trim();

                    //注册验证
                    if (StringUtils.isEmpty(college)) {
                        showToast("请选择二级学院");
                    } else if (StringUtils.isEmpty(number)) {
                        showToast("请输入学号");
                    } else if (StringUtils.isEmpty(name)) {
                        showToast("请输入姓名");
                    } else if (StringUtils.isEmpty(gender)) {
                        showToast("请选择性别");
                    } else if (!StringUtils.isEmpty(phone)) {
                        ArrayList<User> data = mDBOpenHelper.getAllData();
                        for (int i = 0; i < data.size(); i++) {
                            User user = data.get(i);
                            if (phone.equals(user.getPhone())) {
                                showToast("该手机号已被注册，请重新输入！");
                                return;
                            }
                        }
                        if (!ValidUtils.isPhoneValid(phone)) {
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
                        } else if (!cbStatementRegisterAct.isChecked()) {
                            showToast("您未阅读并同意《闽生活平台服务声明》");
                        } else {
                            mDBOpenHelper.add(college, number, name, gender, phone, dormitory, password);
                            navigateTo(LoginActivity.class);
                            showToast("注册成功");
                            finish();
                        }
                    } else showToast("请输入手机号");
                    break;
            }


        }
    }
}
