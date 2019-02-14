package com.king.maillyms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.king.maillyms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDataActivity extends AppCompatActivity {

    @BindView(R.id.head_image)
    ImageView head_image;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.pwd)
    TextView mPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        //加载黄油刀
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String head = intent.getStringExtra("head");
        String name = intent.getStringExtra("name");
        String pwd = intent.getStringExtra("pwd");
        //Toast.makeText(this,name+"----你的昵称",Toast.LENGTH_SHORT).show();
        Glide.with(this).load(head).into(head_image);
        mName.setText(name);
        mPwd.setText(pwd);
    }
}
