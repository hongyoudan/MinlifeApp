package com.example.minlifeapp;

import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import com.example.minlifeapp.util.BaseActivity;

public class AboutUsActivity extends BaseActivity {
    private Toolbar tbAboutAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        tbAboutAct=findViewById(R.id.toolbar_AboutAct);
        //返回按钮
        tbAboutAct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(HomeActivity.class);
                finish();
            }
        });
    }
}