package com.simpleryo.leyotang.bean;

/**
 * @ClassNname：UpdateOrderBean.java
 * @Describe 修改订单
 * @author huanglei
 * @time 2018/5/29 13:32
 */
public class UpdateOrderBean extends BaseResult {

    /**
     * data : {"id":null,"mid":null,"storeId":"5af4e4f751183172a0e74b0e","creatorId":null,"creationTime":null,"no":null,"courseId":"5b08229351183157f2d547b4","courseName":"数学","imageUrl":null,"unitPrice":0,"quantity":1,"totalAmt":0,"payAmt":0,"payTime":null,"payType":"WECHATPAY","routeCode":"ANDROID","status":null,"remark":null,"coachId":"5af4fa0651183175f2ccb997","payStatus":null,"userPhone":"15221316476","userName":"黄磊","userRemark":"单次课程预约","creationDate":"2018-06-11 08:00-16:00","aboutArrangeId":"5b08229351183157f2d547b8","lastAboutArrangeId":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : null
         * mid : null
         * storeId : 5af4e4f751183172a0e74b0e
         * creatorId : null
         * creationTime : null
         * no : null
         * courseId : 5b08229351183157f2d547b4
         * courseName : 数学
         * imageUrl : null
         * unitPrice : 0
         * quantity : 1
         * totalAmt : 0
         * payAmt : 0
         * payTime : null
         * payType : WECHATPAY
         * routeCode : ANDROID
         * status : null
         * remark : null
         * coachId : 5af4fa0651183175f2ccb997
         * payStatus : null
         * userPhone : 15221316476
         * userName : 黄磊
         * userRemark : 单次课程预约
         * creationDate : 2018-06-11 08:00-16:00
         * aboutArrangeId : 5b08229351183157f2d547b8
         * lastAboutArrangeId : null
         */

        private String id;
        private String mid;
        private String storeId;
        private String creatorId;
        private String creationTime;
        private String no;
        private String courseId;
        private String courseName;
        private String imageUrl;
        private int unitPrice;
        private int quantity;
        private int totalAmt;
        private int payAmt;
        private String payTime;
        private String payType;
        private String routeCode;
        private String status;
        private String remark;
        private String coachId;
        private String payStatus;
        private String userPhone;
        private String userName;
        private String userRemark;
        private String creationDate;
        private String aboutArrangeId;
        private String lastAboutArrangeId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(String creatorId) {
            this.creatorId = creatorId;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getTotalAmt() {
            return totalAmt;
        }

        public void setTotalAmt(int totalAmt) {
            this.totalAmt = totalAmt;
        }

        public int getPayAmt() {
            return payAmt;
        }

        public void setPayAmt(int payAmt) {
            this.payAmt = payAmt;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getRouteCode() {
            return routeCode;
        }

        public void setRouteCode(String routeCode) {
            this.routeCode = routeCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCoachId() {
            return coachId;
        }

        public void setCoachId(String coachId) {
            this.coachId = coachId;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserRemark() {
            return userRemark;
        }

        public void setUserRemark(String userRemark) {
            this.userRemark = userRemark;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public String getAboutArrangeId() {
            return aboutArrangeId;
        }

        public void setAboutArrangeId(String aboutArrangeId) {
            this.aboutArrangeId = aboutArrangeId;
        }

        public String getLastAboutArrangeId() {
            return lastAboutArrangeId;
        }

        public void setLastAboutArrangeId(String lastAboutArrangeId) {
            this.lastAboutArrangeId = lastAboutArrangeId;
        }
    }
}
