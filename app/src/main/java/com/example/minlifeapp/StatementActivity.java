package com.example.minlifeapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.example.minlifeapp.util.BaseActivity;

public class StatementActivity extends BaseActivity {
    private Toolbar tbStatementAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        tbStatementAct=findViewById(R.id.toolbar_StatementAct);
        //返回按钮
        tbStatementAct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(RegisterActivity.class);
                finish();
            }
        });
    }
}