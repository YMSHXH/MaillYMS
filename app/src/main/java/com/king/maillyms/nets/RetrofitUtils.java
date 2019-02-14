package com.king.maillyms.nets;

import com.example.lib_core.common.Apis;
import com.king.maillyms.beans.SearchBean;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static RetrofitUtils instance;
    private final Retrofit retrofit;

    private RetrofitUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        //第一步
        retrofit = new Retrofit.Builder()
                .baseUrl(Apis.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//gson解析器
                .client(okHttpClient)
                .build();
    }

    public static RetrofitUtils getInstance(){
        if (instance == null) {
            synchronized (RetrofitUtils.class){
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 注册
     */
    public void reqtoSear(String api, Map<String,String> params, final RetrofitUtilsCallBack retrofitUtilsCallBack){
        //模式：外观模式
        //设计模式：构建者模式
        //第二步 ,创建请求接口类对象，体现一个动态代理模式
        UserApiService userApiService = retrofit.create(UserApiService.class);

        //第三步，创建请求对象
        Call<SearchBean> reqest = userApiService.reqest(api, params);

        //第四部，请求
        reqest.enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {

                SearchBean body = response.body();
                retrofitUtilsCallBack.success(body);
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {
                retrofitUtilsCallBack.failure("网络错误");
            }
        });
    }
}
