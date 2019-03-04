package com.king.maillyms.adapter;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_core.utils.ShapedP;
import com.king.maillyms.R;
import com.king.maillyms.activity.ToPayActivity;
import com.king.maillyms.beans.DanBean;
import com.king.maillyms.model.DeleteDanModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DanAdapter extends RecyclerView.Adapter<DanAdapter.DanAdapterVH> {

    private Context context;
    private List<DanBean.OrderListBean> list;

    public DanAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setList(List<DanBean.OrderListBean> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DanAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_dao, viewGroup, false);
        DanAdapterVH danAdapterVH = new DanAdapterVH(inflate);
        return danAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final DanAdapterVH danAdapterVH, int i) {
        final DanBean.OrderListBean orderListBean = list.get(i);
        danAdapterVH.expressCompName.setText(orderListBean.getExpressCompName());
        danAdapterVH.orderIdName.setText(orderListBean.getOrderId());
        danAdapterVH.expressSnName.setText(orderListBean.getExpressSn());

        List<DanBean.OrderListBean.DetailListBean> detailList = orderListBean.getDetailList();

        DaoGoodsAdapter daoGoodsAdapter = new DaoGoodsAdapter(context,detailList);
        danAdapterVH.danRecyitem.setLayoutManager(new LinearLayoutManager(context));
        danAdapterVH.danRecyitem.setAdapter(daoGoodsAdapter);

        danAdapterVH.btnGotoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去支付
                Intent intent = new Intent(context, ToPayActivity.class);
                intent.putExtra("orderId",orderListBean.getOrderId());
                intent.putExtra("payAmount",orderListBean.getPayAmount());
                context.startActivity(intent);
            }
        });

        danAdapterVH.toDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = LayoutInflater.from(context).inflate(R.layout.popupwindow_delete, null, false);
                final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.setTouchable(true);
                popupWindow.showAsDropDown(v);
                Button btn_delete = popupView.findViewById(R.id.btn_delete);
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //请求删除
                        Map<String,String> params = new HashMap<>();
                        params.put("userId", ShapedP.getmInstance().getSP("userId"));
                        params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));
                        DeleteDanModel deleteDanModel = new DeleteDanModel();
                        deleteDanModel.setDeleteList(params, orderListBean.getOrderId(), new DeleteDanModel.DeleteModelCallBack() {
                            @Override
                            public void onSuccess(String msg) {
                                ToastUtils.showLong(msg);
                                list.remove(orderListBean);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFile(String msg) {
                                ToastUtils.showLong(msg);
                            }
                        });

                        popupWindow.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DanAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.dan_recyitem)
        RecyclerView danRecyitem;
        @BindView(R.id.orderIdName)
        TextView orderIdName;
        @BindView(R.id.expressCompName)
        TextView expressCompName;
        @BindView(R.id.expressSnName)
        TextView expressSnName;
        @BindView(R.id.btn_goto_pay)
        Button btnGotoPay;
        @BindView(R.id.to_delete)
        ImageView toDelete;


        public DanAdapterVH(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
