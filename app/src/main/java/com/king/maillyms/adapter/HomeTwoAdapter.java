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
import com.king.maillyms.beans.ProductBean;

import java.util.List;

public class HomeTwoAdapter extends RecyclerView.Adapter<HomeTwoAdapter.HomeOneVH> {

    private Context context;
    private List<ProductBean.ResultBean.MlssBean.CommodityListBeanXX> list;
    private int position;
    private HomeTwoCallBack homeTwoCallBack;

    public HomeTwoAdapter(Context context, List<ProductBean.ResultBean.MlssBean.CommodityListBeanXX> list, int position) {
        this.context = context;
        this.list = list;
        this.position = position;
    }

    @NonNull
    @Override
    public HomeOneVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_home_two,viewGroup,false);

        HomeOneVH homeOneVH = new HomeOneVH(view);

        return homeOneVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeOneVH homeOneVH, int i) {
        final ProductBean.ResultBean.MlssBean.CommodityListBeanXX commodityListBeanXX = list.get(i);

        homeOneVH.one_title.setText(commodityListBeanXX.getCommodityName());
        homeOneVH.one_price.setText("￥："+commodityListBeanXX.getPrice());
        Glide.with(context).load(commodityListBeanXX.getMasterPic()).into(homeOneVH.one_img);

        homeOneVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeTwoCallBack.setOnClickListener(commodityListBeanXX.getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class HomeOneVH extends RecyclerView.ViewHolder{

        ImageView one_img;
        TextView one_title,one_price;
        public HomeOneVH(@NonNull View itemView) {
            super(itemView);
            one_img = itemView.findViewById(R.id.one_img);
            one_title = itemView.findViewById(R.id.one_title);
            one_price = itemView.findViewById(R.id.one_price);
        }
    }

    public void setHomeTwoCallBack(HomeTwoCallBack  homeTwoCallBack){
        this.homeTwoCallBack = homeTwoCallBack;
    }

    interface HomeTwoCallBack{
        void setOnClickListener(String s);
    }
}
