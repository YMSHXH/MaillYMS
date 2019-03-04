package com.king.maillyms.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.apiserver.DanApiServer;
import com.king.maillyms.beans.AddCarBean;
import com.king.maillyms.contact.ToPayDanContact;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ToPayDanModel implements ToPayDanContact.IToPayDanModel {

    @SuppressLint("CheckResult")
    @Override
    public void setDeleteList(Map<String, String> params, Map<String, String> paramsBody, final ToPayDanModelCallBack callBack) {
        RetrofitUtils.getInstance().createService(DanApiServer.class)
                .reqToPay(params,paramsBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCarBean>() {
                    @Override
                    public void accept(AddCarBean addCarBean) throws Exception {
                        if ("0000".equals(addCarBean.getStatus())){
                            callBack.onSuccess(addCarBean.getMessage());
                        } else {
                            callBack.onFile(addCarBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onFile("请求错误");
                    }
                });
    }

    public interface ToPayDanModelCallBack{
        void onSuccess(String msg);
        void onFile(String msg);
    }
}
