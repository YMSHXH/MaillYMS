package com.king.maillyms.myview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.maillyms.R;

public class SearchView extends ConstraintLayout {
    private SearchViewCallBack searchViewCallBack;

    public SearchView(Context context) {
        this(context,null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_search,this,true);
        ImageView select = view.findViewById(R.id.select);
        final EditText search_goods = view.findViewById(R.id.search_goods);
        TextView btn_searc = view.findViewById(R.id.btn_searc);
        ImageView twoma = view.findViewById(R.id.twoma);


        twoma.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewCallBack.setTwoma(v);
            }
        });

        btn_searc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String goods_name = search_goods.getText().toString();
                //获取数据
                searchViewCallBack.setBtn_searc(goods_name);
            }
        });

//        search_goods.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                search_goods.setFocusable(true);
//            }
//        });

    }

    public void setSearchViewCallBack(SearchViewCallBack searchViewCallBack){
        this.searchViewCallBack = searchViewCallBack;
    }

    public interface SearchViewCallBack{
        void setTwoma(View v);
        void setBtn_searc(String goods_name);
    }
}
