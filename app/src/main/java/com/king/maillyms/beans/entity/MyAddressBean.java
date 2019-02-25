package com.king.maillyms.beans.entity;

import com.example.lib_network.bean.BaseResponseBean;

/**
 * 查询收获地址
 */
public class MyAddressBean extends BaseResponseBean {

    /**
     * address : 北京 上地八街
     * createTime : 1551060195000
     * id : 863
     * phone : 18110011222
     * realName : 张三
     * userId : 739
     * whetherDefault : 1
     * zipCode : 101010
     */

    private String address;
    private String createTime;
    private String id;
    private String phone;
    private String realName;
    private String userId;
    private String whetherDefault;
    private String zipCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWhetherDefault() {
        return whetherDefault;
    }

    public void setWhetherDefault(String whetherDefault) {
        this.whetherDefault = whetherDefault;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
