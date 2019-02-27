package com.king.maillyms.contact;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.king.maillyms.beans.DanBean;
import com.king.maillyms.model.DanListModel;

import java.util.List;
import java.util.Map;

import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

/**
 * 根据订单状态查询订单信息
 */
public interface DanListContact {

    abstract class IDanListPresenter extends BasePresenter<IDanListModel,IDanListView>{

        @Override
        public IDanListModel getModule() {
            return new DanListModel();
        }

        public abstract void setDanList(Map<String,String> params,Map<String,String> paramsBody);
    }

    interface IDanListModel extends IBaseModel{
        void setDanList(Map<String,String> params,
                        Map<String,String> paramsBody,
                        DanListModel.DanListCallBack danListCallBack);
    }

    interface IDanListView extends IBaseView{
        void onSuccess(List<DanBean.OrderListBean> list);
        void onFile(String msg);
    }
}
