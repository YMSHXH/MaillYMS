package com.king.maillyms.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CartEntity {

    @Id
    @Unique
    private long commodityId;
    private int count;
    @Generated(hash = 349262527)
    public CartEntity(long commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }
    @Generated(hash = 1508478210)
    public CartEntity() {
    }
    public long getCommodityId() {
        return this.commodityId;
    }
    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }


}
