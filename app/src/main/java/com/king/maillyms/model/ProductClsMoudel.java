package com.king.maillyms.model;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.example.lib_network.bean.BaseResponseBean;
import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.apis.apiserver.ProudctClsApiServer;
import com.king.maillyms.beans.entity.ProducetClsBean;
import com.king.maillyms.contact.ProductClsContact;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ProductClsMoudel implements ProductClsContact.IProductClsMoudel {

    @SuppressLint("CheckResult")
    @Override
    public void setProductClsList(Map<String, String> params, final ProductClsMoudelCallBack clsMoudelCallBack) {
        RetrofitUtils.getInstance().createService(ProudctClsApiServer.class)
                .requestProducetCls(ProductApis.PRODUCT_CLS,params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseBean<List<ProducetClsBean>>>() {
                    @Override
                    public void accept(BaseResponseBean<List<ProducetClsBean>> listBaseResponseBean) throws Exception {
                        if("0000".equals(listBaseResponseBean.status)) {
                            clsMoudelCallBack.onSuccess(listBaseResponseBean.result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        clsMoudelCallBack.onFile("网络错误");
                    }
                });
    }

    public interface ProductClsMoudelCallBack {
        void onSuccess(List<ProducetClsBean> list);
        void onFile(String msg);
    }
}
