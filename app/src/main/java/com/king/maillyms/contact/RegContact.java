package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.model.LoginModel;
import com.king.maillyms.model.RegModel;

import java.util.HashMap;

public interface RegContact {

    abstract class IRegPresenter extends BasePresenter<IRegModel,IRegView>{
       @Override
       public IRegModel getModule() {
           return new RegModel();
       }

       public abstract void setRegList(HashMap<String, String> parmas);
    }

    interface IRegModel extends IBaseModel {
        void setRegList(HashMap<String, String> parmas, OkhttpCallback requestCallback);
    }

    interface IRegView extends IBaseView {
        void onSeccess(String meg);
        void onFail(String s);
    }

}
