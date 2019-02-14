package com.king.maillyms.beans;

public class FindBean {

    /**
     * result : {"createTime":1547505649000,"headPic":"http://172.17.8.100/images/small/default/user.jpg","nickName":"pn_7VAZ8","password":"GpADd/DoEZhu9k7vOJTh+A==","phone":"13563015479","sex":1,"userId":1706}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * createTime : 1547505649000
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * nickName : pn_7VAZ8
         * password : GpADd/DoEZhu9k7vOJTh+A==
         * phone : 13563015479
         * sex : 1
         * userId : 1706
         */

        private String createTime;
        private String headPic;
        private String nickName;
        private String password;
        private String phone;
        private String sex;
        private String userId;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
