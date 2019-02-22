package com.king.maillyms.fragment.detailsfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.lib_core.base.BaseFragment;
import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.google.gson.Gson;
import com.king.maillyms.R;
import com.king.maillyms.adapter.GoodsDetailsAdapter;
import com.king.maillyms.beans.GoodsDetails;
import com.king.maillyms.contact.GoodsDetailsContact;
import com.king.maillyms.presenter.GoodsDetailsPresenter;

import java.util.HashMap;

import butterknife.BindView;

public class DetaFragment extends BaseMvpFragment<GoodsDetailsContact.IGoodsDetailsModel,
        GoodsDetailsContact.IGoodsDetailsPresenter> implements GoodsDetailsContact.IGoodsDetailsView {

    @BindView(R.id.goods_recy)
    RecyclerView goods_recy;
    private GoodsDetailsAdapter goodsDetailsAdapter;
    private GoodsDetails.ResultBean result;

    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_goods_details;
    }

    @Override
    protected void initView(View view) {
        goods_recy.setLayoutManager(new LinearLayoutManager(getActivity()));

        result = new GoodsDetails.ResultBean();
    }

//    @Override
//    protected void initData() {
//        super.initData();
//
//
//
//    }

    @Override
    protected void init() {
        Intent intent = getActivity().getIntent();
        String commodityId = intent.getStringExtra("commodityId");

        HashMap<String, String> param = new HashMap<>();
        param.put("commodityId",commodityId);
        param.put("userId",ShapedP.getmInstance().getSP("userId"));
        param.put("sessionId",ShapedP.getmInstance().getSP("sessionId"));
        presenter.setGoodsDetailsData(param);
    }

    @Override
    public void onSeccess(String meg) {
        //Toast.makeText(getActivity(),meg,Toast.LENGTH_SHORT).show();
        GoodsDetails goodsDetails = new Gson().fromJson(meg, GoodsDetails.class);
        if ("0000".equals(goodsDetails.getStatus())){
            result = goodsDetails.getResult();
            Toast.makeText(getActivity(), result.getCommodityName(),Toast.LENGTH_SHORT).show();
            goodsDetailsAdapter = new GoodsDetailsAdapter(getActivity(), result);
            goods_recy.setAdapter(goodsDetailsAdapter);
        }
    }

    @Override
    public void onFail(String s) {

    }

    @Override
    public BasePresenter initPresenter() {
        return new GoodsDetailsPresenter();
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
