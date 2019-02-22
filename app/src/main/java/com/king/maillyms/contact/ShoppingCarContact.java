package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.beans.entity.ShoppingCarBean;
import com.king.maillyms.model.ShoppingCarModel;

import java.util.List;
import java.util.Map;

public interface ShoppingCarContact {

     abstract class IShoppingPresenter extends BasePresenter<IShoppingCarMoudel,IShoppingCarView> {

        @Override
        public IShoppingCarMoudel getModule() {
            return new ShoppingCarModel();
        }
         public abstract void setShoppingCarList(String api, Map<String, String> parmas);
    }

    public interface IShoppingCarMoudel extends IBaseModel {
        void setShoppingCarList(String api,Map<String, String> parmas, ShoppingCarModel.ISpCarModel iShoppingCarModel);
    }

    public interface IShoppingCarView extends IBaseView {
        void onSuccess(List<ShoppingCarBean> list);
        void onFile(String msg);
    }

}
