package com.king.maillyms.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.king.maillyms.R;
import com.king.maillyms.contact.AddAddressContact;
import com.king.maillyms.presenter.AddMyAddPresenter;
import com.xw.repo.XEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMyAddressActivity extends BaseMvpActivity<AddAddressContact.IAddAddressModel,AddAddressContact.IAddAddressPresenter>
        implements AddAddressContact.IAddAddressView {


    @BindView(R.id.address_new_name)
    XEditText addressNewName;
    @BindView(R.id.address_new_phone)
    XEditText addressNewPhone;
    @BindView(R.id.address_new_address)
    XEditText addressNewAddress;
    @BindView(R.id.address_new_sj)
    TextView addressNewSj;
    @BindView(R.id.address_new_xx)
    XEditText addressNewXx;
    @BindView(R.id.address_new_zipcode)
    XEditText addressNewZipcode;
    @BindView(R.id.address_new_btn)
    Button addressNewBtn;


    @Override
    protected void initView() {
        getSupportActionBar().hide();
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_add_my_address;
    }

    @OnClick({R.id.address_new_sj, R.id.address_new_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_new_sj:


                break;
            case R.id.address_new_btn:
                Map<String, String> params = new HashMap<>();
                params.put("userId", ShapedP.getmInstance().getSP("userId"));
                params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));

                Map<String, String> paramsBody = new HashMap<>();
                paramsBody.put("realName",addressNewName.getTrimmedString());
                paramsBody.put("phone",addressNewPhone.getTrimmedString());
                paramsBody.put("address",addressNewXx.getTrimmedString());
                paramsBody.put("zipCode",addressNewZipcode.getTrimmedString());
                presenter.setAddAddressList(params,paramsBody);
                break;
        }
    }

    @Override
    public void onSeccess(String meg) {
        ToastUtils.showLong(meg);

        finish();
    }

    @Override
    public void onFail(String s) {
        ToastUtils.showLong(s);
    }

    @Override
    public BasePresenter initPresenter() {
        return new AddMyAddPresenter();
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
