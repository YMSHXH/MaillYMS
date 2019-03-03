package com.king.maillyms.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.king.maillyms.R;
import com.king.maillyms.adapter.SumMoneyAdapter;
import com.king.maillyms.beans.entity.ShoppingCarBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SumMoneyActivity extends AppCompatActivity {

    @BindView(R.id.sun_recy)
    RecyclerView sunRecy;
    @BindView(R.id.sum_jia)
    TextView sumJia;
    @BindView(R.id.sum_ti)
    TextView sumTi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_money);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        sunRecy.setLayoutManager(new LinearLayoutManager(this));
    }


    @Subscribe(sticky = true)
    public void getSunList(List<ShoppingCarBean> tosumlist) {
        //ToastUtils.showLong(tosumlist.size() + "");
        SumMoneyAdapter sumMoneyAdapter = new SumMoneyAdapter(this,tosumlist);
        sunRecy.setAdapter(sumMoneyAdapter);
    }

    @OnClick(R.id.sum_ti)
    public void onViewClicked() {
    }
}
