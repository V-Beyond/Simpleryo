package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * @ClassNname：MyCouponListBean.java
 * @Describe 我的优惠券列表实体类
 * @author huanglei
 * @time 2018/8/6 10:23
 */
@HttpResponse(parser = JsonResponseParser.class)
public class MyCouponListBean extends BaseResult {

    /**
     * data : [{"type":{"id":"5b6567c351183112561f5fc1","mid":"01","storeId":null,"creatorId":"system","creationTime":1533372355816,"name":"7折优惠券","imageUri":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/849eb764a1e6606acd825bfbc9d5726e","startTime":1533312000000,"endTime":1535817600000,"limitAmount":-1,"enable":true,"discount":70,"subtractAmount":null,"totalCounter":100,"consumeCounter":0,"retrieveCounter":1,"expireCounter":null,"retrieved":null,"category":"DISCOUNT","notes":null,"firstCourseTypes":null,"secondCourseTypes":null,"store":null,"courses":[]},"status":"UNUSED","creationTime":1533380687977,"consumeTime":null,"creator":{"id":"5b56bcec51183107612cf161","nickName":"小李君","avatarUrl":"http://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c6d7c644da5452c777ec283f6fba7069","name":null,"phone":null,"address":null}},{"type":{"id":"5b654cc05118310d7ec208bb","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1533365440377,"name":"试试6","imageUri":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/849eb764a1e6606acd825bfbc9d5726e","startTime":1533139200000,"endTime":1535904000000,"limitAmount":100,"enable":true,"discount":null,"subtractAmount":90,"totalCounter":6,"consumeCounter":0,"retrieveCounter":1,"expireCounter":null,"retrieved":null,"category":"CASH","notes":null,"firstCourseTypes":null,"secondCourseTypes":null,"store":{"id":"5b5608c451183107612cf15f","name":"微信小程序实战训练营"},"courses":[]},"status":"UNUSED","creationTime":1533378360072,"consumeTime":null,"creator":{"id":"5b56bcec51183107612cf161","nickName":"小李君","avatarUrl":"http://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c6d7c644da5452c777ec283f6fba7069","name":null,"phone":null,"address":null}}]
     * count : 2
     */

    private int count;
    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : {"id":"5b6567c351183112561f5fc1","mid":"01","storeId":null,"creatorId":"system","creationTime":1533372355816,"name":"7折优惠券","imageUri":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/849eb764a1e6606acd825bfbc9d5726e","startTime":1533312000000,"endTime":1535817600000,"limitAmount":-1,"enable":true,"discount":70,"subtractAmount":null,"totalCounter":100,"consumeCounter":0,"retrieveCounter":1,"expireCounter":null,"retrieved":null,"category":"DISCOUNT","notes":null,"firstCourseTypes":null,"secondCourseTypes":null,"store":null,"courses":[]}
         * status : UNUSED
         * creationTime : 1533380687977
         * consumeTime : null
         * creator : {"id":"5b56bcec51183107612cf161","nickName":"小李君","avatarUrl":"http://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c6d7c644da5452c777ec283f6fba7069","name":null,"phone":null,"address":null}
         */

        private TypeBean type;
        private String status;
        private String creationTime;
        private String consumeTime;
        private CreatorBean creator;

        public TypeBean getType() {
            return type;
        }

        public void setType(TypeBean type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getConsumeTime() {
            return consumeTime;
        }

        public void setConsumeTime(String consumeTime) {
            this.consumeTime = consumeTime;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public static class TypeBean {
            /**
             * id : 5b6567c351183112561f5fc1
             * mid : 01
             * storeId : null
             * creatorId : system
             * creationTime : 1533372355816
             * name : 7折优惠券
             * imageUri : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/849eb764a1e6606acd825bfbc9d5726e
             * startTime : 1533312000000
             * endTime : 1535817600000
             * limitAmount : -1
             * enable : true
             * discount : 70
             * subtractAmount : null
             * totalCounter : 100
             * consumeCounter : 0
             * retrieveCounter : 1
             * expireCounter : null
             * retrieved : null
             * category : DISCOUNT
             * notes : null
             * firstCourseTypes : null
             * secondCourseTypes : null
             * store : null
             * courses : []
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private String creationTime;
            private String name;
            private String imageUri;
            private String startTime;
            private String endTime;
            private int limitAmount;
            private boolean enable;
            private int discount;
            private String subtractAmount;
            private int totalCounter;
            private int consumeCounter;
            private int retrieveCounter;
            private String expireCounter;
            private String retrieved;
            private String category;
            private String notes;
            private String firstCourseTypes;
            private String secondCourseTypes;
            private StoreBean store;
            private List<?> courses;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageUri() {
                return imageUri;
            }

            public void setImageUri(String imageUri) {
                this.imageUri = imageUri;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getLimitAmount() {
                return limitAmount;
            }

            public void setLimitAmount(int limitAmount) {
                this.limitAmount = limitAmount;
            }

            public boolean isEnable() {
                return enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public String getSubtractAmount() {
                return subtractAmount;
            }

            public void setSubtractAmount(String subtractAmount) {
                this.subtractAmount = subtractAmount;
            }

            public int getTotalCounter() {
                return totalCounter;
            }

            public void setTotalCounter(int totalCounter) {
                this.totalCounter = totalCounter;
            }

            public int getConsumeCounter() {
                return consumeCounter;
            }

            public void setConsumeCounter(int consumeCounter) {
                this.consumeCounter = consumeCounter;
            }

            public int getRetrieveCounter() {
                return retrieveCounter;
            }

            public void setRetrieveCounter(int retrieveCounter) {
                this.retrieveCounter = retrieveCounter;
            }

            public String getExpireCounter() {
                return expireCounter;
            }

            public void setExpireCounter(String expireCounter) {
                this.expireCounter = expireCounter;
            }

            public String getRetrieved() {
                return retrieved;
            }

            public void setRetrieved(String retrieved) {
                this.retrieved = retrieved;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getNotes() {
                return notes;
            }

            public void setNotes(String notes) {
                this.notes = notes;
            }

            public String getFirstCourseTypes() {
                return firstCourseTypes;
            }

            public void setFirstCourseTypes(String firstCourseTypes) {
                this.firstCourseTypes = firstCourseTypes;
            }

            public String getSecondCourseTypes() {
                return secondCourseTypes;
            }

            public void setSecondCourseTypes(String secondCourseTypes) {
                this.secondCourseTypes = secondCourseTypes;
            }

            public StoreBean getStore() {
                return store;
            }

            public void setStore(StoreBean store) {
                this.store = store;
            }

            public List<?> getCourses() {
                return courses;
            }

            public void setCourses(List<?> courses) {
                this.courses = courses;
            }
        }
        public class StoreBean{
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        public static class CreatorBean {
            /**
             * id : 5b56bcec51183107612cf161
             * nickName : 小李君
             * avatarUrl : http://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/c6d7c644da5452c777ec283f6fba7069
             * name : null
             * phone : null
             * address : null
             */

            private String id;
            private String nickName;
            private String avatarUrl;
            private String name;
            private String phone;
            private String address;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
