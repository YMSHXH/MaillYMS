package com.king.maillyms.model;

import android.annotation.SuppressLint;

import com.example.lib_network.bean.BaseResponseBean;
import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.apis.apiserver.QuanApiServer;
import com.king.maillyms.beans.entity.QuanBean;
import com.king.maillyms.contact.QuanContact;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class QuanModel implements QuanContact.IQuanModel {
    @SuppressLint("CheckResult")
    @Override
    public void setQuanList(Map<String, String> params, Map<String, String> headParams, final QuanModelCallBack quanModelCallBack) {
        RetrofitUtils.getInstance().createService(QuanApiServer.class)
                .reqQuan(ProductApis.QUAN_SEARCH,params,headParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseBean<List<QuanBean>>>() {
                    @Override
                    public void accept(BaseResponseBean<List<QuanBean>> listBaseResponseBean) throws Exception {
                        if("0000".equals(listBaseResponseBean.status)){
                            quanModelCallBack.onSeccess(listBaseResponseBean.result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        quanModelCallBack.onFail("圈子请求网络错误");
                    }
                });
    }

    public interface QuanModelCallBack{
        void onSeccess(List<QuanBean> list);
        void onFail(String s);
    }
}
