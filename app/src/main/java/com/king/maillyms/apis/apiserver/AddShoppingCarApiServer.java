package com.king.maillyms.apis.apiserver;

import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.beans.AddCarBean;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface AddShoppingCarApiServer {

    @PUT
    @FormUrlEncoded//表单
    Observable<AddCarBean> requestAddCar(@Url String api, @HeaderMap Map<String, String> parmas,@Field("data") String data);

//    @PUT
//    Observable<AddCarBean> requestAddCar(@Url String api, @HeaderMap Map<String, String> parmas,@Query("data") String data);

    //设置默认地址
    @POST(ProductApis.SET_MY_ADDRESS)
    @FormUrlEncoded//表单
    Observable<AddCarBean> requestSetAddress(@HeaderMap Map<String, String> parmas,@Field("id") String data);
}
