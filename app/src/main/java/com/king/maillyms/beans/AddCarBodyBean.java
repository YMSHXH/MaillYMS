package com.king.maillyms.beans;

public class AddCarBodyBean {


    /**
     * commodityId : 5
     * count : 3
     */

    private int commodityId;
    private int count;

    public AddCarBodyBean(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
