package com.king.maillyms.presenter;

import com.king.maillyms.contact.AddAddressContact;
import com.king.maillyms.model.AddAddressModel;

import java.util.Map;

public class AddMyAddPresenter extends AddAddressContact.IAddAddressPresenter {

    @Override
    public void setAddAddressList(Map<String, String> parmas, Map<String, String> parmasBody) {
        modle.setAddAddressList(parmas, parmasBody, new AddAddressModel.AddAddressModelCallBack() {
            @Override
            public void onSeccess(String meg) {
                view.onSeccess(meg);
            }

            @Override
            public void onFail(String s) {
                view.onFail(s);
            }
        });
    }
}
