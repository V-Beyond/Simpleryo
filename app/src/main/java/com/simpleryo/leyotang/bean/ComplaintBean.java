package com.simpleryo.leyotang.bean;

import java.util.List;

/**
 * @ClassNname：ComplaintBean.java
 * @Describe 投诉建议实体类
 * @author huanglei
 * @time 2018/4/30 16:57
 */

public class ComplaintBean extends BaseResult {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String mid;
        private String storeId;
        private String creatorId;
        private long creationTime;
        private int sequence;
        private String typeCode;
        private String creator;
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

        public long getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(long creationTime) {
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

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
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

        public static class ImageUrlsBean {
            /**
             * value : https://wongleoi.oss-cn-shanghai.aliyuncs.com/file/35894b8cf167a0c639b70ae5d73e31e9
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
