package com.king.maillyms.presenter;

import com.king.maillyms.beans.AddCarBean;
import com.king.maillyms.contact.AddCarContact;
import com.king.maillyms.model.AddCarModel;

import java.util.Map;

public class AddCarPresnenter extends AddCarContact.IAddCarPresenter {
    @Override
    public void setAddCarList(Map<String, String> parmas, String body) {
        modle.setAddCarList(parmas, body, new AddCarModel.AddCarModelCallBack() {
            @Override
            public void onSeccess(AddCarBean addCarBean) {
                view.onSeccess(addCarBean);
            }

            @Override
            public void onFail(String s) {
                view.onFail(s);
            }
        });
    }
}
