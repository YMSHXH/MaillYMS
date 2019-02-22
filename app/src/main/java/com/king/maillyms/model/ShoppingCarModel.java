package com.king.maillyms.model;

import android.annotation.SuppressLint;

import com.example.lib_network.bean.BaseResponseBean;
import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.apiserver.ShoppingCarApiServer;
import com.king.maillyms.beans.entity.ShoppingCarBean;
import com.king.maillyms.contact.ShoppingCarContact;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShoppingCarModel implements ShoppingCarContact.IShoppingCarMoudel {


    @SuppressLint("CheckResult")
    @Override
    public void setShoppingCarList(String api, Map<String, String> parmas, final ISpCarModel iShoppingCarModel) {
        RetrofitUtils.getInstance().createService(ShoppingCarApiServer.class)
                .requestSPcar(api,parmas)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseBean<List<ShoppingCarBean>>>() {
                    @Override
                    public void accept(BaseResponseBean<List<ShoppingCarBean>> listBaseResponseBean) throws Exception {
                        if ("0000".equals(listBaseResponseBean.status)){
                            iShoppingCarModel.onSuccess(listBaseResponseBean.result);
                        } else {
                            iShoppingCarModel.onFile("网络错误");
                            System.out.println(listBaseResponseBean.message);
                            System.out.println(listBaseResponseBean.status);
                        }
                        System.out.println(listBaseResponseBean.message);
                        System.out.println(listBaseResponseBean.status);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("网络错误!!");
                    }
                });
    }

    public interface ISpCarModel{
        void onSuccess(List<ShoppingCarBean> list);
        void onFile(String msg);
    }
}
