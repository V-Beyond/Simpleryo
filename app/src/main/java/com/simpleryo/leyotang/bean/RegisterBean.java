package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

/**
 * @ClassNname：RegisterBean.java
 * @Describe 用户注册信息实体类
 * @author huanglei
 * @time 2018/4/11 9:45
 */
@HttpResponse(parser = JsonResponseParser.class)
public class RegisterBean extends BaseResult {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String mid;
        private String storeId;
        private String creatorId;
        private long creationTime;
        private String nickName;
        private String name;
        private String avatarUrl;
        private String gender;
        private String country;
        private String said;
        private String email;
        private String phone;
        private String intro;
        private String chatAuth;
        private String wxaddr;
        private String shipToAddr;
        private String loginName;
        private String password;
        private String role;
        private String no;
        private String msgAuthCode;
        private String authorities;
        private String wxnum;
        private String job;
        private String starSign;
        private String noPermit;

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

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getGender() {
            return gender;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getChatAuth() {
            return chatAuth;
        }

        public void setChatAuth(String chatAuth) {
            this.chatAuth = chatAuth;
        }

        public String getWxaddr() {
            return wxaddr;
        }

        public void setWxaddr(String wxaddr) {
            this.wxaddr = wxaddr;
        }

        public String getShipToAddr() {
            return shipToAddr;
        }

        public void setShipToAddr(String shipToAddr) {
            this.shipToAddr = shipToAddr;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getMsgAuthCode() {
            return msgAuthCode;
        }

        public void setMsgAuthCode(String msgAuthCode) {
            this.msgAuthCode = msgAuthCode;
        }

        public String getAuthorities() {
            return authorities;
        }

        public void setAuthorities(String authorities) {
            this.authorities = authorities;
        }

        public String getWxnum() {
            return wxnum;
        }

        public void setWxnum(String wxnum) {
            this.wxnum = wxnum;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getStarSign() {
            return starSign;
        }

        public void setStarSign(String starSign) {
            this.starSign = starSign;
        }

        public String getNoPermit() {
            return noPermit;
        }

        public void setNoPermit(String noPermit) {
            this.noPermit = noPermit;
        }
    }
}
