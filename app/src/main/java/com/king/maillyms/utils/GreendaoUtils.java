package com.king.maillyms.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.king.maillyms.constants.Constants;
import com.king.maillyms.greendao.DaoMaster;
import com.king.maillyms.greendao.DaoSession;

public class GreendaoUtils {
    private static GreendaoUtils mInstance;
    private DaoSession daoSession;

    private GreendaoUtils(){

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 双重检索锁
     * @return
     */
    public static GreendaoUtils getInstance(){
        if (mInstance==null){
            synchronized (GreendaoUtils.class){
                if (mInstance==null){
                    mInstance = new GreendaoUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    public void initGreenDao(Context context){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, Constants.GOODS_DB);
        //获取数据库
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        //创建对象
        DaoMaster daoMaster = new DaoMaster(db);
        //创建daosession
        daoSession = daoMaster.newSession();

    }


}
