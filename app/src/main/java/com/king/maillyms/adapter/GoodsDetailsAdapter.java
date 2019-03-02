package com.king.maillyms.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.chrisbanes.photoview.PhotoView;
import com.king.maillyms.R;
import com.king.maillyms.beans.GoodsDetails;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsDetailsAdapter extends RecyclerView.Adapter<GoodsDetailsAdapter.GoodsDetailsAdapterVH> {

    private Context context;
    private GoodsDetails.ResultBean result;

    public GoodsDetailsAdapter(Context context, GoodsDetails.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public GoodsDetailsAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_home_goods_image, viewGroup, false);
        GoodsDetailsAdapterVH goodsDetailsAdapterVH = new GoodsDetailsAdapterVH(inflate);
        return goodsDetailsAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsDetailsAdapterVH goodsDetailsAdapterVH, int i) {
        String picture = result.getPicture();
        final String[] split = picture.split(",");
        List<String> list = new ArrayList<>();
        for (int j = 0; j < split.length; j ++ ){
            list.add(split[j]);
        }
        //加载本地网页
        goodsDetailsAdapterVH.webV.loadData(result.getDetails(),
                "text/html; charset=UTF-8",
                null);

        goodsDetailsAdapterVH.imageBanner.setData(list,null);
        goodsDetailsAdapterVH.imageBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(context).load(split[position]).into((ImageView) view);
            }
        });
        goodsDetailsAdapterVH.imageBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, final int position) {
                View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_photoview, null, false);
                final PhotoView photoView = dialogView.findViewById(R.id.photo_view);
                final ImageView photoFinish = dialogView.findViewById(R.id.photo_finish);
                final ImageView photoLast = dialogView.findViewById(R.id.photo_last);
                final ImageView photoNext = dialogView.findViewById(R.id.photo_next);
                Glide.with(context).load(split[position]).into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        photoView.setImageDrawable(resource);
                    }
                });
                photoLast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position-1 > 0){
                            Glide.with(context).load(split[position-1]).into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    photoView.setImageDrawable(resource);
                                }
                            });
                        }else {
                            Glide.with(context).load(split[split.length-1]).into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    photoView.setImageDrawable(resource);
                                }
                            });
                        }

                    }
                });
                photoNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position+1 < split.length){
                            Glide.with(context).load(split[position+1]).into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    photoView.setImageDrawable(resource);
                                }
                            });
                        }else {
                            Glide.with(context).load(split[0]).into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    photoView.setImageDrawable(resource);
                                }
                            });
                        }
                    }
                });
                final AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setView(dialogView)
                        .setCancelable(true)
                        .create();
                final AlertDialog dialog = builder.show();


                photoFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });



        goodsDetailsAdapterVH.detailsName.setText(result.getCommodityName());
        goodsDetailsAdapterVH.detailsPrice.setText("￥："+result.getPrice());
    }

    @Override
    public int getItemCount() {
        return 1;
    }



    public class GoodsDetailsAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.image_banner)
        XBanner imageBanner;
        @BindView(R.id.details_price)
        TextView detailsPrice;
        @BindView(R.id.details_name)
        TextView detailsName;
        @BindView(R.id.webV)
        WebView webV;
        public GoodsDetailsAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
