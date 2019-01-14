package com.king.maillyms.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.king.maillyms.R;
import com.king.maillyms.fragment.detailsfragment.DetaFragment;
import com.king.maillyms.fragment.detailsfragment.GoodsFragment;
import com.king.maillyms.fragment.detailsfragment.PingFragment;

public class GoodsDetailsActivity extends AppCompatActivity {
    private TabLayout mGoodTab;
    private ViewPager mGoodPager;

    String[] name = {"商品","详情","评价"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        initView();

    }

    private void initView() {
        mGoodTab = (TabLayout) findViewById(R.id.goodTab);
        mGoodPager = (ViewPager) findViewById(R.id.goodPager);

        mGoodPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new GoodsFragment();
                    case 1:
                        return new DetaFragment();
                    case 2:
                        return new PingFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return name[position];
            }
        });
        //设置同步
        mGoodTab.setupWithViewPager(mGoodPager);
    }
}