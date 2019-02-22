package com.king.maillyms.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchEntity {
    @Id(autoincrement = true)//自增的
    @Unique//唯一的
    @Property(nameInDb = "Search")
    private long id;
    private String commodityId;
    private String commodityName;
    private String masterPic;
    private String price;
    private String saleNum;
    @Generated(hash = 1833880400)
    public SearchEntity(long id, String commodityId, String commodityName,
            String masterPic, String price, String saleNum) {
        this.id = id;
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.masterPic = masterPic;
        this.price = price;
        this.saleNum = saleNum;
    }
    @Generated(hash = 1021466028)
    public SearchEntity() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCommodityId() {
        return this.commodityId;
    }
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
        return this.commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getMasterPic() {
        return this.masterPic;
    }
    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getSaleNum() {
        return this.saleNum;
    }
    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

}
