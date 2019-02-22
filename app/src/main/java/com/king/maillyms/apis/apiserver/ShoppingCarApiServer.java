package com.king.maillyms.apis.apiserver;

import com.example.lib_network.bean.BaseResponseBean;
import com.king.maillyms.beans.entity.ShoppingCarBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface ShoppingCarApiServer {

    @GET
    Observable<BaseResponseBean<List<ShoppingCarBean>>> requestSPcar(@Url String api, @HeaderMap Map<String, String> parmas);
}
