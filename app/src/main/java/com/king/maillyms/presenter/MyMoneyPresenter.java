package com.king.maillyms.presenter;

import com.king.maillyms.beans.entity.MyMoneyBean;
import com.king.maillyms.contact.MyMoneyContact;
import com.king.maillyms.model.MyMoneyMoedl;

import java.util.Map;

public class MyMoneyPresenter extends MyMoneyContact.IMyMoneyPresenter {
    @Override
    public void setList(Map<String, String> params, Map<String, String> paramsBody) {
        modle.setList(params, paramsBody, new MyMoneyMoedl.MyMoneyMoedlCallBack() {
            @Override
            public void onSuccess(MyMoneyBean myMoneyBean) {
                view.onSuccess(myMoneyBean);
            }

            @Override
            public void onFile(String msg) {
                view.onFile(msg);
            }
        });
    }
}
