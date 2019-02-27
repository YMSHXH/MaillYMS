package com.king.maillyms.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.utils.ShapedP;
import com.king.maillyms.R;
import com.king.maillyms.beans.HeadImageBean;
import com.king.maillyms.contact.HeadImageContact;
import com.king.maillyms.presenter.HeadImagePresenter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyDataActivity extends BaseMvpActivity<HeadImageContact.IHeadImageModel,HeadImageContact.IHeadImagePresenter>
        implements HeadImageContact.IHeadImageView {

    @BindView(R.id.head_image)
    ImageView head_image;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.pwd)
    TextView mPwd;

    @Override
    protected void initView() {
        //加载黄油刀
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        String head = intent.getStringExtra("head");
        String name = intent.getStringExtra("name");
        String pwd = intent.getStringExtra("pwd");
        //Toast.makeText(this,name+"----你的昵称",Toast.LENGTH_SHORT).show();
        Glide.with(this).load(head).into(head_image);
        mName.setText(name);
        mPwd.setText(pwd);
    }


    /**
     * 设置头像
     * @return
     */
    @Override
    protected int getResLayoutById() {
        return R.layout.activity_my_data;
    }

    @OnClick(R.id.head_image)
    public void setToHeadImage(View view){

        Map<String, String> params = new HashMap<>();
        params.put("userId", ShapedP.getmInstance().getSP("userId"));
        params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));
        //加载图片
        //判断sd卡是否挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//挂载
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                    +File.separator + "79d949b8g7c203071abf1&690.jpg";

            System.out.println("path:====="+path);
            File file = new File(path);
            if (file!=null && file.exists()){
                System.out.println("path:========="+path);
                //图片请求体
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("image",
                        file.getName(),requestBody);
                presenter.setheadList(params,filePart);
            } else{
                ToastUtils.showShort("请选择文件");
            }
        }
    }

    @Override
    public void onSeccess(HeadImageBean headImageBean) {
        Toast.makeText(this,headImageBean.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String s) {

    }

    @Override
    public BasePresenter initPresenter() {
        return new HeadImagePresenter();
    }

    @Override
    public void showLoding() {

    }

    @Override
    public void hideLoding() {

    }

    @Override
    public void failLoding(String msg) {

    }
}
