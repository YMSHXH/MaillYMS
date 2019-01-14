package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.model.LoginModel;
import com.king.maillyms.model.ProductModel;

import java.util.HashMap;

public interface ProductContact {

    abstract class IProductPresenter extends BasePresenter<IProductModel,IProductView>{
       @Override
       public IProductModel getModule() {
           return new ProductModel();
       }


       public abstract void setProductList(HashMap<String, String> parmas);
       public abstract void setBannerList(HashMap<String, String> parmas);
    }

    interface IProductModel extends IBaseModel {
        void setProductList(HashMap<String, String> parmas, OkhttpCallback requestCallback);
        void setBannerList(HashMap<String, String> parmas, OkhttpCallback requestCallback);
    }

    interface IProductView extends IBaseView {
        void onSeccess(String meg);
        void onFail(String s);
        void onBannerSeccess(String s);
        void onBannerFail(String s);
    }

}
