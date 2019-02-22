package com.king.maillyms.beans.entity;

import com.example.lib_network.bean.BaseResponseBean;

public class QuanBean extends BaseResponseBean {

        /**
         * commodityId : 1
         * content :
         * createTime : 1550854009000
         * greatNum : 1
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * id : 523
         * image : http://172.17.8.100/images/small/circle_pic/2019-02-22/0687320190222104649.png
         * nickName : HA_DM5T9
         * userId : 11
         * whetherGreat : 2
         */

    private String commodityId;
    private String content;
    private String createTime;
    private String greatNum;
    private String headPic;
    private String id;
    private String image;
    private String nickName;
    private String userId;
    private String whetherGreat;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGreatNum() {
        return greatNum;
    }

    public void setGreatNum(String greatNum) {
        this.greatNum = greatNum;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWhetherGreat() {
        return whetherGreat;
    }

    public void setWhetherGreat(String whetherGreat) {
        this.whetherGreat = whetherGreat;
    }

}
