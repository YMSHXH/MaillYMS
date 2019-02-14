package com.king.maillyms.nets;

import com.king.maillyms.beans.SearchBean;

public interface RetrofitUtilsCallBack {
    void failure(String msg);//服务器请求失败：断网，服务器宕机等等因素
    void success(SearchBean body);
}
