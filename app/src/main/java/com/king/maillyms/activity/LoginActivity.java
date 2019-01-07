package com.king.maillyms.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
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
import com.king.maillyms.presenter.LoginPresenter;

import java.util.HashMap;

import butterknife.BindView;

public class LoginActivity extends BaseMvpActivity<LoginContact.ILoginModel,LoginContact.ILoginPresenter> implements LoginContact.ILoginView {

    @BindView(R.id.phone)
    EditText ed_phone;

    @BindView(R.id.pwd)
    EditText ed_pwd;

    @BindView(R.id.ckb_pwd)
    CheckBox ckb_pwd;

    @BindView(R.id.tv_kuai)
    TextView tv_kuai;

    @BindView(R.id.btn_login)
    Button btn_login;
    private HashMap<String, String> params;


    @Override
    protected void initView() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ed_phone.getText().toString();
                String pwd = ed_pwd.getText().toString();

                params = new HashMap<>();
                params.put("phone",phone);
                params.put("pwd",pwd);
                presenter.setLoginList(params);

            }
        });

        tv_kuai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegActivity.class);
                startActivityForResult(intent, 1000);
                //startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_login;
    }

    @Override
    public BasePresenter initPresenter() {
        return new LoginPresenter();
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

    @Override
    public void onSeccess(String meg) {
        LoginBean loginBean = new Gson().fromJson(meg, LoginBean.class);
        if ("登录成功".equals(loginBean.getMessage())){
            toIntent(MainActivity.class);
            finish();
        }
        //toToast(meg);
    }

    @Override
    public void onFail(String s) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 2000) {
            String phone = data.getStringExtra("phone");
            String pwd = data.getStringExtra("pwd");
            ed_phone.setText(phone);
            ed_pwd.setText(pwd);
        }
    }
}
