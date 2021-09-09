package com.example.minlifeapp.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class FixedViewPager extends ViewPager {
    public FixedViewPager(@NonNull Context context) {
        super(context);
    }

    public FixedViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //关闭viewpager切换时的平滑动画效果
    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }
}
