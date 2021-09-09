package com.example.minlifeapp.util;
//将重复的代码放到BaseActivity中，然后需要用到直接继承
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context =this;
    }

    //Toast封装
    public void showToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    //Intent封装，实现界面跳转
    public void navigateTo(Class cls){
        Intent intent=new Intent(context,cls);
        startActivity(intent);
    }
}
