package com.king.maillyms.presenter;

import com.king.maillyms.beans.entity.ShoppingCarBean;
import com.king.maillyms.contact.ShoppingCarContact;
import com.king.maillyms.model.ShoppingCarModel;

import java.util.List;
import java.util.Map;


public class ShoppingCarPresenter extends ShoppingCarContact.IShoppingPresenter {

    @Override
    public void setShoppingCarList(String api, Map<String, String> parmas) {
        modle.setShoppingCarList(api, parmas, new ShoppingCarModel.ISpCarModel() {
            @Override
            public void onSuccess(List<ShoppingCarBean> list) {
                view.onSuccess(list);
            }

            @Override
            public void onFile(String msg) {
                view.onFile(msg);
            }
        });
    }
}
