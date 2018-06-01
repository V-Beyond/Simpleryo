package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：OrderDetailBean.java
 * @Describe 订单详情实体类
 * @author huanglei
 * @time 2018/4/13 11:19
 */

@HttpResponse(parser = JsonResponseParser.class)
public class OrderDetailBean extends BaseResult {



    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String mid;
        private String storeId;
        private String creatorId;
        private String creationTime;
        private String id;
        private String no;
        private String courseId;
        private String courseName;
        private String imageUrl;
        private int unitPrice;
        private int quantity;
        private int totalAmt;
        private int payAmt;
        private String payTime;
        private String payType;
        private String routeCode;
        private String status;
        private String remark;
        private StoreBean store;
        private BuyerBean buyer;
        private CoachBean coach;
        private OrderCourseBean course;
        private String userName;
        private String userPhone;
        private  String userRemark;
        private  boolean arrangeFlag;
        private  String classTime;

        public String getClassTime() {
            return classTime;
        }

        public void setClassTime(String classTime) {
            this.classTime = classTime;
        }

        public boolean isArrangeFlag() {
            return arrangeFlag;
        }

        public void setArrangeFlag(boolean arrangeFlag) {
            this.arrangeFlag = arrangeFlag;
        }
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserRemark() {
            return userRemark;
        }

        public void setUserRemark(String userRemark) {
            this.userRemark = userRemark;
        }

        public OrderCourseBean getCourse() {
            return course;
        }

