package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.beans.AddCarBean;
import com.king.maillyms.model.AddCarModel;

import java.util.Map;

public interface AddCarContact {

     abstract class IAddCarPresenter extends BasePresenter<IAddCarModel,IAddCarView>{
         @Override
         public IAddCarModel getModule() {
             return new AddCarModel();
         }

         public abstract void setAddCarList(Map<String, String> parmas,String body);
    }

    interface IAddCarModel extends IBaseModel {
        void setAddCarList(Map<String, String> parmas,String body,AddCarModel.AddCarModelCallBack addCarModelCallBack);
    }

    interface IAddCarView extends IBaseView {
        void onSeccess(AddCarBean addCarBean);
        void onFail(String s);
    }

}
