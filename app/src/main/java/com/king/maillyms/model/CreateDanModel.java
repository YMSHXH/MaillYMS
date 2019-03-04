package com.king.maillyms.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.apiserver.DanApiServer;
import com.king.maillyms.beans.CreatDan;
import com.king.maillyms.contact.CreateDancontact;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CreateDanModel implements CreateDancontact.ICreateDanModel {

    @SuppressLint("CheckResult")
    @Override
    public void onSetCreatDanMessage(Map<String, String> params,
                                     String orderInFo,
                                     double totalPrice,
                                     int addressId,
                                     final CreateDanModelCallBack createDanModelCallBack) {
        RetrofitUtils.getInstance().createService(DanApiServer.class)
                .reqCreateDan(params,orderInFo,totalPrice,addressId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CreatDan>() {
                    @Override
                    public void accept(CreatDan creatDan) throws Exception {
                        if ("0000".equals(creatDan.getStatus())){
                            createDanModelCallBack.onSuccess(creatDan.getMessage());
                        } else {
                            createDanModelCallBack.onFile(creatDan.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        createDanModelCallBack.onFile("网络错误");
                        System.out.println("创建订单"+throwable);
                    }
                });
    }

    public interface CreateDanModelCallBack{
        void onSuccess(String msg);
        void onFile(String msg);
    }
}
