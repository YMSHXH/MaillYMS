package com.king.maillyms.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.apis.apiserver.DanApiServer;
import com.king.maillyms.beans.DanBean;
import com.king.maillyms.contact.DanListContact;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DanListModel implements DanListContact.IDanListModel {

    @SuppressLint("CheckResult")
    @Override
    public void setDanList(Map<String, String> params, Map<String, String> paramsBody, final DanListCallBack danListCallBack) {
        RetrofitUtils.getInstance().createService(DanApiServer.class)
                .reqDao(ProductApis.DAN_LIST,params,paramsBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DanBean>() {
                    @Override
                    public void accept(DanBean danBean) throws Exception {
                        if("0000".equals(danBean.getStatus())){
                            danListCallBack.onSuccess(danBean.getOrderList());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        danListCallBack.onFile("网络错误");
                    }
                });
    }

    public interface DanListCallBack{
        void onSuccess(List<DanBean.OrderListBean> list);
        void onFile(String msg);
    }
}
