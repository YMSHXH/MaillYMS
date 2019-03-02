package com.king.maillyms.apis.apiserver;

import com.example.lib_network.bean.BaseResponseBean;
import com.king.maillyms.beans.AddAddressBean;
import com.king.maillyms.beans.entity.MyAddressBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface MyAddressApiServer {
    @GET
    Observable<BaseResponseBean<List<MyAddressBean>>> requestMyAdd(@Url String api, @HeaderMap Map<String, String> parmas);


    @POST
    @FormUrlEncoded
    Observable<AddAddressBean> reqAddMyAddress(@Url String api,
                                               @HeaderMap Map<String, String> parmas,
                                               @FieldMap Map<String, String> parmasbody);

    @PUT
    @FormUrlEncoded
    Observable<AddAddressBean> reqUpdateMyAddress(@Url String api,
                                               @HeaderMap Map<String, String> parmas,
                                               @FieldMap Map<String, String> parmasbody);
}
