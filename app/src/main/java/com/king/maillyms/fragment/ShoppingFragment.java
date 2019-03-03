package com.king.maillyms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.activity.SumMoneyActivity;
import com.king.maillyms.adapter.ShoppingCarAdapter;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.beans.entity.ShoppingCarBean;
import com.king.maillyms.contact.ShoppingCarContact;
import com.king.maillyms.presenter.ShoppingCarPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingFragment extends BaseMvpFragment<ShoppingCarContact.IShoppingCarMoudel, ShoppingCarContact.IShoppingPresenter>
        implements ShoppingCarContact.IShoppingCarView, ShoppingCarAdapter.ShoppingCarAdapterCallBack {
    @BindView(R.id.xrecyView)
    XRecyclerView xrecyView;
    @BindView(R.id.ckb_quan)
    CheckBox ckbQuan;
    @BindView(R.id.go_pay)
    TextView goPay;
    @BindView(R.id.ckb_sum)
    TextView ckbSum;
    Unbinder unbinder;
    private ShoppingCarAdapter shoppingCarAdapter;
    private List<ShoppingCarBean> splist;

    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        xrecyView.setLayoutManager(new LinearLayoutManager(getActivity()));

        shoppingCarAdapter = new ShoppingCarAdapter(getActivity());
        xrecyView.setAdapter(shoppingCarAdapter);
        shoppingCarAdapter.setShoppingCarAdapterCallBack(this);

        //进行全选.反选 设置
        ckbQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ckbQuan.isChecked();
                for (ShoppingCarBean shoppingCarBean : splist) {
                    shoppingCarBean.setChedcked(checked);
                }
                //计算总和
                sumPrice();
                shoppingCarAdapter.notifyDataSetChanged();
            }
        });

        goPay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<ShoppingCarBean> tosumlist = new ArrayList<>();
                //拿到数据
                for (ShoppingCarBean shoppingCarBean : splist) {
                    if (shoppingCarBean.isChedcked()){
                        tosumlist.add(shoppingCarBean);
                    }
                }

                EventBus.getDefault().postSticky(tosumlist);
                Intent intent = new Intent(getActivity(),SumMoneyActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 计算价格
     */
    private void sumPrice() {
        double sum_price = 0;
        for (ShoppingCarBean shoppingCarBean : splist) {
            if (shoppingCarBean.isChedcked()){
                double price = Double.parseDouble(shoppingCarBean.getPrice());
                int count = Integer.parseInt(shoppingCarBean.getCount());
                sum_price +=  price * count ;
            }
        }
        ckbSum.setText("合计 ￥：" + sum_price);
    }

    @Override
    protected void init() {
        splist = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        params.put("userId", ShapedP.getmInstance().getSP("userId"));
        params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));
        presenter.setShoppingCarList(ProductApis.SHOPPING_SEARCH, params);
    }

    @Override
    public void onSuccess(List<ShoppingCarBean> list) {
        //Toast.makeText(getActivity(), list.size() + "", Toast.LENGTH_SHORT).show();
        splist = list;
        //添加数据
        shoppingCarAdapter.setList(list);

    }

    @Override
    public void onFile(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public BasePresenter initPresenter() {
        return new ShoppingCarPresenter();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    /**
     * 适配器接口回调1
     */
    @Override
    public void notifyCheck(boolean isCheck) {
        boolean isAllCheck = true;
        for (ShoppingCarBean shoppingCarBean : splist) {
            if (shoppingCarBean.isChedcked()!=true){
                ckbQuan.setChecked(false);
                isAllCheck = false;
            }
        }
        if (isAllCheck) {
            ckbQuan.setChecked(isCheck);
        }
        sumPrice();
    }
    /**
     * 适配器接口回调2
     */
    @Override
    public void notifySum() {
        sumPrice();
    }
}
