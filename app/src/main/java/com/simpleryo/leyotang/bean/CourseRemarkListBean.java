package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;



/**
 * @ClassNname：CourseRemarkListBean.java
 * @Describe 课程评论列表实体类
 * @author huanglei
 * @time 2018/8/7 9:14
 */
@HttpResponse(parser = JsonResponseParser.class)
public class CourseRemarkListBean extends BaseResult {

    /**
     * data : [{"comment":"在安卓端评论了一个课程，四星的，附带两张图片","typeCode":"ORDER","resourceId":"ff80808164e50f1e0164e517fedf0000","point":4,"imageUrls":[{"value":"http://p.simpleryo.com/file/d1d7e2c8f0117dc26d8ee63db0aa7eb2"}],"creationTime":1533566001113,"praiseCount":0,"reportCount":0,"creator":{"id":"5b56bcec51183107612cf161","nickName":"小李君","avatarUrl":"https://upimage.simpleryo.com/file/06101621982363f9c16454243b387687.png","name":null,"phone":null,"address":null},"toUser":null},{"comment":"我只给4星评价","typeCode":"ORDER","resourceId":"ff80808164e50f1e0164e517fedf0000","point":4,"imageUrls":[],"creationTime":1533460555978,"praiseCount":0,"reportCount":0,"creator":{"id":"5b56bcec51183107612cf161","nickName":"小李君","avatarUrl":"https://upimage.simpleryo.com/file/06101621982363f9c16454243b387687.png","name":null,"phone":null,"address":null},"toUser":null}]
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
         * comment : 在安卓端评论了一个课程，四星的，附带两张图片
         * typeCode : ORDER
         * resourceId : ff80808164e50f1e0164e517fedf0000
         * point : 4
         * imageUrls : [{"value":"http://p.simpleryo.com/file/d1d7e2c8f0117dc26d8ee63db0aa7eb2"}]
         * creationTime : 1533566001113
         * praiseCount : 0
         * reportCount : 0
         * creator : {"id":"5b56bcec51183107612cf161","nickName":"小李君","avatarUrl":"https://upimage.simpleryo.com/file/06101621982363f9c16454243b387687.png","name":null,"phone":null,"address":null}
         * toUser : null
         */

        private String comment;
        private String typeCode;
        private String resourceId;
        private int point;
        private long creationTime;
        private int praiseCount;
        private int reportCount;
        private CreatorBean creator;
        private String toUser;
        private List<ImageUrlsBean> imageUrls;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getResourceId() {
            return resourceId;
        }

        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public long getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(long creationTime) {
            this.creationTime = creationTime;
        }

        public int getPraiseCount() {
            return praiseCount;
        }

        public void setPraiseCount(int praiseCount) {
            this.praiseCount = praiseCount;
        }

        public int getReportCount() {
            return reportCount;
        }

        public void setReportCount(int reportCount) {
            this.reportCount = reportCount;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public String getToUser() {
            return toUser;
        }

        public void setToUser(String toUser) {
            this.toUser = toUser;
        }

        public List<ImageUrlsBean> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<ImageUrlsBean> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public static class CreatorBean {
            /**
             * id : 5b56bcec51183107612cf161
             * nickName : 小李君
             * avatarUrl : https://upimage.simpleryo.com/file/06101621982363f9c16454243b387687.png
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

        public static class ImageUrlsBean {
            /**
             * value : http://p.simpleryo.com/file/d1d7e2c8f0117dc26d8ee63db0aa7eb2
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
