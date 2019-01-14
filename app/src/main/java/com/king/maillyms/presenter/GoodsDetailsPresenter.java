package com.king.maillyms.presenter;

import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.contact.GoodsDetailsContact;

import java.util.HashMap;

public class GoodsDetailsPresenter extends GoodsDetailsContact.IGoodsDetailsPresenter {
    @Override
    public void setGoodsDetailsData(HashMap<String, String> parmas) {
        modle.setGoodsDetailsData(parmas, new OkhttpCallback() {
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
