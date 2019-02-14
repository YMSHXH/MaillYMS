package com.king.maillyms.model;


import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_core.net.OkhttpUtils;
import com.king.maillyms.apis.LoginApis;
import com.king.maillyms.contact.FindContact;
import com.king.maillyms.contact.LoginContact;

import java.util.HashMap;

public class FindModel implements FindContact.IFindModel {

    @Override
    public void setFindList(HashMap<String, String> parmas, OkhttpCallback requestCallback) {
        OkhttpUtils.getInstance().doGet(LoginApis.FIND_API,parmas,requestCallback);
    }

}
