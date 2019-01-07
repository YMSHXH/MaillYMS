package com.king.maillyms.presenter;

import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.contact.LoginContact;
import com.king.maillyms.contact.RegContact;

import java.util.HashMap;

public class RegPresenter extends RegContact.IRegPresenter {


    @Override
    public void setRegList(HashMap<String, String> parmas) {
        modle.setRegList(parmas, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                view.onFail(msg);
            }

            @Override
            public void success(String result) {
                view.onSeccess(result);
            }
        });
    }
}
