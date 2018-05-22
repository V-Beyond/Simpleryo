package com.simpleryo.leyotang.bean;

import java.util.List;

/**
 * @ClassNname：StoreFollowBean.java
 * @Describe 关注门店实体类
 * @author huanglei
 * @time 2018/4/23 14:17
 */

public class StoreFollowBean extends BaseResult {


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
        private String idNumber;
        private String name;
        private String licenceUrl;
        private  String followCount;
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
        public String getFollowCount() {
            return followCount;
        }

        public void setFollowCount(String followCount) {
            this.followCount = followCount;
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
            private String lat;
            private String lng;
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

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
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
