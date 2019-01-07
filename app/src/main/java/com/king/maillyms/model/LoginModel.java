package com.king.maillyms.model;


import com.example.lib_core.common.Apis;
import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_core.net.OkhttpUtils;
import com.king.maillyms.apis.LoginApis;
import com.king.maillyms.contact.LoginContact;

import java.util.HashMap;

public class LoginModel implements LoginContact.ILoginModel {

    @Override
    public void setLoginList(HashMap<String, String> parmas, OkhttpCallback requestCallback) {
        OkhttpUtils.getInstance().doPost(LoginApis.LOGIN_API,parmas,requestCallback);
    }

//    @Override
//    public void setLoginList(String api, HashMap<String, String> parmas, OkhttpCallback requestCallback) {
//        OkhttpUtils.getInstance().doPost(api,parmas,requestCallback);
//    }
}
