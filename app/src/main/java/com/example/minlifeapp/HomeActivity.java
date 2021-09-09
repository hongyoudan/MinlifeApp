package com.example.minlifeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.app.Activity;
import android.os.Bundle;
import com.example.minlifeapp.adapter.MyPagerAdapter;
import com.example.minlifeapp.entity.TabEntity;
import com.example.minlifeapp.fragment.CommunityFragment;
import com.example.minlifeapp.fragment.HomeFragment;
import com.example.minlifeapp.fragment.MyFragment;
import com.example.minlifeapp.fragment.OrderFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    private String[] mTitles = {"首页", "订单", "社区", "我的"};
    private int[] mIconUnSelectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_order_unselect,
            R.mipmap.tab_community_unselect, R.mipmap.tab_my_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_order_select,
            R.mipmap.tab_community_select, R.mipmap.tab_my_select};
    Activity activity;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        activity = this;
        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
        initview();

    }
    void initview () {
        //将Fragments放到集合里
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(OrderFragment.newInstance());
        mFragments.add(CommunityFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
        //遍历mTitles
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        //绑定
        commonTabLayout.setTabData(mTabEntities);
        //当tab切换有事件回调(实现页面切换)
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            //当点击tab是会执行onTabSelect
            public void onTabSelect(int position) {
                //给viewPager切换Fragments,position(下标)
                viewPager.setCurrentItem(position);
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
        //当滑动viewpager底部tab也跟着动
        viewPager.setOffscreenPageLimit(mFragments.size());//设置预加载
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitles, mFragments));
    }


}