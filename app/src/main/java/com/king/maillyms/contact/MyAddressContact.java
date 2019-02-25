package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.beans.entity.MyAddressBean;
import com.king.maillyms.model.MyAddressModel;

import java.util.List;
import java.util.Map;

public interface MyAddressContact {

    abstract class IMyAddressPresenter extends BasePresenter<IMyAddressMoudel,IMyAddressView>{

        @Override
        public IMyAddressMoudel getModule() {
            return new MyAddressModel();
        }
        public abstract void setMyAddressList(Map<String, String> params);
    }

    interface IMyAddressMoudel extends IBaseModel {
        void setMyAddressList(Map<String, String> params, MyAddressModel.MyAddressModelCallBack myAddressModelCallBack);
    }

    interface IMyAddressView extends IBaseView {
        void onSuccess(List<MyAddressBean> list);
        void onFile(String msg);
    }
}
