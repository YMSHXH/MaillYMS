package com.king.maillyms.model;

import android.annotation.SuppressLint;

import com.example.lib_core.base.mvp.IBaseView;
import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.apis.apiserver.AddShoppingCarApiServer;
import com.king.maillyms.apis.apiserver.MyAddressApiServer;
import com.king.maillyms.beans.AddAddressBean;
import com.king.maillyms.contact.AddAddressContact;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddAddressModel implements AddAddressContact.IAddAddressModel {


    @SuppressLint("CheckResult")
    @Override
    public void setAddAddressList(Map<String, String> parmas, Map<String, String> parmasBody, final AddAddressModelCallBack addAddressModelCallBack) {
        RetrofitUtils.getInstance().createService(MyAddressApiServer.class)
                .reqAddMyAddress(ProductApis.ADD_MY_ADDRESS,parmas,parmasBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddAddressBean>() {
                    @Override
                    public void accept(AddAddressBean addAddressBean) throws Exception {
                        if ("0000".equals(addAddressBean.getStatus())){
                            addAddressModelCallBack.onSeccess(addAddressBean.getMessage());
                        } else {
                            addAddressModelCallBack.onSeccess(addAddressBean.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        addAddressModelCallBack.onFail("请求网络错误");
                    }
                });

    }

    public interface AddAddressModelCallBack {
        void onSeccess(String meg);
        void onFail(String s);
    }
}
