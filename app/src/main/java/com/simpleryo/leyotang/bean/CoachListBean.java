package com.simpleryo.leyotang.bean;

import java.util.List;

/**
 * @ClassNname：CoachListBean.java
 * @Describe 教练实体类
 * @author huanglei
 * @time 2018/5/2 10:02
 */

public class CoachListBean extends BaseResult {


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
        private String no;
        private String storeName;
        private String courseCount;
        private String name;
        private String nickName;
        private String avatarUrl;
        private String studentCount;
        private String point;
        private String phone;
        private String chatNum;
        private String workLife;
        private String intro;
        private String desc;
        private List<SkillsBean> skills;

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

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getCourseCount() {
            return courseCount;
        }

        public void setCourseCount(String courseCount) {
            this.courseCount = courseCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getStudentCount() {
            return studentCount;
        }

        public void setStudentCount(String studentCount) {
            this.studentCount = studentCount;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getChatNum() {
            return chatNum;
        }

        public void setChatNum(String chatNum) {
            this.chatNum = chatNum;
        }

        public String getWorkLife() {
            return workLife;
        }

        public void setWorkLife(String workLife) {
            this.workLife = workLife;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<SkillsBean> getSkills() {
            return skills;
        }

        public void setSkills(List<SkillsBean> skills) {
            this.skills = skills;
        }

        public static class SkillsBean {
            /**
             * value : 柔道
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
