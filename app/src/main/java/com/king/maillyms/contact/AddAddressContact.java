package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.beans.AddAddressBean;
import com.king.maillyms.model.AddAddressModel;

import java.util.Map;

public interface AddAddressContact {
    abstract class IAddAddressPresenter extends BasePresenter<IAddAddressModel,IAddAddressView> {

        @Override
        public IAddAddressModel getModule() {
            return new AddAddressModel();
        }

        public abstract void setAddAddressList(Map<String, String> parmas, Map<String, String> parmasBody);
    }

    interface IAddAddressModel extends IBaseModel {
        void setAddAddressList(Map<String, String> parmas,
                           Map<String, String> parmasBody,
                           AddAddressModel.AddAddressModelCallBack addAddressModelCallBack);
    }

    interface IAddAddressView extends IBaseView {
        void onSeccess(String meg);
        void onFail(String s);
    }
}
