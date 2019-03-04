package com.king.maillyms.apis.apiserver;

import com.king.maillyms.apis.ProductApis;
import com.king.maillyms.beans.AddCarBean;
import com.king.maillyms.beans.CreatDan;
import com.king.maillyms.beans.DanBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface DanApiServer {

    /**
     * 创建订单
     * @param params
     * @param orderInFo
     * @param totalPrice
     * @param addressId
     * @return
     */
    @POST(ProductApis.CREATE_DAN)
    @FormUrlEncoded
    Observable<CreatDan> reqCreateDan(@HeaderMap Map<String,String> params,
                                      @Field("orderInfo") String orderInFo,
                                      @Field("totalPrice") double totalPrice,
                                      @Field("addressId") int addressId);

    /**
     * 查询订单
     * @param api
     * @param params
     * @param paramsBody
     * @return
     */
    @GET
    Observable<DanBean> reqDao(@Url String api,
                               @HeaderMap Map<String,String> params,
                               @QueryMap Map<String,String> paramsBody);


    /**
     * 删除订单
     * @param params
     * @param orderID
     * @return
     */
    @DELETE(ProductApis.DELETE_LIST)
    Observable<AddCarBean> reqDelDan(@HeaderMap Map<String,String> params,
                                     @Query("orderId") String orderID);



    @POST(ProductApis.TO_PAY)
    Observable<AddCarBean> reqToPay(@HeaderMap Map<String,String> params,
                                    @QueryMap Map<String,String> paramsBody);

}
