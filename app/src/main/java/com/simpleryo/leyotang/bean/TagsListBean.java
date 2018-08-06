package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;
/**
 * @ClassNname：TagsListBean.java
 * @Describe 标签列表
 * @author huanglei
 * @time 2018/8/6 14:13
 */
@HttpResponse(parser = JsonResponseParser.class)
public class TagsListBean extends BaseResult {
    /**
     * data : [{"id":"5b5607d651183107612cf15b","spaceCode":"COURSE_TYPE","name":"IT课程","imageUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/849eb764a1e6606acd825bfbc9d5726e","cod":null,"refId":null,"parentId":null},{"id":"5b56080d51183107612cf15c","spaceCode":"COURSE_TYPE","name":"健身中心","imageUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/f9bfacabb4d920537c92fbfbbece51f7","cod":null,"refId":null,"parentId":null},{"id":"5b56082151183107612cf15d","spaceCode":"COURSE_TYPE","name":"女性美妆","imageUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/cea0d8dc90fb6a41215a6c965adc67f5","cod":null,"refId":null,"parentId":null},{"id":"5b56083251183107612cf15e","spaceCode":"COURSE_TYPE","name":"亲子教育","imageUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/a5207c9d948620dc0c2f3a1c59ab4553","cod":null,"refId":null,"parentId":null},{"id":"5b58035d51183107612cf182","spaceCode":"COURSE_TYPE","name":"语言学习","imageUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/e9ff9eb648e74ca11202dc04a4fe3f94","cod":null,"refId":null,"parentId":null}]
     * count : 5
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
         * id : 5b5607d651183107612cf15b
         * spaceCode : COURSE_TYPE
         * name : IT课程
         * imageUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/849eb764a1e6606acd825bfbc9d5726e
         * cod : null
         * refId : null
         * parentId : null
         */

        private String id;
        private String spaceCode;
        private String name;
        private String imageUrl;
        private String cod;
        private String refId;
        private String parentId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSpaceCode() {
            return spaceCode;
        }

        public void setSpaceCode(String spaceCode) {
            this.spaceCode = spaceCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCod() {
            return cod;
        }

        public void setCod(String cod) {
            this.cod = cod;
        }

        public String getRefId() {
            return refId;
        }

        public void setRefId(String refId) {
            this.refId = refId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }
}
