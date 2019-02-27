package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.beans.HeadImageBean;
import com.king.maillyms.model.HeadImageModel;

import java.util.Map;

import okhttp3.MultipartBody;

public interface HeadImageContact {

    abstract class IHeadImagePresenter extends BasePresenter<IHeadImageModel,IHeadImageView>{

        @Override
        public IHeadImageModel getModule() {
            return new HeadImageModel();
        }
        public abstract void setheadList(Map<String,String> params,
                                         MultipartBody.Part filePart);
    }

    public interface IHeadImageModel extends IBaseModel {
        void setheadList(Map<String,String> params,
                         MultipartBody.Part filePart,
                         HeadImageModel.HeadImageModelCallBack headImageModelCallBack);
    }

    public interface IHeadImageView extends IBaseView {
        void onSeccess(HeadImageBean headImageBean);
        void onFail(String s);
    }
}
