package com.king.maillyms.apis.apiserver;

import com.king.maillyms.beans.HeadImageBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface HeadImageApiServer {
    @POST
    @Multipart
    Observable<HeadImageBean> reqHeadImage(@Url String api,
                                           @HeaderMap Map<String,String> params,
                                           @Part MultipartBody.Part f);

}
