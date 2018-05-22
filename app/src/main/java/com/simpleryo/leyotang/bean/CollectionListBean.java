package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * @author huanglei
 * @ClassNname：CollectionListBean.java
 * @Describe 收藏列表
 * @time 2018/4/16 14:14
 */
@HttpResponse(parser = JsonResponseParser.class)
public class CollectionListBean extends BaseResult {


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
        private String name;
        private String coverUrl;
        private String tagId1;
        private String tagId2;
        private String intro;
        private String storeName;
        private String coachId;
        private boolean enable;
        private String collectCount;
        private boolean hasCollect;
        private int popular;
        private int price;
        private int grouprice;
        private int minCount;
        private int maxCount;
        private AddressBean address;
        private CoachBean coach;
        private List<RecommendsBean> recommends;
        private List<WorkdayBean> workday;
        //        private List<DurationsBean> durations;
        private int classCount;
        private int upperLimit;

        public int getClassCount() {
            return classCount;
        }

        public void setClassCount(int classCount) {
            this.classCount = classCount;
        }

        public int getUpperLimit() {
            return upperLimit;
        }

        public void setUpperLimit(int upperLimit) {
            this.upperLimit = upperLimit;
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

        public List<WorkdayBean> getWorkday() {
            return workday;
        }

        public void setWorkday(List<WorkdayBean> workday) {
            this.workday = workday;
        }

//        public List<DurationsBean> getDurations() {
//            return durations;
//        }
//
//        public void setDurations(List<DurationsBean> durations) {
//            this.durations = durations;
//        }

        public static class AddressBean {
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

        public static class CoachBean {
            /**
             * id : 5ad2bd3f8fa1ca116b796a2d
             * mid : 01
             * storeId : null
             * creatorId : system
             * creationTime : 1523760447564
             * sequence : 0
             * no : 1
             * name : yiller
             * nickName : n_yiller
             * avatarUrl : http://imgsrc.baidu.com/forum/w=580/sign=6ade238c064f78f0800b9afb49300a83/6cbb60d0f703918f31df8012543d269758eec47c.jpg
             * phone : 12345678900
             * chatNum : 101010
             * workLife : 2
             * intro : 暂无简介
             * desc : 暂无资料
             * skills : [{"value":"柔道"}]
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private long creationTime;
            private int sequence;
            private String no;
            private String name;
            private String nickName;
            private String avatarUrl;
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

        public static class RecommendsBean {
            /**
             * value : 5ac1fe7a24efe368f5bab62a
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class WorkdayBean {
            /**
             * value : 0
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class DurationsBean {
            /**
             * name : string
             * startHour : 0
             * startMinute : 0
             * endHour : 0
             * endMinute : 0
             */

            private String name;
            private int startHour;
            private int startMinute;
            private int endHour;
            private int endMinute;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getStartHour() {
                return startHour;
            }

            public void setStartHour(int startHour) {
                this.startHour = startHour;
            }

            public int getStartMinute() {
                return startMinute;
            }

            public void setStartMinute(int startMinute) {
                this.startMinute = startMinute;
            }

            public int getEndHour() {
                return endHour;
            }

            public void setEndHour(int endHour) {
                this.endHour = endHour;
            }

            public int getEndMinute() {
                return endMinute;
            }

            public void setEndMinute(int endMinute) {
                this.endMinute = endMinute;
            }
        }
    }
}
