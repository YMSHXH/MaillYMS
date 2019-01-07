package com.king.maillyms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.google.gson.Gson;
import com.king.maillyms.R;
import com.king.maillyms.apis.LoginApis;
import com.king.maillyms.beans.LoginBean;
import com.king.maillyms.contact.LoginContact;
import com.king.maillyms.contact.RegContact;
import com.king.maillyms.presenter.RegPresenter;

import java.util.HashMap;

import butterknife.BindView;

public class RegActivity extends BaseMvpActivity<RegContact.IRegModel,RegContact.IRegPresenter> implements RegContact.IRegView {

    @BindView(R.id.rphone)
    EditText ed_phone;

    @BindView(R.id.rpwd)
    EditText ed_pwd;

    @BindView(R.id.btn_reg)
    Button btn_reg;

    private HashMap<String, String> params;

    @Override
    protected void initView() {
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ed_phone.getText().toString();
                String pwd = ed_pwd.getText().toString();

                params = new HashMap<>();
                params.put("phone",phone);
                params.put("pwd",pwd);
                presenter.setRegList(params);
            }
        });
    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_reg;
    }


    @Override
    public void onSeccess(String meg) {

        toToast(meg);
        LoginBean loginBean = new Gson().fromJson(meg, LoginBean.class);
        if ("注册成功".equals(loginBean.getMessage())){
            Intent intent = new Intent();
            intent.putExtra("phone",ed_phone.getText().toString());
            intent.putExtra("pwd",ed_pwd.getText().toString());
            setResult(2000,intent);
            finish();
        }
    }

    @Override
    public void onFail(String s) {

    }

    @Override
    public BasePresenter initPresenter() {
        return new RegPresenter();
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
