package com.king.maillyms.model;

import android.annotation.SuppressLint;

import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.apis.apiserver.MyAddressApiServer;
import com.king.maillyms.beans.AddAddressBean;
import com.king.maillyms.contact.UpdateAddressContact;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class UpdateAddressModel implements UpdateAddressContact.IUpdateAddressModel {


    @SuppressLint("CheckResult")
    @Override
    public void setUpdateAddressList(Map<String, String> parmas, Map<String, String> parmasBody,
                                     final UpdateAddressModelCallBack updateAddressModelCallBack) {
        RetrofitUtils.getInstance().createService(MyAddressApiServer.class)
                .reqUpdateMyAddress(ProductApis.UPADTA_MY_ADDRESS,parmas,parmasBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddAddressBean>() {
                    @Override
                    public void accept(AddAddressBean addAddressBean) throws Exception {
                        if ("0000".equals(addAddressBean.getStatus())){
                            updateAddressModelCallBack.onSeccess(addAddressBean.getMessage());
                        } else {
                            updateAddressModelCallBack.onSeccess(addAddressBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        updateAddressModelCallBack.onFail("请求网络错误");
                    }
                });

    }


    public interface UpdateAddressModelCallBack {
        void onSeccess(String meg);
        void onFail(String s);
    }
}
