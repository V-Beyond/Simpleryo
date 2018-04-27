package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * Created by 77429 on 2018/4/11.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class CourseListBean extends BaseResult {

    /**
     * data : [{"id":"5adc5ca95118310ff9412b50","mid":"01","storeId":"5ad49583511831431f7e2f1c","creatorId":"5ad49583511831431f7e2f1d","creationTime":1524391080919,"name":"瑜伽","coverUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/89c7edf3c39f35d7d329f18266f287da","tagId1":"5ad5f2275118317191e6df3b","tagId2":null,"recommends":[{"value":"5ad8a0c151183106cd04a780"}],"intro":"<p>瑜伽是起源于古印度的一种健身术，是一种生活方式，一种生活理念，是一种指导生活的思想体系又是一种博大精深的哲学体系。瑜伽在西方国家近几十年从健身界炽热不衰至现在已融入他们的工作、生活、像微软、苹果公司每周都要请专业瑜伽老师给员工们培训以便员工能适时减压、放松。保养心身更好地有效地工作。我校自<span lang=\"EN-US\">2007<\/span>年在全校范围内开设健身瑜伽公共选修课以来，深受广大学生的欢迎和喜爱。随着健身瑜伽影响力的扩大和师资力量的增强，<span lang=\"EN-US\">2009<\/span>年在体育学院开设了健身瑜伽选修课程。我们开设的瑜伽理论课主要内容有：瑜伽文化、瑜伽流派、瑜伽生理学、瑜伽动作的创编及练习方法。术科主要内容有：基础瑜伽、瑜伽体位法、呼吸、冥想、双人瑜伽。学生通过练习，能够掌握一种终身受用的健身方式，克服生活中种种恶习，建立一套健康的生活方式。其次它不仅能强健肌肉骨骼而且通过神经腺体的刺激，使整个身体心灵得到完全放松，有一个良好的人格气质。通过本课程的学习与训练使学生在德、智、体、美等方面得到全面发展，具有社会公德、社会责任感、义务感和履行职责的行为等基本素质；系统地掌握瑜伽的基本理论、基本知识和基本技能，具备瑜伽教学、训练、裁判、设计、策划的基本能力，提高学生对瑜伽运动的组织开展、推广普及和咨询指导能力，提高学生对瑜伽的探究心理。<\/p>","storeName":"store","coachId":"5ad7145e51183106cd04a778","enable":true,"collectCount":1,"classCount":0,"goodReviewRate":0,"hasCollect":true,"popular":null,"originPrice":99900,"price":99800,"grouprice":9800,"minCount":100,"maxCount":200,"durations":{"startDate":"2018-04-22","endDate":"2018-05-30","data":[{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"8:00","endTime":"16:00","week":"周二"}]},"address":{"id":null,"lat":31.075571,"lng":121.51550399999996,"provice":null,"city":null,"district":null,"detail":"中国上海市闵行区联航路1515号 邮政编码: 201112","linkman":null,"phone":null,"isDefault":null},"coach":{"id":"5ad7145e51183106cd04a778","mid":"01","storeId":"5ad49583511831431f7e2f1c","creatorId":"5ad49583511831431f7e2f1d","creationTime":1524044894833,"sequence":0,"no":"1524044894833","storeName":"store","courseCount":1,"name":"教练1","nickName":"教练1昵称","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/bcd3eb7f010cabb948f1c213549c1971","studentCount":1,"point":0,"phone":"66666666666","chatNum":"","workLife":"10年以上","intro":"1) 微信识别或扫下面图中二维码，进入「翻牌有奖」页面；\n2) 点击随机翻开任意一张，等待3s，将立即跳转到抽奖页面，即可参与抽奖","desc":"<p><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/6f993a338dd3eec991105c99d42ed061\" alt=\"\" width=\"570\" height=\"120\" /><\/p>","skills":[{"value":"散打"}]}}]
     * count : 1
     */

    private int count;
    private List<DataBeanX> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * id : 5adc5ca95118310ff9412b50
         * mid : 01
         * storeId : 5ad49583511831431f7e2f1c
         * creatorId : 5ad49583511831431f7e2f1d
         * creationTime : 1524391080919
         * name : 瑜伽
         * coverUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/89c7edf3c39f35d7d329f18266f287da
         * tagId1 : 5ad5f2275118317191e6df3b
         * tagId2 : null
         * recommends : [{"value":"5ad8a0c151183106cd04a780"}]
         * intro : <p>瑜伽是起源于古印度的一种健身术，是一种生活方式，一种生活理念，是一种指导生活的思想体系又是一种博大精深的哲学体系。瑜伽在西方国家近几十年从健身界炽热不衰至现在已融入他们的工作、生活、像微软、苹果公司每周都要请专业瑜伽老师给员工们培训以便员工能适时减压、放松。保养心身更好地有效地工作。我校自<span lang="EN-US">2007</span>年在全校范围内开设健身瑜伽公共选修课以来，深受广大学生的欢迎和喜爱。随着健身瑜伽影响力的扩大和师资力量的增强，<span lang="EN-US">2009</span>年在体育学院开设了健身瑜伽选修课程。我们开设的瑜伽理论课主要内容有：瑜伽文化、瑜伽流派、瑜伽生理学、瑜伽动作的创编及练习方法。术科主要内容有：基础瑜伽、瑜伽体位法、呼吸、冥想、双人瑜伽。学生通过练习，能够掌握一种终身受用的健身方式，克服生活中种种恶习，建立一套健康的生活方式。其次它不仅能强健肌肉骨骼而且通过神经腺体的刺激，使整个身体心灵得到完全放松，有一个良好的人格气质。通过本课程的学习与训练使学生在德、智、体、美等方面得到全面发展，具有社会公德、社会责任感、义务感和履行职责的行为等基本素质；系统地掌握瑜伽的基本理论、基本知识和基本技能，具备瑜伽教学、训练、裁判、设计、策划的基本能力，提高学生对瑜伽运动的组织开展、推广普及和咨询指导能力，提高学生对瑜伽的探究心理。</p>
         * storeName : store
         * coachId : 5ad7145e51183106cd04a778
         * enable : true
         * collectCount : 1
         * classCount : 0
         * goodReviewRate : 0
         * hasCollect : true
         * popular : null
         * originPrice : 99900
         * price : 99800
         * grouprice : 9800
         * minCount : 100
         * maxCount : 200
         * durations : {"startDate":"2018-04-22","endDate":"2018-05-30","data":[{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"8:00","endTime":"16:00","week":"周二"}]}
         * address : {"id":null,"lat":31.075571,"lng":121.51550399999996,"provice":null,"city":null,"district":null,"detail":"中国上海市闵行区联航路1515号 邮政编码: 201112","linkman":null,"phone":null,"isDefault":null}
         * coach : {"id":"5ad7145e51183106cd04a778","mid":"01","storeId":"5ad49583511831431f7e2f1c","creatorId":"5ad49583511831431f7e2f1d","creationTime":1524044894833,"sequence":0,"no":"1524044894833","storeName":"store","courseCount":1,"name":"教练1","nickName":"教练1昵称","avatarUrl":"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/bcd3eb7f010cabb948f1c213549c1971","studentCount":1,"point":0,"phone":"66666666666","chatNum":"","workLife":"10年以上","intro":"1) 微信识别或扫下面图中二维码，进入「翻牌有奖」页面；\n2) 点击随机翻开任意一张，等待3s，将立即跳转到抽奖页面，即可参与抽奖","desc":"<p><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/6f993a338dd3eec991105c99d42ed061\" alt=\"\" width=\"570\" height=\"120\" /><\/p>","skills":[{"value":"散打"}]}
         */

        private String id;
        private String mid;
        private String storeId;
        private String creatorId;
        private String creationTime;
        private String name;
        private String coverUrl;
        private String tagId1;
        private String tagId2;
        private String intro;
        private String storeName;
        private String coachId;
        private boolean enable;
        private int collectCount;
        private int classCount;
        private int goodReviewRate;
        private boolean hasCollect;
        private String popular;
        private int originPrice;
        private int price;
        private int grouprice;
        private int minCount;
        private int maxCount;
        private DurationsBean durations;
        private AddressBean address;
        private CoachBean coach;
        private List<RecommendsBean> recommends;

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

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getTagId1() {
            return tagId1;
        }

        public void setTagId1(String tagId1) {
            this.tagId1 = tagId1;
        }

        public String getTagId2() {
            return tagId2;
        }

        public void setTagId2(String tagId2) {
            this.tagId2 = tagId2;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getCoachId() {
            return coachId;
        }

        public void setCoachId(String coachId) {
            this.coachId = coachId;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public int getClassCount() {
            return classCount;
        }

        public void setClassCount(int classCount) {
            this.classCount = classCount;
        }

        public int getGoodReviewRate() {
            return goodReviewRate;
        }

        public void setGoodReviewRate(int goodReviewRate) {
            this.goodReviewRate = goodReviewRate;
        }

        public boolean isHasCollect() {
            return hasCollect;
        }

        public void setHasCollect(boolean hasCollect) {
            this.hasCollect = hasCollect;
        }

        public String getPopular() {
            return popular;
        }

        public void setPopular(String popular) {
            this.popular = popular;
        }

        public int getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(int originPrice) {
            this.originPrice = originPrice;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getGrouprice() {
            return grouprice;
        }

        public void setGrouprice(int grouprice) {
            this.grouprice = grouprice;
        }

        public int getMinCount() {
            return minCount;
        }

        public void setMinCount(int minCount) {
            this.minCount = minCount;
        }

        public int getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }

        public DurationsBean getDurations() {
            return durations;
        }

        public void setDurations(DurationsBean durations) {
            this.durations = durations;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public CoachBean getCoach() {
            return coach;
        }

        public void setCoach(CoachBean coach) {
            this.coach = coach;
        }

        public List<RecommendsBean> getRecommends() {
            return recommends;
        }

        public void setRecommends(List<RecommendsBean> recommends) {
            this.recommends = recommends;
        }

        public static class DurationsBean {
            /**
             * startDate : 2018-04-22
             * endDate : 2018-05-30
             * data : [{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"8:00","endTime":"16:00","week":"周二"}]
             */

            private String startDate;
            private String endDate;
            private List<DataBean> data;

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * startTime : 8:00
                 * endTime : 16:00
                 * week : 周一
                 */

                private String startTime;
                private String endTime;
                private String week;

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

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }
            }
        }

        public static class AddressBean {
            /**
             * id : null
             * lat : 31.075571
             * lng : 121.51550399999996
             * provice : null
             * city : null
             * district : null
             * detail : 中国上海市闵行区联航路1515号 邮政编码: 201112
             * linkman : null
             * phone : null
             * isDefault : null
             */

            private String id;
            private double lat;
            private double lng;
            private String provice;
            private String city;
            private String district;
            private String detail;
            private String linkman;
            private String phone;
            private String isDefault;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public String getProvice() {
                return provice;
            }

            public void setProvice(String provice) {
                this.provice = provice;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getLinkman() {
                return linkman;
            }

            public void setLinkman(String linkman) {
                this.linkman = linkman;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(String isDefault) {
                this.isDefault = isDefault;
            }
        }

        public static class CoachBean {
            /**
             * id : 5ad7145e51183106cd04a778
             * mid : 01
             * storeId : 5ad49583511831431f7e2f1c
             * creatorId : 5ad49583511831431f7e2f1d
             * creationTime : 1524044894833
             * sequence : 0
             * no : 1524044894833
             * storeName : store
             * courseCount : 1
             * name : 教练1
             * nickName : 教练1昵称
             * avatarUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/bcd3eb7f010cabb948f1c213549c1971
             * studentCount : 1
             * point : 0
             * phone : 66666666666
             * chatNum : 
             * workLife : 10年以上
             * intro : 1) 微信识别或扫下面图中二维码，进入「翻牌有奖」页面；
             2) 点击随机翻开任意一张，等待3s，将立即跳转到抽奖页面，即可参与抽奖
             * desc : <p><img style="display: block; margin-left: auto; margin-right: auto;" src="https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/6f993a338dd3eec991105c99d42ed061" alt="" width="570" height="120" /></p>
             * skills : [{"value":"散打"}]
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
                 * value : 散打
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

        public static class RecommendsBean {
            /**
             * value : 5ad8a0c151183106cd04a780
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
