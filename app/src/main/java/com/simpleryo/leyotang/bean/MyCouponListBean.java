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
     * data : [{"type":{"id":"5b682d6551183108293afc16","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1533554021588,"name":"具体课程券","imageUri":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/909125fb51bfd9b618c584306c57e3f1","startTime":1533661200000,"endTime":1538240400000,"limitAmount":-1,"enable":true,"discount":null,"subtractAmount":1000,"totalCounter":15,"consumeCounter":0,"retrieveCounter":1,"expireCounter":null,"retrieved":null,"category":"CASH","notes":null,"firstCourseTypes":{"id":"5b5607d651183107612cf15b","name":"IT课程"},"secondCourseTypes":{"id":"5b666323511831226d406644","name":"开发"},"store":{"id":"5b5608c451183107612cf15f","name":"微信小程序实战训练营"},"courses":[{"id":"5b67e0865118317498214a59","name":"C语言基础教学"}]},"status":"UNUSED","id":"5b6a544551183146edb56ef9","creationTime":1533695045527,"consumeTime":null,"creator":{"id":"5b5fbfe75118310c41f3a657","nickName":"WongLeoi","avatarUrl":"http://p.simpleryo.com/file/1f1d7de9c8f14087d87902079e85dba2","name":"WongLeoi","phone":null,"address":null}}]
     * count : 1
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
         * type : {"id":"5b682d6551183108293afc16","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1533554021588,"name":"具体课程券","imageUri":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/909125fb51bfd9b618c584306c57e3f1","startTime":1533661200000,"endTime":1538240400000,"limitAmount":-1,"enable":true,"discount":null,"subtractAmount":1000,"totalCounter":15,"consumeCounter":0,"retrieveCounter":1,"expireCounter":null,"retrieved":null,"category":"CASH","notes":null,"firstCourseTypes":{"id":"5b5607d651183107612cf15b","name":"IT课程"},"secondCourseTypes":{"id":"5b666323511831226d406644","name":"开发"},"store":{"id":"5b5608c451183107612cf15f","name":"微信小程序实战训练营"},"courses":[{"id":"5b67e0865118317498214a59","name":"C语言基础教学"}]}
         * status : UNUSED
         * id : 5b6a544551183146edb56ef9
         * creationTime : 1533695045527
         * consumeTime : null
         * creator : {"id":"5b5fbfe75118310c41f3a657","nickName":"WongLeoi","avatarUrl":"http://p.simpleryo.com/file/1f1d7de9c8f14087d87902079e85dba2","name":"WongLeoi","phone":null,"address":null}
         */

        private TypeBean type;
        private String status;
        private String id;
        private long creationTime;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(long creationTime) {
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
             * id : 5b682d6551183108293afc16
             * mid : 01
             * storeId : 5b5608c451183107612cf15f
             * creatorId : 5b5608c451183107612cf160
             * creationTime : 1533554021588
             * name : 具体课程券
             * imageUri : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/909125fb51bfd9b618c584306c57e3f1
             * startTime : 1533661200000
             * endTime : 1538240400000
             * limitAmount : -1
             * enable : true
             * discount : null
             * subtractAmount : 1000
             * totalCounter : 15
             * consumeCounter : 0
             * retrieveCounter : 1
             * expireCounter : null
             * retrieved : null
             * category : CASH
             * notes : null
             * firstCourseTypes : {"id":"5b5607d651183107612cf15b","name":"IT课程"}
             * secondCourseTypes : {"id":"5b666323511831226d406644","name":"开发"}
             * store : {"id":"5b5608c451183107612cf15f","name":"微信小程序实战训练营"}
             * courses : [{"id":"5b67e0865118317498214a59","name":"C语言基础教学"}]
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private long creationTime;
            private String name;
            private String imageUri;
            private long startTime;
            private long endTime;
            private int limitAmount;
            private boolean enable;
            private String discount;
            private int subtractAmount;
            private int totalCounter;
            private int consumeCounter;
            private int retrieveCounter;
            private String expireCounter;
            private String retrieved;
            private String category;
            private String notes;
            private FirstCourseTypesBean firstCourseTypes;
            private SecondCourseTypesBean secondCourseTypes;
            private StoreBean store;
            private List<CoursesBean> courses;

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

            public long getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(long creationTime) {
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

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
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

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public int getSubtractAmount() {
                return subtractAmount;
            }

            public void setSubtractAmount(int subtractAmount) {
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

            public FirstCourseTypesBean getFirstCourseTypes() {
                return firstCourseTypes;
            }

            public void setFirstCourseTypes(FirstCourseTypesBean firstCourseTypes) {
                this.firstCourseTypes = firstCourseTypes;
            }

            public SecondCourseTypesBean getSecondCourseTypes() {
                return secondCourseTypes;
            }

            public void setSecondCourseTypes(SecondCourseTypesBean secondCourseTypes) {
                this.secondCourseTypes = secondCourseTypes;
            }

            public StoreBean getStore() {
                return store;
            }

            public void setStore(StoreBean store) {
                this.store = store;
            }

            public List<CoursesBean> getCourses() {
                return courses;
            }

            public void setCourses(List<CoursesBean> courses) {
                this.courses = courses;
            }

            public static class FirstCourseTypesBean {
                /**
                 * id : 5b5607d651183107612cf15b
                 * name : IT课程
                 */

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

            public static class SecondCourseTypesBean {
                /**
                 * id : 5b666323511831226d406644
                 * name : 开发
                 */

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

            public static class StoreBean {
                /**
                 * id : 5b5608c451183107612cf15f
                 * name : 微信小程序实战训练营
                 */

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

            public static class CoursesBean {
                /**
                 * id : 5b67e0865118317498214a59
                 * name : C语言基础教学
                 */

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
        }

        public static class CreatorBean {
            /**
             * id : 5b5fbfe75118310c41f3a657
             * nickName : WongLeoi
             * avatarUrl : http://p.simpleryo.com/file/1f1d7de9c8f14087d87902079e85dba2
             * name : WongLeoi
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
