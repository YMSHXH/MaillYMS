package com.king.maillyms.fragment;

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
import com.king.maillyms.adapter.ShoppingCarAdapter;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.beans.entity.ShoppingCarBean;
import com.king.maillyms.contact.ShoppingCarContact;
import com.king.maillyms.presenter.ShoppingCarPresenter;

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
        ckbQuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (ShoppingCarBean shoppingCarBean : splist) {
                    shoppingCarBean.setChedcked(isChecked);
                }
                //计算总和
                sumPrice();
                shoppingCarAdapter.notifyDataSetChanged();
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
        Toast.makeText(getActivity(), list.size() + "", Toast.LENGTH_SHORT).show();
        splist = list;
//        ShoppingCarBean shoppingCarBean = new ShoppingCarBean("5","双头两用修容笔",
//                "3","http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","39");
//        ShoppingCarBean shoppingCarBean2 = new ShoppingCarBean("6","轻柔系自然裸妆假睫毛",
//                "4","http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","39");
//        splist.add(shoppingCarBean);
//        splist.add(shoppingCarBean2);
        //添加数据
        shoppingCarAdapter.setList(splist);

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
     * 适配器接口回调
     */
    @Override
    public void notifySum() {
        sumPrice();
    }
}
