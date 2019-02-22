package com.king.maillyms.model;

import com.king.maillyms.contact.SearchContact;
import com.king.maillyms.nets.RetrofitUtils;
import com.king.maillyms.nets.RetrofitUtilsCallBack;

import java.util.Map;

public class SearchModel implements SearchContact.ISearchMoudel {
    @Override
    public void setSearchList(String api, Map<String, String> params, RetrofitUtilsCallBack retrofitUtilsCallBack) {
        RetrofitUtils.getInstance().reqtoSear(api,params,retrofitUtilsCallBack);
    }
}
