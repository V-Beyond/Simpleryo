package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * @ClassNname：StoreListBean.java
 * @Describe 门店列表
 * @author huanglei
 * @time 2018/4/26 11:04
 */
@HttpResponse(parser = JsonResponseParser.class)
public class StoreListBean extends BaseResult {


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

        private int coachCount;
        private boolean hasFollow;
        private int courseCount;
        private int followCount;
        private int totalOrderCount;
        private int orderDoneCounter;
        private int orderNewCounter;
        private String auditorName;
        private int percent;
        private AccountBean account;
        private StoreInfoBean storeInfo;

        public int getCoachCount() {
            return coachCount;
        }

        public void setCoachCount(int coachCount) {
            this.coachCount = coachCount;
        }

        public boolean isHasFollow() {
            return hasFollow;
        }

        public void setHasFollow(boolean hasFollow) {
            this.hasFollow = hasFollow;
        }

        public int getCourseCount() {
            return courseCount;
        }

        public void setCourseCount(int courseCount) {
            this.courseCount = courseCount;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getTotalOrderCount() {
            return totalOrderCount;
        }

        public void setTotalOrderCount(int totalOrderCount) {
            this.totalOrderCount = totalOrderCount;
        }

        public int getOrderDoneCounter() {
            return orderDoneCounter;
        }

        public void setOrderDoneCounter(int orderDoneCounter) {
            this.orderDoneCounter = orderDoneCounter;
        }

        public int getOrderNewCounter() {
            return orderNewCounter;
        }

        public void setOrderNewCounter(int orderNewCounter) {
            this.orderNewCounter = orderNewCounter;
        }

        public String getAuditorName() {
            return auditorName;
        }

        public void setAuditorName(String auditorName) {
            this.auditorName = auditorName;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public AccountBean getAccount() {
            return account;
        }

        public void setAccount(AccountBean account) {
            this.account = account;
        }

        public StoreInfoBean getStoreInfo() {
            return storeInfo;
        }

        public void setStoreInfo(StoreInfoBean storeInfo) {
            this.storeInfo = storeInfo;
        }

        public static class AccountBean {
            /**
             * id : 5ad49583511831431f7e2f1d
             * mid : 01
             * storeId : 5ad49583511831431f7e2f1c
             * creatorId : system
             * creationTime : 1523881347821
             * loginName : store
             * creator : null
             * password : null
             * role : STORE_ADMIN
             * name : null
             * no : null
             * phone : null
             * email : null
             * authorities : ["STORE_EDIT","STORE_PUBLISH_COURSE","STORE_PUBLISH_COACH","STORE_RECIEVE_ORDER"]
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private String creationTime;
            private String loginName;
            private String creator;
            private String password;
            private String role;
            private String name;
            private String no;
            private String phone;
            private String email;
            private List<String> authorities;

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

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public List<String> getAuthorities() {
                return authorities;
            }

            public void setAuthorities(List<String> authorities) {
                this.authorities = authorities;
            }
        }

        public static class StoreInfoBean {
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
             * hasRecommend : true
             * address : {"id":null,"lat":null,"lng":null,"provice":null,"city":null,"district":null,"detail":"上海市，闵行区，浦江镇","linkman":null,"phone":null,"isDefault":null}
             * authorities : null
             * notifiers : null
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private long creationTime;
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
            private boolean hasRecommend;
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

            public long getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(long creationTime) {
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

            public boolean isHasRecommend() {
                return hasRecommend;
            }

            public void setHasRecommend(boolean hasRecommend) {
                this.hasRecommend = hasRecommend;
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
}
