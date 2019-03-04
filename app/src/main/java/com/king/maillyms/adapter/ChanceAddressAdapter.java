package com.king.maillyms.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.king.maillyms.R;
import com.king.maillyms.beans.entity.MyAddressBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChanceAddressAdapter extends RecyclerView.Adapter<ChanceAddressAdapter.ChanceAddressAdapterVH> {

    private Context context;
    private List<MyAddressBean> beanList;

    private ChanceAddressAdapterCallBack chanceAddressAdapterCallBack;

    public void setChanceAddressAdapterCallBack(ChanceAddressAdapterCallBack chanceAddressAdapterCallBack) {
        this.chanceAddressAdapterCallBack = chanceAddressAdapterCallBack;
    }

    public ChanceAddressAdapter(Context context, List<MyAddressBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public ChanceAddressAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_sum_money_address, viewGroup, false);
        ChanceAddressAdapterVH chanceAddressAdapterVH = new ChanceAddressAdapterVH(inflate);
        return chanceAddressAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ChanceAddressAdapterVH chanceAddressAdapterVH, int i) {
        final MyAddressBean myAddressBean = beanList.get(i);
        chanceAddressAdapterVH.myAddressName.setText(myAddressBean.getRealName());
        chanceAddressAdapterVH.myAddressPhone.setText(myAddressBean.getPhone());
        chanceAddressAdapterVH.myAddressAddress.setText(myAddressBean.getAddress());

        //点击事件
        chanceAddressAdapterVH.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chanceAddressAdapterCallBack.setMyAddress(myAddressBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ChanceAddressAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.my_address_name)
        TextView myAddressName;
        @BindView(R.id.my_address_phone)
        TextView myAddressPhone;
        @BindView(R.id.my_address_address)
        TextView myAddressAddress;
        @BindView(R.id.change)
        TextView change;
        public ChanceAddressAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ChanceAddressAdapterCallBack{
        void setMyAddress(MyAddressBean myAddressBean);
    }
}
