package com.king.maillyms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.adapter.SearchAdapter;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.beans.SearchBean;
import com.king.maillyms.contact.SearchContact;
import com.king.maillyms.greendao.DaoSession;
import com.king.maillyms.nets.RetrofitUtils;
import com.king.maillyms.nets.RetrofitUtilsCallBack;
import com.king.maillyms.presenter.SerachPresenter;
import com.king.maillyms.utils.GreendaoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends BaseMvpActivity<SearchContact.ISearchMoudel,SearchContact.ISerchPresenter>
        implements SearchContact.ISearchView,SearchAdapter.SearchCallBack {

    String goods_name = null;
    private XRecyclerView xre_search;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//        EventBus.getDefault().register(this);
//        getSupportActionBar().hide();
//        setFullScreenEnable(true);
//        initView();
//    }


    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        getSupportActionBar().hide();
        setFullScreenEnable(true);
        xre_search = findViewById(R.id.xre_search);
        xre_search.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    protected void initData() {
        super.initData();
        Map<String,String> params = new HashMap<>();
        params.put("keyword",goods_name);
        params.put("page",1 + "");
        params.put("count",10 + "");
        presenter.setSearchList(ProductApis.GOODS_SEARCH, params);
    }

    @Override
    protected int getResLayoutById() {
        return R.layout.activity_search;
    }

    //获取数据
    @Subscribe(sticky = true)
    public void getSearch(String goods_name){
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
        if ("0000".equals(body.getStatus())){
            List<SearchBean.ResultBean> list = body.getResult();
            
            //添加到数据库
//            DaoSession daoSession = GreendaoUtils.getInstance().getDaoSession();
//            for (SearchBean.ResultBean resultBean : list) {
//                daoSession.insert(list);
//            }
            SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this,list);
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
        Intent intent = new Intent(this,GoodsDetailsActivity.class);
        intent.putExtra("commodityId",s);
        startActivity(intent);
    }
}
