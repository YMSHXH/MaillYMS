package com.king.maillyms.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.adapter.QuanAdapter;
import com.king.maillyms.beans.entity.QuanBean;
import com.king.maillyms.contact.QuanContact;
import com.king.maillyms.presenter.QuanPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuanFragment extends BaseMvpFragment<QuanContact.IQuanModel, QuanContact.IQuanPresenter>
        implements QuanContact.IQuanView {
    @BindView(R.id.xrecy)
    XRecyclerView xrecy;
    Unbinder unbinder;
    private QuanAdapter quanAdapter;
    private Map<String, String> params;
    private Map<String, String> headParams;
    private int page = 1;

    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_quan;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        //对XrecycleView进行处理
        xrecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        //设置适配器
        quanAdapter = new QuanAdapter(getActivity());
        xrecy.setAdapter(quanAdapter);

        //设置刷新加载
        xrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                params.put("page", 1 + "");
                presenter.setQuanList(params, headParams);
                xrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                params.put("page", page + "");
                presenter.setQuanList(params, headParams);
                xrecy.loadMoreComplete();
            }
        });
    }

    @Override
    protected void init() {
        params = new HashMap<>();
        params.put("page", 1 + "");
        params.put("count", 10 + "");
        //头部入参
        headParams = new HashMap<>();
        headParams.put("userId", ShapedP.getmInstance().getSP("userId"));
        headParams.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));
        presenter.setQuanList(params, headParams);
    }

    @Override
    public void onSeccess(List<QuanBean> list) {
        //Toast.makeText(getActivity(), list.size() + "", Toast.LENGTH_SHORT).show();
        if (page == 1){
            quanAdapter.setList(list);
        } else {
            quanAdapter.addList(list);
        }
        page ++ ;
    }

    @Override
    public void onFail(String s) {

    }

    @Override
    public BasePresenter initPresenter() {
        return new QuanPresenter();
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
