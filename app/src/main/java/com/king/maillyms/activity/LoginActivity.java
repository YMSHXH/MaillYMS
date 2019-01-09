package com.king.maillyms.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.google.gson.Gson;
import com.king.maillyms.R;
import com.king.maillyms.apis.LoginApis;
import com.king.maillyms.beans.LoginBean;
import com.king.maillyms.contact.LoginContact;
import com.king.maillyms.presenter.LoginPresenter;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class LoginActivity extends BaseMvpActivity<LoginContact.ILoginModel,LoginContact.ILoginPresenter> implements LoginContact.ILoginView {

    UMShareAPI umShareAPI;

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
        getSupportActionBar().hide();

        //判断是否是首次登录
        isToOne();

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

        ckb_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ShapedP.getmInstance().putSP("isOne","one");
                } else {
                    ShapedP.getmInstance().putSP("isOne","not");
                }
            }
        });
    }

    private void isToOne() {
        String isOne = ShapedP.getmInstance().getSP("isOne");
        if (isOne.equals("one")){
            toIntent(MainActivity.class);
            finish();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        umShareAPI = UMShareAPI.get(this);
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
            ShapedP.getmInstance().putSP("phone",ed_phone.getText().toString());
            ShapedP.getmInstance().putSP("pwd",ed_pwd.getText().toString());
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
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 2000) {
            String phone = data.getStringExtra("phone");
            String pwd = data.getStringExtra("pwd");
            ed_phone.setText(phone);
            ed_pwd.setText(pwd);
        }
    }

    public void QQLogin(View view) {

        if (umShareAPI != null) {
            toToast("正在使用QQ登录");
            umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QZONE, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {
                    Log.e("onStart=======","onStart");
                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    //System.out.println("回调成功");
                    Log.e("=======","成功");
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                    Log.e("throwable=======","throwable");
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {
                    Log.e("onCancel=======","onCancel");
                }
            });
        }
    }

    //设置生命周期

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }
}
