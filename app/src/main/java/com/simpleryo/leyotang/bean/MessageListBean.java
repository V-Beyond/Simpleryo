package com.simpleryo.leyotang.bean;

import java.util.List;

/**
 * Created by 77429 on 2018/4/27.
 */

public class MessageListBean extends BaseResult {


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

        private String id;
        private String mid;
        private String storeId;
        private String creatorId;
        private String creationTime;
        private int sequence;
        private String typeCode;
        private CreatorBean creator;
        private String title;
        private String body;
        private String linkId;
        private String secondTypeCode;
        private String reply;
        private String hasDone;
        private List<ImageUrlsBean> imageUrls;

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

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getLinkId() {
            return linkId;
        }

        public void setLinkId(String linkId) {
            this.linkId = linkId;
        }

        public String getSecondTypeCode() {
            return secondTypeCode;
        }

        public void setSecondTypeCode(String secondTypeCode) {
            this.secondTypeCode = secondTypeCode;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getHasDone() {
            return hasDone;
        }

        public void setHasDone(String hasDone) {
            this.hasDone = hasDone;
        }

        public List<ImageUrlsBean> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<ImageUrlsBean> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public static class CreatorBean {
            /**
             * id : 5adbf6ae51183144ecc4befd
             * nickName : root
             * avatarUrl : 管理员无头像
             * name : 超级管理员
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
             * value : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/89c7edf3c39f35d7d329f18266f287da
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
