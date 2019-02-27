package com.king.maillyms.presenter;

import com.king.maillyms.beans.HeadImageBean;
import com.king.maillyms.contact.HeadImageContact;
import com.king.maillyms.model.HeadImageModel;

import java.util.Map;

import okhttp3.MultipartBody;

public class HeadImagePresenter extends HeadImageContact.IHeadImagePresenter {
    @Override
    public void setheadList(Map<String, String> params, MultipartBody.Part filePart) {
        modle.setheadList(params, filePart, new HeadImageModel.HeadImageModelCallBack() {
            @Override
            public void onSeccess(HeadImageBean headImageBean) {
                view.onSeccess(headImageBean);
            }

            @Override
            public void onFail(String s) {
                view.onFail(s);
            }
        });
    }
}
