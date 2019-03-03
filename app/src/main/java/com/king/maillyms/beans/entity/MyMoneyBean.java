package com.king.maillyms.beans.entity;

import com.example.lib_network.bean.BaseResponseBean;

import java.util.List;

public class MyMoneyBean extends BaseResponseBean {


    /**
     * balance : 99999999
     * detailList : [{"amount":2,"createTime":1542476199000}]
     */

    private String balance;
    private List<DetailListBean> detailList;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    public static class DetailListBean {
        /**
         * amount : 2
         * createTime : 1542476199000
         */

        private String amount;
        private long createTime;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
