package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.model.CreateDanModel;

import java.util.Map;

public interface CreateDancontact {

    abstract class ICreateDanPresenter extends BasePresenter<ICreateDanModel,ICreateDanView>{

        @Override
        public ICreateDanModel getModule() {
            return new CreateDanModel();
        }

        public abstract void onSetCreatDanMessage(Map<String,String> params,
                                                   String orderInFo,
                                                   double totalPrice,
                                                   int addressId);
    }

    interface ICreateDanModel extends IBaseModel {
        void onSetCreatDanMessage(Map<String,String> params,
                                  String orderInFo,
                                  double totalPrice,
                                  int addressId,
                                  CreateDanModel.CreateDanModelCallBack createDanModelCallBack);
    }

    interface ICreateDanView extends IBaseView {
        void onSuccess(String msg);
        void onFile(String msg);
    }
}
