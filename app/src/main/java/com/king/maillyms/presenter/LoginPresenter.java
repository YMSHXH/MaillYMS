package com.king.maillyms.presenter;

import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.contact.LoginContact;

import java.util.HashMap;

public class LoginPresenter extends LoginContact.ILoginPresenter {

    @Override
    public void setLoginList(HashMap<String, String> parmas) {
        modle.setLoginList(parmas, new OkhttpCallback() {
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
