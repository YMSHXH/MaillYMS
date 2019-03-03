package com.king.maillyms.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_network.network.RetrofitUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.activity.UpdataMyActivity;
import com.king.maillyms.apis.apiserver.AddShoppingCarApiServer;
import com.king.maillyms.beans.AddCarBean;
import com.king.maillyms.beans.entity.MyAddressBean;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyAddressAdapter extends XRecyclerView.Adapter<MyAddressAdapter.MyAddressAdapterVH> {

    private Context context;
    private List<MyAddressBean> list;
    private MyAddressAdapterVHCallBack myAddressAdapterVHCallBack;
    private Map<String, String> params;

    public void setMyAddressAdapterVHCallBack(MyAddressAdapterVHCallBack myAddressAdapterVHCallBack) {
        this.myAddressAdapterVHCallBack = myAddressAdapterVHCallBack;
    }

    public MyAddressAdapter(Context context, List<MyAddressBean> list, Map<String, String> params) {
        this.context = context;
        this.list = list;
        this.params = params;
    }

    @NonNull
    @Override
    public MyAddressAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_my_address, viewGroup, false);
        MyAddressAdapterVH myAddressAdapterVH = new MyAddressAdapterVH(inflate);
        return myAddressAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAddressAdapterVH myAddressAdapterVH, int i) {
        final MyAddressBean myAddressBean = list.get(i);
        myAddressAdapterVH.myAddressName.setText(myAddressBean.getRealName());
        myAddressAdapterVH.myAddressPhone.setText(myAddressBean.getPhone());
        myAddressAdapterVH.myAddressAddress.setText(myAddressBean.getAddress());
        if (myAddressBean.getWhetherDefault().equals("1")){
            myAddressAdapterVH.myAddressCheckmo.setChecked(true);
        } else {
            myAddressAdapterVH.myAddressCheckmo.setChecked(false);
        }

        //设置默认收货地址
        myAddressAdapterVH.myAddressCheckmo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                boolean checked = myAddressAdapterVH.myAddressCheckmo.isChecked();
                if (checked){
                    //选择默认地址
                    RetrofitUtils.getInstance().createService(AddShoppingCarApiServer.class)
                            .requestSetAddress(params,myAddressBean.getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<AddCarBean>() {
                                @Override
                                public void accept(AddCarBean addCarBean) throws Exception {
                                    ToastUtils.showLong(addCarBean.getMessage());
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            });
                }
            }
        });

        myAddressAdapterVH.myAddressBtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",myAddressBean.getId());
                bundle.putString("realName",myAddressBean.getRealName());
                bundle.putString("phone",myAddressBean.getPhone());
                bundle.putString("address",myAddressBean.getAddress());
                bundle.putString("zipCode",myAddressBean.getZipCode());
                myAddressAdapterVHCallBack.toUpdateAdress(bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
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

    public interface MyAddressAdapterVHCallBack{
        void toUpdateAdress(Bundle bundle);
    }
}
