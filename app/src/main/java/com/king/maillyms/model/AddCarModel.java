package com.king.maillyms.model;

import android.annotation.SuppressLint;

import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.apis.apiserver.AddShoppingCarApiServer;
import com.king.maillyms.beans.AddCarBean;
import com.king.maillyms.contact.AddCarContact;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddCarModel implements AddCarContact.IAddCarModel {


    @SuppressLint("CheckResult")
    @Override
    public void setAddCarList(Map<String, String> parmas, String body , final AddCarModelCallBack addCarModelCallBack) {
        RetrofitUtils.getInstance().createService(AddShoppingCarApiServer.class)
                .requestAddCar(ProductApis.ADD_SHOPPING_SEARCH,parmas,body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCarBean>() {
                    @Override
                    public void accept(AddCarBean addCarBean) throws Exception {
                        //成功回调
                        if ("0000".equals(addCarBean.getStatus())) {
                            addCarModelCallBack.onSeccess(addCarBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        addCarModelCallBack.onFail("添加购物车网络错误!!!");
                        System.out.println("====" + throwable);
                    }
                });
    }


    public interface AddCarModelCallBack{
        void onSeccess(AddCarBean addCarBean);
        void onFail(String s);
    }
}
