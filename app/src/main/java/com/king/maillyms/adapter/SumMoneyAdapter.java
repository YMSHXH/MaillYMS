package com.king.maillyms.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.king.maillyms.R;
import com.king.maillyms.beans.entity.ShoppingCarBean;
import com.king.maillyms.myview.AddView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SumMoneyAdapter extends RecyclerView.Adapter<SumMoneyAdapter.SumMoneyAdapterVH> {

    private Context context;
    private List<ShoppingCarBean> list;

    public SumMoneyAdapter(Context context, List<ShoppingCarBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SumMoneyAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sun_money, viewGroup, false);
        SumMoneyAdapterVH sumMoneyAdapterVH = new SumMoneyAdapterVH(view);
        return sumMoneyAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull SumMoneyAdapterVH sumMoneyAdapterVH, int i) {
        ShoppingCarBean shoppingCarBean = list.get(i);
        sumMoneyAdapterVH.ckbGoodsCarName.setText(shoppingCarBean.getCommodityName());
        sumMoneyAdapterVH.ckbGoodsCarPrace.setText( "￥："+shoppingCarBean.getPrice());
        Glide.with(context).load(shoppingCarBean.getPic()).into(sumMoneyAdapterVH.goodsCarImg);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SumMoneyAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.goodsCar_img)
        ImageView goodsCarImg;
        @BindView(R.id.ckb_goodsCar_name)
        TextView ckbGoodsCarName;
        @BindView(R.id.ckb_goodsCar_Prace)
        TextView ckbGoodsCarPrace;
        @BindView(R.id.addView)
        AddView addView;
        public SumMoneyAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
