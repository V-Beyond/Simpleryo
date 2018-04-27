package com.simpleryo.leyotang.bean;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * @ClassNname：OrderListBean.java
 * @Describe 订单列表实体类
 * @author huanglei
 * @time 2018/4/13 10:31
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderListBean extends  BaseResult{


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
//        private String remark;
//        private StoreBean store;
//        private BuyerBean buyer;
//        private CoachBean coach;
//        private List<ItemsBean> items;

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

//        public String getRemark() {
//            return remark;
//        }
//
//        public void setRemark(String remark) {
//            this.remark = remark;
//        }
//
//        public StoreBean getStore() {
//            return store;
//        }
//
//        public void setStore(StoreBean store) {
//            this.store = store;
//        }
//
//        public BuyerBean getBuyer() {
//            return buyer;
//        }
//
//        public void setBuyer(BuyerBean buyer) {
//            this.buyer = buyer;
//        }
//
//        public CoachBean getCoach() {
//            return coach;
//        }
//
//        public void setCoach(CoachBean coach) {
//            this.coach = coach;
//        }
//
//        public List<ItemsBean> getItems() {
//            return items;
//        }
//
//        public void setItems(List<ItemsBean> items) {
//            this.items = items;
//        }
        public static class ItemsBean {
            /**
             * id : 402881e462c4e1700162c4e37eac0001
             * mid : null
             * storeId : null
             * creatorId : null
             * creationTime : null
             * checkNo : 111111
             * bookTime : 0
             * status : UNDO
             */

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private String creationTime;
            private String checkNo;
            private int bookTime;
            private String status;

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

            public String getCheckNo() {
                return checkNo;
            }

            public void setCheckNo(String checkNo) {
                this.checkNo = checkNo;
            }

            public int getBookTime() {
                return bookTime;
            }

            public void setBookTime(int bookTime) {
                this.bookTime = bookTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
        public static class StoreBean {

            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private String creationTime;
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
            private String id;
            private String mid;
            private String storeId;
            private String creatorId;
            private String creationTime;
            private int sequence;
            private String no;
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
}
