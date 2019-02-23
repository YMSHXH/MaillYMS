package com.king.maillyms.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.beans.entity.QuanBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuanAdapter extends XRecyclerView.Adapter<QuanAdapter.QuanAdapterVH> {

    private Context context;
    private List<QuanBean> list;

    /**
     * 构造器 :构造QuanAdapter的初始化
     *
     * @param context
     */
    public QuanAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void setList(List<QuanBean> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    public void addList(List<QuanBean> list) {
        if (list != null) {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuanAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item_quan2, viewGroup, false);
        QuanAdapterVH quanAdapterVH = new QuanAdapterVH(inflate);
        return quanAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull QuanAdapterVH quanAdapterVH, int i) {
        QuanBean quanBean = list.get(i);
        quanAdapterVH.headImg.setImageURI(Uri.parse(quanBean.getHeadPic()));
        quanAdapterVH.quanTitle.setText(quanBean.getNickName());
        quanAdapterVH.quanActr.setText(quanBean.getContent());
        if (!"".equals(quanBean.getImage()) && quanBean.getImage() != null){
            quanAdapterVH.quanImg1.setVisibility(View.VISIBLE);
            quanAdapterVH.quanImg1.setImageURI(Uri.parse(quanBean.getImage()));
                //根据图片张数加载布局
                String image = quanBean.getImage();
                String[] image_split = image.split("\\,");
//                System.out.println(quanBean.getNickName() + "=====" +quanBean.getImage());
//                System.out.println(image_split.length + "");
                //根据图片张数判断加载什么布局
                if (image_split !=null && image_split.length == 2)  {
                    quanAdapterVH.quanImg2.setVisibility(View.VISIBLE);
                    quanAdapterVH.quanImg1.setImageURI(Uri.parse(image_split[0]));
                    quanAdapterVH.quanImg2.setImageURI(Uri.parse(image_split[1]));
                }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuanAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.head_img)
        SimpleDraweeView headImg;
        @BindView(R.id.quan_title)
        TextView quanTitle;
        @BindView(R.id.quan_actr)
        TextView quanActr;
        @BindView(R.id.quan_img1)
        SimpleDraweeView quanImg1;
        @BindView(R.id.quan_img2)
        SimpleDraweeView quanImg2;
        public QuanAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
