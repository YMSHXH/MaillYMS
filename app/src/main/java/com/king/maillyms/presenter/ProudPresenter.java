package com.king.maillyms.presenter;

import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.contact.ProductContact;

import java.util.HashMap;

public class ProudPresenter extends ProductContact.IProductPresenter {

    @Override
    public void setProductList(HashMap<String, String> parmas) {

        modle.setProductList(parmas, new OkhttpCallback() {
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

    @Override
    public void setBannerList(HashMap<String, String> parmas) {
        modle.setBannerList(parmas, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                view.onBannerFail(msg);
            }

            @Override
            public void success(String result) {
                view.onBannerSeccess(result);
            }
        });
    }
}
