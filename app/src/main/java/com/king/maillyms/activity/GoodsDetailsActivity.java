package com.king.maillyms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.google.gson.Gson;
import com.king.maillyms.R;
import com.king.maillyms.beans.AddCarBean;
import com.king.maillyms.beans.AddCarBodyBean;
import com.king.maillyms.constants.Constants;
import com.king.maillyms.contact.AddCarContact;
import com.king.maillyms.entity.CartEntity;
import com.king.maillyms.fragment.detailsfragment.DetaFragment;
import com.king.maillyms.fragment.detailsfragment.GoodsFragment;
import com.king.maillyms.fragment.detailsfragment.PingFragment;
import com.king.maillyms.greendao.CartEntityDao;
import com.king.maillyms.presenter.AddCarPresnenter;
import com.king.maillyms.utils.GreendaoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsDetailsActivity extends BaseMvpActivity<AddCarContact.IAddCarModel,AddCarContact.IAddCarPresenter>
        implements AddCarContact.IAddCarView {

    @BindView(R.id.add_car)
    ImageView addCar;
    @BindView(R.id.add_buy)
    ImageView addBuy;

    private TabLayout mGoodTab;
    private ViewPager mGoodPager;

    String[] name = {"商品","详情","评价"};
    private String commodityId;

    public void initView() {
        ButterKnife.bind(this);
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

    @Override
    protected void initData() {
        super.initData();
        //获取到串过来的商品ID
        Intent intent = getIntent();
        commodityId = intent.getStringExtra("commodityId");
        //设置点击数据

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> params = new HashMap<>();
                params.put("userId", ShapedP.getmInstance().getSP("userId"));
                params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));

                //转换commodityId数据类型
                int comm = Integer.parseInt(commodityId);
                //添加数据
                CartEntity cartEntity = new CartEntity(comm, 1);

                //添加到购物的数据库
                //初始化
                GreendaoUtils.getInstance().initGreenDao(GoodsDetailsActivity.this,Constants.CART_DB);
                CartEntityDao cartEntityDao = GreendaoUtils.getInstance().getDaoSession().getCartEntityDao();
                cartEntityDao.insertOrReplace(cartEntity);//添加

                List<CartEntity> cartEntities = cartEntityDao.loadAll();



                String tobody = new Gson().toJson(cartEntities);
                System.out.println("body====" + tobody);
                presenter.setAddCarList(params,tobody);
            }
        });
    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_goods_details;
    }

    @Override
    public void onSeccess(AddCarBean addCarBean) {
        Toast.makeText(this,addCarBean.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public BasePresenter initPresenter() {
        return new AddCarPresnenter();
    }

    @Override
    public void showLoding() {

    }

    @Override
    public void hideLoding() {

    }

    @Override
    public void failLoding(String msg) {

    }
}