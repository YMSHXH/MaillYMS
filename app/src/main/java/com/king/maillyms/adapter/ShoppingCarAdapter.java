package com.king.maillyms.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.king.maillyms.R;
import com.king.maillyms.beans.entity.ShoppingCarBean;
import com.king.maillyms.myview.AddView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCarAdapter extends RecyclerView.Adapter<ShoppingCarAdapter.ShoppingCaVH> {

    private Context context;
    private List<ShoppingCarBean> list;
    private ShoppingCarAdapterCallBack shoppingCarAdapterCallBack;

    public void setShoppingCarAdapterCallBack(ShoppingCarAdapterCallBack shoppingCarAdapterCallBack) {
        this.shoppingCarAdapterCallBack = shoppingCarAdapterCallBack;
    }

    public ShoppingCarAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void setList(List<ShoppingCarBean> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShoppingCaVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shoppingcar, viewGroup, false);
        ShoppingCaVH shoppingCaVH = new ShoppingCaVH(view);
        return shoppingCaVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingCaVH shoppingCaVH, int i) {
        final ShoppingCarBean shoppingCarBean = list.get(i);
        shoppingCaVH.goodsCarCkb.setChecked(shoppingCarBean.isChedcked());

        shoppingCaVH.ckbGoodsCarName.setText(shoppingCarBean.getCommodityName());
        shoppingCaVH.ckbGoodsCarPrace.setText( "￥："+shoppingCarBean.getPrice());
        Glide.with(context).load(shoppingCarBean.getPic()).into(shoppingCaVH.goodsCarImg);

        //设置数量
        shoppingCaVH.addView.setAddMinusCallback(new AddView.AddMinusCallback() {
            @Override
            public void numCallback(int num) {
                //设置数量
                shoppingCarBean.setCount(num + "");
                if (shoppingCarAdapterCallBack != null) {
                    shoppingCarAdapterCallBack.notifySum();
                }
            }
        });

        //计算全选
        shoppingCaVH.goodsCarCkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = shoppingCaVH.goodsCarCkb.isChecked();
                shoppingCarBean.setChedcked(checked);
                notifyDataSetChanged();
                //调用接口
                if (shoppingCarAdapterCallBack != null) {
                    shoppingCarAdapterCallBack.notifySum();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShoppingCaVH extends RecyclerView.ViewHolder {
        @BindView(R.id.goodsCar_ckb)
        CheckBox goodsCarCkb;
        @BindView(R.id.goodsCar_img)
        ImageView goodsCarImg;
        @BindView(R.id.ckb_goodsCar_name)
        TextView ckbGoodsCarName;
        @BindView(R.id.ckb_goodsCar_Prace)
        TextView ckbGoodsCarPrace;
        @BindView(R.id.addView)
        AddView addView;

        public ShoppingCaVH(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface ShoppingCarAdapterCallBack{
        void notifySum();
    }

}
