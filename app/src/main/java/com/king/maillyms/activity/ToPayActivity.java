package com.king.maillyms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_core.utils.ShapedP;
import com.king.maillyms.R;
import com.king.maillyms.contact.ToPayDanContact;
import com.king.maillyms.presenter.ToPayPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToPayActivity extends AppCompatActivity implements ToPayDanContact.IToPayDanView {

    @BindView(R.id.pay_ckb_yu)
    CheckBox payCkbYu;
    @BindView(R.id.pay_ckb_wei)
    CheckBox payCkbWei;
    @BindView(R.id.pay_ckb_zhi)
    CheckBox payCkbZhi;
    @BindView(R.id.pay_topay)
    Button payTopay;
    private String payAmount;
    private String orderId;
    private Map<String, String> params;
    private Map<String, String> paramBody;
    private ToPayPresenter toPayPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_pay);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        setFullScreenEnable(true);

        //获取支付的订单ID
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        payAmount = intent.getStringExtra("payAmount");
        //System.out.println("订单号:"+orderId + "支付金额:" + payAmount);
        //设置支付信息
        payTopay.setText("支付"+ payAmount +"元");
        initData();
    }

    private void initData() {

        //去支付
        toPayPresenter = new ToPayPresenter(this);

        params = new HashMap<>();
        params.put("userId", ShapedP.getmInstance().getSP("userId"));
        params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));

        paramBody = new HashMap<>();
        paramBody.put("orderId",orderId);


    }


    @OnClick({R.id.pay_ckb_yu, R.id.pay_ckb_wei, R.id.pay_ckb_zhi, R.id.pay_topay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_ckb_yu:
                payTopay.setText("余额支付"+payAmount+"元");
                payCkbWei.setChecked(false);
                payCkbZhi.setChecked(false);
                break;
            case R.id.pay_ckb_wei:
                payTopay.setText("微信支付"+payAmount+"元");
                payCkbYu.setChecked(false);
                payCkbZhi.setChecked(false);
                break;
            case R.id.pay_ckb_zhi:
                payTopay.setText("支付宝支付"+payAmount+"元");
                payCkbYu.setChecked(false);
                payCkbWei.setChecked(false);
                break;
            case R.id.pay_topay:
                //获取当前支付状态
                boolean payYu = payCkbYu.isChecked();
                boolean payWei = payCkbWei.isChecked();
                boolean payZhi = payCkbZhi.isChecked();

                //判断使用什么方式支付
                if (payYu){//余额支付
                    paramBody.put("payType","1");
                    toPayPresenter.setDeleteList(params,paramBody);
                } else if (payWei){//微信支付
                    ToastUtils.showLong("暂不支持微信支付");
                } else if (payZhi){//支付宝支付
                    ToastUtils.showLong("暂不支持支付宝支付");
                } else { //没有选中支付方式
                    Toast.makeText(ToPayActivity.this,"您没有选中支付方式,请选择其中的一种支付方式去支付",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void onYuSuccess(String msg) {
        ToastUtils.showLong(msg);
    }

    @Override
    public void onYuFile(String msg) {
        ToastUtils.showLong(msg);
    }

    /**
     * 沉浸式
     *
     * @param enable
     */
    private void setFullScreenEnable(boolean enable) {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if (enable) {
            // 布局占用状态栏，并隐藏状态栏，不影响导航栏
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else {
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        // 全屏布局，状态栏和导航栏覆盖在布局上
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.setAttributes(params);
    }
}
