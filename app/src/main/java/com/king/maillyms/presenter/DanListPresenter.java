package com.king.maillyms.presenter;

import com.king.maillyms.beans.DanBean;
import com.king.maillyms.contact.DanListContact;
import com.king.maillyms.model.DanListModel;

import java.util.List;
import java.util.Map;

public class DanListPresenter extends DanListContact.IDanListPresenter {
    @Override
    public void setDanList(Map<String, String> params, Map<String, String> paramsBody) {
        modle.setDanList(params, paramsBody, new DanListModel.DanListCallBack() {
            @Override
            public void onSuccess(List<DanBean.OrderListBean> list) {
                view.onSuccess(list);
            }

            @Override
            public void onFile(String msg) {
                view.onFile(msg);
            }
        });
    }
}
