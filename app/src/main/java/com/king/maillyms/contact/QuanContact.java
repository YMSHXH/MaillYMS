package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.beans.entity.QuanBean;
import com.king.maillyms.model.QuanModel;

import java.util.List;
import java.util.Map;

public interface QuanContact {

    abstract class IQuanPresenter extends BasePresenter<IQuanModel,IQuanView> {

         @Override
         public IQuanModel getModule() {
             return new QuanModel();
         }
        public abstract void setQuanList(Map<String, String> params, Map<String, String> headParams);
     }

    interface IQuanModel extends IBaseModel {
        void setQuanList(Map<String,String> params, Map<String,String> headParams,QuanModel.QuanModelCallBack quanModelCallBack);
    }

    interface IQuanView extends IBaseView {
        void onSeccess(List<QuanBean> list);
        void onFail(String s);
    }
}
