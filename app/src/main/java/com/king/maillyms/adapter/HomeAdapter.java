package com.king.maillyms.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.king.maillyms.R;
import com.king.maillyms.activity.GoodsDetailsActivity;
import com.king.maillyms.activity.HomeProducetClsActivity;
import com.king.maillyms.activity.ProductActivity;
import com.king.maillyms.beans.BannerBean;
import com.king.maillyms.beans.ProductBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class HomeAdapter extends XRecyclerView.Adapter<HomeAdapter.HomeAdapterVH> {

    private HomeCallBack homeCallBack;
    private Context context;
    private ProductBean.ResultBean result;

    public void setHomeCallBack(HomeCallBack homeCallBack){
        this.homeCallBack = homeCallBack;
    }


    public HomeAdapter(Context context, ProductBean.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public HomeAdapterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_home, viewGroup, false);
        HomeAdapterVH homeAdapterVH = new HomeAdapterVH(view);

        return homeAdapterVH;

    }

    @Override
    public void onBindViewHolder(@NonNull final HomeAdapterVH homeAdapterVH, int i) {



        if (i == 0) {
            final ProductBean.ResultBean.RxxpBean rxxpBean = result.getRxxp().get(0);
            homeAdapterVH._title.setText(rxxpBean.getName());
            //根据商品列表归属标签查询商品信息
            homeAdapterVH.home_cls.setImageResource(R.drawable.common_btn_more_yellow);
            homeAdapterVH.home_cls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToGoodsClsDetails(rxxpBean.getId());
                }
            });

            List<ProductBean.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxpBean.getCommodityList();
            //Toast.makeText(context,commodityList.size()+"1",Toast.LENGTH_SHORT).show();
            homeAdapterVH.item_recy.setLayoutManager(new GridLayoutManager(context,3));
            HomeOneAdapter homeOneAdapter = new HomeOneAdapter(context,commodityList,0);
            homeAdapterVH.item_recy.setAdapter(homeOneAdapter);
            homeAdapterVH._title.setBackgroundResource(R.drawable.bitmap_r);

            homeOneAdapter.setHomeOneCallBack(new HomeOneAdapter.HomeOneCallBack() {
                @Override
                public void setOnClickListener(String s) {
                    //homeCallBack.setOnClickReListener(s);
                    getToGoods(s);
                }
            });
        } else if (i == 1){
            final ProductBean.ResultBean.MlssBean mlssBean = result.getMlss().get(0);
            homeAdapterVH._title.setText(mlssBean.getName());
            homeAdapterVH._title.setBackgroundResource(R.drawable.bitmap);
            //根据商品列表归属标签查询商品信息
            homeAdapterVH.home_cls.setImageResource(R.drawable.home_btn_more_purple);
            homeAdapterVH.home_cls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToGoodsClsDetails(mlssBean.getId());
                }
            });
            List<ProductBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = mlssBean.getCommodityList();
            homeAdapterVH.item_recy.setLayoutManager(new LinearLayoutManager(context));
            HomeTwoAdapter homeTwoAdapter = new HomeTwoAdapter(context,commodityList,1);
            homeAdapterVH.item_recy.setAdapter(homeTwoAdapter);
            homeTwoAdapter.setHomeTwoCallBack(new HomeTwoAdapter.HomeTwoCallBack() {
                @Override
                public void setOnClickListener(String s) {
                    getToGoods(s);
                }
            });

        } else if (i == 2){
            final ProductBean.ResultBean.PzshBean pzshBean = result.getPzsh().get(0);
            homeAdapterVH._title.setText(pzshBean.getName());
            homeAdapterVH._title.setBackgroundResource(R.drawable.bitmap_p);
            //根据商品列表归属标签查询商品信息
            homeAdapterVH.home_cls.setImageResource(R.drawable.home_btn_moer_pink);
            homeAdapterVH.home_cls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToGoodsClsDetails(pzshBean.getId());
                }
            });

            List<ProductBean.ResultBean.PzshBean.CommodityListBeanX> commodityList = pzshBean.getCommodityList();
            homeAdapterVH.item_recy.setLayoutManager(new GridLayoutManager(context,2));
            HomeThreeAdapter homeThreeAdapter = new HomeThreeAdapter(context,commodityList,2);
            homeAdapterVH.item_recy.setAdapter(homeThreeAdapter);

            homeThreeAdapter.setHomeThreeCallBack(new HomeThreeAdapter.HomeThreeCallBack() {
                @Override
                public void setOnClickListener(String s) {

                    getToGoods(s);
                }
            });
        }
    }

    /**
     * 根据商品列表归属标签查询商品信息
     * @param id
     */
    private void goToGoodsClsDetails(String id) {
        homeCallBack.setOnClickReListener(id);
    }

    /**
     * 页面跳转
     * @param s
     */
    private void getToGoods(String s) {
        //Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context,GoodsDetailsActivity.class);
        intent.putExtra("commodityId",s);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class HomeAdapterVH extends XRecyclerView.ViewHolder {
        TextView _title;
        RecyclerView item_recy;
        ImageView home_cls;

        public HomeAdapterVH(@NonNull View itemView) {
            super(itemView);

                _title = itemView.findViewById(R.id.textView);
                home_cls = itemView.findViewById(R.id.home_cls);
                item_recy = itemView.findViewById(R.id.item_recy);

        }
    }


    public interface HomeCallBack{
        void setOnClickReListener(String s);
    }
}
