package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

/**
 * @ClassNname：CreateOrderBean.java
 * @Describe 创建订单实体类
 * @author huanglei
 * @time 2018/4/16 13:30
 */
@HttpResponse(parser = JsonResponseParser.class)
public class CreateOrderBean extends BaseResult {


    /**
     * data : {"id":"ff80808162ed4f530162f14d55e60000","mid":"01","storeId":"5ad49583511831431f7e2f1c","creatorId":"","creationTime":1524466800061,"items":null,"no":"1524466800061","courseId":"5adc5ca95118310ff9412b50","courseName":"瑜伽","imageUrl":null,"unitPrice":99800,"quantity":1,"totalAmt":99800,"payAmt":99800,"payTime":null,"payType":"ALIPAY","routeCode":"ANDROID","status":"NEW","remark":null,"coachId":"5ad7145e51183106cd04a778","creationDate":"2018-04-23","payStatus":null}
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
         * id : ff80808162ed4f530162f14d55e60000
         * mid : 01
         * storeId : 5ad49583511831431f7e2f1c
         * creatorId : 
         * creationTime : 1524466800061
         * items : null
         * no : 1524466800061
         * courseId : 5adc5ca95118310ff9412b50
         * courseName : 瑜伽
         * imageUrl : null
         * unitPrice : 99800
         * quantity : 1
         * totalAmt : 99800
         * payAmt : 99800
         * payTime : null
         * payType : ALIPAY
         * routeCode : ANDROID
         * status : NEW
         * remark : null
         * coachId : 5ad7145e51183106cd04a778
         * creationDate : 2018-04-23
         * payStatus : null
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
        private String creationDate;
        private String payStatus;

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

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }
    }
}
