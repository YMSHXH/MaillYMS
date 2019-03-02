package com.king.maillyms.presenter;

import com.king.maillyms.contact.UpdateAddressContact;
import com.king.maillyms.model.UpdateAddressModel;

import java.util.Map;

public class UpdateMyAddPresenter extends UpdateAddressContact.IUpdateAddressPresenter {

    @Override
    public void setUpdateAddressList(Map<String, String> parmas, Map<String, String> parmasBody) {
        modle.setUpdateAddressList(parmas, parmasBody, new UpdateAddressModel.UpdateAddressModelCallBack() {
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
