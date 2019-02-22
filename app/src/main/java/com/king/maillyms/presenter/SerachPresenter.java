package com.king.maillyms.presenter;

import com.king.maillyms.beans.SearchBean;
import com.king.maillyms.contact.SearchContact;
import com.king.maillyms.nets.RetrofitUtilsCallBack;

import java.util.Map;

public class SerachPresenter extends SearchContact.ISerchPresenter {

    @Override
    public void setSearchList(String api, Map<String, String> params) {
        modle.setSearchList(api, params, new RetrofitUtilsCallBack() {
            @Override
            public void failure(String msg) {
                view.onFile(msg);
            }

            @Override
            public void success(SearchBean body) {
                view.onSuccess(body);
            }
        });
    }
}
