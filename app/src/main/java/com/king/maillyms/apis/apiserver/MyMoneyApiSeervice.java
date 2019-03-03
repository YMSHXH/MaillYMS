package com.king.maillyms.apis.apiserver;

import com.example.lib_network.bean.BaseResponseBean;
import com.king.maillyms.apis.MineApis;
import com.king.maillyms.beans.entity.MyMoneyBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface MyMoneyApiSeervice {

    @GET(MineApis.MY_MONEY)
    Observable<BaseResponseBean<MyMoneyBean>> reqMyMoney(
                                                     @HeaderMap Map<String,String> params,
                                                     @QueryMap Map<String,String> paramsBody);
}
