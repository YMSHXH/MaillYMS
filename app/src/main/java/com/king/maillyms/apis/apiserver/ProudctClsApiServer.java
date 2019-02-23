package com.king.maillyms.apis.apiserver;

import com.example.lib_network.bean.BaseResponseBean;
import com.king.maillyms.beans.entity.ProducetClsBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ProudctClsApiServer {

    @GET
    Observable<BaseResponseBean<List<ProducetClsBean>>> requestProducetCls(@Url String api, @QueryMap Map<String, String> parmas);
}
