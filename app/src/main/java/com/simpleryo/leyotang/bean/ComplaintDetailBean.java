package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * @ClassNname：ComplaintDetailBean.java
 * @Describe 投诉建议详情
 * @author huanglei
 * @time 2018/8/7 13:08
 */
@HttpResponse(parser = JsonResponseParser.class)
public class ComplaintDetailBean extends BaseResult {

    /**
     * data : {"id":"5b66a889511831226d40664a","mid":"01","storeId":null,"creatorId":"5b5d998a5118310c41f3a62b","creationTime":1533454473197,"sequence":-128,"typeCode":"COMPLAINT","creator":{"id":"5b5d998a5118310c41f3a62b","nickName":"WEG86081088","avatarUrl":"https://upimage.simpleryo.com/file/2d02fb2a6cd9743362c3dcce17ae42ab.png","name":null,"phone":null,"address":null},"title":"测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试测试意见反馈意见反馈测试意见反馈 测试意见反馈 测试意见反馈 12223dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd123","body":"测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试测试意见反馈意见反馈测试意见反馈 测试意见反馈 测试意见反馈 12223dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd123","linkId":null,"secondTypeCode":null,"imageUrls":[{"value":"https://upimage.simpleryo.com/file/422e82d889dc184030b7c8ad35b5676e.png"},{"value":"https://upimage.simpleryo.com/file/8a7acd802b72a4a9e90ea4199c4d5492.png"},{"value":"https://upimage.simpleryo.com/file/13b98e750fa1eb8ae73c293500c2a79d.png"},{"value":"https://upimage.simpleryo.com/file/9570d10221e81847a4483f9348473131.png"}],"reply":null,"hasDone":false,"readCount":1}
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
         * id : 5b66a889511831226d40664a
         * mid : 01
         * storeId : null
         * creatorId : 5b5d998a5118310c41f3a62b
         * creationTime : 1533454473197
         * sequence : -128
         * typeCode : COMPLAINT
         * creator : {"id":"5b5d998a5118310c41f3a62b","nickName":"WEG86081088","avatarUrl":"https://upimage.simpleryo.com/file/2d02fb2a6cd9743362c3dcce17ae42ab.png","name":null,"phone":null,"address":null}
         * title : 测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试测试意见反馈意见反馈测试意见反馈 测试意见反馈 测试意见反馈 12223dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd123
         * body : 测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试意见反馈测试测试意见反馈意见反馈测试意见反馈 测试意见反馈 测试意见反馈 12223dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd123
         * linkId : null
         * secondTypeCode : null
         * imageUrls : [{"value":"https://upimage.simpleryo.com/file/422e82d889dc184030b7c8ad35b5676e.png"},{"value":"https://upimage.simpleryo.com/file/8a7acd802b72a4a9e90ea4199c4d5492.png"},{"value":"https://upimage.simpleryo.com/file/13b98e750fa1eb8ae73c293500c2a79d.png"},{"value":"https://upimage.simpleryo.com/file/9570d10221e81847a4483f9348473131.png"}]
         * reply : null
         * hasDone : false
         * readCount : 1
         */

        private String id;
        private String mid;
        private String storeId;
        private String creatorId;
        private long creationTime;
        private int sequence;
        private String typeCode;
        private CreatorBean creator;
        private String title;
        private String body;
        private String linkId;
        private String secondTypeCode;
        private String reply;
        private boolean hasDone;
        private int readCount;
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

        public boolean isHasDone() {
            return hasDone;
        }

        public void setHasDone(boolean hasDone) {
            this.hasDone = hasDone;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public List<ImageUrlsBean> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<ImageUrlsBean> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public static class CreatorBean {
            /**
             * id : 5b5d998a5118310c41f3a62b
             * nickName : WEG86081088
             * avatarUrl : https://upimage.simpleryo.com/file/2d02fb2a6cd9743362c3dcce17ae42ab.png
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
             * value : https://upimage.simpleryo.com/file/422e82d889dc184030b7c8ad35b5676e.png
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
