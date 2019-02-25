package com.king.maillyms.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.king.maillyms.R;
import com.king.maillyms.adapter.MyAddressAdapter;
import com.king.maillyms.beans.entity.MyAddressBean;
import com.king.maillyms.contact.MyAddressContact;
import com.king.maillyms.presenter.MyAddressPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查询我的收货地址
 */
public class MyAddressActivity extends BaseMvpActivity<MyAddressContact.IMyAddressMoudel, MyAddressContact.IMyAddressPresenter>
        implements MyAddressContact.IMyAddressView {


    @BindView(R.id.myAddress_complete)
    TextView myAddressComplete;
    @BindView(R.id.myaddress_xrecy)
    RecyclerView myaddressXrecy;
    @BindView(R.id.myaddress_btn_add_address)
    Button myaddressBtnAddAddress;

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        //设置 recycleView 的样式
        myaddressXrecy.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        super.initData();

        Map<String, String> params = new HashMap<>();
        params.put("userId", ShapedP.getmInstance().getSP("userId"));
        params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));
        presenter.setMyAddressList(params);
    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_my_address;
    }

    @Override
    public void onSuccess(List<MyAddressBean> list) {
        //Toast.makeText(this,list.size() + "",Toast.LENGTH_SHORT).show();
        //设置适配器
        MyAddressAdapter myAddressAdapter = new MyAddressAdapter(this, list);
        myaddressXrecy.setAdapter(myAddressAdapter);
    }

    @Override
    public void onFile(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public BasePresenter initPresenter() {
        return new MyAddressPresenter();
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

    /**
     * 点击数据
     * @param view
     */
    @OnClick({R.id.myAddress_complete, R.id.myaddress_xrecy})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.myAddress_complete://完成
                finish();
                break;
            case R.id.myaddress_xrecy://跳转到添加

                break;
        }
    }
}
