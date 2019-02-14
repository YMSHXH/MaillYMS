package com.king.maillyms.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.adapter.SearchAdapter;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.beans.SearchBean;
import com.king.maillyms.nets.RetrofitUtils;
import com.king.maillyms.nets.RetrofitUtilsCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    private XRecyclerView xre_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        EventBus.getDefault().register(this);
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        xre_search = findViewById(R.id.xre_search);
        xre_search.setLayoutManager(new GridLayoutManager(this,2));
    }

    //获取数据
    @Subscribe(sticky = true)
    public void getSearch(String goods_name){
        Toast.makeText(SearchActivity.this,goods_name,Toast.LENGTH_SHORT).show();
        Map<String,String> params = new HashMap<>();
        params.put("keyword",goods_name);
        params.put("page",1 + "");
        params.put("count",10 + "");
        RetrofitUtils.getInstance().reqtoSear(ProductApis.GOODS_SEARCH, params, new RetrofitUtilsCallBack() {
            @Override
            public void failure(String msg) {
                Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void success(SearchBean body) {
                Toast.makeText(SearchActivity.this, body.getMessage(), Toast.LENGTH_LONG).show();
                if ("0000".equals(body.getStatus())){
                    List<SearchBean.ResultBean> list = body.getResult();
                    SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this,list);
                    xre_search.setAdapter(searchAdapter);
                }
            }
        });
    }
}
