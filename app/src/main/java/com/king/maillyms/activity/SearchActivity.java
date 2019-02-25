package com.king.maillyms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.adapter.SearchAdapter;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.beans.SearchBean;
import com.king.maillyms.contact.SearchContact;
import com.king.maillyms.myview.SearchView;
import com.king.maillyms.presenter.SerachPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseMvpActivity<SearchContact.ISearchMoudel, SearchContact.ISerchPresenter>
        implements SearchContact.ISearchView, SearchAdapter.SearchCallBack {

    String goods_name = null;
    @BindView(R.id.not_search)
    ImageView notSearch;
    @BindView(R.id.not_goods)
    TextView notGoods;
    @BindView(R.id._serach)
    SearchView Serach;
    private XRecyclerView xre_search;
    private Map<String, String> params;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getSupportActionBar().hide();
        setFullScreenEnable(true);
        xre_search = findViewById(R.id.xre_search);
        xre_search.setLayoutManager(new GridLayoutManager(this, 2));

        Serach.setSearchViewCallBack(new SearchView.SearchViewCallBack() {
            @Override
            public void setTwoma(View v) {

            }

            @Override
            public void setBtn_searc(String goods_name) {
                params.put("keyword", goods_name);
                presenter.setSearchList(ProductApis.GOODS_SEARCH, params);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        params = new HashMap<>();
        params.put("keyword", goods_name);
        params.put("page", 1 + "");
        params.put("count", 10 + "");
        presenter.setSearchList(ProductApis.GOODS_SEARCH, params);

    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_search;
    }

    //获取数据
    @Subscribe(sticky = true)
    public void getSearch(String goods_name) {
        this.goods_name = goods_name;
    }


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

    @Override
    public void onSuccess(SearchBean body) {
        Toast.makeText(SearchActivity.this, body.getMessage(), Toast.LENGTH_LONG).show();
        if ("0000".equals(body.getStatus())) {
            List<SearchBean.ResultBean> list = body.getResult();

            if (list == null || list.size() == 0) {
                notSearch.setVisibility(View.VISIBLE);
                notGoods.setVisibility(View.VISIBLE);
                //setContentView(R.layout.search_null);
                return;
            }
            notSearch.setVisibility(View.GONE);
            notGoods.setVisibility(View.GONE);

            //添加到数据库
//            DaoSession daoSession = GreendaoUtils.getInstance().getDaoSession();
//            for (SearchBean.ResultBean resultBean : list) {
//                daoSession.insert(list);
//            }
            SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this, list);
            xre_search.setAdapter(searchAdapter);
            searchAdapter.setSearchCallBack(this);
        }
    }

    @Override
    public void onFile(String msg) {
        Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public BasePresenter initPresenter() {
        return new SerachPresenter();
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
    public void setOnClickListener(String s) {
        Intent intent = new Intent(this, GoodsDetailsActivity.class);
        intent.putExtra("commodityId", s);
        startActivity(intent);
    }

}
