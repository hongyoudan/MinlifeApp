package com.example.minlifeapp;

import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import com.example.minlifeapp.util.BaseActivity;

public class FeedbackActivity extends BaseActivity {
    private Toolbar tbFeedAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        tbFeedAct=findViewById(R.id.toolbar_FeedAct);
        //返回按钮
        tbFeedAct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(HomeActivity.class);
                finish();
            }
        });
    }
}