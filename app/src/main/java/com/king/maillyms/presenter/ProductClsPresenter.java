package com.king.maillyms.presenter;

import com.king.maillyms.beans.entity.ProducetClsBean;
import com.king.maillyms.contact.ProductClsContact;
import com.king.maillyms.model.ProductClsMoudel;

import java.util.List;
import java.util.Map;

public class ProductClsPresenter extends ProductClsContact.IProductClsPresenter {
    @Override
    public void setProductClsList(Map<String, String> params) {
        modle.setProductClsList(params, new ProductClsMoudel.ProductClsMoudelCallBack() {
            @Override
            public void onSuccess(List<ProducetClsBean> list) {
                view.onSuccess(list);
            }

            @Override
            public void onFile(String msg) {
                view.onFile(msg);
            }
        });
    }
}
