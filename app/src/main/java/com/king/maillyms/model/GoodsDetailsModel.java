package com.king.maillyms.model;

import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_core.net.OkhttpUtils;
import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.contact.GoodsDetailsContact;

import java.util.HashMap;

public class GoodsDetailsModel implements GoodsDetailsContact.IGoodsDetailsModel {
    @Override
    public void setGoodsDetailsData(HashMap<String, String> parmas, OkhttpCallback okhttpCallback) {
        OkhttpUtils.getInstance().doGet(ProductApis.GOODS_DETAILS,parmas,okhttpCallback);
    }
}
