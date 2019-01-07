package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.model.LoginModel;

import java.util.HashMap;

public interface LoginContact {

   abstract class ILoginPresenter extends BasePresenter<ILoginModel,ILoginView>{
        @Override
        public ILoginModel getModule() {
            return new LoginModel();
        }

        public abstract void setLoginList(HashMap<String, String> parmas);
    }

    interface ILoginModel extends IBaseModel {
        void setLoginList(HashMap<String, String> parmas,OkhttpCallback requestCallback);
    }

    interface ILoginView extends IBaseView {
        void onSeccess(String meg);
        void onFail(String s);
    }

}
