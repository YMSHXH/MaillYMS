package com.king.maillyms.presenter;

import com.king.maillyms.beans.entity.QuanBean;
import com.king.maillyms.contact.QuanContact;
import com.king.maillyms.model.QuanModel;

import java.util.List;
import java.util.Map;

public class QuanPresenter extends QuanContact.IQuanPresenter {
    @Override
    public void setQuanList(Map<String, String> params, Map<String, String> headParams) {
        modle.setQuanList(params, params, new QuanModel.QuanModelCallBack() {
            @Override
            public void onSeccess(List<QuanBean> list) {
                view.onSeccess(list);
            }

            @Override
            public void onFail(String s) {
                view.onFail(s);
            }
        });
    }
}
