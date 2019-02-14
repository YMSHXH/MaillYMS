package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.net.OkhttpCallback;

import java.util.HashMap;
import java.util.Map;

public interface SearchContact {
    interface ISearchMoudel extends IBaseModel{
        void setSearchList(Map<String, String> parmas, OkhttpCallback requestCallback);
    }
}
