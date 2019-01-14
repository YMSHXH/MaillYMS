package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.model.GoodsDetailsModel;

import java.util.HashMap;

public interface GoodsDetailsContact{

    abstract class IGoodsDetailsPresenter extends BasePresenter<IGoodsDetailsModel,IGoodsDetailsView> {

        @Override
        public IGoodsDetailsModel getModule() {
            return new GoodsDetailsModel();
        }

        public abstract void setGoodsDetailsData(HashMap<String, String> parmas);
    }

    interface IGoodsDetailsModel extends IBaseModel {
        void setGoodsDetailsData(HashMap<String, String> parmas, OkhttpCallback okhttpCallback);
    }

    interface IGoodsDetailsView extends IBaseView {
        void onSeccess(String meg);
        void onFail(String s);
    }

}
