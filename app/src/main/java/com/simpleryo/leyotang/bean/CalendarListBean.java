package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * @ClassNname：CalendarListBean.java
 * @Describe  日历订单列表
 * @author huanglei
 * @time 2018/4/16 10:54
 */
@HttpResponse(parser = JsonResponseParser.class)
public class CalendarListBean extends BaseResult {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public  class DataBean {

        private String dateStr;
        private List<CourseListBean> courses;

        public String getDateStr() {
            return dateStr;
        }

        public void setDateStr(String dateStr) {
            this.dateStr = dateStr;
        }

        public List<CourseListBean> getCourses() {
            return courses;
        }

        public void setCourses(List<CourseListBean> courses) {
            this.courses = courses;
        }

        public class CourseListBean{
            String orderId;
            OrdersBean course;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public OrdersBean getCourse() {
                return course;
            }

            public void setCourse(OrdersBean course) {
                this.course = course;
            }
        }

        public  class OrdersBean {
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

            public  class DurationsBean {
                /**
                 * startDate : 2018-04-22
                 * endDate : 2018-05-30
                 * data : [{"startTime":"8:00","endTime":"16:00","week":"周一"},{"startTime":"8:00","endTime":"16:00","week":"周二"}]
                 */

                private String startDate;
                private String endDate;
                private List<CourseDataBean> data;

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

                public List<CourseDataBean> getData() {
                    return data;
                }

                public void setData(List<CourseDataBean> data) {
                    this.data = data;
                }

                public  class CourseDataBean {
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

            public  class AddressBean {
                /**
                 * id : null
                 * lat : 31.075571
                 * lng : 121.51550399999996
                 * provice : null
                 * city : null
                 * district : null
                 * detail : 中国上海市闵行区联航路1515号
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

            public  class CoachBean {
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

                public  class SkillsBean {
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

            public  class RecommendsBean {
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
}
