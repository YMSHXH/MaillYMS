package com.king.maillyms.model;


import com.example.lib_core.net.OkhttpCallback;
import com.example.lib_core.net.OkhttpUtils;
import com.king.maillyms.apis.LoginApis;
import com.king.maillyms.contact.RegContact;

import java.util.HashMap;

public class RegModel implements RegContact.IRegModel {

    @Override
    public void setRegList(HashMap<String, String> parmas, OkhttpCallback requestCallback) {
        OkhttpUtils.getInstance().doPost(LoginApis.REG_API,parmas,requestCallback);
    }
}
