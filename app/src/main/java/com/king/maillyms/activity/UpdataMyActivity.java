package com.king.maillyms.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.king.maillyms.R;
import com.king.maillyms.contact.UpdateAddressContact;
import com.king.maillyms.presenter.UpdateMyAddPresenter;
import com.xw.repo.XEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdataMyActivity extends BaseMvpActivity<UpdateAddressContact.IUpdateAddressModel,UpdateAddressContact.IUpdateAddressPresenter>
        implements UpdateAddressContact.IUpdateAddressView {

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
    private String id;

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String realName = intent.getStringExtra("realName");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");
        String zipCode = intent.getStringExtra("zipCode");

        addressNewName.setText(realName);
        addressNewPhone.setText(phone);
        addressNewXx.setText(address);
        addressNewZipcode.setText(zipCode);
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
                paramsBody.put("id",id);
                paramsBody.put("realName",addressNewName.getTrimmedString());
                paramsBody.put("phone",addressNewPhone.getTrimmedString());
                paramsBody.put("address",addressNewXx.getTrimmedString());
                paramsBody.put("zipCode",addressNewZipcode.getTrimmedString());
                presenter.setUpdateAddressList(params,paramsBody);
                break;
        }
    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_updata_my;
    }

    @Override
    public void onSeccess(String meg) {
        ToastUtils.showLong(meg);

    }

    @Override
    public void onFail(String s) {
        ToastUtils.showLong(s);
    }

    @Override
    public BasePresenter initPresenter() {
        return new UpdateMyAddPresenter();
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
