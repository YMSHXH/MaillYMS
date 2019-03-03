package com.king.maillyms.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.lib_core.base.BaseFragment;
import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.activity.AmapActivity;
import com.king.maillyms.activity.GoodsDetailsActivity;
import com.king.maillyms.activity.HomeProducetClsActivity;
import com.king.maillyms.activity.ProductActivity;
import com.king.maillyms.activity.SearchActivity;
import com.king.maillyms.adapter.HomeAdapter;
import com.king.maillyms.beans.BannerBean;
import com.king.maillyms.beans.ProductBean;
import com.king.maillyms.contact.ProductContact;
import com.king.maillyms.myview.SearchView;
import com.king.maillyms.presenter.ProudPresenter;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends BaseMvpFragment<ProductContact.IProductModel,ProductContact.IProductPresenter>
        implements ProductContact.IProductView,SearchView.SearchViewCallBack,HomeAdapter.HomeCallBack  {


    private HomeAdapter homeAdapter;
    private XRecyclerView recyclerView;
    private SearchView serach;
    ProductBean.ResultBean resultBean;
    private List<BannerBean.ResultBean> list;
    private XBanner xbanner;
    private View headview;
    private double width_height = 2;//宽高比

    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        list = new ArrayList<>();
        serach = view.findViewById(R.id._serach);

        serach.setSearchViewCallBack(this);
        recyclerView = view.findViewById(R.id.recyclerView);
        resultBean = new ProductBean.ResultBean();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        headview = LayoutInflater.from(getActivity())
                .inflate(R.layout.item_home_banner,recyclerView,false);

        xbanner = headview.findViewById(R.id.xbanner);

    }

    @Override
    protected void init() {
        HashMap<String, String> param = null;

        presenter.setBannerList(param);
        presenter.setProductList(param);
    }

    @Override
    public void onSeccess(String meg) {
        //Toast.makeText(getActivity(),meg,Toast.LENGTH_SHORT).show();
        ProductBean productBean = new Gson().fromJson(meg, ProductBean.class);

        if ("0000".equals(productBean.getStatus())){
//            Toast.makeText(getActivity(),productBean.getMessage(),Toast.LENGTH_SHORT).show();
            //if (homeAdapter==null){
                resultBean = productBean.getResult();
                //Toast.makeText(getActivity(),"b"+list.size(),Toast.LENGTH_SHORT).show();
                homeAdapter = new HomeAdapter(getActivity(),resultBean);
                recyclerView.setAdapter(homeAdapter);

            homeAdapter.setHomeCallBack(this);

        }


    }

    @Override
    public void onFail(String s) {

    }

    /**
     * 追加
     * @param s
     */
    @Override
    public void onBannerSeccess(String s) {
        BannerBean bannerBean = new Gson().fromJson(s, BannerBean.class);
        String status = bannerBean.getStatus();
        if ("0000".equals(status)){

            list = bannerBean.getResult();
            xbanner.setData(list,null);
            //xbanner.setIsClipChildrenMode(true);
            //xbanner.setScrollBarSize();
//            Glide.with(getActivity())
//                    .asBitmap()
//                    .load(list.get(0).getImageUrl())
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                            //获取xbanner的宽度
//                            double width = xbanner.getWidth();
//                            double imagewidth = resource.getWidth();
//                            double imageheight = resource.getHeight();
//                            width_height = imagewidth / imageheight;
//                            Log.e("宽高比",width_height + "");
//                            //获取高度
//                            double height = width / width_height;
//                            //设置banner 的高度
//                            xbanner.setLayoutParams(new ConstraintLayout.LayoutParams((int)width,(int)height));
//                        }
//                    });
            Glide.with(getActivity())
                    .load(list.get(0).getImageUrl())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            //获取xbanner的宽度
                            double width = xbanner.getWidth();
                            double imagewidth = resource.getWidth();
                            double imageheight = resource.getHeight();
                            width_height = imagewidth / imageheight;
                            //Log.e("宽高比",width_height + "");
                            //获取高度
                            double height = width / width_height;
                            Log.e("高度",height + "" );
                            //设置banner 的高度
                            xbanner.setLayoutParams(new ConstraintLayout.LayoutParams((int)width,(int)height));
                        }
                    });
            xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(getActivity())
                            .load(list.get(position).getImageUrl())
                            .into((ImageView) view);

                }
            });
            recyclerView.addHeaderView(headview);
        }
    }

    @Override
    public void onBannerFail(String s) {

    }

    @Override
    public BasePresenter initPresenter() {
        return new ProudPresenter();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if ( requestCode == 201 ){
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS){
                    String result = bundle.getString(CodeUtils.RESULT_STRING);

                    Toast.makeText(getActivity(),"解析结果"+result ,Toast.LENGTH_SHORT).show();;
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED){
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    public void setTwoma(View v) {
        Intent intent = new Intent(getActivity(),CaptureActivity.class);
        startActivityForResult(intent,201);
    }

    @Override
    public void setBtn_searc(String goods_name) {
        if (goods_name != null && !"".equals(goods_name)) {
            EventBus.getDefault().postSticky(goods_name);
            startActivity(new Intent(getActivity(),SearchActivity.class));
        } else {
            Toast.makeText(getActivity(), "输入商品信息为空,请重新输入", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    @Override
    public void setOnClickReListener(String s) {
//        Intent intent2 = new Intent(getActivity(),ProductActivity.class);
//        intent2.putExtra("Id",s);
//        startActivity(intent2);
        EventBus.getDefault().postSticky(s);
        startActivity(new Intent(getActivity(),HomeProducetClsActivity.class));
    }
}
