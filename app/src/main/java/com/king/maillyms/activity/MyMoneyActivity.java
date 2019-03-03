package com.king.maillyms.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.king.maillyms.R;
import com.king.maillyms.adapter.MyMoneyAdapter;
import com.king.maillyms.beans.entity.MyMoneyBean;
import com.king.maillyms.contact.MyMoneyContact;
import com.king.maillyms.presenter.MyMoneyPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMoneyActivity extends BaseMvpActivity<MyMoneyContact.IMyMoneyModel, MyMoneyContact.IMyMoneyPresenter>
        implements MyMoneyContact.IMyMoneyView {

    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.recy_money)
    RecyclerView recyMoney;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        recyMoney.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initData() {
        super.initData();
        Map<String, String> params = new HashMap<>();
        params.put("userId", ShapedP.getmInstance().getSP("userId"));
        params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));

        Map<String, String> paramsBody = new HashMap<>();
        paramsBody.put("page", "1");
        paramsBody.put("count", "1");
        presenter.setList(params,paramsBody);
    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_my_money;
    }

    @Override
    public void onSuccess(MyMoneyBean myMoneyBean) {
        ToastUtils.showLong(myMoneyBean.getBalance());
        money.setText(myMoneyBean.getBalance());

        List<MyMoneyBean.DetailListBean> detailList = myMoneyBean.getDetailList();
        MyMoneyAdapter myMoneyAdapter = new MyMoneyAdapter(this,detailList);
        recyMoney.setAdapter(myMoneyAdapter);

    }

    @Override
    public void onFile(String msg) {

    }

    @Override
    public BasePresenter initPresenter() {
        return new MyMoneyPresenter();
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
