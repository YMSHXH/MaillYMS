package com.king.maillyms.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.apiserver.DanApiServer;
import com.king.maillyms.beans.AddCarBean;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DeleteDanModel {

    @SuppressLint("CheckResult")
    public void setDeleteList(Map<String,String> params, String orderID, final DeleteModelCallBack deleteModelCallBack){
        RetrofitUtils.getInstance().createService(DanApiServer.class)
                .reqDelDan(params,orderID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCarBean>() {
                    @Override
                    public void accept(AddCarBean addCarBean) throws Exception {
                        if ("0000".equals(addCarBean.getStatus())){
                            deleteModelCallBack.onSuccess(addCarBean.getMessage());
                        } else {
                            deleteModelCallBack.onFile(addCarBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        deleteModelCallBack.onFile("请求错误");
                    }
                });
    }

    public interface DeleteModelCallBack{
        void onSuccess(String msg);
        void onFile(String msg);
    }
}
