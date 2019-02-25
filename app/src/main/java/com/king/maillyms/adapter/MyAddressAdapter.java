package com.king.maillyms.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.beans.entity.MyAddressBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddressAdapter extends XRecyclerView.Adapter<MyAddressAdapter.MyAddressAdapterVH> {

    private Context context;
    private List<MyAddressBean> list;

    public MyAddressAdapter(Context context, List<MyAddressBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAddressAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_my_address, viewGroup, false);
        MyAddressAdapterVH myAddressAdapterVH = new MyAddressAdapterVH(inflate);
        return myAddressAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressAdapterVH myAddressAdapterVH, int i) {
        MyAddressBean myAddressBean = list.get(i);
        myAddressAdapterVH.myAddressName.setText(myAddressBean.getRealName());
        myAddressAdapterVH.myAddressPhone.setText(myAddressBean.getPhone());
        myAddressAdapterVH.myAddressAddress.setText(myAddressBean.getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @OnClick({R.id.my_address_btndel, R.id.my_address_btnupdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_address_btndel://删除
                break;
            case R.id.my_address_btnupdate://修改
                break;
        }
    }

    public class MyAddressAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.my_address_name)
        TextView myAddressName;
        @BindView(R.id.my_address_phone)
        TextView myAddressPhone;
        @BindView(R.id.my_address_address)
        TextView myAddressAddress;
        @BindView(R.id.my_address_checkmo)
        CheckBox myAddressCheckmo;
        @BindView(R.id.my_address_btndel)
        Button myAddressBtndel;
        @BindView(R.id.my_address_btnupdate)
        Button myAddressBtnupdate;
        public MyAddressAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}