package com.king.maillyms.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.king.maillyms.R;
import com.king.maillyms.beans.entity.MyMoneyBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMoneyAdapter extends RecyclerView.Adapter<MyMoneyAdapter.MyMoneyAdapterVH> {

    private Context context;
    private List<MyMoneyBean.DetailListBean> detailList;

    public MyMoneyAdapter(Context context, List<MyMoneyBean.DetailListBean> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public MyMoneyAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_mymoney, viewGroup, false);
        MyMoneyAdapterVH myMoneyAdapterVH = new MyMoneyAdapterVH(inflate);
        return myMoneyAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMoneyAdapterVH myMoneyAdapterVH, int i) {
        MyMoneyBean.DetailListBean detailListBean = detailList.get(i);
        myMoneyAdapterVH.amount.setText(detailListBean.getAmount());
        long createTime = detailListBean.getCreateTime();
        Date date = new Date();
        date.setTime(createTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fmDate=simpleDateFormat.format(date);
        myMoneyAdapterVH.createTime.setText(fmDate);
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class MyMoneyAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.createTime)
        TextView createTime;
        public MyMoneyAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
