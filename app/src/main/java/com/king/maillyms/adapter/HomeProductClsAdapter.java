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
import com.king.maillyms.beans.SearchBean;
import com.king.maillyms.beans.entity.ProducetClsBean;

import java.util.ArrayList;
import java.util.List;

public class HomeProductClsAdapter extends XRecyclerView.Adapter<HomeProductClsAdapter.SearchVh>{

    private Context context;
    private List<ProducetClsBean> list;
    private SearchCallBack searchCallBack;

    public void setSearchCallBack(SearchCallBack searchCallBack) {
        this.searchCallBack = searchCallBack;
    }

    public HomeProductClsAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void setList(List<ProducetClsBean> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_search,viewGroup,false);
        SearchVh searchVh = new SearchVh(view);
        return searchVh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchVh searchVh, int i) {
        final ProducetClsBean resultBean = list.get(i);
        Uri uri = Uri.parse(resultBean.getMasterPic());
        searchVh.ser_img.setImageURI(uri);
        searchVh.ser_title.setText(resultBean.getCommodityName());
        searchVh.ser_price.setText("￥:"+resultBean.getPrice());
        searchVh.ser_sum.setText("有售"+resultBean.getSaleNum()+"件");

        searchVh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCallBack.setOnClickListener(resultBean.getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchVh extends RecyclerView.ViewHolder {
        SimpleDraweeView ser_img;
        TextView ser_title,ser_price,ser_sum;
        public SearchVh(@NonNull View itemView) {
            super(itemView);
            ser_img = itemView.findViewById(R.id.ser_img);
            ser_title = itemView.findViewById(R.id.ser_title);
            ser_price = itemView.findViewById(R.id.ser_price);
            ser_price = itemView.findViewById(R.id.ser_price);
            ser_sum = itemView.findViewById(R.id.ser_sum);
        }
    }

    public interface SearchCallBack{
        void setOnClickListener(String s);
    }
}
