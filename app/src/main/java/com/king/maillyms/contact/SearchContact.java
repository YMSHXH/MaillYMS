package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.example.lib_core.net.OkhttpCallback;
import com.king.maillyms.beans.SearchBean;
import com.king.maillyms.model.SearchModel;
import com.king.maillyms.nets.RetrofitUtilsCallBack;

import java.util.Map;

public interface SearchContact {

    abstract class ISerchPresenter extends BasePresenter<ISearchMoudel,ISearchView>{

        @Override
        public ISearchMoudel getModule() {
            return new SearchModel();
        }
        public abstract void setSearchList(String api, Map<String, String> params);
    }


    interface ISearchMoudel extends IBaseModel {
        void setSearchList(String api, Map<String,String> params,RetrofitUtilsCallBack retrofitUtilsCallBack);
    }

    interface ISearchView extends IBaseView {
        void onSuccess(SearchBean body);
        void onFile(String msg);
    }
}
