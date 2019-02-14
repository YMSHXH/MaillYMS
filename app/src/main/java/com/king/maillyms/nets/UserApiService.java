package com.king.maillyms.nets;

import com.king.maillyms.beans.SearchBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface UserApiService {

    @GET
    Call<SearchBean> reqest(@Url String url, @QueryMap Map<String,String> params);
}
