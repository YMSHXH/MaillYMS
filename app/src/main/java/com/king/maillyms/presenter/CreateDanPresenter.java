package com.king.maillyms.presenter;

import com.king.maillyms.contact.CreateDancontact;
import com.king.maillyms.model.CreateDanModel;

import java.util.Map;

public class CreateDanPresenter extends CreateDancontact.ICreateDanPresenter {
    @Override
    public void onSetCreatDanMessage(Map<String, String> params, String orderInFo, double totalPrice, int addressId) {
        modle.onSetCreatDanMessage(params, orderInFo, totalPrice, addressId, new CreateDanModel.CreateDanModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                view.onSuccess(msg);
            }

            @Override
            public void onFile(String msg) {
                view.onFile(msg);
            }
        });
    }
}
