package com.king.maillyms.application;

import android.content.Context;

import com.example.lib_core.base.BaseApp;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.king.maillyms.apis.Umeng;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class MyApp extends BaseApp {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //初始化Uming
        Fresco.initialize(this);
        initUMing();
        ZXingLibrary.initDisplayOpinion(this);
    }


    private void initUMing() {
        /*
        注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，
        也需要在App代码中调用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
        UMConfigure.init调用中appkey和channel参数请置为null）。
        */
        UMConfigure.init(context, Umeng.UMENG_APP_KEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);

        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

        UMConfigure.setLogEnabled(true);
    }
}
