package com.king.maillyms.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lib_core.base.BaseFragment;
import com.king.maillyms.R;
import com.king.maillyms.activity.AmapActivity;

public class DanFragment extends BaseFragment {
    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_dan;
    }

    @Override
    protected void initView(View view) {
        Button gaode = view.findViewById(R.id.gaode);

        gaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"gaode",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(),AmapActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
