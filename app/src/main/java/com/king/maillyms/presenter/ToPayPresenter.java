package com.king.maillyms.presenter;

import com.king.maillyms.contact.ToPayDanContact;
import com.king.maillyms.model.ToPayDanModel;

import java.util.Map;

public class ToPayPresenter extends ToPayDanContact.IToPayPresenter {

    private ToPayDanContact.IToPayDanView iToPayDanView;
    private ToPayDanModel toPayDanModel;

    public ToPayPresenter(ToPayDanContact.IToPayDanView iToPayDanView) {
        this.iToPayDanView = iToPayDanView;
        this.toPayDanModel = new ToPayDanModel();
    }

    @Override
    public void setDeleteList(Map<String, String> params, Map<String, String> paramsBody) {
        if (toPayDanModel != null) {
            toPayDanModel.setDeleteList(params, paramsBody, new ToPayDanModel.ToPayDanModelCallBack() {
                @Override
                public void onSuccess(String msg) {
                    if (iToPayDanView != null) {
                        iToPayDanView.onYuSuccess(msg);
                    }
                }

                @Override
                public void onFile(String msg) {
                    if (iToPayDanView != null) {
                        iToPayDanView.onYuFile(msg);
                    }
                }
            });
        }
    }
}
