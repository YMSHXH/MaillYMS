package com.king.maillyms.model;


import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_core.net.OkhttpUtils;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.contact.ProductContact;

import java.util.HashMap;

public class ProductModel implements ProductContact.IProductModel {

    @Override
    public void setProductList(HashMap<String, String> parmas, OkhttpCallback requestCallback) {
        OkhttpUtils.getInstance().doGet(ProductApis.HOME_PRODUCT,parmas,requestCallback);
    }

    @Override
    public void setBannerList(HashMap<String, String> parmas, OkhttpCallback requestCallback) {
        OkhttpUtils.getInstance().doGet(ProductApis.HOME_BANNER,parmas,requestCallback);
    }
}
