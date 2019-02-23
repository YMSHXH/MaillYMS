package com.king.maillyms.beans.entity;

import com.example.lib_network.bean.BaseResponseBean;

/**
 * 商品分类详情详细信息
 */

public class ProducetClsBean extends BaseResponseBean {

    /**
     * commodityId : 32
     * commodityName : 唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋
     * masterPic : http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg
     * price : 88
     * saleNum : 0
     */

    private String commodityId;
    private String commodityName;
    private String masterPic;
    private String price;
    private String saleNum;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getMasterPic() {
        return masterPic;
    }

    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

}
