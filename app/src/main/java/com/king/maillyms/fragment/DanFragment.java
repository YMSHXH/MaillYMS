package com.king.maillyms.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lib_core.base.BaseFragment;
import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.adapter.DanAdapter;
import com.king.maillyms.beans.DanBean;
import com.king.maillyms.contact.DanListContact;
import com.king.maillyms.presenter.DanListPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 根据订单状态查询订单信息
 */
public class DanFragment extends BaseMvpFragment<DanListContact.IDanListModel,DanListContact.IDanListPresenter>
        implements DanListContact.IDanListView {
    @BindView(R.id.dan_all_list)
    ImageView danAllList;
    @BindView(R.id.dan_pay)
    ImageView danPay;
    @BindView(R.id.dan_receive)
    ImageView danReceive;
    @BindView(R.id.dan_comment)
    ImageView danComment;
    @BindView(R.id.dan_finish)
    ImageView danFinish;
    @BindView(R.id.dan_xrecy)
    XRecyclerView danRecy;
    Unbinder unbinder;
    private DanAdapter danAdapter;
    private Map<String, String> paramsBody;
    private Map<String, String> params;

    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_dan;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        danRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置适配器
        danAdapter = new DanAdapter(getActivity());
        danRecy.setAdapter(danAdapter);

//        Button gaode = view.findViewById(R.id.gaode);//
//        gaode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),"gaode",Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(getActivity(),AmapActivity.class);
//                startActivity(intent);
//            }
//        });


    }

    @Override
    protected void init() {
        params = new HashMap<>();
        params.put("userId", ShapedP.getmInstance().getSP("userId"));
        params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));
        paramsBody = new HashMap<>();
        paramsBody.put("status","0");
        paramsBody.put("page","1");
        paramsBody.put("count","10");
        presenter.setDanList(params, paramsBody);
    }

    @OnClick({R.id.dan_all_list, R.id.dan_pay, R.id.dan_receive, R.id.dan_comment, R.id.dan_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dan_all_list:
                paramsBody.put("status","0");
                presenter.setDanList(params, paramsBody);
                break;
            case R.id.dan_pay:
                paramsBody.put("status","1");
                presenter.setDanList(params, paramsBody);
                break;
            case R.id.dan_receive:
                paramsBody.put("status","2");
                presenter.setDanList(params, paramsBody);
                break;
            case R.id.dan_comment:
                paramsBody.put("status","3");
                presenter.setDanList(params, paramsBody);
                break;
            case R.id.dan_finish:
                paramsBody.put("status","9");
                presenter.setDanList(params, paramsBody);
                break;
        }
    }

    @Override
    public void onSuccess(List<DanBean.OrderListBean> list) {
        //Toast.makeText(getActivity(),"根据订单状态查询订单信息==" + list.size(),Toast.LENGTH_SHORT).show();
        for (DanBean.OrderListBean orderListBean : list) {
            System.out.println(orderListBean.getOrderId());//查询订单号
        }
        danAdapter.setList(list);
    }

    @Override
    public void onFile(String msg) {

    }

    @Override
    public BasePresenter initPresenter() {
        return new DanListPresenter();
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
}
