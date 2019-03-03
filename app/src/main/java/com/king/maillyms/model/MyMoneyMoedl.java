package com.king.maillyms.model;

import com.example.lib_network.bean.BaseResponseBean;
import com.example.lib_network.network.RetrofitUtils;
import com.king.maillyms.apis.apiserver.MyMoneyApiSeervice;
import com.king.maillyms.beans.entity.MyMoneyBean;
import com.king.maillyms.contact.MyMoneyContact;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyMoneyMoedl implements MyMoneyContact.IMyMoneyModel {

    @Override
    public void setList(Map<String, String> params, Map<String, String> paramsBody, final MyMoneyMoedlCallBack myMoneyMoedlCallBack) {
        Disposable subscribe = RetrofitUtils.getInstance().createService(MyMoneyApiSeervice.class)
                .reqMyMoney(params, paramsBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseBean<MyMoneyBean>>() {
                    @Override
                    public void accept(BaseResponseBean<MyMoneyBean> myMoneyBeanBaseResponseBean) throws Exception {
                        if ("0000".equals(myMoneyBeanBaseResponseBean.status)){
                            myMoneyMoedlCallBack.onSuccess(myMoneyBeanBaseResponseBean.result);
                        } else {
                            myMoneyMoedlCallBack.onFile(myMoneyBeanBaseResponseBean.status);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        myMoneyMoedlCallBack.onFile("网络错误");
                    }
                });
    }

    public interface MyMoneyMoedlCallBack{
        void onSuccess(MyMoneyBean myMoneyBean);
        void onFile(String msg);
    }
}
