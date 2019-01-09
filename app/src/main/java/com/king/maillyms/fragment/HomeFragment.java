package com.king.maillyms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lib_core.base.BaseFragment;
import com.king.maillyms.R;
import com.king.maillyms.myview.SearchView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class HomeFragment extends BaseFragment {
    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        //initZxing(view);
        SearchView serach = view.findViewById(R.id.serach);
        serach.setSearchViewCallBack(new SearchView.SearchViewCallBack() {
            @Override
            public void setTwoma(View v) {
                // 打开默认二维码扫描界面
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),CaptureActivity.class);
                        startActivityForResult(intent,201);
                    }
                });
            }
        });
    }

    private void initZxing(View view) {
        ImageView twoma = view.findViewById(R.id.twoma);

        // 打开默认二维码扫描界面
        twoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CaptureActivity.class);
                startActivityForResult(intent,201);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if ( requestCode == 201 ){
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS){
                    String result = bundle.getString(CodeUtils.RESULT_STRING);

                    Toast.makeText(getActivity(),"解析结果"+result ,Toast.LENGTH_SHORT).show();;
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED){
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
