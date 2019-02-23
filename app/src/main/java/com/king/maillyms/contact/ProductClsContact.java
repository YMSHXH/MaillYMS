package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.beans.entity.ProducetClsBean;
import com.king.maillyms.model.ProductClsMoudel;

import java.util.List;
import java.util.Map;

public interface ProductClsContact {

    abstract class IProductClsPresenter extends BasePresenter<IProductClsMoudel,IProductClsView>{

        @Override
        public IProductClsMoudel getModule() {
            return new ProductClsMoudel();
        }

        public abstract void setProductClsList(Map<String, String> params);
    }


    interface IProductClsMoudel extends IBaseModel {
        void setProductClsList(Map<String, String> params, ProductClsMoudel.ProductClsMoudelCallBack clsMoudelCallBack);
    }

    interface IProductClsView extends IBaseView {
        void onSuccess(List<ProducetClsBean> list);
        void onFile(String msg);
    }
}
