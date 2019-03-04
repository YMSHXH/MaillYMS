package com.king.maillyms.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.google.gson.Gson;
import com.king.maillyms.R;
import com.king.maillyms.adapter.ChanceAddressAdapter;
import com.king.maillyms.adapter.SumMoneyAdapter;
import com.king.maillyms.beans.CreateDanCount;
import com.king.maillyms.beans.entity.MyAddressBean;
import com.king.maillyms.beans.entity.ShoppingCarBean;
import com.king.maillyms.contact.CreateDancontact;
import com.king.maillyms.model.MyAddressModel;
import com.king.maillyms.presenter.CreateDanPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SumMoneyActivity extends BaseMvpActivity<CreateDancontact.ICreateDanModel,CreateDancontact.ICreateDanPresenter>
        implements ChanceAddressAdapter.ChanceAddressAdapterCallBack, CreateDancontact.ICreateDanView {

    @BindView(R.id.sun_recy)
    RecyclerView sunRecy;
    @BindView(R.id.sum_jia)
    TextView sumJia;
    @BindView(R.id.sum_ti)
    TextView sumTi;
    @BindView(R.id.my_address_name)
    TextView myAddressName;
    @BindView(R.id.my_address_phone)
    TextView myAddressPhone;
    @BindView(R.id.my_address_address)
    TextView myAddressAddress;
    @BindView(R.id.imageCk)
    ImageView imageCk;
    @BindView(R.id.sum_address)
    RecyclerView sumAddress;

    private List<ShoppingCarBean> shoppingCarBeans;
    private List<MyAddressBean> beanList;
    private boolean isSumAddressHide = true;//是否隐藏选择地址
    private int addressId = 0;
    private Map<String, String> params;
    private String s;
    private double sumPrice;


    public void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        sunRecy.setLayoutManager(new LinearLayoutManager(this));
        sumAddress.setLayoutManager(new LinearLayoutManager(this));

        //共0件商品,需支付0.0元
        sumJia.setText("共"+sumCount()+"件商品,需支付"+sumPrice+"元");
    }

    @Override
    protected void initData() {
        super.initData();
        shoppingCarBeans = new ArrayList<>();
        beanList = new ArrayList<>();


        params = new HashMap<>();
        params.put("userId", ShapedP.getmInstance().getSP("userId"));
        params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));

        MyAddressModel myAddressModel = new MyAddressModel();
        myAddressModel.setMyAddressList(params, new MyAddressModel.MyAddressModelCallBack() {
            @Override
            public void onSuccess(List<MyAddressBean> list) {
                //ToastUtils.showLong(list.size() + "");
                beanList = list;
                for (MyAddressBean myAddressBean : list) {
                    if ("1".equals(myAddressBean.getWhetherDefault())) {
                        myAddressName.setText(myAddressBean.getRealName());
                        myAddressPhone.setText(myAddressBean.getPhone());
                        myAddressAddress.setText(myAddressBean.getAddress());
                        String id = myAddressBean.getId();
                        addressId = Integer.parseInt(id);
                    }
                }
            }

            @Override
            public void onFile(String msg) {

            }
        });
    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_sum_money;
    }


    @Subscribe(sticky = true)
    public void getSunList(List<ShoppingCarBean> tosumlist) {
        //ToastUtils.showLong(tosumlist.size() + "");
        shoppingCarBeans = tosumlist;
        SumMoneyAdapter sumMoneyAdapter = new SumMoneyAdapter(this, tosumlist);
        sunRecy.setAdapter(sumMoneyAdapter);

        List<CreateDanCount> list = new ArrayList<>();
        for (ShoppingCarBean shoppingCarBean : tosumlist) {
            int cooid = Integer.parseInt(shoppingCarBean.getCommodityId());
            int acount = Integer.parseInt(shoppingCarBean.getCount());
            CreateDanCount createDanCount = new CreateDanCount(cooid,1);
            list.add(createDanCount);
            double price = Double.parseDouble(shoppingCarBean.getPrice());
            sumPrice +=  price ;
        }

        s = new Gson().toJson(list);
        System.out.println("gson解析==="+ s);
    }


    @OnClick({R.id.imageCk, R.id.sum_ti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageCk:
                if(isSumAddressHide){
                    sumAddress.setVisibility(View.VISIBLE);
                    ChanceAddressAdapter chanceAddressAdapter = new ChanceAddressAdapter(this,beanList);
                    sumAddress.setAdapter(chanceAddressAdapter);
                    chanceAddressAdapter.setChanceAddressAdapterCallBack(this);
                } else {
                    sumAddress.setVisibility(View.GONE);
                }
                isSumAddressHide = !isSumAddressHide;
                break;
            case R.id.sum_ti:
                //提交订单
                toDan();
                break;
        }
    }

    private void toDan() {
        System.out.println("id==="+addressId);
        System.out.println("总价==="+sumPrice);
        presenter.onSetCreatDanMessage(params,s,sumPrice,addressId);
    }


    /**
     * 计算件数
     * @return
     */
    private int sumCount() {
        int sum_count = 0;
        for (ShoppingCarBean shoppingCarBean : shoppingCarBeans) {
            int count = Integer.parseInt(shoppingCarBean.getCount());
            sum_count +=  count;
        }
        return sum_count;
    }

    
    @Override
    public void setMyAddress(MyAddressBean myAddressBean) {
        myAddressName.setText(myAddressBean.getRealName());
        myAddressPhone.setText(myAddressBean.getPhone());
        myAddressAddress.setText(myAddressBean.getAddress());
        String id = myAddressBean.getId();
        addressId = Integer.parseInt(id);
    }

    @Override
    public void onSuccess(String msg) {
        ToastUtils.showLong(msg);
    }

    @Override
    public void onFile(String msg) {
        ToastUtils.showLong(msg);
    }

    @Override
    public BasePresenter initPresenter() {
        return new CreateDanPresenter();
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
