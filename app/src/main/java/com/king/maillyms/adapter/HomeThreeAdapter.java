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

public class HomeThreeAdapter extends RecyclerView.Adapter<HomeThreeAdapter.HomeOneVH> {

    private Context context;
    private List<ProductBean.ResultBean.PzshBean.CommodityListBeanX> list;
    private int position;
    private HomeThreeCallBack homeThreeCallBack;

    public HomeThreeAdapter(Context context, List<ProductBean.ResultBean.PzshBean.CommodityListBeanX> list, int position) {
        this.context = context;
        this.list = list;
        this.position = position;
    }

    @NonNull
    @Override
    public HomeOneVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_home_three,viewGroup,false);

        HomeOneVH homeOneVH = new HomeOneVH(view);

        return homeOneVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeOneVH homeOneVH, int i) {

        final ProductBean.ResultBean.PzshBean.CommodityListBeanX commodityListBeanX = list.get(i);

        homeOneVH.one_title.setText(commodityListBeanX.getCommodityName());
        homeOneVH.one_price.setText("￥："+commodityListBeanX.getPrice());
        Glide.with(context).load(commodityListBeanX.getMasterPic()).into(homeOneVH.one_img);

        homeOneVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeThreeCallBack.setOnClickListener(commodityListBeanX.getCommodityId());
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

    public void setHomeThreeCallBack(HomeThreeCallBack homeThreeCallBack){
        this.homeThreeCallBack = homeThreeCallBack;
    }

    interface HomeThreeCallBack{
        void setOnClickListener(String s);
    }
}
