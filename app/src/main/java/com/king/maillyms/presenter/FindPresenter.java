package com.king.maillyms.presenter;

import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.contact.FindContact;

import java.util.HashMap;

public class FindPresenter extends FindContact.IFindPresenter {
    @Override
    public void setFindList(HashMap<String, String> parmas) {
        modle.setFindList(parmas, new OkhttpCallback() {
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
