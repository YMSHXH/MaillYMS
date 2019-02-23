package com.king.maillyms.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.adapter.HomeProductClsAdapter;
import com.king.maillyms.beans.entity.ProducetClsBean;
import com.king.maillyms.contact.ProductClsContact;
import com.king.maillyms.presenter.ProductClsPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类
 */
public class HomeProducetClsActivity extends BaseMvpActivity<ProductClsContact.IProductClsMoudel, ProductClsContact.IProductClsPresenter>
        implements ProductClsContact.IProductClsView {


    String labelId = null;
    @BindView(R.id.xrecyView_Cls)
    XRecyclerView xrecyViewCls;
    private Map<String, String> params;
    private HomeProductClsAdapter homeProductClsAdapter;
    private Toolbar toolbar;

    @Subscribe(sticky = true)
    public void setLabelId(String id) {
        //ToastUtils.showLong(id);
        labelId = id;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu);

        //设置种类
        xrecyViewCls.setLayoutManager(new GridLayoutManager(this,2));

        //设置适配器
        homeProductClsAdapter = new HomeProductClsAdapter(this);
        xrecyViewCls.setAdapter(homeProductClsAdapter);

//        switch (item.getItemId()) {
//            case android.R.id.home: //android.R.id.home是Android内置home按钮的id
//                finish();
//                break;
//            case R.id.action_search://搜索Item的ID
//                Toast.makeText(this, "你要搜索啥？？", Toast.LENGTH_SHORT).show();
//                break;
//        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case android.R.id.home: //android.R.id.home是Android内置home按钮的id
                        finish();
                        break;
                    case R.id.action_search://搜索Item的ID
                        //获取数据的

                        Toast.makeText(HomeProducetClsActivity.this, "搜索" , Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }


    @Override
    protected void initData() {
        super.initData();
        params = new HashMap<>();
        params.put("labelId", labelId);
        params.put("page", "1");
        params.put("count", "10");
        presenter.setProductClsList(params);
    }

    //加ToolBar对应的Menu Item
    @Override
    protected int getResLayoutById() {
        return R.layout.activity_home_producet_cls;
    }

    @Override
    public void onSuccess(List<ProducetClsBean> list) {
        //ToastUtils.showLong(list.size()+"");
        homeProductClsAdapter.setList(list);
    }

    @Override
    public void onFile(String msg) {
        ToastUtils.showLong(msg);
    }

    @Override
    public BasePresenter initPresenter() {
        return new ProductClsPresenter();
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
}
