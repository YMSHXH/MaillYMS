package com.king.maillyms.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;
import com.king.maillyms.R;
import com.king.maillyms.fragment.DanFragment;
import com.king.maillyms.fragment.HomeFragment;
import com.king.maillyms.fragment.MineFragment;
import com.king.maillyms.fragment.QuanFragment;
import com.king.maillyms.fragment.ShoppingFragment;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar mBottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottomTabBar);

        mBottomTabBar.init(getSupportFragmentManager())
                .setFontSize(0)
                .setImgSize(60,60)
                .addTabItem(null,R.drawable.home,HomeFragment.class)
                .addTabItem(null,R.drawable.quan,QuanFragment.class)
                .addTabItem(null,R.drawable.shopping,ShoppingFragment.class)
                .addTabItem(null,R.drawable.dan,DanFragment.class)
                .addTabItem(null,R.drawable.mine,MineFragment.class)
                .isShowDivider(false);

    }
}
