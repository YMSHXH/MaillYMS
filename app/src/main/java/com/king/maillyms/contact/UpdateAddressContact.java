package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.model.UpdateAddressModel;

import java.util.Map;

public interface UpdateAddressContact {
    abstract class IUpdateAddressPresenter extends BasePresenter<IUpdateAddressModel,IUpdateAddressView> {
         @Override
         public IUpdateAddressModel getModule() {
             return new UpdateAddressModel();
         }

         public abstract void setUpdateAddressList(Map<String, String> parmas,
                                                   Map<String, String> parmasBody);
    }

    interface IUpdateAddressModel extends IBaseModel {
        void setUpdateAddressList(Map<String, String> parmas,
                                  Map<String, String> parmasBody,
                                  UpdateAddressModel.UpdateAddressModelCallBack updateAddressModelCallBack);
    }

    interface IUpdateAddressView extends IBaseView {
        void onSeccess(String meg);
        void onFail(String s);
    }
}
