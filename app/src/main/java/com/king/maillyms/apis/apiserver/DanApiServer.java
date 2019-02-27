package com.king.maillyms.apis.apiserver;

import com.king.maillyms.beans.DanBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface DanApiServer {
    @GET
    Observable<DanBean> reqDao(@Url String api,
                               @HeaderMap Map<String,String> params,
                               @QueryMap Map<String,String> paramsBody);
}
