package com.king.maillyms.model;


import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.MineApis;
import com.king.maillyms.apis.apiserver.HeadImageApiServer;
import com.king.maillyms.beans.HeadImageBean;
import com.king.maillyms.contact.HeadImageContact;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * 上传头像
 */
public class HeadImageModel implements HeadImageContact.IHeadImageModel {
    
    @SuppressLint("CheckResult")
    @Override
    public void setheadList(Map<String, String> params, MultipartBody.Part filePart, final HeadImageModelCallBack headImageModelCallBack) {
        RetrofitUtils.getInstance().createService(HeadImageApiServer.class)
                .reqHeadImage(MineApis.TO_HEAD_IMAGE,params,filePart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HeadImageBean>() {
                    @Override
                    public void accept(HeadImageBean headImageBean) throws Exception {
                        if ("0000".equals(headImageBean.getStatus())) {
                            headImageModelCallBack.onSeccess(headImageBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        headImageModelCallBack.onFail("网络错误!!!");                        
                    }
                });
    }

    public interface HeadImageModelCallBack{
        void onSeccess(HeadImageBean headImageBean);
        void onFail(String s);
    }
}
