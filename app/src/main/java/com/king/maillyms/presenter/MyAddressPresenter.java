package com.king.maillyms.presenter;

import com.king.maillyms.beans.entity.MyAddressBean;
import com.king.maillyms.contact.MyAddressContact;
import com.king.maillyms.model.MyAddressModel;

import java.util.List;
import java.util.Map;

public class MyAddressPresenter extends MyAddressContact.IMyAddressPresenter {
    @Override
    public void setMyAddressList(Map<String, String> params) {
        modle.setMyAddressList(params, new MyAddressModel.MyAddressModelCallBack() {
            @Override
            public void onSuccess(List<MyAddressBean> list) {
                view.onSuccess(list);
            }

            @Override
            public void onFile(String msg) {
                view.onFile(msg);
            }
        });
    }
}
