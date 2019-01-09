package com.king.maillyms.myview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

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
        ImageView EditText = view.findViewById(R.id.search_goods);
        ImageView TextView = view.findViewById(R.id.btn_searc);
        ImageView twoma = view.findViewById(R.id.twoma);

        twoma.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewCallBack.setTwoma(v);
            }
        });

    }

    public void setSearchViewCallBack(SearchViewCallBack searchViewCallBack){
        this.searchViewCallBack = searchViewCallBack;
    }

    public interface SearchViewCallBack{
        void setTwoma(View v);
    }
}
