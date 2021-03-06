package com.king.maillyms.apis;

import com.example.lib_core.common.Apis;

public class ProductApis {
    public static final String HOME_BANNER = Apis.BASE_URL + "small/commodity/v1/bannerShow";
    public static final String HOME_PRODUCT = Apis.BASE_URL + "small/commodity/v1/commodityList";
    public static final String GOODS_DETAILS = Apis.BASE_URL + "small/commodity/v1/findCommodityDetailsById";

    /**
     * 搜索
     */
    public static final String GOODS_SEARCH = "small/commodity/v1/findCommodityByKeyword";

    /**
     * 商品分类详细
     */
    public static final String PRODUCT_CLS = "small/commodity/v1/findCommodityListByLabel";


    /**
     * 查询购物车
     */
    public static final String SHOPPING_SEARCH = "small/order/verify/v1/findShoppingCart";
    public static final String ADD_SHOPPING_SEARCH = "small/order/verify/v1/syncShoppingCart";


    /**
     * 圈子
     */
    public static final String QUAN_SEARCH = "small/circle/v1/findCircleList";


    /**
     * 我的收获地址列表
     */
    public static final String MY_ADDRESS = "small/user/verify/v1/receiveAddressList";
    public static final String ADD_MY_ADDRESS = "small/user/verify/v1/addReceiveAddress";
    public static final String UPADTA_MY_ADDRESS = "small/user/verify/v1/changeReceiveAddress";
    public static final String SET_MY_ADDRESS = "small/user/verify/v1/setDefaultReceiveAddress";//默认地址


    /**
     * 根据订单状态查询订单信息
     */
    public static final String CREATE_DAN = "small/order/verify/v1/createOrder";//创建
    public static final String DAN_LIST = "small/order/verify/v1/findOrderListByStatus";//查询
    public static final String DELETE_LIST = "small/order/verify/v1/deleteOrder";//删除

    public static final String TO_PAY = "small/order/verify/v1/pay";//支付




}
