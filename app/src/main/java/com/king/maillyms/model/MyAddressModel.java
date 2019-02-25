package com.king.maillyms.model;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_network.bean.BaseResponseBean;
import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.apis.apiserver.MyAddressApiServer;
import com.king.maillyms.beans.entity.MyAddressBean;
import com.king.maillyms.contact.MyAddressContact;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyAddressModel implements MyAddressContact.IMyAddressMoudel {

    @SuppressLint("CheckResult")
    @Override
    public void setMyAddressList(Map<String, String> params, final MyAddressModelCallBack myAddressModelCallBack) {
        RetrofitUtils.getInstance().createService(MyAddressApiServer.class)
                .requestMyAdd(ProductApis.MY_ADDRESS,params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseBean<List<MyAddressBean>>>() {
                    @Override
                    public void accept(BaseResponseBean<List<MyAddressBean>> listBaseResponseBean) throws Exception {
                        if ("0000".equals(listBaseResponseBean.status)){
                            List<MyAddressBean> list =  listBaseResponseBean.result;
                            if (list==null){
                                ToastUtils.showLong(listBaseResponseBean.message);
                            } else {
                                myAddressModelCallBack.onSuccess(list);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        myAddressModelCallBack.onFile("网络错误");
                        Log.e("throwable",throwable + "");
                    }
                });
        //限定符
    }

    public interface MyAddressModelCallBack{
        void onSuccess(List<MyAddressBean> list);
        void onFile(String msg);
    }
}