        public void setCourse(OrderCourseBean course) {
            this.course = course;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getTotalAmt() {
            return totalAmt;
        }

        public void setTotalAmt(int totalAmt) {
            this.totalAmt = totalAmt;
        }

        public int getPayAmt() {
            return payAmt;
        }

        public void setPayAmt(int payAmt) {
            this.payAmt = payAmt;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getRouteCode() {
            return routeCode;
        }

        public void setRouteCode(String routeCode) {
            this.routeCode = routeCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public BuyerBean getBuyer() {
            return buyer;
        }

        public void setBuyer(BuyerBean buyer) {
            this.buyer = buyer;
        }

        public CoachBean getCoach() {
            return coach;
        }

        public void setCoach(CoachBean coach) {
            this.coach = coach;
        }


        public static class StoreBean {
            /**
             * id : 5ad49583511831431f7e2f1c
             * mid : 01
             * storeId : 5ad49583511831431f7e2f1c
             * creatorId : system
             * creationTime : 1523881347799
             * idNumber : store_a
             * name : store
             * licenceUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/89c7edf3c39f35d7d329f18266f287da
             * coverUrl : https://simpleryo-china.oss-cn-hangzhou.aliyuncs.com/file/e7ee64fea67e2a2558ae1a0c0dc4c21c
             * linkman : 测试员
             * phone : 1234567890
             * auditorId : null
             * auditTime : null
             * salesProdtCount : null
             * salesAmount : null
             * IdNumber : store_a
             * accountName : null
             * accountNo : 123
             * status : AUDIT_OK
             * remark : null
             * address : {"id":null,"lat":null,"lng":null,"provice":null,"city":null,"district":null,"detail":"上海市，闵行区，浦江镇","linkman":null,"phone":null,"isDefault":null}
             * authorities : null
             * notifiers : null
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private String creationTime;
            private String idNumber;
            private String name;
            private String licenceUrl;
            private String coverUrl;
            private String linkman;
            private String phone;
            private String auditorId;
            private String auditTime;
            private String salesProdtCount;
            private String salesAmount;
            private String IdNumber;
            private String accountName;
            private String accountNo;
            private String status;
            private String remark;
            private AddressBean address;
            private String authorities;
            private String notifiers;

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

            public String getLicenceUrl() {
                return licenceUrl;
            }

            public void setLicenceUrl(String licenceUrl) {
                this.licenceUrl = licenceUrl;
            }

            public String getCoverUrl() {
                return coverUrl;
            }

            public void setCoverUrl(String coverUrl) {
                this.coverUrl = coverUrl;
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

            public String getAuditorId() {
                return auditorId;
            }

            public void setAuditorId(String auditorId) {
                this.auditorId = auditorId;
            }

            public String getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(String auditTime) {
                this.auditTime = auditTime;
            }

            public String getSalesProdtCount() {
                return salesProdtCount;
            }

            public void setSalesProdtCount(String salesProdtCount) {
                this.salesProdtCount = salesProdtCount;
            }

            public String getSalesAmount() {
                return salesAmount;
            }

            public void setSalesAmount(String salesAmount) {
                this.salesAmount = salesAmount;
            }

            public String getIdNumber() {
                return IdNumber;
            }

            public void setIdNumber(String IdNumber) {
                this.IdNumber = IdNumber;
            }

            public String getAccountName() {
                return accountName;
            }

            public void setAccountName(String accountName) {
                this.accountName = accountName;
            }

            public String getAccountNo() {
                return accountNo;
            }

            public void setAccountNo(String accountNo) {
                this.accountNo = accountNo;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public String getAuthorities() {
                return authorities;
            }

            public void setAuthorities(String authorities) {
                this.authorities = authorities;
            }

            public String getNotifiers() {
                return notifiers;
            }

            public void setNotifiers(String notifiers) {
                this.notifiers = notifiers;
            }

            public static class AddressBean {
                /**
                 * id : null
                 * lat : null
                 * lng : null
                 * provice : null
                 * city : null
                 * district : null
                 * detail : 上海市，闵行区，浦江镇
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
        }

        public static class BuyerBean {
            /**
             * id : 5acd688b51183135c4a19e6e
             * nickName : 早班火车2
             * avatarUrl : https://ps.ssl.qhmsg.com/bdr/_240_/t01df569e66fbb4f34f.jpg
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
             * studentCount : null
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
            private String studentCount;
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

            public String getStudentCount() {
                return studentCount;
            }

            public void setStudentCount(String studentCount) {
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
    }
    
    
    
    public class  OrderCourseBean{


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
        private String collectCount;
        private String classCount;
        private String goodReviewRate;
        private boolean hasCollect;
        private int popular;
        private int originPrice;
        private int price;
        private int grouprice;
        private int minCount;
        private int maxCount;
        private DurationsBean durations;
        private AddressBean address;
        private CoachBean coach;
        private List<RecommendsBean> recommends;
        private String type;
        private ArrayList<Arrange> arranges;
        public ArrayList<Arrange> getArranges() {
            return arranges;
        }

        public void setArranges(ArrayList<Arrange> arranges) {
            this.arranges = arranges;
        }

        public  class Arrange {
            private String id;
            private String courseId;
            private String classDate;
            private String startTime;
            private String endTime;
            private String dateDetail;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCourseId() {
                return courseId;
            }

            public void setCourseId(String courseId) {
                this.courseId = courseId;
            }

            public String getClassDate() {
                return classDate;
            }

            public void setClassDate(String classDate) {
                this.classDate = classDate;
            }

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

            public String getDateDetail() {
                return dateDetail;
            }

            public void setDateDetail(String dateDetail) {
                this.dateDetail = dateDetail;
            }
        }
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
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

        public String getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(String collectCount) {
            this.collectCount = collectCount;
        }

        public String getClassCount() {
            return classCount;
        }

        public void setClassCount(String classCount) {
            this.classCount = classCount;
        }

        public String getGoodReviewRate() {
            return goodReviewRate;
        }

        public void setGoodReviewRate(String goodReviewRate) {
            this.goodReviewRate = goodReviewRate;
        }

        public boolean isHasCollect() {
            return hasCollect;
        }

        public void setHasCollect(boolean hasCollect) {
            this.hasCollect = hasCollect;
        }

        public int getPopular() {
            return popular;
        }

        public void setPopular(int popular) {
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
             * startDate : 2018-04-16
             * endDate : 2018-04-17
             * data : [{"startTime":"8:00","endTime":"12:00","week":"周一"},{"startTime":"8:00","endTime":"12:00","week":"周二"}]
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

            public  class DataBean {
                /**
                 * startTime : 8:00
                 * endTime : 12:00
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
             * id : 1
             * lat : 0
             * lng : 0
             * provice : 上海
             * city : 上海市
             * district : 崇明县
             * detail : 金桥
             * linkman : 联系人
             * phone : 123456
             * isDefault : Y
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

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private String creationTime;
            private int sequence;
            private String no;
            private String storeName;
            private int courseCount;
            private String name;
            private String nickName;
            private String avatarUrl;
            private String studentCount;
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

            public String getStudentCount() {
                return studentCount;
            }

            public void setStudentCount(String studentCount) {
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

        public  class RecommendsBean {
            /**
             * value : 5ac1fde124efe3670322bd66
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
