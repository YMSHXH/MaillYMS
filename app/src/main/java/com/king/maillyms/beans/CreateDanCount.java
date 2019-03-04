package com.king.maillyms.beans;

public class CreateDanCount {

    /**
     * commodityId : 23
     * amount : 1
     */

    private int commodityId;
    private int amount;

    public CreateDanCount(int commodityId, int amount) {
        this.commodityId = commodityId;
        this.amount = amount;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
