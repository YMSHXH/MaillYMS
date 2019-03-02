package com.king.maillyms.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.util.Log;
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
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.king.maillyms.utils.PathUriUtils.getRealPathFromUri;

/**
 * 我的信息
 */
public class MyDataActivity extends BaseMvpActivity<HeadImageContact.IHeadImageModel,HeadImageContact.IHeadImagePresenter>
        implements HeadImageContact.IHeadImageView {

    private static final int REQUEST_CODE_CHOOSE = 1998 ;
    @BindView(R.id.head_image)
    ImageView head_image;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.pwd)
    TextView mPwd;
    private Map<String, String> params;

    //定义takephoto两个变量

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
    public void setToHeadImage(final View view){

        params = new HashMap<>();
        params.put("userId", ShapedP.getmInstance().getSP("userId"));
        params.put("sessionId", ShapedP.getmInstance().getSP("sessionId"));

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            //加载图片
                            Matisse.from(MyDataActivity.this)
                                    .choose(MimeType.of(MimeType.JPEG, MimeType.PNG)) // 选择 mime 的类型
                                    .maxSelectable(1) // 图片选择的最多数量
                                    .countable(true)//是否显示选中数字
                                    .capture(true)//是否提供拍照功能
                                    .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))//存储地址
                                    .maxSelectable(9)//最大选择数
                                    //.addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))//筛选条件
                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//屏幕方向
                                    //.gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                    .gridExpectedSize(240)//图片大小
                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                    .thumbnailScale(0.85f) // 缩略图的比例
                                    .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                                    .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



        //判断sd卡是否挂载
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//挂载
//            String path = Environment.getExternalStorageDirectory().getAbsolutePath()
//                    +File.separator + "79d949b8g7c203071abf1&690.jpg";
//
//            System.out.println("path1:====="+path);
//            File file = new File(path);
//            System.out.println("path2:====="+file);
//            if (file!=null && file.exists()){
//                //图片请求体
//                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
//                MultipartBody.Part filePart = MultipartBody.Part.createFormData("image",
//                        file.getName(),requestBody);
//                presenter.setheadList(params,filePart);
//            } else{
//                ToastUtils.showShort("请选择文件");
//            }
//        }
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

    List<Uri> mSelected = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);

            Uri uri = mSelected.get(0);//获取路径
            String realPathFromUri = getRealPathFromUri(MyDataActivity.this, uri);
            Log.d("Matisse", "mSelected: " + realPathFromUri);
            File file = new File(realPathFromUri);
            if (file!=null && file.exists()){
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


}
