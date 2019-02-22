package com.king.maillyms.apis;

import com.example.lib_core.common.Apis;

public class ProductApis {
    public static final String HOME_BANNER = Apis.BASE_URL + "small/commodity/v1/bannerShow";
    public static final String HOME_PRODUCT = Apis.BASE_URL + "small/commodity/v1/commodityList";
    public static final String GOODS_DETAILS = Apis.BASE_URL + "small/commodity/v1/findCommodityDetailsById";

    //搜索
    public static final String GOODS_SEARCH = "small/commodity/v1/findCommodityByKeyword";

    /**
     * 查询购物车
     */
    public static final String SHOPPING_SEARCH = "small/order/verify/v1/findShoppingCart";
    public static final String ADD_SHOPPING_SEARCH = "small/order/verify/v1/syncShoppingCart";



    /**
     * 圈子
     */
    public static final String QUAN_SEARCH = "small/circle/v1/findCircleList";
}
