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
import com.king.maillyms.beans.DanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaoGoodsAdapter extends RecyclerView.Adapter<DaoGoodsAdapter.DaoGoodsAdapterVH> {

    private Context context;
    private List<DanBean.OrderListBean.DetailListBean> detailList;

    public DaoGoodsAdapter(Context context, List<DanBean.OrderListBean.DetailListBean> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public DaoGoodsAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_dao_goods, viewGroup, false);
        DaoGoodsAdapterVH daoGoodsAdapterVH = new DaoGoodsAdapterVH(inflate);
        return daoGoodsAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull DaoGoodsAdapterVH daoGoodsAdapterVH, int i) {
        DanBean.OrderListBean.DetailListBean detailListBean = detailList.get(i);

        daoGoodsAdapterVH.oneTitle.setText(detailListBean.getCommodityName());
        daoGoodsAdapterVH.onePrice.setText("￥："+detailListBean.getCommodityPrice());

        //加载图片
        String commodityPic = detailListBean.getCommodityPic();
        String[] split = commodityPic.split("\\,");
        Glide.with(context).load(split[0]).into(daoGoodsAdapterVH.oneImg);
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class DaoGoodsAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.one_img)
        ImageView oneImg;
        @BindView(R.id.one_title)
        TextView oneTitle;
        @BindView(R.id.one_price)
        TextView onePrice;

        public DaoGoodsAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
