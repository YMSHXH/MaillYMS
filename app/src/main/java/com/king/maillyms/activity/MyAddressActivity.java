package com.king.maillyms.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.king.maillyms.R;
import com.king.maillyms.beans.entity.MyAddressBean;
import com.king.maillyms.contact.MyAddressContact;
import com.king.maillyms.presenter.MyAddressPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询我的收货地址
 */
public class MyAddressActivity extends BaseMvpActivity<MyAddressContact.IMyAddressMoudel,MyAddressContact.IMyAddressPresenter>
        implements MyAddressContact.IMyAddressView {

    @Override
    protected void initView() {

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
        Toast.makeText(this,list.size() + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFile(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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
}
