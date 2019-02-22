package com.king.maillyms.apis.apiserver;

import com.example.lib_network.bean.BaseResponseBean;
import com.king.maillyms.beans.entity.QuanBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

public interface QuanApiServer {
    @GET
    Observable<BaseResponseBean<List<QuanBean>>> reqQuan(@QueryMap Map<String,String> params,
                                                         @HeaderMap Map<String,String> headParams);
}
