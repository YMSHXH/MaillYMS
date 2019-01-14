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
import com.king.maillyms.beans.GoodsDetails;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private GoodsDetails.ResultBean result;

    public GoodsDetailsAdapter(Context context, GoodsDetails.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_home_goods_image, viewGroup, false);
        ImageVH imageVH = new ImageVH(inflate);
        return imageVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String picture = result.getPicture();
        final String[] split = picture.split(",");
        List<String> list = new ArrayList<>();

        for (int j = 0; j < split.length; j ++ ){
            list.add(split[j]);
        }

        ((ImageVH)viewHolder).image_banner.setData(list,null);

        ((ImageVH)viewHolder).image_banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(context).load(split[position]).into((ImageView) view);
            }
        });
        ((ImageVH)viewHolder).image_banner.stopAutoPlay();

        ((ImageVH)viewHolder).details_name.setText(result.getCommodityName());
        ((ImageVH)viewHolder).details_price.setText("￥："+result.getPrice());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ImageVH extends RecyclerView.ViewHolder{
        XBanner image_banner;
        TextView details_price,details_name;
        public ImageVH(@NonNull View itemView) {
            super(itemView);
            image_banner = itemView.findViewById(R.id.image_banner);
            details_price = itemView.findViewById(R.id.details_price);
            details_name = itemView.findViewById(R.id.details_name);

        }
    }

    class GoodsVH extends RecyclerView.ViewHolder{

        public GoodsVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
