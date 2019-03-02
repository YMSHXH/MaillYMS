package com.king.maillyms.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lib_core.base.BaseFragment;
import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.google.gson.Gson;
import com.king.maillyms.R;
import com.king.maillyms.activity.MyAddressActivity;
import com.king.maillyms.activity.MyDataActivity;
import com.king.maillyms.beans.FindBean;
import com.king.maillyms.contact.FindContact;
import com.king.maillyms.presenter.FindPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineFragment extends BaseMvpFragment<FindContact.IFindModel,FindContact.IFindPresenter>
        implements FindContact.IFindView {

    @BindView(R.id.head_image_big)
    ImageView head_image_big;
    @BindView(R.id.head_image_simile)
    ImageView head_image_simile;
    @BindView(R.id.name_ni)
    TextView name_ni;

    @BindView(R.id.information)
    TextView information;
    @BindView(R.id.circle)
    TextView circle;
    @BindView(R.id.foot)
    TextView foot;
    @BindView(R.id.wallet)
    TextView wallet;
    @BindView(R.id.address)
    TextView address;
    private FindBean.ResultBean result;
    private Unbinder bind;

    @Override
    protected int getResLayoutById() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        bind = ButterKnife.bind(this, view);
        result = new FindBean.ResultBean();

        //点击事件
        setOnClick();

    }

    /**
     * 点击事件
     */
    private void setOnClick() {
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//信息
                Intent intent = new Intent(getActivity(),MyDataActivity.class);
                intent.putExtra("head",result.getHeadPic());
                intent.putExtra("name",result.getNickName());
                intent.putExtra("pwd",result.getPassword());
                startActivity(intent);
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //收货地址
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyAddressActivity.class);
                startActivity(intent);
            }
        });


        head_image_simile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    protected void init() {
        presenter.setFindList(null);
    }

    @Override
    public void onSeccess(String meg) {
        //Toast.makeText(getActivity(),meg,Toast.LENGTH_SHORT).show();
        FindBean findBean = new Gson().fromJson(meg, FindBean.class);
        if ("0000".equals(findBean.getStatus())){
            result = findBean.getResult();
            Glide.with(getActivity()).load(result.getHeadPic()).into(head_image_big);
            Glide.with(getActivity()).load(result.getHeadPic()).into(head_image_simile);
            name_ni.setText(result.getNickName());
        }
    }

    @Override
    public void onFail(String s) {

    }

    @Override
    public BasePresenter initPresenter() {
        return new FindPresenter();
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
