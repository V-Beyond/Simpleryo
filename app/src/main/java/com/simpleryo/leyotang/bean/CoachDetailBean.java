package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * @ClassNname：CoachDetailBean.java
 * @Describe 教练详情实体类
 * @author huanglei
 * @time 2018/8/6 13:28
 */
@HttpResponse(parser = JsonResponseParser.class)
public class CoachDetailBean extends BaseResult {

    /**
     * data : {"id":"5b57cefc51183107612cf162","mid":"01","storeId":"5b5608c451183107612cf15f","creatorId":"5b5608c451183107612cf160","creationTime":1532481276199,"sequence":0,"no":"1532481276199","storeName":"微信小程序实战训练营","courseCount":1,"name":"Tom","nickName":"Joy","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/3d0dead17b237d20723827ddc2fecaed","studentCount":0,"point":0,"phone":"15221317042","chatNum":"","workLife":"2年-3年","intro":"从事于微信小程序研发及讲授，从事于前端研发3年","desc":"<p>从事于移动前端研发三年，有丰富的开发实战经验。个人喜欢旅游，喜欢写作<\/p>","skills":[{"value":"微信小程序"},{"value":"前端"}]}
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
         * id : 5b57cefc51183107612cf162
         * mid : 01
         * storeId : 5b5608c451183107612cf15f
         * creatorId : 5b5608c451183107612cf160
         * creationTime : 1532481276199
         * sequence : 0
         * no : 1532481276199
         * storeName : 微信小程序实战训练营
         * courseCount : 1
         * name : Tom
         * nickName : Joy
         * avatarUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/3d0dead17b237d20723827ddc2fecaed
         * studentCount : 0
         * point : 0
         * phone : 15221317042
         * chatNum :
         * workLife : 2年-3年
         * intro : 从事于微信小程序研发及讲授，从事于前端研发3年
         * desc : <p>从事于移动前端研发三年，有丰富的开发实战经验。个人喜欢旅游，喜欢写作</p>
         * skills : [{"value":"微信小程序"},{"value":"前端"}]
         */

        private String id;
        private String mid;
        private String storeId;
        private String creatorId;
        private long creationTime;
        private int sequence;
        private String no;
        private String storeName;
        private int courseCount;
        private String name;
        private String nickName;
        private String avatarUrl;
        private int studentCount;
        private int point;
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

        public int getCourseCount() {
            return courseCount;
        }

        public void setCourseCount(int courseCount) {
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

        public int getStudentCount() {
            return studentCount;
        }

        public void setStudentCount(int studentCount) {
            this.studentCount = studentCount;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
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
             * value : 微信小程序
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
