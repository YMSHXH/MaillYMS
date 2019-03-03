package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.beans.entity.MyMoneyBean;
import com.king.maillyms.model.MyMoneyMoedl;

import java.util.Map;

public interface MyMoneyContact {

    abstract class IMyMoneyPresenter extends BasePresenter<IMyMoneyModel,IMyMoneyView>{

        @Override
        public IMyMoneyModel getModule() {
            return new MyMoneyMoedl();
        }

        public abstract void setList(Map<String,String> params,
                                     Map<String,String> paramsBody);
    }

    interface IMyMoneyModel extends IBaseModel{
        void setList(Map<String,String> params,
                     Map<String,String> paramsBody,
                     MyMoneyMoedl.MyMoneyMoedlCallBack myMoneyMoedlCallBack);
    }

    interface IMyMoneyView extends IBaseView {
        void onSuccess(MyMoneyBean myMoneyBean);
        void onFile(String msg);
    }
}
