package com.king.maillyms.contact;

import com.king.maillyms.model.ToPayDanModel;

import java.util.Map;

public interface ToPayDanContact {

    abstract class IToPayPresenter{
        public abstract void setDeleteList(Map<String,String> params,
                           Map<String,String> paramsBody);
    }


    interface IToPayDanModel{
        void setDeleteList(Map<String,String> params,
                           Map<String,String> paramsBody,
                           ToPayDanModel.ToPayDanModelCallBack callBack);
    }

    interface IToPayDanView{
        void onYuSuccess(String msg);
        void onYuFile(String msg);
    }

}
