package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * @ClassNname：UserInfoBean.java
 * @Describe 查询用户信息实体类
 * @author huanglei
 * @time 2018/4/11 14:51
 */
@HttpResponse(parser = JsonResponseParser.class)
public class UserInfoBean extends BaseResult {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public  class DataBean {

        private String id;
        private String nickName;
        private String name;
        private String phone;
        private String wxnum;
        private String avatarUrl;
        private String gender;
        private String country;
        private String wxaddr;
        private String said;
        private String email;
        private String intro;
        private String job;
        private String followCount;
        private String collectCount;
        private String starSign;
        private List<ThirdNo> thirdNos;
        private ActivityDataBean activityData;
        private List<NoPermitBean> noPermit;
        private List<ShipToAddrBean> shipToAddr;

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

        public String getWxnum() {
            return wxnum;
        }

        public void setWxnum(String wxnum) {
            this.wxnum = wxnum;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getGender() {
            return gender;
        }

        public List<ThirdNo> getThirdNos() {
            return thirdNos;
        }

        public void setThirdNos(List<ThirdNo> thirdNos) {
            this.thirdNos = thirdNos;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getWxaddr() {
            return wxaddr;
        }

        public void setWxaddr(String wxaddr) {
            this.wxaddr = wxaddr;
        }

        public String getSaid() {
            return said;
        }

        public void setSaid(String said) {
            this.said = said;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getFollowCount() {
            return followCount;
        }

        public void setFollowCount(String followCount) {
            this.followCount = followCount;
        }

        public String getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(String collectCount) {
            this.collectCount = collectCount;
        }

        public String getStarSign() {
            return starSign;
        }

        public void setStarSign(String starSign) {
            this.starSign = starSign;
        }

        public ActivityDataBean getActivityData() {
            return activityData;
        }

        public void setActivityData(ActivityDataBean activityData) {
            this.activityData = activityData;
        }

        public List<NoPermitBean> getNoPermit() {
            return noPermit;
        }

        public void setNoPermit(List<NoPermitBean> noPermit) {
            this.noPermit = noPermit;
        }

        public List<ShipToAddrBean> getShipToAddr() {
            return shipToAddr;
        }

        public void setShipToAddr(List<ShipToAddrBean> shipToAddr) {
            this.shipToAddr = shipToAddr;
        }

        public  class ActivityDataBean {
            /**
             * followerCount : null
             * fanCount : null
             * praiseCount : null
             * msgCount : null
             * workCount : null
             * balance : null
             * totalIncome : null
             */

            private String followerCount;
            private String fanCount;
            private String praiseCount;
            private String msgCount;
            private String workCount;
            private String balance;
            private String totalIncome;

            public String getFollowerCount() {
                return followerCount;
            }

            public void setFollowerCount(String followerCount) {
                this.followerCount = followerCount;
            }

            public String getFanCount() {
                return fanCount;
            }

            public void setFanCount(String fanCount) {
                this.fanCount = fanCount;
            }

            public String getPraiseCount() {
                return praiseCount;
            }

            public void setPraiseCount(String praiseCount) {
                this.praiseCount = praiseCount;
            }

            public String getMsgCount() {
                return msgCount;
            }

            public void setMsgCount(String msgCount) {
                this.msgCount = msgCount;
            }

            public String getWorkCount() {
                return workCount;
            }

            public void setWorkCount(String workCount) {
                this.workCount = workCount;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getTotalIncome() {
                return totalIncome;
            }

            public void setTotalIncome(String totalIncome) {
                this.totalIncome = totalIncome;
            }
        }

        public  class NoPermitBean {
            /**
             * value : NO_VISIT
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public class ThirdNo{
            String typeCode;
            String thirdNo;

            public String getTypeCode() {
                return typeCode;
            }

            public void setTypeCode(String typeCode) {
                this.typeCode = typeCode;
            }

            public String getThirdNo() {
                return thirdNo;
            }

            public void setThirdNo(String thirdNo) {
                this.thirdNo = thirdNo;
            }
        }

        public  class ShipToAddrBean {
            /**
             * id : null
             * lat : 0
             * lng : 0
             * provice : 测试省
             * city : 测试city
             * district : tttt
             * detail : 测试detail
             * linkman : 测试linkman
             * phone : 15821387785
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
}
