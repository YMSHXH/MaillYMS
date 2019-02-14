package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.model.FindModel;

import java.util.HashMap;

public interface FindContact {

    abstract class IFindPresenter extends BasePresenter<IFindModel,IFindView>{
        @Override
        public IFindModel getModule() {
            return new FindModel();
        }

        public abstract void setFindList(HashMap<String, String> parmas);
    }

    interface IFindModel extends IBaseModel {
        void setFindList(HashMap<String, String> parmas, OkhttpCallback requestCallback);
    }

    interface IFindView extends IBaseView {
        void onSeccess(String meg);
        void onFail(String s);
    }

}
