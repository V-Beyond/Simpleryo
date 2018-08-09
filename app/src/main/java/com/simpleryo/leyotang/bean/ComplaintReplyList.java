package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;
/**
 * @ClassNname：ComplaintReplyList.java
 * @Describe 投诉建议回复列表实体类
 * @author huanglei
 * @time 2018/8/8 15:18
 */
@HttpResponse(parser = JsonResponseParser.class)
public class ComplaintReplyList extends BaseResult {

    /**
     * data : [{"creator":null,"creationTime":1533711573104,"body":null,"imageUrls":null},{"creator":null,"creationTime":1533704048328,"body":"防守打法","imageUrls":null},{"creator":null,"creationTime":1533692321731,"body":null,"imageUrls":null},{"creator":null,"creationTime":1533691713340,"body":null,"imageUrls":null},{"creator":null,"creationTime":1533644135234,"body":"这是小李君给Android的回复","imageUrls":null},{"creator":null,"creationTime":1533644014514,"body":"really","imageUrls":null},{"creator":null,"creationTime":1533644004825,"body":"英文不行吗","imageUrls":null},{"creator":null,"creationTime":1533643986617,"body":"为什么那么神奇？","imageUrls":null},{"creator":null,"creationTime":null,"body":"回复u，哈哈","imageUrls":null},{"creator":null,"creationTime":null,"body":"nsss","imageUrls":null},{"creator":null,"creationTime":null,"body":"你好","imageUrls":null},{"creator":null,"creationTime":null,"body":null,"imageUrls":null},{"creator":null,"creationTime":null,"body":null,"imageUrls":null},{"creator":null,"creationTime":null,"body":null,"imageUrls":null},{"creator":null,"creationTime":null,"body":"你有什么想法，请讲？","imageUrls":null},{"creator":null,"creationTime":null,"body":"琴江发送到附近的飞机离开的房间","imageUrls":null},{"creator":null,"creationTime":null,"body":"nihao","imageUrls":null},{"creator":null,"creationTime":null,"body":"ggsfdgfg","imageUrls":null},{"creator":null,"creationTime":null,"body":"ggsfdgfg","imageUrls":null}]
     * count : 19
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
         * creator : null
         * creationTime : 1533711573104
         * body : null
         * imageUrls : null
         */

        private CreatorBean creator;
        private String creationTime;
        private String body;
        private List<ImageUrlsBean> imageUrls;

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<ImageUrlsBean> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<ImageUrlsBean> imageUrls) {
            this.imageUrls = imageUrls;
        }
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
